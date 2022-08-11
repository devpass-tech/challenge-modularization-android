package br.com.devpass.launchpad.presentation

import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.devpass.launchpad.R
import br.com.devpass.launchpad.databinding.ActivityLaunchpadDetailsBinding
import br.com.devpass.launchpad.utils.latitude
import br.com.devpass.launchpad.utils.longitude
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class LaunchpadDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityLaunchpadDetailsBinding
    private val viewModel by viewModels<LaunchDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaunchpadDetailsBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        setupToolbar()
        setupMaps()
        observeData()
    }

    private fun setupToolbar() {
        val toolbar = binding.includeToolbar.tvToolbarTitle
        toolbar.text = resources.getString(R.string.label_launchpad)
        toolbar.gravity = Gravity.CENTER
    }

    private fun setupMaps() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is State.Loading -> {
                        binding.viewFlipper.displayedChild = 0
                    }
                    is State.Success -> {
                        binding.apply {
                            viewFlipper.displayedChild = 2
                            tvLaunchpad.text = state.name
                            tvNameLaunchpad.text = state.fullName
                            tvLaunchSite.text = state.locale
                            tvLaunchAttempts.text = state.attempts
                            tvLaunchSuccesses.text = state.successes
                        }
                    }
                    is State.Failure -> {
                        binding.apply {
                            viewFlipper.displayedChild = 1
                            errorText.text = state.error
                        }
                    }
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val launchpad = LatLng(latitude, longitude)
        val zoomLevel = 15f

        mMap.addMarker(MarkerOptions().position(launchpad))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(launchpad, zoomLevel))
    }
}