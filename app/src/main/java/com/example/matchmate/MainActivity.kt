package com.example.matchmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmate.adapter.CardAdapter
import com.example.matchmate.matchui.MatchFragment
import com.example.matchmate.utils.FragmentUtils

class MainActivity : AppCompatActivity() {

    private val fragmentUtils = FragmentUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            fragmentUtils.replaceFragment(
                supportFragmentManager,
                R.id.container,
                MatchFragment::class.java
            )
        }
    }
}
