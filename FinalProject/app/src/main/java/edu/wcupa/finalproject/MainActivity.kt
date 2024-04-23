package edu.wcupa.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import edu.wcupa.finalproject.databinding.ActivityMainBinding
import edu.wcupa.finalproject.login.SignInActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        if (firebaseAuth.currentUser == null) {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        val currentUser = firebaseAuth.currentUser
        val welcomeMessage = "Welcome, ${currentUser?.email}"
        Toast.makeText(this, welcomeMessage, Toast.LENGTH_SHORT).show()

        binding.logOutButton.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

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