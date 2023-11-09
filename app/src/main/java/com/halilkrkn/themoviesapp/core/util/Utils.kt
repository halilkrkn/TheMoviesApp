package com.halilkrkn.themoviesapp.core.util

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.halilkrkn.themoviesapp.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())

        fun showMessage(
            context: Context,
            message: String?,
        ) = makeText(context, message, LENGTH_LONG).show()
    }
}