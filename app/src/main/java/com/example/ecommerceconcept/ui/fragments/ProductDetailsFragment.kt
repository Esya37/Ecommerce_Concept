package com.example.ecommerceconcept.ui.fragments

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.widget.TextViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Result
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.adapters.*
import com.example.ecommerceconcept.dependencies_injection.annotations.FragmentScope
import com.example.ecommerceconcept.utils.PhoneColor
import com.example.ecommerceconcept.utils.PhoneSd
import com.example.ecommerceconcept.viewmodels.ProductDetailsViewModel
import com.example.ecommerceconcept.viewmodels.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductDetailsFragment : BaseFragment(R.layout.fragment_product_details) {

    private lateinit var inflatedView: View

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ProductDetailsViewModel

    private lateinit var cartButton: AppCompatImageButton
    private lateinit var backButton: AppCompatImageButton
    private lateinit var isFavoriteButton: AppCompatImageButton
    private lateinit var acceptButton: View

    private lateinit var phoneImagesRecyclerView: CarouselRecyclerview
    private lateinit var phoneColorsRecyclerView: RecyclerView
    private lateinit var phoneSdsRecyclerView: RecyclerView

    private lateinit var phoneNameTextView: TextView
    private lateinit var phoneCpuTextView: TextView
    private lateinit var phoneCameraTextView: TextView
    private lateinit var phoneRamTextView: TextView
    private lateinit var phoneSdTextView: TextView

    private lateinit var phoneRatingRatingBar: RatingBar
    private lateinit var productDetailsTabLayout: TabLayout

    private lateinit var phoneImagesAdapter: ListDelegationAdapter<List<String>>
    private lateinit var phoneColorsAdapter: ListDelegationAdapter<List<PhoneColor>>
    private lateinit var phoneSdsAdapter: ListDelegationAdapter<List<PhoneSd>>

    private fun phoneColorsRecyclerViewInitialization() {
        phoneColorsAdapter = ListDelegationAdapter(
            phoneColorsAdapterDelegates {
                if (phoneColorsAdapter.items != null) {
                    phoneColorsAdapter.items!![viewModel.selectedColorIndex].isSelected = false
                    phoneColorsAdapter.notifyItemChanged(viewModel.selectedColorIndex)
                    viewModel.selectedColorIndex = phoneColorsAdapter.items!!.indexOf(it)
                    phoneColorsAdapter.items!![viewModel.selectedColorIndex].isSelected = true
                    phoneColorsAdapter.notifyItemChanged(viewModel.selectedColorIndex)
                }
            }
        )
        phoneColorsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


    }

    private fun phoneImagesRecyclerViewInitialization() {

        phoneImagesAdapter = ListDelegationAdapter(
            phoneImagesAdapterDelegates()
        )

    }

    private fun tabLayoutInitialization() {
        val tab: TabLayout.Tab = productDetailsTabLayout.getTabAt(0)!!
        val views = arrayListOf<View>()
        tab.view.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
        views.forEach { view ->
            if (view is TextView) {
                TextViewCompat.setTextAppearance(view, R.style.TabLayoutTextSelected)
            }
        }
        productDetailsTabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab != null) {
                        val views = arrayListOf<View>()
                        tab.view.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
                        views.forEach { view ->
                            if (view is TextView) {
                                TextViewCompat.setTextAppearance(
                                    view,
                                    R.style.TabLayoutTextSelected
                                )
                            }
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    if (tab != null) {
                        val views = arrayListOf<View>()
                        tab.view.findViewsWithText(views, tab.text, View.FIND_VIEWS_WITH_TEXT)
                        views.forEach { view ->
                            if (view is TextView) {
                                TextViewCompat.setTextAppearance(
                                    view,
                                    R.style.TabLayoutTextUnselected
                                )
                            }
                        }
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            }
        )
    }

    private fun setPhoneInfo() {
        phoneNameTextView.text = viewModel.currentPhone.title
        phoneCpuTextView.text = viewModel.currentPhone.cpu
        phoneCameraTextView.text = viewModel.currentPhone.camera
        phoneRamTextView.text = viewModel.currentPhone.ssd
        phoneSdTextView.text = viewModel.currentPhone.sd

        phoneRatingRatingBar.rating = viewModel.currentPhone.rating

        acceptButton.findViewById<TextView>(R.id.tv_add_to_cart_button_price).text =
            viewModel.decimalFormat.format(viewModel.currentPhone.price)

        updateFavoriteButton()
    }

    private fun updateFavoriteButton() {
        if (viewModel.currentPhone.isFavorites) {
            isFavoriteButton.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            isFavoriteButton.setImageResource(R.drawable.ic_favorite)
        }
    }

    private fun recyclerViewInitialization() {
        phoneColorsRecyclerViewInitialization()
        phoneSdsRecyclerViewInitialization()
        phoneImagesRecyclerViewInitialization()
    }

    private fun phoneSdsRecyclerViewInitialization() {
        phoneSdsAdapter = ListDelegationAdapter(
            phoneSdsSelectedAdapterDelegates(),
            phoneSdsUnselectedAdapterDelegates {
                if (phoneSdsAdapter.items != null) {
                    phoneSdsAdapter.items!![viewModel.selectedSdCapacityIndex].isSelected = false
                    phoneSdsAdapter.notifyItemChanged(viewModel.selectedSdCapacityIndex)
                    viewModel.selectedSdCapacityIndex = phoneSdsAdapter.items!!.indexOf(it)
                    phoneSdsAdapter.items!![viewModel.selectedSdCapacityIndex].isSelected = true
                    phoneSdsAdapter.notifyItemChanged(viewModel.selectedSdCapacityIndex)
                }
            }
        )
        phoneSdsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }

    override fun setClickListeners() {
        isFavoriteButton.setOnClickListener {
            viewModel.currentPhone.isFavorites = !viewModel.currentPhone.isFavorites
            updateFavoriteButton()
        }

        backButton.setOnClickListener {
            inflatedView.findNavController().navigateUp()
        }

        cartButton.setOnClickListener {
            inflatedView.findNavController()
                .navigate(R.id.action_productDetailsFragment_to_myCartFragment)
        }

        acceptButton.setOnClickListener {
            inflatedView.findNavController()
                .navigate(R.id.action_productDetailsFragment_to_myCartFragment)
        }
    }

    override fun injectFragment() {
        AndroidSupportInjection.inject(this)
    }

    override fun otherViewInitialization() {
        tabLayoutInitialization()
    }

    override fun informationInitialization() {

        lifecycleScope.launch(Dispatchers.IO) {
            val result = viewModel.getPhoneInfo()
            if (result is Result.Success) {
                viewModel.currentPhone = result.data

                phoneColorsAdapter.items =
                    viewModel.currentPhone.color.map { PhoneColor(it, false) }
                phoneColorsAdapter.items!![viewModel.selectedColorIndex].isSelected = true

                phoneSdsAdapter.items = viewModel.currentPhone.capacity.map { PhoneSd(it, false) }
                phoneSdsAdapter.items!![viewModel.selectedSdCapacityIndex].isSelected = true

                phoneImagesAdapter.items = result.data.imagesSourceUrls

                withContext(Dispatchers.Main) {
                    setPhoneInfo()
                    phoneColorsRecyclerView.adapter = phoneColorsAdapter
                    phoneSdsRecyclerView.adapter = phoneSdsAdapter
                    phoneImagesRecyclerView.adapter = phoneImagesAdapter

                    phoneImagesRecyclerView.apply {
                        setIntervalRatio(0.58f)
                        setInfinite(true)
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

        recyclerViewInitialization()
    }

    override fun findViews() {
        cartButton = inflatedView.findViewById(R.id.img_btn_product_details_cart)
        backButton = inflatedView.findViewById(R.id.img_btn_product_details_back)
        isFavoriteButton = inflatedView.findViewById(R.id.img_btn_product_details_favorite)
        acceptButton = inflatedView.findViewById(R.id.include_product_details_accept_button)

        phoneImagesRecyclerView = inflatedView.findViewById(R.id.rv_product_details_phone_images)
        phoneColorsRecyclerView = inflatedView.findViewById(R.id.rv_product_details_phone_colors)
        phoneSdsRecyclerView = inflatedView.findViewById(R.id.rv_product_details_phone_sds)

        phoneNameTextView = inflatedView.findViewById(R.id.tv_product_details_name)
        phoneCpuTextView = inflatedView.findViewById(R.id.tv_product_details_cpu)
        phoneCameraTextView = inflatedView.findViewById(R.id.tv_product_details_camera)
        phoneRamTextView = inflatedView.findViewById(R.id.tv_product_details_ram)
        phoneSdTextView = inflatedView.findViewById(R.id.tv_product_details_sd)

        phoneRatingRatingBar = inflatedView.findViewById(R.id.rating_bar_product_details_rating)
        productDetailsTabLayout = inflatedView.findViewById(R.id.tab_layout_product_details)
    }

    override fun initialInitialization() {
        inflatedView = inflatedViewBaseFragment
        viewModel =
            ViewModelProvider(this, viewModelFactory)[ProductDetailsViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProductDetailsFragment()
    }
}