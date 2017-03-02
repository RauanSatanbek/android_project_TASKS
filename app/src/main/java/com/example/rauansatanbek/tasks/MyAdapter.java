package com.example.rauansatanbek.tasks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener{
    public interface MyAdapterRemoveItem {
        void removeItem(int index);
    }
    MyAdapterRemoveItem myAdapterRemoveItem;
    private List<Tasks> tasks;
    private List<ViewHolder> viewHolders;
    private int i = 0;
    MyAdapter(ArrayList<Tasks> tasks, Context context) {
        this.tasks = tasks;
        myAdapterRemoveItem = (MyAdapterRemoveItem) context;
        viewHolders = new ArrayList<>();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.card_title.setText(tasks.get(position).title);
        holder.card_text.setText(tasks.get(position).text);
//        holder.removeTask.setTag(i);
        i++;
//        holder.removeTask.setOnClickListener(this);
        viewHolders.add(0, holder);
    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public void onClick(View v) {
        int index = (tasks.size() - 1 - Integer.parseInt(v.getTag().toString()));
        for(int i = index - 1; i >= 0; i--) {
            int j = (int) viewHolders.get(i).removeTask.getTag();
            viewHolders.get(i).removeTask.setTag(j - 1);
        }
        viewHolders.remove(index);
        if(i != 0) i--;
        myAdapterRemoveItem.removeItem(index);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView card_title, card_text;
        private ImageButton removeTask;
        ViewHolder(View v) {
            super(v);
            card_title = (TextView) v.findViewById(R.id.card_title);
            card_text = (TextView) v.findViewById(R.id.card_text);
//            removeTask = (ImageButton) v.findViewById(R.id.removeTask);
        }
    }
}