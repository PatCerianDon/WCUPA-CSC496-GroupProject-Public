package edu.wcupa.csc496.finalproject.ui.calender

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.wcupa.csc496.finalproject.R
import android.widget.CalendarView

class CalenderFragment : Fragment() {

    companion object {
        fun newInstance() = CalenderFragment()
    }

    private val viewModel: CalenderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_calender, container, false)

        val calendarView = view.findViewById<CalendarView>(R.id.calendar)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Do something when the date is changed
        }

        return view
    }
}