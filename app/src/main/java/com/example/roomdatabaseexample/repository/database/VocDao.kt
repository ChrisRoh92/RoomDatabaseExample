package com.example.roomdatabaseexample.repository.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VocDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(voc:Voc)

    @Delete
    suspend fun delete(voc:Voc)

    @Update
    suspend fun update(voc:Voc)

    @Query("SELECT * FROM Voc WHERE id = :vocId")
    suspend fun getVocById(vocId:Long):Voc

    @Query("SELECT * FROM Voc")
    suspend fun getVocList():List<Voc>

    @Query("SELECT * FROM Voc")
    fun getLiveDataVocList():LiveData<List<Voc>>
}