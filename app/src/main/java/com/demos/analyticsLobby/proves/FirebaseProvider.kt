package com.demos.analyticsLobby.proves

import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import org.json.JSONObject

class FirebaseProvider(private val firebaseAnalytics: FirebaseAnalytics) : AnalyticsProvider {
    override val name = "Firebase"

    override fun logEvent(eventName: String, parameters: JSONObject) {
        // Definir un prefijo para los eventos personalizados
        val prefixedEventName = if (eventName.startsWith("custom_")) eventName else "custom_$eventName"

        Log.d("FirebaseProvider", "Logging event: $prefixedEventName, parameters: $parameters")

        val bundle = Bundle()
        parameters.keys().forEach { key ->
            bundle.putString(key, parameters.getString(key))
        }

        // Registrar el evento en Firebase
        firebaseAnalytics.logEvent(prefixedEventName, bundle)
        Log.d("FirebaseProvider", "Event logged to Firebase: $prefixedEventName")
    }
}
