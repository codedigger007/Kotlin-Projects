package com.example.mockexam

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LagosDao {
    @Query(value = "SELECT * FROM lagos_data")
    suspend fun getStoredData(): List<LagosSecondData>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storedData(lagos: LagosSecondData)


    @Query("DELETE FROM lagos_data")
    suspend fun deleteData()

    @Query("DELETE FROM lagos_data WHERE id = :Id")
    suspend fun deleteItem(Id: Int)
}