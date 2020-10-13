package com.example.roomdatabaseexample.repository.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomdatabaseexample.repository.database.Voc
import com.example.roomdatabaseexample.repository.database.VocDao
import com.example.roomdatabaseexample.repository.database.VocDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(application: Application)
{
    private val vocDao:VocDao

    init {
        val db = VocDataBase.createInstance(application)
        vocDao = db.vocDao
    }

    // Implement all functions
    suspend fun insert(voc: Voc)
    {
        withContext(Dispatchers.IO)
        {
            vocDao.insert(voc)
        }
    }

    suspend fun delete(voc:Voc)
    {
        withContext(Dispatchers.IO)
        {
            vocDao.delete(voc)
        }
    }

    suspend fun update(voc:Voc)
    {
        withContext(Dispatchers.IO)
        {
            vocDao.update(voc)
        }
    }

    suspend fun getVocById(vocId:Long):Voc?
    {
        var voc:Voc? = null
        withContext(Dispatchers.IO)
        {
            voc =  vocDao.getVocById(vocId)
        }
        return voc
    }

    suspend fun getAllVocs():List<Voc>?
    {
        var vocs:List<Voc>? = null
        withContext(Dispatchers.IO)
        {
            vocs =  vocDao.getVocList()
        }
        return vocs
    }

    fun getLiveDataVocs():LiveData<List<Voc>>
    {
        return vocDao.getLiveDataVocList()
    }
}