package com.keyboardxboxcontroller

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class AccessibilityService : AccessibilityService() {

    companion object {
        private const val TAG = "AccessibilityService"
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Log accessibility events for debugging
        event?.let {
            Log.d(TAG, "Accessibility event: ${it.eventType}")
        }
    }

    override fun onInterrupt() {
        Log.d(TAG, "Accessibility service interrupted")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d(TAG, "Accessibility service connected")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Accessibility service destroyed")
    }
}
