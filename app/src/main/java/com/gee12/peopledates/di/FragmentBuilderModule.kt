package com.gee12.peopledates.di

import android.os.Bundle
import com.gee12.peopledates.di.scope.FragmentScope
import com.gee12.peopledates.ui.group.GroupsFragment
import com.gee12.peopledates.ui.group.GroupsModule
import com.gee12.peopledates.ui.details.DetailsFragment
import com.gee12.peopledates.ui.details.DetailsModule
import com.gee12.peopledates.ui.group.GroupDialogFragment
import com.gee12.peopledates.ui.login.LoginFragment
import com.gee12.peopledates.ui.login.LoginModule
import com.gee12.peopledates.ui.person.PersonsFragment
import com.gee12.peopledates.ui.person.PersonsModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
class FragmentBuilderModule {

    /**
     * Будет сгенерирован AndroidInject (сабкомпонент) для конкретного фрагмента со скоупом @FragmentScope.
     * Будут доступны зависимости @Singleton, @ActivityScope, которые указаны в данном модуле
     *  и те, что указаны в качестве модулей для данного фрагмента.
     */
    @FragmentScope
//    @ContributesAndroidInjector(modules = [LoginModule::class])
    @Provides
    fun bindLoginFragment() = LoginFragment()

    @FragmentScope
//    @ContributesAndroidInjector(modules = [PersonsModule::class])
    @Provides
    fun bindPersonsFragment(groupId: Int) = PersonsFragment.newInstance(groupId)
//    fun bindPersonsFragment(groupId: Int): PersonsFragment {
//        return PersonsFragment().apply {
//            arguments = Bundle().apply {
//                putInt(PersonsFragment.ARG_GROUP_ID, groupId)
//            }
//        }
//    }

    @FragmentScope
//    @ContributesAndroidInjector(modules = [DetailsModule::class])
    @Provides
    fun bindDetailsFragment(id: Int) = DetailsFragment.newInstance(id)
//    fun bindDetailsFragment(id: Int): DetailsFragment {
//        return DetailsFragment().apply {
//            arguments = Bundle().apply {
//                putInt(DetailsFragment.ARG_ID, id)
//            }
//        }
//    }

    @FragmentScope
//    @ContributesAndroidInjector(modules = [GroupsModule::class])
    @Provides
    fun bindGroupsFragment() = GroupsFragment()

    @FragmentScope
//    @ContributesAndroidInjector(modules = [GroupsModule::class])
    @Provides
    fun bindGroupDialogFragment(id: Int) = GroupDialogFragment.newInstance(id)
//    fun bindGroupDialogFragment(groupId: Int): GroupDialogFragment {
//        return GroupDialogFragment().apply {
//            arguments = Bundle().apply {
//                putInt(GroupDialogFragment.ARG_GROUP_ID, groupId)
//            }
//        }
//    }
}