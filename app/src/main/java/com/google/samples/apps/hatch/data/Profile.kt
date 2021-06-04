
package com.google.samples.apps.hatch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val avatarImage: String,
    val name: String,
    val lastMessageSent: String
) : ProfileListItem