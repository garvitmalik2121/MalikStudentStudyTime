package com.malik21.malikstudentstudytime.viewmodel



import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.malik21.malikstudentstudytime.data.UserPreferences

class SettingsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val preferences = UserPreferences(context)
        return SettingsViewModel(preferences) as T
    }
}
