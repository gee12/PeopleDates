package com.gee12.peopledates.ui

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/*abstract class BaseActivity : AppCompatActivity()*//*, HasSupportFragmentInjector*//* {

//    @Inject
//    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

//    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
//        return fragmentInjector
//    }
}*/

open class BaseActivity : DaggerAppCompatActivity() {

}