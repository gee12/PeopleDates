package com.gee12.peopledates.ui.group

import com.gee12.peopledates.GroupsViewModel
import com.gee12.peopledates.PersonsViewModel
import com.gee12.peopledates.data.db.DatabaseRepository
import com.gee12.peopledates.di.scope.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class GroupsModule {

    @FragmentScope
    @Provides
    fun bindViewModel(repo: DatabaseRepository) = GroupsViewModel(repo)
}