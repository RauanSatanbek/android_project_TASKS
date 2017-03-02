package com.example.rauansatanbek.tasks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

public class ShowDateTimePicker extends DialogFragment implements AlertDialog.OnClickListener {
    View view;
    EditText etFromNewTask;
    ImageButton imageButton;
    final int DATE_PICKER = 1, TIME_PICKER = 2;
    int layout, picker;
    ShowDateTimePicker(int layout, int picker, EditText etFromNewTask, ImageButton imageButton) {
        this.layout = layout;
        this.picker = picker;
        this.etFromNewTask = etFromNewTask;
        this.imageButton = imageButton;
    }
    public interface AddNewTask {
        void add(String title, String text);
    }

    AddNewTask addNewTask;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(layout, null);
        builder.setView(view);
        builder.setPositiveButton("Ок", this);
        builder.setNegativeButton("Отмена", this);
        return builder.create();
    }
    String addZero(int number) {
        if(number < 10) {
            return "0" + number;
        }
        return "" + number;
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.d("MyLogs", "onClick which = " + which);
        switch (which) {
            case -1:
                switch (picker) {
                    case DATE_PICKER:
                        DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth() + 1;
                        int day = datePicker.getDayOfMonth();
                        etFromNewTask.setText(addZero(day) + "." + addZero(month) + "." + year);
                        break;
                    case TIME_PICKER:
                        TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);
                        int hour = timePicker.getCurrentHour();
                        int minute = timePicker.getCurrentMinute();
                        etFromNewTask.setText(addZero(hour) + ":" + addZero(minute));
                        break;
                }
                imageButton.setVisibility(View.VISIBLE);
//                Log.d("MyLogs", "Date: " + datePicker.getDayOfMonth() + "." + datePicker.getMonth() + "." + datePicker.getYear());
//                Log.d("MyLogs", "Time: " + timePicker.getCurrentHour() + "." + timePicker.getCurrentMinute());
//                Context context = getActivity();
//                Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
//                intent.putExtra("String", 10 + "");
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0);
//                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
//
//                Toast.makeText(context, "Alarm" , Toast.LENGTH_LONG).show();
//                int day = datePicker.getDayOfMonth();
//                int month = datePicker.getMonth();
//                int year = datePicker.getYear();
//                int hour = timePicker.getCurrentHour();
//                int minute = timePicker.getCurrentMinute();
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTimeInMillis(System.currentTimeMillis());
//                calendar.set(Calendar.HOUR_OF_DAY, hour);
//                calendar.set(Calendar.MINUTE, minute);
//                alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);
//
//                long yourmilliseconds = System.currentTimeMillis();
//                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//                Date resultdate = new Date(yourmilliseconds);
//                Date resultdate2 = new Date(yourmilliseconds + (5 * 1000));
//                Toast.makeText(context, "Alarm set in " + 5 + " seconds Date: " + sdf.format(resultdate) + " : " + sdf.format(resultdate2),Toast.LENGTH_LONG).show();
//                addNewTask.add(alert_title.getText().toString(), alert_text.getText().toString());
                break;
            case -2:
                break;
        }
    }
}