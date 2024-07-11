package pl.madsoft.bootmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pl.madsoft.bootmanager.app.NotificationWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var infoTextView: TextView
    private lateinit var totalDismissalsEditText: EditText
    private lateinit var intervalBetweenDismissalsEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        schedulePeriodicWork()

        infoTextView = findViewById(R.id.infoTextView)
        totalDismissalsEditText = findViewById(R.id.totalDismissalsEditText)
        intervalBetweenDismissalsEditText = findViewById(R.id.intervalBetweenDismissalsEditText)

        val updateSettingsButton = findViewById<Button>(R.id.updateSettingsButton)

        updateSettingsButton.setOnClickListener {
            updateSettings()
        }

        displayBootEvents()
    }

    private fun schedulePeriodicWork() {
        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun displayBootEvents() {
        val sharedPreferences = getSharedPreferences("BootLogs", Context.MODE_PRIVATE)
        val gson = Gson()
        val type = object : TypeToken<ArrayList<String>>() {}.type
        val bootLogs: ArrayList<String> = gson.fromJson(sharedPreferences.getString("boot_logs", "[]"), type)

        if (bootLogs.isEmpty()) {
            infoTextView.text = getString(R.string.no_boots_detected)
        } else {
            val dateCountMap = bootLogs.groupingBy { it }.eachCount()
            val displayText = dateCountMap.entries.joinToString("\n") { "${it.key} - ${it.value}" }
            infoTextView.text = displayText
        }
    }

    private fun updateSettings() {
        val totalDismissals = totalDismissalsEditText.text.toString().toIntOrNull() ?: 0
        val intervalBetweenDismissals = intervalBetweenDismissalsEditText.text.toString().toIntOrNull() ?: 0


        // TODO: save settings
    }
}