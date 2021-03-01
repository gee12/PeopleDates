package com.gee12.peopledates.di.module

import android.app.Application
import android.content.Context
import com.gee12.peopledates.PeopleDatesApp
import com.gee12.peopledates.di.scope.ActivityScope
import com.gee12.peopledates.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton


@Suppress("unused")
@Module
interface AppModule/*(val application: PeopleDatesApp)*/ {

/*    var application: Application? = null

    fun AppModule(app: PeopleDatesApp) {
        application = app
    }*/

    @Binds
    abstract fun bindContext(application: Application): Context

//    @Provides
//    @Singleton
//    fun provideContext() = application

//    @ActivityScope
//    @ContributesAndroidInjector(modules = [MainActivityModule::class])
//    abstract fun bindMainActivity(): MainActivity
}