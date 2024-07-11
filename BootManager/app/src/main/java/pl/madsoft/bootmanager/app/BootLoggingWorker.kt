package pl.madsoft.bootmanager.app

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BootLoggingWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        logBootEvent()
        return Result.success()
    }

    private fun logBootEvent() {

        // TODO: change implementation from Local Storage to Room database

        val sharedPreferences = applicationContext.getSharedPreferences("BootLogs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val gson = Gson()

        val type = object : TypeToken<ArrayList<String>>() {}.type
        val bootLogs: ArrayList<String> = gson.fromJson(sharedPreferences.getString("boot_logs", "[]"), type)

        bootLogs.add(dateTime)

        editor.putString("boot_logs", gson.toJson(bootLogs))
        editor.apply()
    }
}