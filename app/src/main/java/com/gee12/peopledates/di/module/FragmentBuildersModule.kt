/*
 * Copyright (C) 2017 The Android Open Source Project
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

import com.gee12.peopledates.di.scope.FragmentScope
import com.gee12.peopledates.ui.details.DetailsFragment
import com.gee12.peopledates.ui.group.GroupDialogFragment
import com.gee12.peopledates.ui.group.GroupsFragment
import com.gee12.peopledates.ui.login.LoginFragment
import com.gee12.peopledates.ui.person.PersonsFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Будет сгенерирован AndroidInject (сабкомпонент) для конкретного фрагмента со скоупом @FragmentScope.
 * Будут доступны зависимости @Singleton, @ActivityScope, которые указаны в данном модуле
 *  и те, что указаны в качестве модулей для данного фрагмента.
 */
@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributePersonsFragment(): PersonsFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeDetailsFragment(): DetailsFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeGroupsFragment(): GroupsFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeGroupDialogFragment(): GroupDialogFragment
}
