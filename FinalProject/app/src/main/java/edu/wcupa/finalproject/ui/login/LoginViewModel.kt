package edu.wcupa.finalproject.ui.login
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.wcupa.finalproject.database.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun login(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val isLoggedIn = userRepository.loginUser(username, password)
            onResult(isLoggedIn)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logoutUser()
        }
    }
}
