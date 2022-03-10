package com.example.attractortest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.attractortest.R
import com.example.attractortest.ui.bottom_nav.BottomNavFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.findFragmentByTag(BottomNavFragment.TAG) ?: run {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.main_container_fragments,
                    BottomNavFragment.newInstance(),
                    BottomNavFragment.TAG
                )
                .commit()
        }
    }
}