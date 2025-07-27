package com.jpn.core.utils.managers

import android.util.Log
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

class TimberLogManager @Inject constructor() : LogManager {
    override fun d(message: String, json: Any?) {
        Timber.tag("jpLogs").d("Xjjjkkkkkkkkkjhh " +message+ " :\n "+convertJson(json))
        Log.d("jpLogs", "Hiiiiiiiiiiiiiii")
       // Log.d("jpLogs",message+ " :\n "+convertJson(json))
    }

   override fun d(message: String)  {
       Timber.tag("jpLogs").d(message)
       Log.d("jpLogs", "Log Log Log Log")
       Timber.tag("jpLogs").d("Jo Jo Jo jo")
   }

    override fun i(message: String) = Timber.i(message)

    override fun w(message: String) = Timber.w(message)

    override fun e(message: String, throwable: Throwable?) {
        Timber.e(throwable, message)
    }

    override fun json(json: String) {
        try {
            val formatted = if (json.trim().startsWith("{"))
                JSONObject(json).toString(4)
            else if (json.trim().startsWith("["))
                JSONArray(json).toString(4)
            else json
            Timber.d(formatted)
        } catch (e: Exception) {
            Timber.e(e, "Invalid JSON")
            Timber.d(json)
        }
    }

    override fun convertJson(json: Any?):String{
        var temp=""
        try {
            temp = Gson().toJson(json)
        }catch (e:Exception){
            temp= e.message+""
        }
        return temp
    }
}
