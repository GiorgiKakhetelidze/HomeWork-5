package com.example.homework_5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_5.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()

    }

    private fun setData(){
        val data  = intent.getParcelableExtra<User>(MainActivity.USER_DATA)
        binding.apply {
            nameTxtView.text = data?.firstName
            lastNameTxtTxtView.text = data?.lastName
            ageTxtView.text = data?.age.toString()
        }
    }
}