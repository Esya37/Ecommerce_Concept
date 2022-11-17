package com.example.ecommerceconcept.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment(private val layoutId: Int) : Fragment() {

    lateinit var inflatedViewBaseFragment: View
    var fragment: Fragment? = null

    abstract fun findViews()
    abstract fun informationInitialization()
    abstract fun setClickListeners()
    abstract fun otherViewInitialization()
    abstract fun injectFragment()
    abstract fun initialInitialization()

    override fun onAttach(context: Context) {
        injectFragment()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedViewBaseFragment = inflater.inflate(layoutId, container, false)
        return inflatedViewBaseFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialInitialization()

        findViews()

        informationInitialization()

        otherViewInitialization()

        setClickListeners()
    }
}