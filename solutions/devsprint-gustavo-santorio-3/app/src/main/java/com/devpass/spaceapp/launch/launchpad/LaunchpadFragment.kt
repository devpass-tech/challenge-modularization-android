package com.devpass.spaceapp.launch.launchpad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devpass.spaceapp.databinding.FragmentLaunchpadBinding
class LaunchpadFragment : Fragment() {

    private var binding: FragmentLaunchpadBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLaunchpadBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.root
    }

    companion object{
        private const val ID = "id"

        fun newInstance(id : String) : LaunchpadFragment =
            LaunchpadFragment().apply {
                Bundle().apply {
                    putString(ID, id)
                }.also {
                    arguments = it
                }
            }
    }
}