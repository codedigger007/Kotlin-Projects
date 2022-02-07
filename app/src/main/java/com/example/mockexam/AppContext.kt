package com.example.mockexam

import android.app.Application

class AppContext : Application() {
       public val database: LagosDataBase by lazy { LagosDataBase.getDatabase(this) }
    }
