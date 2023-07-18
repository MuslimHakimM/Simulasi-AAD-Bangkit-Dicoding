package com.dicoding.habitapp.setting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.habitapp.R
import com.dicoding.habitapp.utils.DarkMode

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            //TODO 11 : Update theme based on value in ListPreference
            val switchDark : ListPreference? = findPreference(getString(R.string.pref_key_dark))
            switchDark?.setOnPreferenceChangeListener { _, newValue ->
                when(newValue){
                    getString(R.string.pref_dark_follow_system) -> if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) updateTheme(
                        DarkMode.FOLLOW_SYSTEM.value)
                    else updateTheme(DarkMode.OFF.value)
                    getString(R.string.pref_dark_on) -> updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
                    getString(R.string.pref_dark_off) -> updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
                    else -> updateTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
                true
            }

        }

        private fun updateTheme(mode: Int): Boolean {
            AppCompatDelegate.setDefaultNightMode(mode)
            requireActivity().recreate()
            return true
        }
    }
}