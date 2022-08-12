package com.devpass.spaceapp.rocketdetails.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.devpass.spaceapp.rocketdetails.databinding.ActivityRocketDetailsBinding
import kotlinx.coroutines.launch

class RocketDetailsActivity : AppCompatActivity() {

    private val viewModel : RocketDetailsViewModel by viewModels()
    private lateinit var binding: ActivityRocketDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRocketDetailsBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        observeData()
    }

    private fun observeData(){
        lifecycleScope.launch {
            viewModel.state.collect{
                when(it){
                    is State.Failure -> {
                        binding.viewFlipper.displayedChild = 1
                    }
                    State.Loading -> {
                        binding.viewFlipper.displayedChild = 0
                    }
                    is State.Success -> {
                        binding.viewFlipper.displayedChild = 2
                        binding.textViewNameRocketDetails.text = it.name
                        binding.textViewDetailsRocketDetails.text = it.details
                    }
                }
            }
        }
    }
}