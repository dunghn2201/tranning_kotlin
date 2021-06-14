package com.dunghn.tranningkotlin.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")

class UserPreferences(context: Context) {


    private val mDataStore: DataStore<Preferences> = context.dataStore

    val authToken: Flow<String?>
        get() = mDataStore.data.catch { exception ->
            if (this is IOException) {
                Log.d("LOGGING", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            preference[KEY_AUTH]
        }

    suspend fun saveAuthToken(authToken: String) {
        mDataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

    suspend fun clear() {
        mDataStore.edit { preference ->
            preference.clear()

        }
    }

    companion object {
        val KEY_AUTH = stringPreferencesKey("key_auth")
    }

}