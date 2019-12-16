package com.example.todolist;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.example.todolist.dataBase.DataManager;
import com.example.todolist.dataBase.Task.Task;

import java.time.LocalDateTime;
import java.util.List;

public class MyService extends Service {
    DataManager dataManager;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();

        dataManager = MainActivity.getDataManager();
        List<Task> listTasks = dataManager.getAllTasks();
        if (!listTasks.isEmpty()){
                    addNotification();

        }

    }




    private void addNotification() {
        int NOTIFICATION_ID = 234;
        String CHANNEL_ID = "my_channel_01";
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            manager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp) //set icon for notification
                        .setContentTitle("ToDoList") //set title of notification
                        .setContentText("Masz zaczęte listy!!")//this is notification message
                        .setAutoCancel(true) // makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification


        Intent notificationIntent = new Intent(this, NotificationView.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "Zobacz, które zadania nie zostały jeszcze wykonane!");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // Add as notification
        manager.notify(0, builder.build());
    }


}
