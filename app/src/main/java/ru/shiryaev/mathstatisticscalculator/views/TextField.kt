package ru.shiryaev.mathstatisticscalculator.views

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.shiryaev.mathstatisticscalculator.R

class TextField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.TextFieldStyle
) : androidx.appcompat.widget.AppCompatEditText(context, attrs, defStyleAttr), TextWatcher {

    var onTextChanged: ((String) -> Unit)? = null

    init {
        this.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
        onTextChanged?.invoke(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//        TODO("Not yet implemented")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//        TODO("Not yet implemented")
    }
}