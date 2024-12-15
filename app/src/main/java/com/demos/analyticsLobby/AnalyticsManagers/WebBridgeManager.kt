package com.demos.analyticsLobby.AnalyticsManagers

import android.content.Context
import android.webkit.JavascriptInterface

class WebBridgeManager(private val context: Context) {

    @JavascriptInterface
    fun logEvent(eventName: String, parameters: String) {
        // Aquí iría la lógica para pasar datos a los providers
        println("Evento recibido desde WebView: $eventName con parámetros: $parameters")
    }
}
