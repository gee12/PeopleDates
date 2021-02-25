package com.gee12.peopledates.ui.person

import com.gee12.peopledates.PersonsViewModel
import com.gee12.peopledates.data.LoginRepository
import com.gee12.peopledates.data.db.DatabaseRepository
import com.gee12.peopledates.di.scope.FragmentScope
import com.gee12.peopledates.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class PersonsModule {

    @FragmentScope
    @Provides
    fun bindViewModel(repo: DatabaseRepository) = PersonsViewModel(repo)
}