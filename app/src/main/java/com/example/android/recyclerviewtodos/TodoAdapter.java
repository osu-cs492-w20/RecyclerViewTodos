package com.example.android.recyclerviewtodos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private ArrayList<String> mTodoList;

    public TodoAdapter() {
        mTodoList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.todo_list_item, parent, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        String todo = mTodoList.get(position);
        holder.bind(todo);
    }

    public void addTodo(String todo) {
        mTodoList.add(0, todo);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView mTodoTV;

        public TodoViewHolder(View itemView) {
            super(itemView);
            mTodoTV = itemView.findViewById(R.id.tv_todo_text);
        }

        void bind(String newTodoText) {
            mTodoTV.setText(newTodoText);
        }
    }
}
