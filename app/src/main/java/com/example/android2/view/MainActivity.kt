package com.example.android2.view

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android2.R
import com.example.android2.view.main.MainFragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            (context as? Activity)?.showSnackbar(getString(R.string.connection_warning_msg), Snackbar.LENGTH_LONG)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }
}

val Activity.rootView: View get() = window.decorView.rootView

fun Activity.showSnackbar(text: String, length: Int = Snackbar.LENGTH_INDEFINITE) {
    rootView.let {
        Snackbar.make(it, text, length).show()
    }
}