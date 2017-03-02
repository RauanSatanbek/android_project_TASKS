package com.example.rauansatanbek.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Collections;

import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;

public class MainActivity extends AppCompatActivity implements ShowDateTimePicker.AddNewTask, MyAdapter.MyAdapterRemoveItem{

    MenuItem addIcon, alarmIcon, alarmOnIcon, editIcon;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;
    ArrayList<Tasks> data;
    DatePicker datePicker;
    Context context;
    @Override
    public void add(String title, String text) {
        addToData(title, text, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addToData("Title", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus temporibus dolorem sed enim nulla iusto.", 0);

                Intent intent = new Intent(context, NewTask.class);
                startActivity(intent);

//                DialogFragment addDesires = new ShowDateTimePicker(R.layout.date_picker);
//                addDesires.show(getSupportFragmentManager(), "add");
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 0)fab.hide();
                else fab.show();
            }
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        SlideInRightAnimator animator = new SlideInRightAnimator();
        animator.setInterpolator(new OvershootInterpolator());
        recyclerView.setItemAnimator(animator);

        recyclerView.getItemAnimator().setAddDuration(300);
        recyclerView.getItemAnimator().setRemoveDuration(300);
        recyclerView.getItemAnimator().setMoveDuration(500);
        recyclerView.getItemAnimator().setChangeDuration(500);

        data = new ArrayList<>();
        adapter = new MyAdapter(data, this);
        recyclerView.setAdapter(adapter);
    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.add:
                addToData("Title", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus temporibus dolorem sed enim nulla iusto.", 0);
                break;
            case R.id.remove:
                remove(0);
                break;
            case R.id.change:
                change();
                break;
        }
    }
    public void addToData(String title, String text, int position) {
        data.add(position, new Tasks(title, text));
        adapter.notifyItemInserted(position);
    }
    public void remove(int position) {
        data.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void change() {
        Collections.shuffle(data);
        adapter.notifyItemRangeChanged(0, data.size());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void removeItem(int index) {
        remove(index);
    }
}
