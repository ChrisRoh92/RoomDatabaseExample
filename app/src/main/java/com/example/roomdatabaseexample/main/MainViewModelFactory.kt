package com.example.roomdatabaseexample.main

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(application: Application):
    ViewModelProvider.AndroidViewModelFactory(application) {
}