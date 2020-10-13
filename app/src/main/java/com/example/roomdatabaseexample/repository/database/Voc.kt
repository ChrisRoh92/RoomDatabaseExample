package com.example.roomdatabaseexample.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Voc
    (@PrimaryKey(autoGenerate = true) var id:Long,
     var nativeWord:String,
     var foreignWord:String,
     var date:String,
     var status:Int)