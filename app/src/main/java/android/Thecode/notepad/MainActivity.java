package android.Thecode.notepad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLiteHelper sqLiteHelper;


    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Button btnadd;

    List<Memo> memoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(MainActivity.this);
        memoList = sqLiteHelper.selectAll();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter(memoList);
        recyclerView.setAdapter(recyclerAdapter);

        btnadd = (Button) findViewById(R.id.addbutton);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0){
            String strMain = data.getStringExtra("memo");

            Memo memo = new Memo(strMain);
            recyclerAdapter.addItem(memo);
            recyclerAdapter.notifyDataSetChanged();

            sqLiteHelper.insertMemo(memo);
        }
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{

        private List<Memo> listdata;

        public RecyclerAdapter(List<Memo> listdata){
            this.listdata = listdata;
        }

        void addItem(Memo memo){
            listdata.add(memo);
        }

        void removeItem(int postion){
            listdata.remove(postion);
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            Memo memo = listdata.get(position);
            holder.maintext.setTag(memo.getID());
            holder.maintext.setText(memo.getMainText());
        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }

        class ItemViewHolder extends RecyclerView.ViewHolder{

            private TextView maintext;

            public ItemViewHolder(@NonNull View itemView){
                super(itemView);

                maintext = (TextView) itemView.findViewById(R.id.maintext);

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = getAdapterPosition();
                        int ID =(int) maintext.getTag();

                        if (position != RecyclerView.NO_POSITION){
                            sqLiteHelper.deleteMemo(ID);
                            removeItem(position);
                            notifyDataSetChanged();
                        }

                        return false;
                    }
                });
            }
        }
    }
}