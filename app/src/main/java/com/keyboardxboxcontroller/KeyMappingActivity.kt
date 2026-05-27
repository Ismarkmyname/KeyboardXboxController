package com.keyboardxboxcontroller

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keyboardxboxcontroller.databinding.ActivityKeyMappingBinding

class KeyMappingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKeyMappingBinding
    private lateinit var keyMappingManager: KeyMappingManager
    private lateinit var mappingAdapter: KeyMappingAdapter
    private var currentlyMapping: ControllerButton? = null
    private lateinit var instructionText: TextView
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKeyMappingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        keyMappingManager = KeyMappingManager(this)
        instructionText = binding.instructionText
        cancelBtn = binding.cancelBtn
        saveBtn = binding.saveBtn

        setupRecyclerView()

        cancelBtn.setOnClickListener {
            finish()
        }

        saveBtn.setOnClickListener {
            Toast.makeText(this, "Mappings saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupRecyclerView() {
        mappingAdapter = KeyMappingAdapter(
            keyMappingManager.getAllMappings().toMutableMap(),
            onItemClick = { button ->
                currentlyMapping = button
                instructionText.text = "Press a key for ${button.name}..."
            }
        )
        binding.mappingRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@KeyMappingActivity)
            adapter = mappingAdapter
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (currentlyMapping != null && keyCode != KeyEvent.KEYCODE_BACK) {
            val button = currentlyMapping!!
            keyMappingManager.setMapping(button, keyCode)
            mappingAdapter.updateMapping(button, keyCode)
            instructionText.text = "Key mapped for $button"
            currentlyMapping = null
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
