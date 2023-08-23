package com.example.fakestoreapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.fakestoreapp.R
import com.example.fakestoreapp.core.entities.ProductItem
import com.example.fakestoreapp.databinding.FragmentHomeBinding
import com.example.fakestoreapp.presentation.ui.adapters.ProductsAdapter
import com.example.fakestoreapp.presentation.viewmodels.ProductsViewModel
import com.example.fakestoreapp.utilities.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), ProductsAdapter.OnItemClicked {

    private lateinit var binding: FragmentHomeBinding
    private val productsViewModel by viewModels<ProductsViewModel>()
    private lateinit var navController: NavController
    private lateinit var adapter: ProductsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        navController = findNavController()
        initRecyclerView()
        initProductsObserver()
        binding.retry = productsViewModel.retry()
        return binding.root
    }

    private fun initRecyclerView() {
        adapter = ProductsAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    private fun initProductsObserver() {
        lifecycleScope.launch {
            productsViewModel.products.collect { resource ->
                binding.resource = resource
                when (resource.status) {
                    Status.LOADING -> {

                    }

                    Status.SUCCESS -> {
                        resource.response?.let {
                            adapter.setList(it)
                        }
                    }

                    Status.ERROR -> {
                        Toast.makeText(
                            context,
                            resource.error?.message ?: "General Error message",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }
            }
        }

    }

    companion object {
        private const val TAG = "HomeFragment"
    }

    override fun onItemClicked(productItem: ProductItem) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(productItem)
        navController.navigate(action)
    }

}