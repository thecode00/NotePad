package android.Thecode.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editText = (EditText) findViewById(R.id.editmemo);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memo = editText.getText().toString();
                Log.e("", "eawfaef");
                if (memo.length() > 0) {
                    Intent intent = new Intent();
                    intent.putExtra("memo", memo);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.memo_save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.menu_save:
                String memo = editText.getText().toString();
                Log.e("", Integer.toString(id));
                if (memo.length() > 0){
                    Intent intent = new Intent();
                    intent.putExtra("memo", memo);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}