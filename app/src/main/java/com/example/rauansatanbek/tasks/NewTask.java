package com.example.rauansatanbek.tasks;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText taskDate, taskTime, taskName, taskText;
    ImageButton cleanTime, cleanDate;
    CheckBox checkBoxNotification;
    TextView notification, turnOff;
    final int DATE_PICKER = 1, TIME_PICKER = 2;
    Spinner spinner_repeat;
    LinearLayout repeat, ll_time;
    ArrayList<Integer> listForTaskDate, listForTaskTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        setTitle("Новая задача");
        //toolbar
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        //set Home icon - ArrowBack
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Lists for Time || Date
            listForTaskDate = new ArrayList<>();
            listForTaskTime = new ArrayList<>();
        // we take a main info from
            taskDate = (EditText) findViewById(R.id.taskDate);
            taskTime = (EditText) findViewById(R.id.taskTime);
            taskName = (EditText) findViewById(R.id.taskName);
            taskText = (EditText) findViewById(R.id.taskText);
        // used to clean date || time
            cleanTime = (ImageButton) findViewById(R.id.cleanTime);
            cleanDate = (ImageButton) findViewById(R.id.cleanDate);
        //notification = (TextView) findViewById(R.id.notification);
            turnOff = (TextView) findViewById(R.id.turnOff);
            checkBoxNotification = (CheckBox) findViewById(R.id.checkBoxNotification);
            repeat = (LinearLayout) findViewById(R.id.repeat);
            ll_time = (LinearLayout) findViewById(R.id.ll_time);
        //Spinner - start
            spinner_repeat = (Spinner) findViewById(R.id.spinner_repeat);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.planets_array, R.layout.simple_spinner_item);
//        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.lists, list);
            // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner_repeat.setAdapter(adapter);
            spinner_repeat.setOnItemSelectedListener(this);
        //Spinner - end
    }
    //onCreateOptionsMenu
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_new_task, menu);
            return true;
        }
    //-end
    //onOptionsItemSelected
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    break;
                case R.id.addNewTask:
                    String nameOfTask = taskName.getText().toString();
                    String textOfTask = taskText.getText().toString();
                    if(nameOfTask.equals("") || textOfTask.equals("") || listForTaskDate.size() != 3 || listForTaskTime.size() != 2) {
                        Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "OK", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
            return super.onOptionsItemSelected(item);
        }
    //-end
    //  Пказать DatePicker or TimePicker если нажата на EditText or ImageButton и передать в ShowDateTimePicker
    //  путь к layout у
        void showDateOrTimePicker(View v) {
            switch (v.getId()) {
                case R.id.taskDate:
                case R.id.showDatePicker:
                    DialogFragment dateDialog = new ShowDateTimePicker(R.layout.date_picker, DATE_PICKER, taskDate, cleanDate, listForTaskDate, this);
                    dateDialog.show(getSupportFragmentManager(), "date");
                    break;
                case R.id.taskTime:
                case R.id.showTimePicker:
                    DialogFragment timeDialog = new ShowDateTimePicker(R.layout.time_picker, TIME_PICKER, taskTime, cleanTime, listForTaskTime, this);
                    timeDialog.show(getSupportFragmentManager(), "time");
                    break;
            }
        }
    //-end
    //clean the EditText when clicking to ImageButton
        void Clean(View v) {
            switch (v.getId()) {
                case R.id.cleanDate:
                    taskDate.setText("");
                    cleanDate.setVisibility(View.GONE);
                    ll_time.setVisibility(View.GONE);
                    listForTaskDate.removeAll(listForTaskDate);
                    break;
                case R.id.cleanTime:
                    taskTime.setText("");
                    cleanTime.setVisibility(View.GONE);
                    listForTaskTime.removeAll(listForTaskTime);
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
    //AdapterView.OnItemSelectedListener -start
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
            String mselection = spinner_repeat.getSelectedItem().toString();
            Log.d("MyLogs", "pos = " + position + ", " + "id = " + id + ", " + mselection);
            if(mselection.equals("Другое")) {
                Log.d("MyLogs", "Others");
                DialogFragment dialogFragment = new SetOtherRepeat();
                dialogFragment.show(getSupportFragmentManager(), "others");
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    //AdapterView.OnItemSelectedListener -end
    public class CustomListAdapter extends ArrayAdapter<String> {
        private Context context;
        private int resource;
        private List<String> items;
        public CustomListAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
            this.context = context;
            this.resource = resource;
            this.items = items;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View mView = convertView;
            if(mView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mView = layoutInflater.inflate(resource, null);
            }
            TextView textView = (TextView) mView.findViewById(R.id.radioButtonColor);
            textView.setText(items.get(position));
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(Color.RED);
            return super.getView(position, convertView, parent);
        }
    }
}
