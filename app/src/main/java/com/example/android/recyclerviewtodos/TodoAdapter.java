package com.example.android.recyclerviewtodos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private ArrayList<String> mTodoList;
    private OnTodoCheckedChangeListener mListener;

    public interface OnTodoCheckedChangeListener {
        void onTodoCheckedChanged(String todoText, boolean isChecked);
    }

    public TodoAdapter(OnTodoCheckedChangeListener listener) {
        mTodoList = new ArrayList<>();
        mListener = listener;
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
//        notifyDataSetChanged();
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView mTodoTV;

        public TodoViewHolder(final View itemView) {
            super(itemView);
            mTodoTV = itemView.findViewById(R.id.tv_todo_text);

            CheckBox checkBox = itemView.findViewById(R.id.cb_todo);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String todoText = mTodoList.get(getAdapterPosition());
                    mListener.onTodoCheckedChanged(todoText, isChecked);
                }
            });
        }

        public void removeFromList() {
            int position = getAdapterPosition();
            mTodoList.remove(position);
            notifyItemRemoved(position);
        }

        void bind(String newTodoText) {
            mTodoTV.setText(newTodoText);
        }
    }
}
