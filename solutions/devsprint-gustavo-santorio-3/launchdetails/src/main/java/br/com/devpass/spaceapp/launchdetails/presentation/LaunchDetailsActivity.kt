package br.com.devpass.spaceapp.launchdetails.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.devpass.spaceapp.launchdetails.databinding.ActivityLaunchDetailsBinding

class LaunchDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityLaunchDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchDetailsBinding
            .inflate(layoutInflater)
            .also {
                setContentView(binding.root)
            }

        binding.tvDescription.text = intent.extras?.getString(DESCRIPTION)
    }

    companion object {
        const val DESCRIPTION = "description"
    }

}