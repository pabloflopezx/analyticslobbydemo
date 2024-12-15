package com.demos.analyticsLobby

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.demos.analyticsLobby.proves.FirebaseProvider
import org.json.JSONObject

class AnalyticsLobby private constructor(context: Context) {

    private val firebaseProvider = FirebaseProvider(FirebaseAnalytics.getInstance(context))

    companion object {
        @Volatile
        private var instance: AnalyticsLobby? = null

        fun getInstance(context: Context): AnalyticsLobby {
            return instance ?: synchronized(this) {
                instance ?: AnalyticsLobby(context).also { instance = it }
            }
        }
    }

    fun logEvent(eventName: String, params: Map<String, Any>) {
        firebaseProvider.logEvent(eventName, JSONObject(params))
    }
}
    