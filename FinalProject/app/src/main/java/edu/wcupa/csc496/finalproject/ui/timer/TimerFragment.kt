package edu.wcupa.csc496.finalproject.ui.timer

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.wcupa.csc496.finalproject.R
import edu.wcupa.csc496.finalproject.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

    companion object {
        fun newInstance() = TimerFragment()
    }

    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    private var seconds = 0
    private var running = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            running = !running
            if (running) {
                binding.startButton.text = "Stop"
                runTimer()
            } else {
                binding.startButton.text = "Start"
                showAlertDialog()
            }
        }
    }

    private fun runTimer() {
        val runnable = object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60

                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                binding.timerText.text = time

                if (running) {
                    seconds++
                }

                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Are you sure?")
            .setMessage("Do you want to submit this roll?")
            .setPositiveButton("Yes") { dialog, _ ->
                // Handle the user's confirmation here
                dialog.dismiss()
                // Send user to RoundEntryFragment screen
                findNavController().navigate(R.id.navigation_roundEntry)
            }
            .setNegativeButton("No") { dialog, _ ->
                // Handle the user's denial here
                dialog.dismiss()
            }
            .create()
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
        _binding = null
        // Reset the timer
        seconds = 0
        running = false
    }
}