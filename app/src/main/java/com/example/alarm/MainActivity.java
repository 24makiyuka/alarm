package com.example.alarm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.alarm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    Notification notification = null;
    View view;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setContentView(R.layout.activity_main);

        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.gray);


        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        String chID = getString(R.string.app_name);

        //안드러이드 버전별로 경우가 다르다고 해서 구분
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {     //APIが「26」이상


            NotificationChannel notificationChannel = new NotificationChannel(chID, chID, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription(chID);

            notificationManager.createNotificationChannel(notificationChannel);

            notification = new Notification.Builder(this, chID)
                    .setContentTitle(getString(R.string.app_name))  //title
                    .setContentText("money note")        //내용
                    .setSmallIcon(R.drawable.icon)                  //아이콘
                    .build();
        } else {
            //APIが「25」이하

            notification = new Notification.Builder(this)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText("money note")
                    .setSmallIcon(R.drawable.icon)
                    .build();
        }

        notificationManager.notify(1, notification);
    }

    public void goRed(View v) {
        view.setBackgroundResource(R.color.red);
    }
    public void goGreen(View v) {
        view.setBackgroundResource(R.color.green);
    }
    public void goGray(View v) {
        view.setBackgroundResource(R.color.gray);
    }
}