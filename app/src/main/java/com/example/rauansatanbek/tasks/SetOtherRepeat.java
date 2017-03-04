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
import android.widget.NumberPicker;

/**
 * Created by Rauan Satanbek on 03.03.2017.
 */

public class SetOtherRepeat extends DialogFragment implements AlertDialog.OnClickListener{
    NumberPicker np_days, np_hours, np_minutes;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.set_other_repeat, null);
        builder.setView(view);
        builder.setPositiveButton("Set", this);
        builder.setNegativeButton("Cancel", this);
        //np date
            np_days = (NumberPicker) view.findViewById(R.id.np_days);
            np_days.setMinValue(0);
            np_days.setMaxValue(31);
        //np hours
            np_hours = (NumberPicker) view.findViewById(R.id.np_hours);
            np_hours.setMinValue(0);
            np_hours.setMaxValue(24);
        //np minutes
            np_minutes = (NumberPicker) view.findViewById(R.id.np_minutes);
            np_minutes.setMinValue(0);
            np_minutes.setMaxValue(60);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case -1:
                int days = np_days.getValue();
                int hours = np_hours.getValue();
                int minutes = np_minutes.getValue();
                Log.d("MyLogs", "Days = " + days + ", " + "Hours = " + hours + ", Minutes = " + minutes);
                break;
            case -2:
                break;
        }
    }
}
