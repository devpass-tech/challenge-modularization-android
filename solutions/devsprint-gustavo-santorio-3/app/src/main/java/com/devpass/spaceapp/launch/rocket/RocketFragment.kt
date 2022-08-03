package com.devpass.spaceapp.launch.rocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devpass.spaceapp.databinding.FragmentRocketBinding

class RocketFragment : Fragment() {

    private var binding: FragmentRocketBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRocketBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object{
        private const val ID = "id"

        fun newInstance(id : String) : RocketFragment =
            RocketFragment().apply {
                Bundle().apply {
                    putString(ID, id)
                }.also {
                    arguments = it
                }
            }
    }
}