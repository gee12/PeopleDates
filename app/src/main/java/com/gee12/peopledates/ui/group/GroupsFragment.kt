package com.gee12.peopledates.ui.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.gee12.peopledates.*
import com.gee12.peopledates.data.model.Group
import com.gee12.peopledates.ui.BaseFragment
import com.gee12.peopledates.ui.getNavigationController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class GroupsFragment : BaseFragment(), GroupDialogFragment.ConfirmationListener {

    companion object {
        fun newInstance() = GroupsFragment()
    }

    @Inject
    lateinit var viewModel: GroupViewModel

//    @Inject
//    lateinit var groupDialogFragment: GroupDialogFragment

    private lateinit var recyclerAdapter: GroupRecyclerViewAdapter

    private var recycler: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var fabNewGroup: FloatingActionButton? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigationController = getNavigationController()

        // заменено на DI
        /*viewModel = ViewModelProvider(this,
            ViewModelFactory(requireContext().applicationContext)
        ).get(GroupsViewModel::class.java)*/

        recyclerAdapter = GroupRecyclerViewAdapter({
                id -> navigationController.openGroup(id)
        }, {
                id -> editGroup(id)
        })

        recycler = (view.findViewById(R.id.recycler_view_groups) as RecyclerView?)?.apply {
            adapter = recyclerAdapter
            setHasFixedSize(true)
        }
        progressBar = view.findViewById(R.id.progress_circle)
        fabNewGroup = view.findViewById<FloatingActionButton?>(R.id.fab_group_add)?.apply {
            setOnClickListener { createGroup() }
        }

        viewModel.groupsList.observe(viewLifecycleOwner, Observer { updateAdapter(it) })
        viewModel.successMessage.observe(viewLifecycleOwner, Observer { showMessage(it) })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel.loadingState.observe(viewLifecycleOwner, Observer { showLoading(it) })

        viewModel.loadGroups()
    }

    private fun createGroup() {
        /*GroupDialogs.createGroupDialog(requireContext(), null) { name: String, url: String ->
            viewModel.addGroup(name, url)
//            viewModel.loadGroups()
        }*/
        showGroupDialog(null)
    }

    private fun editGroup(id: Int) {
        showGroupDialog(id)
    }

    private fun showGroupDialog(id: Int?) {
        val ft: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//        GroupDialogFragment.newInstance(id).apply {
        (requireActivity().supportFragmentManager
            .findFragmentByTag( GroupDialogFragment::class.qualifiedName) as DialogFragment)
            .apply {
//        groupDialogFragment.apply {
            arguments = GroupDialogFragment.newArgBundle(id)
            setTargetFragment(this@GroupsFragment, 300)
            show(ft, "GroupDialogFragment")
        }
    }

    private fun updateAdapter(persons: List<Group>) {
        recyclerAdapter.submitList(persons)
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    private fun showLoading(loading: Boolean) {
        progressBar?.isVisible = loading
    }

    override fun confirmButtonClicked() {
        viewModel.loadGroups()
    }
}