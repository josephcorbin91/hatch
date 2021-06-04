package com.google.samples.apps.hatch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Profile class.
 */
@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile ORDER BY name")
    fun getProfiles(): Flow<List<Profile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(profiles: List<Profile>)
}
