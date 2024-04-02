package edu.wcupa.csc496.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.wcupa.csc496.finalproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)


        // Add an OnItemSelectedListener to the BottomNavigationView
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Clear the back stack and navigate to the home fragment
                    navController.apply {
                        popBackStack(R.id.navigation_home, false)
                        navigate(R.id.navigation_home)
                    }
                }
                R.id.navigation_timer -> {
                    // Clear the back stack and navigate to the dashboard fragment
                    navController.apply {
                        popBackStack(R.id.navigation_timer, false)
                        navigate(R.id.navigation_timer)
                    }
                }
                R.id.navigation_notifications -> {
                    // Clear the back stack and navigate to the notifications fragment
                    navController.apply {
                        popBackStack(R.id.navigation_notifications, false)
                        navigate(R.id.navigation_notifications)
                    }
                }
            }
            true
        }
    }
}