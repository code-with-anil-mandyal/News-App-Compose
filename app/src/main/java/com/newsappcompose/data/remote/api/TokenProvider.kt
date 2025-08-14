package com.newsappcompose.data.remote.api

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenProvider @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        val TOKEN_KEY = stringPreferencesKey(AUTH_TOKEN)
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    suspend fun getToken(): String? {
        val prefs = dataStore.data.first()
        return prefs[TOKEN_KEY]
    }
}