package com.gee12.peopledates.ui.person

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gee12.peopledates.*
import com.gee12.peopledates.data.model.Group
import com.gee12.peopledates.data.model.Person
import com.gee12.peopledates.network.ApiFactory
import com.gee12.peopledates.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class PersonsFragment : BaseFragment() {

    companion object {
        const val ARG_GROUP_ID = "ARG_GROUP_ID"

        @JvmStatic
        fun newInstance(id: Int?) = PersonsFragment().apply {
            arguments = newArgBundle(id)
        }

        fun newArgBundle(id: Int?) = Bundle().apply {
            id?.let { putInt(ARG_GROUP_ID, id) }
        }
    }

    @Inject
    lateinit var viewModel: PersonsViewModel
//    private val viewModel: MoviesListViewModelImpl by viewModels {
//        MovieListViewModelFactory((requireActivity() as MovieRepositoryProvider).provideMovieRepository())
//    }

    private lateinit var recyclerAdapter: PersonRecyclerViewAdapter
//    private val personsAdapter by lazy {
//        PersonRecyclerViewAdapter(ApiFactory.buildImageLoader(requireContext().applicationContext))
//    }

    private var recycler: RecyclerView? = null
    private var progressBar: ProgressBar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_persons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupId = arguments?.getInt(ARG_GROUP_ID) ?: 0
//        if (groupId == null) {
//            parentFragmentManager.popBackStack()
//            return
//        }

        val navigationController = getNavigationController()

        // views
        recyclerAdapter = PersonRecyclerViewAdapter(
            ApiFactory.buildImageLoader(requireContext().applicationContext)) {
            id -> navigationController.openDetails(id)
        }
        recycler = (view.findViewById(R.id.recycler_view_persons) as RecyclerView?)?.apply {
            adapter = recyclerAdapter
            setHasFixedSize(true)
        }
        progressBar = view.findViewById(R.id.progress_circle)

        // заменено на DI
        // viewModel
        /*viewModel = ViewModelProvider(this,
                ViewModelFactory(requireContext().applicationContext)
        ).get(PersonsViewModel::class.java)*/

        viewModel.group.observe(this.viewLifecycleOwner, Observer { updateGroup(it) })
        viewModel.personsList.observe(this.viewLifecycleOwner, Observer { updateAdapter(it) })
        viewModel.errorMessage.observe(this.viewLifecycleOwner, Observer { showError(it) })
        viewModel.loadingState.observe(this.viewLifecycleOwner, Observer { showLoading(it) })

        if (savedInstanceState == null) {
            viewModel.loadPersons(groupId)
        }
    }

    private fun updateGroup(group: Group) {
        activity?.actionBar?.title = group.name
    }

    private fun updateAdapter(persons: List<Person>) {
        recyclerAdapter.submitList(persons)
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    private fun showLoading(loading: Boolean) {
        progressBar?.isVisible = loading
    }
}