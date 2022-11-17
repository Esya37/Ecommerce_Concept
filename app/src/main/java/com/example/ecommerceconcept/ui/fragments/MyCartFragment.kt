package com.example.ecommerceconcept.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.viewmodels.MyCartViewModel
import com.example.ecommerceconcept.viewmodels.ProductDetailsViewModel
import com.example.ecommerceconcept.viewmodels.ViewModelFactory
import com.example.domain.Result
import com.example.domain.entities.BasketItem
import com.example.ecommerceconcept.adapters.productsInCartAdapterDelegates
import com.example.ecommerceconcept.dependencies_injection.annotations.FragmentScope
import com.example.ecommerceconcept.utils.PhoneColor
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text
import javax.inject.Inject

class MyCartFragment : BaseFragment(R.layout.fragment_my_cart) {

    private lateinit var inflatedView: View

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MyCartViewModel

    private lateinit var backButton: ImageButton

    private lateinit var totalCostTextView: TextView
    private lateinit var deliveryCostTextView: TextView

    private lateinit var cartRecyclerView: RecyclerView

    private lateinit var productsInCartAdapter: ListDelegationAdapter<List<BasketItem>>


    companion object {
        @JvmStatic
        fun newInstance() =
            MyCartFragment()
    }

    private fun setBasketInfo() {
        totalCostTextView.text =
            viewModel.decimalFormatUs.format(viewModel.currentCart.total)
        deliveryCostTextView.text = viewModel.currentCart.delivery
    }

    override fun findViews() {
        backButton = inflatedView.findViewById(R.id.img_btn_cart_back)

        totalCostTextView = inflatedView.findViewById(R.id.tv_cart_total_cost)
        deliveryCostTextView = inflatedView.findViewById(R.id.tv_cart_delivery_cost)

        cartRecyclerView = inflatedView.findViewById(R.id.rv_cart_products_in_cart)
    }

    override fun informationInitialization() {
        lifecycleScope.launch {
            val result = viewModel.getBasket()
            if (result is Result.Success) {

                viewModel.currentCart = result.data

                productsInCartAdapter = ListDelegationAdapter(
                    productsInCartAdapterDelegates(viewModel.decimalFormatDot, viewModel.currentCart.basketItems)
                )

                productsInCartAdapter.items =
                    viewModel.currentCart.basketItems
                
                withContext(Dispatchers.Main) {
                    setBasketInfo()
                    cartRecyclerView.adapter = productsInCartAdapter

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

        cartRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun setClickListeners() {
        backButton.setOnClickListener {
            inflatedView.findNavController().navigateUp()
        }
    }

    override fun otherViewInitialization() {

    }

    override fun injectFragment() {
        AndroidSupportInjection.inject(this)
    }

    override fun initialInitialization() {
        inflatedView = inflatedViewBaseFragment
        viewModel = ViewModelProvider(this, viewModelFactory)[MyCartViewModel::class.java]
    }
}