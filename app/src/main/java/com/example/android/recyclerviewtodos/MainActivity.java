package com.example.android.recyclerviewtodos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TodoAdapter.OnTodoCheckedChangeListener {

//    private TextView mTodoListTV;
    private EditText mTodoEntryET;
    private RecyclerView mTodoListRV;
    private TodoAdapter mTodoAdapter;

    private Toast mToast;

//    private ArrayList<String> mTodoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToast = null;

//        mTodoList = new ArrayList<>();

//        mTodoListTV = findViewById(R.id.tv_todo_list);
        mTodoEntryET = findViewById(R.id.et_todo_entry);
        mTodoListRV = findViewById(R.id.rv_todo_list);

        mTodoListRV.setLayoutManager(new LinearLayoutManager(this));
        mTodoListRV.setHasFixedSize(true);

        mTodoAdapter = new TodoAdapter(this);
        mTodoListRV.setAdapter(mTodoAdapter);

        mTodoListRV.setItemAnimator(new DefaultItemAnimator());

        Button addTodoBtn = findViewById(R.id.btn_add_todo);
        addTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todoText = mTodoEntryET.getText().toString();
                if (!TextUtils.isEmpty(todoText)) {
//                    mTodoList.add(0, todoText);
//                    mTodoListTV.setText("");
//                    for (String todo : mTodoList) {
//                        mTodoListTV.append(todo + "\n\n");
//                    }
                    mTodoListRV.scrollToPosition(0);
                    mTodoAdapter.addTodo(todoText);
                    mTodoEntryET.setText("");
                }
            }
        });
    }

    @Override
    public void onTodoCheckedChanged(String todoText, boolean isChecked) {
        if (mToast != null) {
            mToast.cancel();
        }
        String completed = isChecked ? "COMPLETED" : "MARKED INCOMPLETE";
        String toastText = completed + ": " + todoText;
        mToast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        mToast.show();
    }
}
