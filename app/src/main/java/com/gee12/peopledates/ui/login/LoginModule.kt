package com.gee12.peopledates.ui.login

import com.gee12.peopledates.GroupsViewModel
import com.gee12.peopledates.PersonsViewModel
import com.gee12.peopledates.data.LoginRepository
import com.gee12.peopledates.data.db.DatabaseRepository
import com.gee12.peopledates.di.scope.ActivityScope
import com.gee12.peopledates.di.scope.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class LoginModule{

//    @Binds
//    @ActivityScope
//    abstract fun bindLoginView(loginActivity: LoginActivity): LoginContract.View
//
//    @Binds
//    @ActivityScope
//    abstract fun bindLoginPresenter(loginPresenter: LoginPresenter): LoginContract.Presenter

    @FragmentScope
    @Provides
    fun bindViewModel(repo: LoginRepository) = LoginViewModel(repo)
}