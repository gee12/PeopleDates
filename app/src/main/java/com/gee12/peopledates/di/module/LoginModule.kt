package com.gee12.peopledates.di.module

import com.gee12.peopledates.data.LoginRepository
import com.gee12.peopledates.di.scope.FragmentScope
import com.gee12.peopledates.ui.login.LoginViewModel
import dagger.Module
import dagger.Provides

@Deprecated("")
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