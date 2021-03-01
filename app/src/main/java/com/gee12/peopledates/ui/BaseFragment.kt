package com.gee12.peopledates.ui

import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {

    fun setTitle(title: String) {
//        activity?.actionBar?.title = title
        requireActivity().title = title
    }
}