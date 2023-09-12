package com.example.matchmate

import android.app.Application
import com.example.matchmate.roomdatabase.ResultsDatabase

class MatchMateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ResultsDatabase.getDatabase(this)
    }

}