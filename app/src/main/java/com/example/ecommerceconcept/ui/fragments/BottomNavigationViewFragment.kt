package com.example.ecommerceconcept.ui.fragments

import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.domain.Result
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.adapters.productsInCartAdapterDelegates
import com.example.ecommerceconcept.dependencies_injection.annotations.FragmentScope
import com.example.ecommerceconcept.viewmodels.MyCartViewModel
import com.example.ecommerceconcept.viewmodels.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BottomNavigationViewFragment : BaseFragment(R.layout.fragment_bottom_navigation_view) {

    private lateinit var inflatedView: View

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MyCartViewModel

    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var addressesSpinner: Spinner
    private lateinit var filterButton: Button

    override fun findViews() {
        bottomNavigationView = inflatedView.findViewById(R.id.bnv_bottom_navigation_fragment)
        addressesSpinner = inflatedView.findViewById(R.id.spinner_address)
        filterButton = inflatedView.findViewById(R.id.btn_filter)
    }

    override fun informationInitialization() {

        lifecycleScope.launch {
            val result = viewModel.getBasket()
            if (result is Result.Success) {
                viewModel.currentCart = result.data

                withContext(Dispatchers.Main) {
                    if(viewModel.currentCart.basketItems.isEmpty()){
                        bottomNavigationView.getOrCreateBadge(R.id.fragment_cart).clearNumber()
                    } else {
                        bottomNavigationView.getOrCreateBadge(R.id.fragment_cart).number = viewModel.currentCart.basketItems.size
                    }
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

    override fun setClickListeners() {
        val navController =
            findNavController(requireActivity(), R.id.fragment_bottom_navigation_view)
        val navControllerMain = findNavController(requireActivity(), R.id.fragment_container_view)

        setupWithNavController(bottomNavigationView, navController)

        bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            if (item.itemId == R.id.fragment_cart) {
                navControllerMain.navigate(R.id.action_bottomNavigationViewFragment_to_myCartFragment)
            }
            onNavDestinationSelected(item, navController)
        }

        filterButton.setOnClickListener {

            val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.FilterDialogStyle)
            bottomSheetDialog.setContentView(R.layout.dialog_filter)

            val clickListener = {
                bottomSheetDialog.dismiss()
            }

            val closeButton: ImageButton = bottomSheetDialog.findViewById(R.id.btn_filter_close)!!
            val acceptButton: Button = bottomSheetDialog.findViewById(R.id.btn_filter_accept)!!

            closeButton.setOnClickListener { clickListener() }
            acceptButton.setOnClickListener { clickListener() }

            bottomSheetDialog.show()

        }
    }

    override fun otherViewInitialization() {
        bottomNavigationView.getOrCreateBadge(bottomNavigationView.menu[1].itemId).backgroundColor =
            ResourcesCompat.getColor(
                resources, R.color.background_color, null
            );
    }

    override fun injectFragment() {
        AndroidSupportInjection.inject(this)
    }

    override fun initialInitialization() {
        inflatedView = inflatedViewBaseFragment
        viewModel = ViewModelProvider(this, viewModelFactory)[MyCartViewModel::class.java]
    }

    companion object {

        fun newInstance() =
            BottomNavigationViewFragment()
    }
}