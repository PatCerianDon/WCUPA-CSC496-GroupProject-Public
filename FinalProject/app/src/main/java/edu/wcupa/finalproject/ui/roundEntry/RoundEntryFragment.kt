package edu.wcupa.finalproject.ui.roundEntry

import edu.wcupa.finalproject.ui.roundEntry.view.ButtonFieldButtonView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial
import edu.wcupa.finalproject.databinding.FragmentRoundEntryBinding
import edu.wcupa.finalproject.R

class RoundEntryFragment : Fragment() {

    private var _binding: FragmentRoundEntryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoundEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSeekBar(binding.seekBar1)
        setupSeekBar(binding.seekBar2)

        view.findViewById<ButtonFieldButtonView>(R.id.wrestling_container_field1)
        view.findViewById<ButtonFieldButtonView>(R.id.wrestling_container_field2)

        setupButtonField(view, R.id.wrestling_container_field1, 20, getString(R.string.takedown_successful))
        setupButtonField(view, R.id.wrestling_container_field2, 20, getString(R.string.takedown_failed))
    }

    private fun setupSeekBar(seekBar: SeekBar) {
        val inflater = LayoutInflater.from(context)
        val popupView = inflater.inflate(R.layout.popup_progress, null)
        val popupProgress = popupView.findViewById<TextView>(R.id.popupProgress)
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update and show the popup
                popupProgress.text = "$progress%"
                if (fromUser) {
                    val location = IntArray(2)
                    seekBar?.getLocationInWindow(location)
                    val x = location[0] + seekBar?.width!! * progress / seekBar.max
                    val y = location[1]
                    if (!popupWindow.isShowing) {
                        popupWindow.showAtLocation(seekBar, 0, x, y)
                    } else {
                        popupWindow.update(x, y, -1, -1)
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                popupWindow.isOutsideTouchable = false
                popupWindow.isFocusable = false
                popupWindow.update()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                popupWindow.dismiss()
            }
        })
        val switch: SwitchMaterial? = view?.findViewById(R.id.switch1)
        val wrestlingContainer: LinearLayout? = view?.findViewById(R.id.wrestling_container)

        switch?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The switch is in the "on" state, so make the layout visible
                wrestlingContainer?.visibility = View.VISIBLE
            } else {
                // The switch is in the "off" state, so hide the layout
                wrestlingContainer?.visibility = View.GONE
            }
        }
    }

    fun setupButtonField(view: View, id: Int, number: Int, text: String) {
        val buttonField = view.findViewById<ButtonFieldButtonView>(id)
        buttonField.setNumber(number)
        buttonField.setText(text)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}