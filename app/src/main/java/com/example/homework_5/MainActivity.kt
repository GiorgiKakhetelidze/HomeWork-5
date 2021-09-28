package com.example.homework_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.core.widget.doOnTextChanged
import com.example.homework_5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btnLogin.setOnClickListener {
            if (checkValidityOfInputs()) {
                binding.apply {
                    navigateToProfile(
                        User(
                            email = emailEditTxt.text.toString(),
                            userName = userNameEditTxt.text.toString(),
                            firstName = firstNameEditTxt.text.toString(),
                            lastName = lastNameEditTxt.text.toString(),
                            age = ageEditTxt.text.toString().toInt()
                        )
                    )
                }
            }
        }

        binding.emailEditTxt.doOnTextChanged { text, _, _, _ ->
            if (text?.length ?: 0 < 3 || !text.toString().checkEmail())
                binding.emailEditTxt.error = getString(R.string.email_not_valid)
        }

        binding.ageEditTxt.doOnTextChanged { text, _, _, _ ->
            if (text?.isDigitsOnly() == false)
                binding.ageEditTxt.error = getString(R.string.age_not_integer)
        }

        binding.userNameEditTxt.doOnTextChanged { text, _, _, _ ->
            if (text?.length ?: 0 < 10)
                binding.userNameEditTxt.error = getString(R.string.username_length)
        }
    }

    private fun navigateToProfile(data: User) {
        val intent = Intent(this, ProfileActivity::class.java).apply {
            putExtra(USER_DATA, data)
        }
        startActivity(intent)
    }

    private fun checkValidityOfInputs(): Boolean {
        when {
            binding.emailEditTxt.text.isNullOrEmpty() ||
                    binding.userNameEditTxt.text.isNullOrEmpty() ||
                    binding.firstNameEditTxt.text.isNullOrEmpty() ||
                    binding.lastNameEditTxt.text.isNullOrEmpty() ||
                    binding.ageEditTxt.text.isNullOrEmpty() -> {
                toast(
                    getString(R.string.fill_every_fields)
                )
                return false
            }
            !binding.emailEditTxt.text.toString()
                .checkEmail() -> {
                toast(getString(R.string.email_not_valid))
                return false
            }
            binding.userNameEditTxt.text?.length?.let { it < 10 } == true -> {
                toast(getString(R.string.username_length))
                return false
            }
            !binding.ageEditTxt.text.toString()
                .isDigitsOnly() -> {
                toast(getString(R.string.age_not_integer))
                return false
            }
            else -> {
                toast(getString(R.string.fill_fields))
                return true
            }
        }
    }

    private fun String.checkEmail() = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    companion object {
        const val USER_DATA = "USER"
    }
}