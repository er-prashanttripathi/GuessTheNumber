package com.example.guessthenumber


import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map


class UserDetails(val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        val USERNAME = stringPreferencesKey("USER_NAME")
        val LEVEL = intPreferencesKey("LEVEL")
        val STAGE = intPreferencesKey("STAGE")
    }

    private val pref: SharedPreferences = context.getSharedPreferences(
        "USER_NAME",
        AppCompatActivity.MODE_PRIVATE
    )

    suspend fun storeUserLevel(glevel: Int) {
        try {
            context.dataStore.edit { preferences ->
                preferences[LEVEL] = glevel
            }
        } catch (e: Exception) {
            // Handle the exception
            Log.e("DataStoreLevel", "Error storing user level: ${e.message}", e)
        }
    }

    suspend fun storeUserStage(gstate: Int) {
        try {
            context.dataStore.edit { preferences ->
                preferences[STAGE] = gstate
            }
        } catch (e: Exception) {
            // Handle the exception
            Log.e("DataStoreStage", "Error storing user stage: ${e.message}", e)
        }
    }

    fun getName() = context.dataStore.data.map {
        it[USERNAME] ?: "No Age Data Found"
    }

    fun getState() = context.dataStore.data.map {
        it[STAGE] ?: 1

    }

    fun getLevel() = context.dataStore.data.map {
        it[LEVEL] ?: 1
    }


}


