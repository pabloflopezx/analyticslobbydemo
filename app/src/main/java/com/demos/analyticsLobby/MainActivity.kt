package com.demos.analyticsLobby

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.demos.analyticsLobby.AnalyticsManagers.SessionManager
import com.demos.analyticsLobby.AnalyticsManagers.UserManager
import com.demos.analyticsLobby.AnalyticsManagers.WebBridgeManager
import com.demos.analyticsLobby.proves.FirebaseProvider
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        // Inicializar FirebaseAnalytics
        val firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        // Crear managers
        val userManager = UserManager(this)
        val sessionManager = SessionManager(this)

        // Crear lista de providers (incluyendo Firebase)
        val analyticsProviders = listOf(
            FirebaseProvider(firebaseAnalytics) // Agregar FirebaseProvider
        )
        // Loguear un evento de prueba
        val testBundle = Bundle().apply {
            putString("test_param", "test_value")
        }
        firebaseAnalytics.logEvent("custom_test_event", testBundle)

        // Loguear Instance ID
        firebaseAnalytics.appInstanceId.addOnSuccessListener { instanceId ->
            Log.d("MainActivity", "Firebase Instance ID: $instanceId")
        }

        // Configurar un timeout corto para pruebas
        firebaseAnalytics.setSessionTimeoutDuration(1000)

        // Inyectar WebBridgeManager
        webView.addJavascriptInterface(
            WebBridgeManager(this, userManager, sessionManager, analyticsProviders),
            "AndroidBridge"
        )

        // Cargar la URL del WebView
        val baseUrl = "https://tutofiba.firebaseapp.com/"
        val utmParams = "?utm_source=app&utm_medium=webview&utm_campaign=test_campaign"
        webView.loadUrl(baseUrl + utmParams)
    }
}
