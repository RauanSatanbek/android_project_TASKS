package com.example.rauansatanbek.tasks;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class NewTask extends AppCompatActivity {
    EditText taskDate, taskTime;
    ImageButton cleanTime, cleanDate;
    CheckBox checkBoxNotification;
    TextView notification, turnOff;
    final int DATE_PICKER = 1, TIME_PICKER = 2;
    Spinner spinner_repeat;
    LinearLayout repeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Новая задача");

        taskDate = (EditText) findViewById(R.id.taskDate);
        taskTime = (EditText) findViewById(R.id.taskTime);
        cleanTime = (ImageButton) findViewById(R.id.cleanTime);
        cleanDate = (ImageButton) findViewById(R.id.cleanDate);
//        notification = (TextView) findViewById(R.id.notification);
        turnOff = (TextView) findViewById(R.id.turnOff);
        checkBoxNotification = (CheckBox) findViewById(R.id.checkBoxNotification);
        repeat = (LinearLayout) findViewById(R.id.repeat);
        spinner_repeat = (Spinner) findViewById(R.id.spinner_repeat);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_repeat.setAdapter(adapter);
    }
//  onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_task, menu);
        return true;
    }

//  onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//  Пказать DatePicker or TimePicker если нажата на EditText or ImageButton и передать в ShowDateTimePicker
//  путь к layout у
    void showDateOrTimePicker(View v) {
        switch (v.getId()) {
            case R.id.taskDate:
            case R.id.showDatePicker:
                DialogFragment dateDialog = new ShowDateTimePicker(R.layout.date_picker, DATE_PICKER, taskDate, cleanDate);
                dateDialog.show(getSupportFragmentManager(), "date");
                break;
            case R.id.taskTime:
            case R.id.showTimePicker:
                DialogFragment timeDialog = new ShowDateTimePicker(R.layout.time_picker, TIME_PICKER, taskTime, cleanTime);
                timeDialog.show(getSupportFragmentManager(), "time");
                break;
        }
    }
//    clean the EditText when clicking to ImageButton
    void Clean(View v) {
        switch (v.getId()) {
            case R.id.cleanDate:
                taskDate.setText("");
                cleanDate.setVisibility(View.GONE);
                break;
            case R.id.cleanTime:
                taskTime.setText("");
                cleanTime.setVisibility(View.GONE);
                break;
        }
    }
    void checked(View v) {
        Log.d("MyLogs", "Checked");
        if(checkBoxNotification.isChecked()) {
            checkBoxNotification.setChecked(false);
            turnOff.setText("Выключено");
            repeat.setVisibility(View.GONE);
        } else {
            checkBoxNotification.setChecked(true);
            turnOff.setText("Включено");
            repeat.setVisibility(View.VISIBLE);
        }
    }
}
