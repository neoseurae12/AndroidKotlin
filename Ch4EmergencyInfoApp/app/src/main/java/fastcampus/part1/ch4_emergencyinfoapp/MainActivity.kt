package fastcampus.part1.ch4_emergencyinfoapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import fastcampus.part1.ch4_emergencyinfoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataUiUpdate()

        binding.goInputActivityButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }

        binding.deleteButton.setOnClickListener {
            deleteData()
        }

        binding.phonenumLayer.setOnClickListener {
            with(Intent(Intent.ACTION_VIEW)) {
                val phoneNumber = binding.phonenumValueTextView.text.toString()
                    .replace("-", "")
                data = Uri.parse("tel:$phoneNumber")
                startActivity(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        getDataUiUpdate()
    }

    private fun getDataUiUpdate() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE)) {
            binding.nameValueTextView.text = getString(NAME, "UNKNOWN")
            binding.birthdateValueTextView.text = getString(BIRTHDATE, "0000-00-00")
            binding.bloodValueTextView.text = getString(BLOOD, "UNKNOWN")
            binding.phonenumValueTextView.text = getString(PHONENUMBER, "000-0000-0000")
            val caution = getString(CAUTION, "")

            binding.cautionTextView.isVisible = caution.isNullOrBlank().not()
            binding.cautionValueTextView.isVisible = caution.isNullOrBlank().not()

            if (!caution.isNullOrBlank()) {
                binding.cautionValueTextView.text = caution
            }
        }
    }

    private fun deleteData() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
            clear()
            apply()
            getDataUiUpdate()
        }



        Toast.makeText(this, "초기화를 완료했습니다!", Toast.LENGTH_SHORT).show()
    }
}