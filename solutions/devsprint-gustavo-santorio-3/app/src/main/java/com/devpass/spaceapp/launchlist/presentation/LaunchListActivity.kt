package com.devpass.spaceapp.launchlist.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devpass.spaceapp.databinding.ActivityLaunchListBinding
import com.devpass.spaceapp.launchlist.LaunchListAdapter
import kotlinx.coroutines.launch

class LaunchListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchListBinding
    private lateinit var adapter: LaunchListAdapter

    private val viewModel : LaunchListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycleView()
        observeData()
    }

    private fun setupRecycleView() {
        adapter = LaunchListAdapter()
        binding.rvLaunches.adapter = adapter
        binding.rvLaunches.layoutManager = LinearLayoutManager(this)
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
                        adapter.submitList(it.list)
                    }
                }
            }
        }
    }
}