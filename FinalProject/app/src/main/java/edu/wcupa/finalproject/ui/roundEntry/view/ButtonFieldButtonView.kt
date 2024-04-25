package edu.wcupa.finalproject.ui.roundEntry.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.EditText
import android.widget.TextView
import edu.wcupa.finalproject.R

class ButtonFieldButtonView : LinearLayout {
    private lateinit var numberEntryField: EditText
    private lateinit var textView: TextView
    private lateinit var incrementButton: View
    private lateinit var decrementButton: View

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.reusable_buttonfieldbutton, this, true)
        numberEntryField = findViewById(R.id.numberEntryField)
        textView = findViewById(R.id.textView)

        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)

        incrementButton.setOnClickListener {
            val number = numberEntryField.text.toString().toInt()
                numberEntryField.setText((number + 1).toString())
        }

        decrementButton.setOnClickListener {
            val number = numberEntryField.text.toString().toInt()
            if (number > 0) {
                numberEntryField.setText((number - 1).toString())
            }
        }
    }

    fun setNumber(number: Int) {
        numberEntryField.setText(number.toString())
    }

    fun setText(text: String) {
        textView.text = text
    }
}