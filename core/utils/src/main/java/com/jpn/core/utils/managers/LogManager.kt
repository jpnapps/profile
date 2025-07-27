package com.jpn.core.utils.managers

interface LogManager {
    fun d(message: String, json:Any?)
    fun d(message: String)
    fun i(message: String)
    fun w(message: String)
    fun e(message: String, throwable: Throwable? = null)
    fun json(json: String)
    fun convertJson(json: Any?):String
}