package com.google.samples.apps.hatch.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.samples.apps.hatch.data.AppDatabase
import com.google.samples.apps.hatch.data.Profile
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val profileList: List<Profile> = mutableListOf(Profile("id","invalid", "name", "lastMessageSent"))
            val database = AppDatabase.getInstance(applicationContext)
            database.profileDao().insertAll(profileList)
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}
