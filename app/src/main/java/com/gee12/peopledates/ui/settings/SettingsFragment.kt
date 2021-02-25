package com.gee12.peopledates.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.gee12.peopledates.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}