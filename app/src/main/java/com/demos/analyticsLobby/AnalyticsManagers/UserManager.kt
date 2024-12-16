package com.demos.analyticsLobby.AnalyticsManagers

import android.content.Context

class UserManager(private val context: Context) {
    private val prefs = context.getSharedPreferences("AnalyticsPrefs", Context.MODE_PRIVATE)

    fun validateUserId(userId: String?): String {
        if (userId == null || userId.isBlank()) {
            return generateNewUserId()
        }
        saveUserId(userId)
        return userId
    }

    fun generateNewUserId(): String {
        val newUserId = "user_${System.currentTimeMillis()}_${(1000..9999).random()}"
        saveUserId(newUserId)
        return newUserId
    }

    private fun saveUserId(userId: String) {
        prefs.edit().putString("user_id", userId).apply()
    }

    fun isNewUser(userId: String): Boolean {
        return prefs.getString("user_id", null) != userId
    }
}

