package com.example.todolist;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.example.todolist.dataBase.DataManager;
import com.example.todolist.dataBase.Task.Task;

import java.time.LocalDateTime;
import java.util.List;

public class MyService extends Service {
    DataManager dataManager;
    Task task;
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

        LocalDateTime dateTime = LocalDateTime.now();
        dataManager = MainActivity.getDataManager();
        List<Task> listTasks = dataManager.getAllTasks();
        if (!listTasks.isEmpty()){
            for (Task task : listTasks) {
                if(task.getTaskReminder().isEqual(dateTime))
                    addNotification();
            }
        }
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp) //set icon for notification
                        .setContentTitle("ToDoList") //set title of notification
                        .setContentText("Nie wszystkie zadania zostały wykonane!")//this is notification message
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
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


}
