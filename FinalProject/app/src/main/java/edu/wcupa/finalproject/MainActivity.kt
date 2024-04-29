package edu.wcupa.finalproject

import androidx.navigation.ui.setupWithNavController
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.wcupa.finalproject.databinding.ActivityMainBinding
import edu.wcupa.finalproject.utils.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        // Navigate immediately based on login status
        if (sessionManager.isLoggedIn()) {
            if (navController.currentDestination?.id != R.id.navigation_home) {
                navController.navigate(R.id.navigation_home)
            }
        } else {
            if (navController.currentDestination?.id != R.id.navigation_login) {
                navController.navigate(R.id.navigation_login)
            }
        }

        navView.setOnItemSelectedListener { item ->
            if (navController.currentDestination?.id != item.itemId) {
                navController.navigate(item.itemId)
            }
            true
        }
    }
}
