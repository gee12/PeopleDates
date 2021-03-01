package com.gee12.peopledates.di.module

import com.gee12.peopledates.data.db.DatabaseRepo
import com.gee12.peopledates.di.scope.FragmentScope
import com.gee12.peopledates.ui.group.GroupViewModel
import dagger.Module
import dagger.Provides

@Deprecated("")
@Module
class GroupModule {

    @FragmentScope
    @Provides
    fun bindViewModel(repo: DatabaseRepo) = GroupViewModel(repo)
}