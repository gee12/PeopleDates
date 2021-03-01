package com.gee12.peopledates.di.module

import com.gee12.peopledates.data.db.DatabaseRepo
import com.gee12.peopledates.di.scope.FragmentScope
import com.gee12.peopledates.network.repo.PersonRemoteRepo
import com.gee12.peopledates.ui.person.PersonViewModel
import dagger.Module
import dagger.Provides

@Deprecated("")
@Module
class PersonsModule {

    @FragmentScope
    @Provides
    fun bindViewModel(
        repo: DatabaseRepo,
        remoteRepository: PersonRemoteRepo
    ) = PersonViewModel(repo, remoteRepository)
}