package com.example.matchmate.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentUtils {

    fun replaceFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragmentClass: Class<out Fragment>,
        addToBackStack: Boolean = false,
        args: Bundle? = null
    ) {
        val transaction = fragmentManager.beginTransaction()
        val fragment = fragmentClass.newInstance()

        if (args != null) {
            fragment.arguments = args
        }

        transaction.replace(containerId, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }
}