package com.example.fakestoreapp.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fakestoreapp.R
import com.example.fakestoreapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding : FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        val clickedProduct = DetailsFragmentArgs.fromBundle(requireArguments()).product

        binding.product = clickedProduct

        binding.tvToolbarTitle.text = getString(R.string.str_details_screen)

        binding.ivDismiss.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }


}