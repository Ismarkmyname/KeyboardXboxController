package com.keyboardxboxcontroller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keyboardxboxcontroller.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var mappingAdapter: KeyMappingAdapter
    private lateinit var keyMappingManager: KeyMappingManager
    private lateinit var mappingRecyclerView: RecyclerView
    private lateinit var resetBtn: Button
    private lateinit var editMappingsBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        keyMappingManager = KeyMappingManager(this)
        mappingRecyclerView = binding.mappingRecyclerView
        resetBtn = binding.resetBtn
        editMappingsBtn = binding.editMappingsBtn

        setupRecyclerView()

        resetBtn.setOnClickListener {
            keyMappingManager.resetToDefaults()
            mappingAdapter.updateMappings(keyMappingManager.getAllMappings())
            Toast.makeText(this, "Reset to default mappings", Toast.LENGTH_SHORT).show()
        }

        editMappingsBtn.setOnClickListener {
            startActivity(Intent(this, KeyMappingActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        mappingAdapter = KeyMappingAdapter(
            keyMappingManager.getAllMappings().toMutableMap()
        ) { button, keyCode ->
            keyMappingManager.setMapping(button, keyCode)
        }
        mappingRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SettingsActivity)
            adapter = mappingAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        mappingAdapter.updateMappings(keyMappingManager.getAllMappings())
    }
}
