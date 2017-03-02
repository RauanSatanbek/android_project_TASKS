package com.example.rauansatanbek.tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        //Указываем длительность вибрации в миллисекундах,
        //в нашем примере будет вибро-сигнал длительностью в 2 секунды
        vibrator.vibrate(3000);
        Log.d("MyLogs", "Hi");
        Toast.makeText(context, "Alarm.... Time is : " + intent.getStringExtra("String"), Toast.LENGTH_LONG).show();
    }
}