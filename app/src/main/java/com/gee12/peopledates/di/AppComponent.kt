package com.gee12.peopledates.di

import android.app.Application
import com.gee12.peopledates.PeopleDatesApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    FragmentBuilderModule::class,
    RepositoryModule::class,
    NetworkModule::class
])
interface AppComponent : AndroidInjector<PeopleDatesApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: PeopleDatesApp?)
}