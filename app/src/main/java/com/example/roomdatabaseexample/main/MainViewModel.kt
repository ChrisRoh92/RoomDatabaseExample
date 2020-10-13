package com.example.roomdatabaseexample.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabaseexample.repository.database.Voc
import com.example.roomdatabaseexample.repository.repository.AppRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(application: Application): AndroidViewModel(application)
{
    ////////////////////////////////////////////////////////////
    // Repository:
    private val repository = AppRepository(application)
    private var liveVocList = repository.getLiveDataVocs()

    ////////////////////////////////////////////////////////////
    // Methods to interact with the repository:
    fun insert(nativeWord:String,foreignWord:String)
    {
        viewModelScope.launch {
            val voc = Voc(0L,nativeWord,foreignWord,Date().toStringFormat(),0)
            repository.insert(voc)
        }
    }

    fun update(voc:Voc)
    {
        viewModelScope.launch {
            repository.update(voc)
        }
    }

    fun delete(voc:Voc)
    {
        viewModelScope.launch {
            repository.delete(voc)
        }
    }

    fun getVocById(vocId:Long):Voc?
    {
        var voc:Voc? = null
        viewModelScope.launch {
            voc = repository.getVocById(vocId)
        }

        return voc
    }

    fun getAllVocs():List<Voc>?
    {
        var vocs:List<Voc>? = null
        viewModelScope.launch {
            vocs =  repository.getAllVocs()
        }
        return vocs
    }

    ////////////////////////////////////////////////////////////
    // Getters for LiveData
    fun getLiveVocList():LiveData<List<Voc>> = liveVocList

    ////////////////////////////////////////////////////////////
    // Utils
    private fun Date.toStringFormat(pattern:String="dd.MM.yyyy"):String
    {
        return SimpleDateFormat(pattern).format(this)
    }
}