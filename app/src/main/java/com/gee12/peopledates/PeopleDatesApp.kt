package com.gee12.peopledates

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.gee12.peopledates.di.component.AppComponent
import com.gee12.peopledates.di.component.DaggerAppComponent
import com.gee12.peopledates.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject


//class PeopleDatesApp : Application() {
//
//    var component: AppComponent? = null
//
////    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
////        return DaggerAppComponent.builder().create(this)
////    }
//
//    override fun onCreate() {
//        super.onCreate()
//        component = DaggerAppComponent.builder()
//            .appModule(AppModule(this))
//            .build()
//    }
//}

class PeopleDatesApp : DaggerApplication(), ImageLoaderFactory {

    @Inject
    lateinit var imageLoader: ImageLoader


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    /**
     * Для загрузки изображений с помощью бибилиотеки Coil из любого места программы.
     * (не используется, оставлено в ознакомительных целях)
     */
    override fun newImageLoader(): ImageLoader {
        return imageLoader
    }
}