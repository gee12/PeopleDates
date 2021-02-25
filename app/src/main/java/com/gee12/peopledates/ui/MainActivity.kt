package com.gee12.peopledates.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.gee12.peopledates.NavigationController
import com.gee12.peopledates.R
import com.gee12.peopledates.ui.group.GroupsFragment
import com.gee12.peopledates.ui.details.DetailsFragment
import com.gee12.peopledates.ui.login.LoginFragment
import com.gee12.peopledates.ui.person.PersonsFragment
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationController {

    companion object {
        private const val FRAGMENT_PERSONS = "FRAGMENT_PERSONS"
    }

    @Inject
    lateinit var loginFragment: LoginFragment

    private var drawer: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawer = findViewById(R.id.layout_drawer)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
//                    .replace(R.id.persons_container, PersonsFragment.newInstance(lastGroupId))
//                    .replace(R.id.persons_container, LoginFragment::class.java, null)
                    .replace(R.id.persons_container, loginFragment)
                    .replace(R.id.groups_container, GroupsFragment::class.java, null)
                    .commitNow()
        }
    }

    override fun loginSuccess() {
        // TODO: получить id из SharedPrefs
//            val lastGroupId = "1555666975q3x79jvh82"
        val lastGroupId = 123

        supportFragmentManager.commit {
            addToBackStack(null)
            // TODO: использовать di вместо создания фрагмента вручную
            replace(R.id.persons_container, PersonsFragment::class.java, PersonsFragment.newArgBundle(lastGroupId))
        }
    }

    override fun openGroup(id: Int) {
        // удаляем из BackStack аналогичный фрагмент, если он там уже есть
        supportFragmentManager.popBackStack(FRAGMENT_PERSONS, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        // добаляем фрагмент в BackStack и устанавливаем текущим
        supportFragmentManager.commit {
            addToBackStack(FRAGMENT_PERSONS)
            // TODO: использовать di вместо создания фрагмента вручную
            replace(R.id.persons_container, PersonsFragment::class.java, PersonsFragment.newArgBundle(id))
        }

        findViewById<DrawerLayout>(R.id.layout_drawer).closeDrawer(Gravity.LEFT)
    }

    override fun openDetails(personId: Int) {
        supportFragmentManager.commit {
            addToBackStack(null)
            // TODO: использовать di вместо создания фрагмента вручную
            replace(R.id.persons_container, DetailsFragment::class.java, DetailsFragment.newArgBundle(personId))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer?.isDrawerOpen(Gravity.LEFT) == true) {
            drawer?.closeDrawer(Gravity.LEFT)
        } else {
            super.onBackPressed()
        }
    }
}