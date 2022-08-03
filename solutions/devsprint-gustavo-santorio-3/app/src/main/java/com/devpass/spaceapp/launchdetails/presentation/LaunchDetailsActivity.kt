package com.devpass.spaceapp.launchdetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devpass.spaceapp.R
import com.devpass.spaceapp.databinding.ActivityLaunchDetailsBinding

class LaunchDetailsActivity : AppCompatActivity() {
    lateinit var binding : ActivityLaunchDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchDetailsBinding
            .inflate(layoutInflater)
            .also {
                setContentView(binding.root)
            }

        binding.tvDescription.text = intent.extras?.getString(DESCRIPTION)
    }

    companion object{
        const val DESCRIPTION = "description"
    }

}