package com.malik21.malikstudentstudytime


import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.malik21.malikstudentstudytime.data.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserPreferencesInstrumentedTest {

    private lateinit var userPreferences: UserPreferences

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        userPreferences = UserPreferences(context)
    }

    @Test
    fun testSetAndGetNotificationsEnabled() = runBlocking {
        userPreferences.setNotificationsEnabled(true)
        val value = userPreferences.notificationsEnabled.first()
        Assert.assertTrue(value)

        userPreferences.setNotificationsEnabled(false)
        val updatedValue = userPreferences.notificationsEnabled.first()
        Assert.assertFalse(updatedValue)
    }


}
