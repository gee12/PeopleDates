package com.gee12.peopledates

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.gee12.peopledates.di.DaggerAppComponent
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector

class PeopleDatesApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}