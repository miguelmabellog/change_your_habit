package com.example.changeyourhabit.settings

import androidx.appcompat.app.AppCompatDelegate

enum class NightMode(val value: Int) {


    AUTO(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY),


    ON(AppCompatDelegate.MODE_NIGHT_YES),


    OFF(AppCompatDelegate.MODE_NIGHT_NO)

}