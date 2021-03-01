package com.gee12.peopledates.di.module

import com.gee12.peopledates.di.scope.ActivityScope
import com.gee12.peopledates.di.scope.FragmentScope
import com.gee12.peopledates.ui.MainActivity
import com.gee12.peopledates.ui.details.DetailsFragment
import com.gee12.peopledates.ui.group.GroupDialogFragment
import com.gee12.peopledates.ui.group.GroupsFragment
import com.gee12.peopledates.ui.login.LoginFragment
import com.gee12.peopledates.ui.person.PersonsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}