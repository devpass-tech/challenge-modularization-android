package com.devpass.spaceapp.launch.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devpass.spaceapp.databinding.FragmentDetailsBinding

class DetailsFragment: Fragment() {

    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            tvTextCard.text = arguments?.getString(DESCRIPTION)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.root
    }

    companion object{
        private const val DESCRIPTION = "description"
        fun newInstance(description : String) : DetailsFragment =
            DetailsFragment().apply {
                Bundle().apply {
                    putString(DESCRIPTION, description)
                }.also {
                    arguments = it
                }
            }
    }
}