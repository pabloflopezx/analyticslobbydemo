package com.demos.analyticsLobby.proves

import org.json.JSONObject

interface AnalyticsProvider {
    val name: String
    fun logEvent(eventName: String, parameters: JSONObject)
}
