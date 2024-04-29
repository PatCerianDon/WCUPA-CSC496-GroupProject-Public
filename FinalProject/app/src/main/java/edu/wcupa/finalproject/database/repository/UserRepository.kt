package edu.wcupa.finalproject.database.repository

import edu.wcupa.finalproject.database.UserDao
import edu.wcupa.finalproject.utils.SessionManager

class UserRepository(private val userDao: UserDao, private val sessionManager: SessionManager) {
    suspend fun loginUser(username: String, password: String): Boolean {
        val isAuthenticated = checkCredentials(username, password)

        if (isAuthenticated) {
            sessionManager.setLogin(true)  // Update session login state
        }

        return isAuthenticated
    }

    fun logoutUser() {
        sessionManager.setLogin(false)  // Update session login state
    }

    private suspend fun checkCredentials(username: String, password: String): Boolean {
        val user = userDao.getUserByUsernameAndPassword(username, password)
        return user != null
    }
}
