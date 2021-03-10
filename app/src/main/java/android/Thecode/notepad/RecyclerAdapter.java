package android.Thecode.notepad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    private List<Memo> memoData = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Memo> data){
        memoData = data;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ItemViewHolder holder, int position) {
        Memo memo = memoData.get(position);

        holder.mainText.setText(memo.getMainText());
    }

    void addItem(Memo memo){
        memoData.add(memo);
    }

    void removeItem(int position){
        memoData.remove(position);
    }

    @Override
    public int getItemCount() {
        return memoData.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView mainText;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);

            mainText = (TextView) itemView.findViewById(R.id.maintext);
        }
    }

}
