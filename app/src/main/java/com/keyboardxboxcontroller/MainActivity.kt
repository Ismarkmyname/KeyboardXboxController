package com.keyboardxboxcontroller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.keyboardxboxcontroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var serviceSwitch: Switch
    private lateinit var statusText: TextView
    private lateinit var settingsBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        serviceSwitch = binding.serviceSwitch
        statusText = binding.statusText
        settingsBtn = binding.settingsBtn

        // Check if service is running
        updateServiceStatus()

        // Service toggle
        serviceSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startControllerService()
            } else {
                stopControllerService()
            }
        }

        // Settings button
        settingsBtn.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        updateServiceStatus()
    }

    private fun updateServiceStatus() {
        val isRunning = ControllerService.isRunning
        serviceSwitch.isChecked = isRunning
        statusText.text = if (isRunning) {
            "Service: ACTIVE"
        } else {
            "Service: INACTIVE"
        }
    }

    private fun startControllerService() {
        Intent(this, ControllerService::class.java).also { intent ->
            startService(intent)
        }
        updateServiceStatus()
    }

    private fun stopControllerService() {
        Intent(this, ControllerService::class.java).also { intent ->
            stopService(intent)
        }
        updateServiceStatus()
    }
}
