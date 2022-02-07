package com.example.mockexam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LagosModel(private val lagosDao: LagosDao) : ViewModel() {

   suspend  fun getLagosData(): List<LagosSecondData> = lagosDao.getStoredData()
     suspend fun addLagosData(lagos: LagosSecondData) = lagosDao.storedData(lagos)
    suspend fun deleteData() = lagosDao.deleteData()
    suspend fun deleteItems(id: Int)= lagosDao.deleteItem(id)

}

class LagosModelFactory(
    private val lagosDao: LagosDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LagosModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LagosModel(lagosDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}