package com.devpass.spaceapp.launch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.devpass.spaceapp.databinding.ActivityTabBinding
import com.devpass.spaceapp.launch.details.DetailsFragment
import com.devpass.spaceapp.launch.launchpad.LaunchpadFragment
import com.devpass.spaceapp.launch.rocket.RocketFragment
import kotlinx.coroutines.launch

class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabBinding
    private val viewModel : LaunchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()
    }

    private fun observeData(){
        lifecycleScope.launch {
            viewModel.state.collect{
                when(it){
                    State.Loading -> {
                        binding.viewFlipper.displayedChild = 0
                    }
                    is State.Failure -> {
                        binding.apply {
                            viewFlipper.displayedChild = 1
                            errorText.text = it.error
                        }
                    }
                    is State.Success -> {
                        binding.viewFlipper.displayedChild = 2
                        with(it){
                            binding.tvTittle.text = vo.title
                            binding.tvDate.text = vo.date
                            binding.tvStatus.text = vo.status
                            setupViewPager(vo.details, vo.rocket, vo.launchpad)
                        }
                    }
                }
            }
        }
    }

    private fun setupViewPager(details : String, rocketID : String , launchpadID : String){
        val fragments = listOf(
            DetailsFragment.newInstance(details),
            RocketFragment.newInstance(rocketID),
            LaunchpadFragment.newInstance(launchpadID)
        )
        val fragmentsPageTitle = listOf("Details", "Rocket", "Launchpad")

        val viewPagerAdapter = ViewPagerAdapter(
            fragments = fragments,
            fragmentManager = supportFragmentManager,
            tittles = fragmentsPageTitle
        )

        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }


    companion object{
        const val ID = "id"
    }

}