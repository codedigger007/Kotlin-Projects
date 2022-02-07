package com.example.mockexam

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "lagos_data")
data class LagosSecondData(@PrimaryKey val id: Int, val login: String, val avatar_url: String, val url: String)