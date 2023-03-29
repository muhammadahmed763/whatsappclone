package com.example.tablayout.tools

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi


class CalenderUtils {
    @RequiresApi(Build.VERSION_CODES.N)
    private val c=Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.N)
    val day=c.get(Calendar.DAY_OF_MONTH)

}