package com.tarantik.android_course_starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.add
import androidx.fragment.app.commit
import cz.tarantik.android_course_starter.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MoviesListFragment>(R.id.container)
            }
        }
    }
}