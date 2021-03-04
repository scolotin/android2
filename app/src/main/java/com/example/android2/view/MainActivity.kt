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
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.android2.R
import com.example.android2.databinding.ActivityMainBinding
import com.example.android2.view.history.HistoryFragment
import com.example.android2.view.main.MainFragment
import com.example.android2.view.settings.SettingsFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            (context as? Activity)?.showSnackbar(getString(R.string.connection_warning_msg), Snackbar.LENGTH_LONG)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater.apply {
            inflate(R.layout.activity_main, null)
        })
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.nav_drawer_open, R.string.nav_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener {
            if (navigateFragment(it.itemId)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return@OnNavigationItemSelectedListener true
            }
            return@OnNavigationItemSelectedListener false
        })
    }

    private fun navigateFragment(id: Int): Boolean {
        return when (id) {
            R.id.action_settings -> {
                supportFragmentManager.replaceFragment(R.id.container, SettingsFragment.newInstance())
                true
            }
            R.id.action_history -> {
                supportFragmentManager.replaceFragment(R.id.container, HistoryFragment.newInstance())
                true
            }
            else -> false
        }
    }
}

val Activity.rootView: View get() = window.decorView.rootView

fun Activity.showSnackbar(text: String, length: Int = Snackbar.LENGTH_INDEFINITE) {
    rootView.let {
        Snackbar.make(it, text, length).show()
    }
}

fun FragmentManager.replaceFragment(resId: Int, fragment: Fragment) {
    beginTransaction()
        .replace(resId, fragment)
        .addToBackStack("")
        .commitAllowingStateLoss()
}