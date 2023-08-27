package com.example.fakestoreapp.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.fakestoreapp.core.entities.ProductItem
import com.example.fakestoreapp.databinding.FragmentHomeBinding
import com.example.fakestoreapp.presentation.ui.adapters.ProductsAdapter
import com.example.fakestoreapp.presentation.viewmodels.ProductsViewModel
import com.example.fakestoreapp.utilities.Status
import com.example.fakestoreapp.utilities.showSnackBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
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

        binding.shimmerLayout.startShimmer()

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
                            adapter.submitList(it)
                        }
                    }

                    Status.ERROR -> {
                        showSnackBar(
                            message = resource.error?.message ?: "General Error message",
                            duration = Snackbar.LENGTH_LONG
                        )
                    }

                    Status.NETWORK ->{

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

    override fun onResume() {
        super.onResume()
        binding.shimmerLayout.startShimmer()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.shimmerLayout.stopShimmer()
    }
}