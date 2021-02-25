package com.gee12.peopledates.ui.details

import com.gee12.peopledates.PersonsViewModel
import com.gee12.peopledates.data.db.DatabaseRepository
import com.gee12.peopledates.di.scope.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DetailsModule {

    @FragmentScope
    @Provides
    fun bindViewModel(repo: DatabaseRepository) = PersonsViewModel(repo)
}