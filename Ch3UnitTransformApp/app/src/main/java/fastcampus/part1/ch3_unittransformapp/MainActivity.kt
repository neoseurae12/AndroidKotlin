package fastcampus.part1.ch3_unittransformapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import fastcampus.part1.ch3_unittransformapp.databinding.ActivityMainBinding
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var inputNumber: BigDecimal = BigDecimal.ZERO
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditText = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
        val swapImageButton = binding.swapImageButton

        inputEditText.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrBlank()) {
                BigDecimal.ZERO
            } else {
                text.toString().toBigDecimal()
            }

            if(cmToM) {
                outputTextView.text = inputNumber.divide(BigDecimal(100)).toString()
            } else {
                outputTextView.text = inputNumber.multiply(BigDecimal(100)).toString()
            }
        }

        swapImageButton.setOnClickListener {
            cmToM = cmToM.not()

            if(cmToM) {
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.divide(BigDecimal(100)).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.multiply(BigDecimal(100)).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putBoolean("cmToM", cmToM)

        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if(cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if(cmToM) "m" else "cm"

        super.onRestoreInstanceState(savedInstanceState)
    }
}