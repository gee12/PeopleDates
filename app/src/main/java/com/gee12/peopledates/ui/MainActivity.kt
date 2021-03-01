package com.gee12.peopledates.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.gee12.peopledates.R
import com.gee12.peopledates.ui.group.GroupsFragment
import com.gee12.peopledates.ui.person.PersonsFragment

class MainActivity : BaseActivity(), NavigationController  {

    companion object {
        private const val FRAGMENT_LOGIN = "FRAGMENT_LOGIN"
        private const val FRAGMENT_PERSONS = "FRAGMENT_PERSONS"
    }

    private var drawer: DrawerLayout? = null

    private var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DI
//        val app = application as PeopleDatesApp
//        app.component?.inject(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navController = Navigation.findNavController(this, R.id.persons_container)
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.personsFragment -> {
                    drawer?.closeDrawer(Gravity.LEFT)
                    lockDrawer(false)
                }
                R.id.detailsFragment -> lockDrawer(true)
                else -> lockDrawer(false)
            }
        }
        NavigationUI.setupActionBarWithNavController(this, navController!!)

        drawer = findViewById(R.id.layout_drawer)
        lockDrawer(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
//                    .replace(R.id.persons_container, PersonsFragment.newInstance(lastGroupId))
//                    .replace(R.id.persons_container, LoginFragment::class.java, null)
                    .replace(R.id.groups_container, GroupsFragment::class.java, null)
//                    .add(GroupDialogFragment::class.java, null, GroupDialogFragment::class.qualifiedName)
                    .commitNow()
        }
    }

    override fun loginSuccess() {
        /*// удаляем из BackStack LoginFragment, если он там есть
        supportFragmentManager.popBackStack(FRAGMENT_LOGIN, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        // TODO: получить id из SharedPrefs
//            val lastGroupId = "1555666975q3x79jvh82"
        val lastGroupId = 123

        supportFragmentManager.commit {
            addToBackStack(null)
            // TODO: использовать di вместо создания фрагмента вручную
            replace(R.id.persons_container, PersonsFragment::class.java, PersonsFragment.newArgBundle(lastGroupId))
            setDrawerVisibility(true)
        }*/
    }

    override fun openGroup(id: Int) {
        navController?.navigate(R.id.showPersonsFragment, PersonsFragment.newArgBundle(id))
/*        // удаляем из BackStack аналогичный фрагмент, если он там есть
        supportFragmentManager.popBackStack(FRAGMENT_PERSONS, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        // добаляем фрагмент в BackStack и устанавливаем текущим
        supportFragmentManager.commit {
            addToBackStack(FRAGMENT_PERSONS)
            // TODO: использовать di вместо создания фрагмента вручную
            replace(R.id.persons_container, PersonsFragment::class.java, PersonsFragment.newArgBundle(id))
        }

        findViewById<DrawerLayout>(R.id.layout_drawer).closeDrawer(Gravity.LEFT)*/
    }

    override fun openDetails(personId: Int) {
        /*supportFragmentManager.commit {
            addToBackStack(null)
            // TODO: использовать di вместо создания фрагмента вручную
            replace(R.id.persons_container, DetailsFragment::class.java, DetailsFragment.newArgBundle(personId))
            setDrawerVisibility(false)
        }*/
    }

    fun lockDrawer(lock: Boolean) {
        if (lock)
            drawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.LEFT)
        else
            drawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.LEFT)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
//                supportFragmentManager.popBackStack()
                navController?.popBackStack();
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("RestrictedApi")
    override fun onBackPressed() {
        if (drawer?.isDrawerOpen(Gravity.LEFT) == true) {
            drawer?.closeDrawer(Gravity.LEFT)
        } else if (navController?.backStack?.size == 0) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}