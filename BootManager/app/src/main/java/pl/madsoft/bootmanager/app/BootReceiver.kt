package pl.madsoft.bootmanager.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Log.d("BootReceiver", "onReceive invoked")

        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {

            Log.d("BootReceiver", "Action: ACTION_BOOT_COMPLETED")

            val workRequest = OneTimeWorkRequest.Builder(BootLoggingWorker::class.java).build()
            WorkManager.getInstance(context).enqueue(workRequest)
        }
    }
}