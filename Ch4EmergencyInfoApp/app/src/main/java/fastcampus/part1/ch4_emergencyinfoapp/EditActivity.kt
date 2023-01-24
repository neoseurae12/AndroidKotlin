package fastcampus.part1.ch4_emergencyinfoapp

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import fastcampus.part1.ch4_emergencyinfoapp.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bloodSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.bloods,
            android.R.layout.simple_list_item_1
        )

        binding.birthdateLayer.setOnClickListener {
            val listener = OnDateSetListener { _, year, month, dayOfMonth ->
                binding.birthdateValueTextView.text = "$year-${month.inc()}-$dayOfMonth"
            }
            DatePickerDialog(
                this,
                listener,
                2000, 0, 1
            ).show()
        }

        binding.cautionCheckBox.setOnCheckedChangeListener { _, isChecked ->
            binding.cautionEditText.isVisible = isChecked
        }

        binding.cautionEditText.isVisible = binding.cautionCheckBox.isChecked

        binding.saveButton.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun saveData() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
            putString(NAME, binding.nameEditText.text.toString())
            putString(BIRTHDATE, binding.birthdateValueTextView.text.toString())
            putString(BLOOD, getBlood())
            putString(PHONENUMBER, binding.phonenumEditText.text.toString())
            putString(CAUTION, getCaution())
            apply()
        }

        Toast.makeText(this, "저장을 완료했습니다!", Toast.LENGTH_SHORT).show()

    }

    private fun getBlood(): String {
        val bloodAlphabet = binding.bloodSpinner.selectedItem.toString()
        val bloodSign = if(binding.bloodPlus.isChecked) "+" else "-"
        return "$bloodSign$bloodAlphabet"
    }

    private fun getCaution() :String {
        return if(binding.cautionCheckBox.isChecked) binding.cautionEditText.text.toString() else ""
    }
}