package com.keyboardxboxcontroller

import android.content.Context
import android.content.SharedPreferences
import android.view.KeyEvent

class KeyMappingManager(context: Context) {

    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences("key_mappings", Context.MODE_PRIVATE)

    companion object {
        const val PREF_PREFIX = "mapping_"
        const val PREF_ENABLED = "mapping_enabled"
    }

    // Default mappings for NBA 2K26 MyTeam
    private val defaultMappings = mapOf(
        ControllerButton.A to KeyEvent.KEYCODE_SPACE,           // Pass/Shoot
        ControllerButton.B to KeyEvent.KEYCODE_X,               // Shoot
        ControllerButton.X to KeyEvent.KEYCODE_Q,               // Pass
        ControllerButton.Y to KeyEvent.KEYCODE_E,               // Steal/Defend
        ControllerButton.LB to KeyEvent.KEYCODE_W,              // Block
        ControllerButton.RB to KeyEvent.KEYCODE_R,              // Sprint
        ControllerButton.LT to KeyEvent.KEYCODE_C,              // Left Trigger
        ControllerButton.RT to KeyEvent.KEYCODE_V,              // Right Trigger
        ControllerButton.DPAD_UP to KeyEvent.KEYCODE_DPAD_UP,
        ControllerButton.DPAD_DOWN to KeyEvent.KEYCODE_DPAD_DOWN,
        ControllerButton.DPAD_LEFT to KeyEvent.KEYCODE_DPAD_LEFT,
        ControllerButton.DPAD_RIGHT to KeyEvent.KEYCODE_DPAD_RIGHT,
        ControllerButton.LEFT_STICK to KeyEvent.KEYCODE_BUTTON_THUMBL,
        ControllerButton.RIGHT_STICK to KeyEvent.KEYCODE_BUTTON_THUMBR,
        ControllerButton.START to KeyEvent.KEYCODE_MENU,
        ControllerButton.BACK to KeyEvent.KEYCODE_BACK
    )

    init {
        // Initialize default mappings if not already set
        if (!sharedPreferences.getBoolean(PREF_ENABLED, false)) {
            saveDefaultMappings()
            sharedPreferences.edit().putBoolean(PREF_ENABLED, true).apply()
        }
    }

    private fun saveDefaultMappings() {
        val editor = sharedPreferences.edit()
        defaultMappings.forEach { (button, keyCode) ->
            editor.putInt(PREF_PREFIX + button.name, keyCode)
        }
        editor.apply()
    }

    fun getMapping(button: ControllerButton): Int {
        return sharedPreferences.getInt(
            PREF_PREFIX + button.name,
            defaultMappings[button] ?: KeyEvent.KEYCODE_UNKNOWN
        )
    }

    fun setMapping(button: ControllerButton, keyCode: Int) {
        sharedPreferences.edit().apply {
            putInt(PREF_PREFIX + button.name, keyCode)
            apply()
        }
    }

    fun getAllMappings(): Map<ControllerButton, Int> {
        return ControllerButton.values().associateWith { getMapping(it) }
    }

    fun resetToDefaults() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        saveDefaultMappings()
        sharedPreferences.edit().putBoolean(PREF_ENABLED, true).apply()
    }

    fun exportMappings(): String {
        val mappings = getAllMappings()
        return mappings.entries.joinToString("\n") { (button, keyCode) ->
            "$button:$keyCode"
        }
    }

    fun importMappings(data: String) {
        try {
            val editor = sharedPreferences.edit()
            data.lines().forEach { line ->
                val parts = line.split(":")
                if (parts.size == 2) {
                    val button = ControllerButton.valueOf(parts[0])
                    val keyCode = parts[1].toInt()
                    editor.putInt(PREF_PREFIX + button.name, keyCode)
                }
            }
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

enum class ControllerButton {
    A, B, X, Y,
    LB, RB, LT, RT,
    DPAD_UP, DPAD_DOWN, DPAD_LEFT, DPAD_RIGHT,
    LEFT_STICK, RIGHT_STICK,
    START, BACK
}

enum class ControllerTrigger {
    LEFT_TRIGGER, RIGHT_TRIGGER
}
