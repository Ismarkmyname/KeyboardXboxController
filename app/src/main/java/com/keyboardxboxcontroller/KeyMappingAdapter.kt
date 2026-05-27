package com.keyboardxboxcontroller

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keyboardxboxcontroller.databinding.ItemKeyMappingBinding

class KeyMappingAdapter(
    private val mappings: MutableMap<ControllerButton, Int>,
    private val onMappingChanged: ((ControllerButton, Int) -> Unit)? = null,
    private val onItemClick: ((ControllerButton) -> Unit)? = null
) : RecyclerView.Adapter<KeyMappingAdapter.ViewHolder>() {

    private val buttons = ControllerButton.values().toList()

    inner class ViewHolder(private val binding: ItemKeyMappingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(button: ControllerButton) {
            val keyCode = mappings[button] ?: KeyEvent.KEYCODE_UNKNOWN
            binding.buttonName.text = button.name
            binding.keyName.text = getKeyName(keyCode)

            binding.root.setOnClickListener {
                onItemClick?.invoke(button)
            }
        }

        private fun getKeyName(keyCode: Int): String {
            return try {
                KeyEvent.keyCodeToString(keyCode)
            } catch (e: Exception) {
                "Unknown (${keyCode})"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemKeyMappingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(buttons[position])
    }

    override fun getItemCount(): Int = buttons.size

    fun updateMappings(newMappings: Map<ControllerButton, Int>) {
        mappings.clear()
        mappings.putAll(newMappings)
        notifyDataSetChanged()
    }

    fun updateMapping(button: ControllerButton, keyCode: Int) {
        mappings[button] = keyCode
        val position = buttons.indexOf(button)
        if (position >= 0) {
            notifyItemChanged(position)
        }
        onMappingChanged?.invoke(button, keyCode)
    }
}
