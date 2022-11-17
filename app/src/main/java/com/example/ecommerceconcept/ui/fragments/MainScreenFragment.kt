package com.example.ecommerceconcept.ui.fragments

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Result
import com.example.domain.entities.BestSeller
import com.example.domain.entities.HomeStore
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.adapters.bestSellerAdapterDelegates
import com.example.ecommerceconcept.adapters.hotSalesAdapterDelegates
import com.example.ecommerceconcept.adapters.selectedCategoryAdapterDelegate
import com.example.ecommerceconcept.adapters.unselectedCategoryAdapterDelegate
import com.example.ecommerceconcept.dependencies_injection.annotations.FragmentScope
import com.example.ecommerceconcept.utils.Category
import com.example.ecommerceconcept.utils.Constants
import com.example.ecommerceconcept.viewmodels.MainScreenFragmentViewModel
import com.example.ecommerceconcept.viewmodels.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainScreenFragment : BaseFragment(R.layout.fragment_main_screen) {

    private lateinit var inflatedView: View

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainScreenFragmentViewModel

    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var hotSalesRecyclerView: RecyclerView
    private lateinit var bestSellersRecyclerView: RecyclerView

    private lateinit var categoriesAdapter: ListDelegationAdapter<List<Category>>
    private lateinit var hotSalesAdapter: ListDelegationAdapter<List<HomeStore>>
    private lateinit var bestSellerAdapter: ListDelegationAdapter<List<BestSeller>>

    private fun recyclerViewInitialization() {
        categoriesRecyclerViewInitialization()
        hotSalesRecyclerViewInitialization()
        bestSellerRecyclerViewInitialization()
    }

    private fun updateRecyclerViewsLayoutParams() {
        categoriesRecyclerView.updateLayoutParams {
            height = (Resources.getSystem().displayMetrics.heightPixels * 0.12).toInt()
        }
        hotSalesRecyclerView.updateLayoutParams {
            height = (Resources.getSystem().displayMetrics.heightPixels * 0.25).toInt()
        }
        bestSellersRecyclerView.updateLayoutParams {
            height = (Resources.getSystem().displayMetrics.heightPixels * 0.55).toInt()
        }
    }

    private fun bestSellerRecyclerViewInitialization() {
        bestSellerAdapter = ListDelegationAdapter(
            bestSellerAdapterDelegates(
                resources = resources,
                onFavouriteClickListener = {
                    it.isFavorites = !it.isFavorites
                    bestSellerAdapter.notifyItemChanged(bestSellerAdapter.items!!.indexOf(it))
                },
                onPhoneClickListener = {
                    Navigation.findNavController(
                        requireActivity(),
                        R.id.fragment_container_view
                    ).navigate(R.id.action_bottomNavigationViewFragment_to_productDetailsFragment)
                }

            )
        )
        bestSellersRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        lifecycleScope.launch(Dispatchers.IO) {
            val result = viewModel.getBestSellers()
            if (result is Result.Success) {
                viewModel.bestSellers = result.data
                bestSellerAdapter.items = viewModel.bestSellers
                withContext(Dispatchers.Main) {
                    bestSellersRecyclerView.adapter = bestSellerAdapter
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        (result as Result.Error).exception.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun hotSalesRecyclerViewInitialization() {
        hotSalesAdapter = ListDelegationAdapter(
            hotSalesAdapterDelegates()
        )
        hotSalesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        PagerSnapHelper().attachToRecyclerView(hotSalesRecyclerView)

        lifecycleScope.launch(Dispatchers.IO) {
            val result = viewModel.getHomeStore()
            if (result is Result.Success) {
                viewModel.homeStore = result.data
                hotSalesAdapter.items = viewModel.homeStore
                withContext(Dispatchers.Main) {
                    hotSalesRecyclerView.adapter = hotSalesAdapter
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        (result as Result.Error).exception.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun categoriesRecyclerViewInitialization() {
        categoriesAdapter = ListDelegationAdapter(
            selectedCategoryAdapterDelegate(resources),
            unselectedCategoryAdapterDelegate(
                resources = resources,
                itemClickedListener = {
                    categoriesAdapter.items!![viewModel.selectedCategoryIndex].isSelected = false
                    categoriesAdapter.notifyItemChanged(viewModel.selectedCategoryIndex)
                    viewModel.selectedCategoryIndex = categoriesAdapter.items!!.indexOf(it)
                    categoriesAdapter.items!![viewModel.selectedCategoryIndex].isSelected = true
                    categoriesAdapter.notifyItemChanged(viewModel.selectedCategoryIndex)
                })
        )
        categoriesAdapter.items = Constants.getCategoriesList()
        categoriesAdapter.items!![viewModel.selectedCategoryIndex].isSelected = true
        categoriesAdapter.notifyItemChanged(viewModel.selectedCategoryIndex)

        categoriesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoriesRecyclerView.adapter = categoriesAdapter
    }

    override fun findViews() {
        categoriesRecyclerView = inflatedView.findViewById(R.id.rv_categories)
        hotSalesRecyclerView = inflatedView.findViewById(R.id.rv_hot_sales)
        bestSellersRecyclerView = inflatedView.findViewById(R.id.rv_best_seller)
    }

    override fun informationInitialization() {
        recyclerViewInitialization()
    }

    override fun setClickListeners() {

    }

    override fun otherViewInitialization() {
        updateRecyclerViewsLayoutParams()
    }

    override fun injectFragment() {
        AndroidSupportInjection.inject(this)
    }

    override fun initialInitialization() {
        inflatedView = inflatedViewBaseFragment
        viewModel = ViewModelProvider(this, viewModelFactory)[MainScreenFragmentViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainScreenFragment().apply {

            }
    }
}