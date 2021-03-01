package com.gee12.peopledates.di.component

import android.app.Application
import com.gee12.peopledates.PeopleDatesApp
import com.gee12.peopledates.di.module.*
//import com.gee12.peopledates.di.module.FragmentsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    LocalRepoModule::class,
    RemoteRepoModule::class,
    MainActivityModule::class,
    FragmentBuildersModule::class
])
interface AppComponent : AndroidInjector<PeopleDatesApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}
//interface AppComponent {
//
///*    @Component.Builder
//    abstract class Builder : AndroidInjector.Builder<PeopleDatesApp>()*/
//    /*interface Builder {
//        @BindsInstance
//        fun application(application: Application): Builder
//        fun build(): AppComponent
//    }*/
//
////    fun inject(app: PeopleDatesApp)
//    fun inject(activity: MainActivity)
//}