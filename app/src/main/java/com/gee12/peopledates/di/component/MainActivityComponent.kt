package com.gee12.peopledates.di.component

//import com.gee12.peopledates.di.module.MainActivityModule
import com.gee12.peopledates.ui.MainActivity
import dagger.Component

@Deprecated("")
//@Component(
//    modules = [MainActivityModule::class],
//    dependencies = [AppComponent::class])
interface MainActivityComponent {

    fun inject(activity: MainActivity)
}
