package com.keyboardxboxcontroller

import android.content.Context
import android.util.Log
import android.view.KeyEvent

class KeyEventListener(
    private val context: Context,
    private val keyMappingManager: KeyMappingManager
) {

    companion object {
        private const val TAG = "KeyEventListener"
    }

    fun onKeyEvent(keyCode: Int, event: KeyEvent): Boolean {
        Log.d(TAG, "Key event received: $keyCode")

        // Check if this key is mapped to a controller button
        val mappedButton = findButtonForKeyCode(keyCode)
        
        if (mappedButton != null) {
            when (event.action) {
                KeyEvent.ACTION_DOWN -> {
                    Log.d(TAG, "Button pressed: ${mappedButton.name}")
                    // Handle button down
                }
                KeyEvent.ACTION_UP -> {
                    Log.d(TAG, "Button released: ${mappedButton.name}")
                    // Handle button up
                }
            }
            return true
        }

        return false
    }

    private fun findButtonForKeyCode(keyCode: Int): ControllerButton? {
        val allMappings = keyMappingManager.getAllMappings()
        return allMappings.entries.find { it.value == keyCode }?.key
    }

    fun handleAxisEvent(axis: Int, value: Float) {
        Log.d(TAG, "Axis event: axis=$axis, value=$value")
        // Handle analog stick and trigger movements
    }
}
