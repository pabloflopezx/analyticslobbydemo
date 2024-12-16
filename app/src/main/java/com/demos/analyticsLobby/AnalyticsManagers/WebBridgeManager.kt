package com.demos.analyticsLobby.AnalyticsManagers

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import com.demos.analyticsLobby.proves.AnalyticsProvider
import org.json.JSONObject

class WebBridgeManager(
    private val context: Context,
    private val userManager: UserManager,
    private val sessionManager: SessionManager,
    private val analyticsProviders: List<AnalyticsProvider>
) {

    @JavascriptInterface
    fun postMessage(message: String) {
        Log.d("WebBridgeManager", "postMessage called with: $message")
        try {
            val jsonObject = JSONObject(message)
            var eventName = jsonObject.optString("event")
            val parameters = jsonObject.optJSONObject("params") ?: JSONObject()

            // Agregar prefijo al nombre del evento
            eventName = "custom_$eventName"

            Log.d("WebBridgeManager", "Prefixed event: $eventName, params: $parameters")

            // Validar y procesar user_id y session_id
            val userId = userManager.validateUserId(parameters.optString("user_id"))
            val sessionId = sessionManager.validateSessionId(parameters.optString("session_id"))

            // Enriquecer los parámetros
            val enrichedParams = parameters.apply {
                put("user_id", userId)
                put("session_id", sessionId)
                put("is_new_user", userManager.isNewUser(userId))
                put("context", "webview")
            }

            // Delegar el evento a los providers
            analyticsProviders.forEach { provider ->
                provider.logEvent(eventName, enrichedParams)
            }
        } catch (e: Exception) {
            Log.e("WebBridgeManager", "Error processing message: ${e.message}", e)
        }
    }
}
