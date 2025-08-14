package com.newsappcompose.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = PreferenceKeys.PREFERENCE_FILE_NAME)


@Module
@InstallIn(SingletonComponent::class)
class AppPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val gson = Gson()


    // Boolean
    suspend fun setBooleanValue(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        context.dataStore.edit { it[prefKey] = value }
    }

    fun getBooleanFlow(key: String): Flow<Boolean> {
        val prefKey = booleanPreferencesKey(key)
        return context.dataStore.data.map { it[prefKey] ?: false }
    }

    suspend fun getBooleanValue(key: String): Boolean {
        val prefKey = booleanPreferencesKey(key)
        return context.dataStore.data.first()[prefKey] ?: false
    }

    // String
    suspend fun setStringValue(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        context.dataStore.edit { it[prefKey] = value }
    }

    fun getStringFlow(key: String): Flow<String> {
        val prefKey = stringPreferencesKey(key)
        return context.dataStore.data.map { it[prefKey] ?: "" }
    }

    suspend fun getStringValue(key: String): String {
        val prefKey = stringPreferencesKey(key)
        return context.dataStore.data.first()[prefKey] ?: ""
    }

    // Int
    suspend fun setIntValue(key: String, value: Int) {
        val prefKey = intPreferencesKey(key)
        context.dataStore.edit { it[prefKey] = value }
    }

    fun getIntFlow(key: String): Flow<Int> {
        val prefKey = intPreferencesKey(key)
        return context.dataStore.data.map { it[prefKey] ?: 0 }
    }

    suspend fun getIntValue(key: String): Int {
        val prefKey = intPreferencesKey(key)
        return context.dataStore.data.first()[prefKey] ?: 0
    }

    // Save any object as JSON
    suspend fun <T> setObject(key: String, value: T) {
        val json = gson.toJson(value)
        setStringValue(key, json)
    }

    suspend fun <T> getObject(key: String, clazz: Class<T>): T? {
        val json = getStringValue(key)
        return if (json.isEmpty()) null else gson.fromJson(json, clazz)
    }

    // Save List
    suspend fun <T> setList(key: String, list: List<T>) {
        val json = gson.toJson(list)
        setStringValue(key, json)
    }

    suspend inline fun <reified T> getList(key: String): List<T> {
        val json = getStringValue(key)
        return if (json.isEmpty()) emptyList()
        else gson.fromJson(json, object : com.google.gson.reflect.TypeToken<List<T>>() {}.type)
    }

    // Clear all data
    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}