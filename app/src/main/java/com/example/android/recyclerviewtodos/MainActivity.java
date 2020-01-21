package com.example.android.recyclerviewtodos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    private TextView mTodoListTV;
    private EditText mTodoEntryET;
    private RecyclerView mTodoListRV;
    private TodoAdapter mTodoAdapter;

//    private ArrayList<String> mTodoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTodoList = new ArrayList<>();

//        mTodoListTV = findViewById(R.id.tv_todo_list);
        mTodoEntryET = findViewById(R.id.et_todo_entry);
        mTodoListRV = findViewById(R.id.rv_todo_list);

        mTodoListRV.setLayoutManager(new LinearLayoutManager(this));
        mTodoListRV.setHasFixedSize(true);

        mTodoAdapter = new TodoAdapter();
        mTodoListRV.setAdapter(mTodoAdapter);

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
                    mTodoAdapter.addTodo(todoText);
                    mTodoEntryET.setText("");
                }
            }
        });
    }
}
