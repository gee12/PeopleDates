/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gee12.peopledates.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.gee12.peopledates.ui.group.GroupViewModel
import com.gee12.peopledates.ui.login.LoginViewModel
import com.gee12.peopledates.ui.person.PersonViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(userViewModel: LoginViewModel): ViewModel

    @Binds
//    @IntoMap
//    @ViewModelKey(GroupViewModel::class)
    abstract fun bindGroupViewModel(searchViewModel: GroupViewModel): ViewModel

    @Binds
//    @IntoMap
//    @ViewModelKey(PersonViewModel::class)
    abstract fun bindPersonViewModel(repoViewModel: PersonViewModel): ViewModel

//    @Binds
//    abstract fun bindViewModelFactory(factory: GithubViewModelFactory): ViewModelProvider.Factory
}
