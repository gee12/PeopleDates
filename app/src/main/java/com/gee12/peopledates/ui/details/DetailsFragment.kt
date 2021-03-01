package com.gee12.peopledates.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.load
import com.gee12.peopledates.ui.person.PersonViewModel
import com.gee12.peopledates.R
import com.gee12.peopledates.utils.Utils
import com.gee12.peopledates.data.model.Person
import com.gee12.peopledates.ui.BaseFragment
import com.gee12.peopledates.ui.person.PersonsFragmentArgs
import javax.inject.Inject


class DetailsFragment : BaseFragment() {

    companion object {
        const val ARG_ID = "ARG_ID"

//        @JvmStatic
//        fun newInstance(id: Int) = DetailsFragment().apply {
//            arguments = newArgBundle(id)
//        }

        fun newArgBundle(id: Int?) = Bundle().apply {
            id?.let { putInt(ARG_ID, id) }
        }
    }

    @Inject
    lateinit var personsViewModel: PersonViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    private val params by navArgs<DetailsFragmentArgs>()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val id = arguments?.getInt(ARG_ID)
        val id = params.personId
        if (id < 0) {
            parentFragmentManager.popBackStack()
            return
        }

        // заменено на DI
       /* personsViewModel = ViewModelProvider(this,
                ViewModelFactory(requireContext().applicationContext)
        ).get(PersonsViewModel::class.java)*/

        personsViewModel.person.observe(viewLifecycleOwner, Observer { updatePerson(it) })

        personsViewModel.setPersonId(id)

        setTitle("Детальная информация")
    }

    private fun initViews(view: View, person: Person) {
        val headerView = view.findViewById<ConstraintLayout>(R.id.layout_header)
        headerView.findViewById<TextView>(R.id.tv_person_date_birth).text =
                Utils.dateToString(person.dateBirth, "%1\$te %1\$tb %1\$tY")
        headerView.findViewById<TextView>(R.id.tv_person_date_death).text =
                Utils.dateToString(person.dateDeath, "%1\$te %1\$tb %1\$tY")
        headerView.findViewById<TextView>(R.id.tv_person_age).text = "(${person.age})"
        headerView.findViewById<TextView>(R.id.tv_person_name).text = person.name
        headerView.findViewById<TextView>(R.id.tv_person_prof).text = person.prof
        headerView.findViewById<ImageView>(R.id.iv_person_photo).load(
                person.imageUrl,
                imageLoader
        ) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_menu_delete)
        }
        view.findViewById<TextView>(R.id.tv_info).text = person.info
    }

    private fun updatePerson(person: Person) {
        initViews(requireView(), person)
    }
}