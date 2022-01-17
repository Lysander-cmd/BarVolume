package www.smktelkommalang.sch.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var editWidth: EditText
    private lateinit var editHeight: EditText
    private lateinit var editLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editWidth = findViewById(R.id.edit_width)
        editHeight = findViewById(R.id.edit_height)
        editLength = findViewById(R.id.edit_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)
        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }

    }
        override fun onClick(v: View) {
            if (v.getId() === R.id.btn_calculate) {
                val inputLength: String = editLength.getText().toString().trim()
                val inputWidth: String = editWidth.getText().toString().trim()
                val inputHeight: String = editHeight.getText().toString().trim()
                var isEmptyFields = false
                var isInvalidDouble = false
                if (TextUtils.isEmpty(inputLength)) {
                    isEmptyFields = true
                    editLength.setError("Field ini tidak boleh kosong")
                }
                if (TextUtils.isEmpty(inputWidth)) {
                    isEmptyFields = true
                    editWidth.setError("Field ini tidak boleh kosong")
                }
                if (TextUtils.isEmpty(inputHeight)) {
                    isEmptyFields = true
                    editHeight.setError("Field ini tidak boleh kosong")
                }
                val length = toDouble(inputLength)
                val width = toDouble(inputWidth)
                val height = toDouble(inputHeight)
                if (length == null) {
                    isInvalidDouble = true
                    editLength.setError("Field ini harus berupa nomer yang valid")
                }
                if (width == null) {
                    isInvalidDouble = true
                    editWidth.setError("Field ini harus berupa nomer yang valid")
                }
                if (height == null) {
                    isInvalidDouble = true
                    editHeight.setError("Field ini harus berupa nomer yang valid")
                }
                if (!isEmptyFields && !isInvalidDouble) {
                    val volume = length!! * width!! * height!!
            tvResult.setText(volume.toString())
        }
}
        }

        private fun toDouble(str: String): Double? {
            return try {
                java.lang.Double.valueOf(str)
            } catch (e: NumberFormatException) {
                null
            }
        }

    companion object {
        private const val STATE_RESULT = "state_result"
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }




}



