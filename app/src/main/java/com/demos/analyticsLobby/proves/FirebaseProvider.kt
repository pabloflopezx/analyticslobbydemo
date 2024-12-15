package com.demos.analyticsLobby.proves

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import org.json.JSONObject

class FirebaseProvider(private val firebaseAnalytics: FirebaseAnalytics) : AnalyticsProvider {
    override val name = "Firebase"

    override fun logEvent(eventName: String, parameters: JSONObject) {
        val bundle = Bundle()
        parameters.keys().forEach { key ->
            bundle.putString(key, parameters.getString(key))
        }
        firebaseAnalytics.logEvent(eventName, bundle)
    }
}
