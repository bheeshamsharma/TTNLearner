package com.psgpw.pickapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.psgpw.pickapp.data.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "ttnlearner"
)

class DataStoreManager(val context: Context) {

    private val USER_API_KEY = stringPreferencesKey("user_api_key")
    private val USER_ACCESS_TOKEN = stringPreferencesKey("access_token")

    private val USER_LOGIN_STATUS = booleanPreferencesKey("login_status")

    private val USER_NAME = stringPreferencesKey("user_name")
    private val USER_ID = stringPreferencesKey("user_id")
    private val USER_EMAIL = stringPreferencesKey("user_email")
    private val USER_PHONE = stringPreferencesKey("user_phone")
    private val USER_IMAGE = stringPreferencesKey("user_image")

    suspend fun saveUserToPreferencesStore(user: User) {
        context.dataStore.edit { preferences ->
            preferences[USER_ACCESS_TOKEN] = user.access_token ?: ""
//            preferences[USER_NAME] = user.name
            preferences[USER_ID] = user.id
            preferences[USER_EMAIL] = user.email
//            preferences[USER_PHONE] = user.phone ?: ""
//            preferences[USER_IMAGE] = user.image
            preferences[USER_LOGIN_STATUS] = true
        }
    }

    //
    fun getUserFromPreferencesStore(): Flow<User> = context.dataStore.data
        .map { preferences ->
            User(
                id = preferences[USER_ID] ?: "",
                access_token = preferences[USER_ACCESS_TOKEN] ?: "",
//                name = preferences[USER_NAME] ?: "",
                email = preferences[USER_EMAIL] ?: "",
//                image = preferences[USER_IMAGE] ?: "",
//                phone = preferences[USER_PHONE] ?: "",
            )
        }

    fun getUserApiKey(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[USER_API_KEY] ?: ""
        }

    fun getAccessToken(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[USER_ACCESS_TOKEN] ?: ""
        }

    fun getUserId(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[USER_ID] ?: ""
        }

    fun getUserImage(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[USER_IMAGE] ?: ""
        }


    suspend fun setUserImage(image: String) =
        context.dataStore.edit { preferences ->
            preferences[USER_IMAGE] = image
        }

    suspend fun setUserName(name: String) =
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = name
        }

    suspend fun setUserPhone(phone: String) =
        context.dataStore.edit { preferences ->
            preferences[USER_PHONE] = phone
        }

    fun isUserLogin(): Flow<Boolean> =
        context.dataStore.data.map {
            it[USER_LOGIN_STATUS] ?: false
        }

    suspend fun userLogout() {
        context.dataStore.edit { preferences ->
            preferences[USER_LOGIN_STATUS] = false
        }
    }
}