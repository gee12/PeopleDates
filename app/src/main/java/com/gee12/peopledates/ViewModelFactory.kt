package com.gee12.peopledates

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Deprecated("")
class ViewModelFactory(
    private val applicationContext: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
//        LoginViewModel::class.java -> {
//            LoginViewModel(
//                repository = LoginRepository(applicationContext)
//            )
//        }
//        PersonsViewModel::class.java -> {
//            PersonsViewModel(
//                repository = DatabaseRepository(applicationContext)
////                bitmapLoader = ApiFactory.buildImageLoader(applicationContext)
//            )
//        }
//        GroupsViewModel::class.java -> {
//            GroupsViewModel(
//                repository = DatabaseRepository(applicationContext)
//            )
//        }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}