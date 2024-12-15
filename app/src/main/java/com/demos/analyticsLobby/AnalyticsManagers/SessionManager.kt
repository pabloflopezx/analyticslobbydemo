package com.demos.analyticsLobby.AnalyticsManagers

object SessionManager {
    private val activeProviders = mutableMapOf<String, Boolean>()

    fun activateProvider(providerName: String) {
        activeProviders[providerName] = true
    }

    fun deactivateProvider(providerName: String) {
        activeProviders[providerName] = false
    }

    fun isProviderActive(providerName: String): Boolean {
        return activeProviders[providerName] ?: false
    }
}
