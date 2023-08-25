package com.tarantik.android_course_starter.database

import androidx.room.TypeConverter
import com.squareup.moshi.Types
import com.tarantik.android_course_starter.api.MoviesApi.moshi

class Converters {

    @TypeConverter
    fun listToJson(value: List<Int>): String {
        val type = Types.newParameterizedType(List::class.java, Integer::class.java)
        val adapter = moshi.adapter<List<Int>>(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun listFromJson(value: String): List<Int>? {
        val type = Types.newParameterizedType(List::class.java, Integer::class.java)
        val adapter = moshi.adapter<List<Int>>(type)
        return adapter.fromJson(value)
    }
}