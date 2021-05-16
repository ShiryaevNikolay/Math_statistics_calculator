package ru.shiryaev.mathstatisticscalculator.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import ru.shiryaev.mathstatisticscalculator.R

class CounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val mTitle: TextView
    private val mCounter: TextField
    private val mIncrease: ImageButton
    private val mDecrease: ImageButton

    var counter: Int = 0

    var onIncrease: (() -> Unit)? = null
    var onDecrease: (() -> Unit)? = null
    var onChangeNumber: (() -> String)? = null

    var minCounterValue: Int? = null
        set(value) {
            value?.let {
                if (counter < value) {
                    counter = value
                }
            }
            field = value
            updateCounter()
        }

    var maxCounterValue: Int? = null
        set(value) {
            value?.let {
                if (counter > value) {
                    counter = value
                }
            }
            field = value
            updateCounter()
        }

    init {
        inflate(context, R.layout.counter, this)
        var title: String?
        attrs.let {
            val a = context.theme.obtainStyledAttributes(
                attrs, R.styleable.CounterView, 0, 0
            )
            title = a.getString(R.styleable.CounterView_title)
            a.recycle()
        }

        mTitle = findViewById(R.id.title_tv)
        mCounter = findViewById(R.id.counter_et)
        mIncrease = findViewById(R.id.increase_btn)
        mDecrease = findViewById(R.id.decrease_btn)

        mTitle.text = title ?: ""

        updateCounter()

        mIncrease.setOnClickListener {
            val maxCounter = maxCounterValue
            if (maxCounter == null) {
                counter++
            } else {
                if (counter < maxCounter) {
                    counter++
                }
            }
            updateCounter()
            onIncrease?.invoke()
        }
        mDecrease.setOnClickListener {
            val minCounter = minCounterValue
            if (minCounter == null){
                counter--
            } else {
                if (counter > minCounter) {
                    counter--
                }
            }
            updateCounter()
            onDecrease?.invoke()
        }
        mCounter.onTextChanged = { text ->
            counter = text.toIntOrNull() ?: counter
            onChangeNumber?.invoke()
        }
    }

    fun changeCounter(number: Int){
        counter = number
        updateCounter()
    }

    private fun updateCounter() {
        mCounter.setText(counter.toString())
    }
}