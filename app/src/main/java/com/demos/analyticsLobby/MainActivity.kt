package com.demos.analyticsLobby

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.demos.analyticsLobby.AnalyticsManagers.WebBridgeManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        // Inyectar el bridge
        webView.addJavascriptInterface(WebBridgeManager(this), "AnalyticsBridge")

        // Cargar URL
        webView.loadUrl("https://tutofiba.firebaseapp.com/")
    }
}
