package edu.wcupa.finalproject.ui.login
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.wcupa.finalproject.R
import edu.wcupa.finalproject.database.AppDatabase
import edu.wcupa.finalproject.database.repository.UserRepository
import edu.wcupa.finalproject.utils.SessionManager

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userDao = AppDatabase.getDatabase(requireContext()).userDao()
        val sessionManager = SessionManager(requireContext())
        val userRepository = UserRepository(userDao, sessionManager)
        val factory = LoginViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<Button>(R.id.login_button).setOnClickListener {
            val username = view.findViewById<EditText>(R.id.username_input).text.toString()
            val password = view.findViewById<EditText>(R.id.password_input).text.toString()
            loginUser(username, password)
        }

        return view
    }

    private fun loginUser(username: String, password: String) {
        viewModel.login(username, password) { isLoggedIn ->
            if (isLoggedIn) {
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
