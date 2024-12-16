package com.demos.analyticsLobby.AnalyticsManagers

import android.content.Context

class SessionManager(private val context: Context) {
    private val prefs = context.getSharedPreferences("AnalyticsPrefs", Context.MODE_PRIVATE)

    fun validateSessionId(sessionId: String?): String {
        if (sessionId == null || sessionId.isBlank()) {
            return generateNewSessionId()
        }
        saveSessionId(sessionId)
        return sessionId
    }

    fun generateNewSessionId(): String {
        val newSessionId = "session_${System.currentTimeMillis()}_${(1000..9999).random()}"
        saveSessionId(newSessionId)
        return newSessionId
    }

    private fun saveSessionId(sessionId: String) {
        prefs.edit().putString("session_id", sessionId).apply()
    }

    fun getCurrentSessionId(): String? {
        return prefs.getString("session_id", null)
    }
}
