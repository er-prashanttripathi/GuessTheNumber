package com.example.guessthenumber

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class UserDetails(val context: Context) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

    companion object {
        val USERNAME = stringPreferencesKey("USER_NAME")
        val GameLevel = intPreferencesKey("AGE")
        val GameStage = intPreferencesKey("AGE")

    }

     suspend fun storeUserData(name: String, glevel: Int, gstate: Int) {
        context.dataStore.edit {
            it[USERNAME] = name
            it[GameLevel] = glevel
            it[GameStage] = gstate
        }
    }

    fun getName() = context.dataStore.data.map {
        it[USERNAME] ?: "No Age Data Found"
    }

    fun getState() = context.dataStore.data.map {
        it[GameStage] ?: 1

    }

    fun getLevel() = context.dataStore.data.map {
        it[GameLevel] ?: 1
    }
}