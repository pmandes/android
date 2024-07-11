package pl.madsoft.bootmanager.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pl.madsoft.bootmanager.MainActivity
import pl.madsoft.bootmanager.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        Log.d("NotificationWorker", "doWork")

        val message = createNotificationMessage()
        displayNotification("Boot manager", message)
        return Result.success()
    }

    private fun displayNotification(title: String, message: String) {

        Log.d("NotificationWorker", "Display notification...")

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "startup_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Startup Notifications", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        //val intent = Intent(applicationContext, MainActivity::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        //val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)


        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            //.setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }

    private fun createNotificationMessage(): String {

        val sharedPreferences = applicationContext.getSharedPreferences("BootPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val type = object : TypeToken<ArrayList<String>>() {}.type
        val bootLogs: ArrayList<String> = gson.fromJson(sharedPreferences.getString("boot_logs", "[]"), type)

        return when {
            bootLogs.isEmpty() -> "No boots detected"
            bootLogs.size == 1 -> {
                val date = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).parse(bootLogs.first())
                "The boot was detected = ${SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(date)}"
            }
            else -> {
                val lastDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(bootLogs.last())!!
                val preLastDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(bootLogs[bootLogs.size - 2])!!
                val timeDelta = lastDate.time - preLastDate.time
                val deltaSeconds = timeDelta / 1000 % 60
                val deltaMinutes = timeDelta / (1000 * 60) % 60
                val deltaHours = timeDelta / (1000 * 60 * 60) % 24
                "Last boots time delta = ${String.format("%02d:%02d:%02d", deltaHours, deltaMinutes, deltaSeconds)}"
            }
        }
    }
}
