package com.gee12.peopledates.ui.group

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.gee12.peopledates.GroupsViewModel
import com.gee12.peopledates.R
import com.gee12.peopledates.data.model.Group
import com.gee12.peopledates.ui.afterTextChanged
import com.gee12.peopledates.ui.details.DetailsFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class GroupDialogFragment : DialogFragment() {

    companion object {
        const val ARG_GROUP_ID = "ARG_GROUP_ID"

        @JvmStatic
        fun newInstance(id: Int?) = GroupDialogFragment().apply {
            arguments = newArgBundle(id)
        }

        fun newArgBundle(id: Int?) = Bundle().apply {
            id?.let { putInt(ARG_GROUP_ID, id) }
        }
    }

    interface ConfirmationListener {
        fun confirmButtonClicked()
//        fun cancelButtonClicked()
    }

    @Inject
    lateinit var viewModel: GroupsViewModel

    private lateinit var listener: ConfirmationListener

    private var viewLayout: View? = null
    private var buttonOk: Button? = null
    private var etName: EditText? = null
    private var etUrl: EditText? = null


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        try {
//            listener = activity as ConfirmationListener
            listener = targetFragment as ConfirmationListener
        } catch (e: ClassCastException) {
            throw ClassCastException(targetFragment.toString() + " must implement ConfirmationListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val id = arguments?.getInt(ARG_GROUP_ID)
        val isNew = (id == null)

        // viewModel
        /*viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext().applicationContext)
        ).get(GroupsViewModel::class.java)*/

        viewLayout = requireActivity().layoutInflater.inflate(R.layout.dialog_group, null)
        initViews(viewLayout!!)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(if (isNew) "Новая группа" else "Изменение группы")
//            .setView(R.layout.dialog_group)
            .setView(viewLayout)
            .setPositiveButton(R.string.answer_ok) { _, _ ->
                if (isNew) createGroup()
                else updateGroup(id!!)
                listener.confirmButtonClicked()
            }
            .setNegativeButton(R.string.answer_cancel) { _, _ ->
//                listener.cancelButtonClicked()
                dismiss()
            }
            .create()
            .apply {
                buttonOk = getButton(AlertDialog.BUTTON_POSITIVE)
            }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view: View = inflater.inflate(R.layout.dialog_group, container, false)
//        initViews(view)
        return viewLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.groupDialogState.observe(this.viewLifecycleOwner, Observer { updateState(it) })

        arguments?.getInt(ARG_GROUP_ID)?.let { id ->
            viewModel.group.observe(this.viewLifecycleOwner, Observer { updateFields(it) })
            viewModel.setGroupId(id)
        }
    }

    override fun onStart() {
        super.onStart()
        (dialog as AlertDialog?)?.apply {
            buttonOk = getButton(Dialog.BUTTON_POSITIVE).apply {
                isEnabled = false
            }
        }
    }

    fun initViews(view: View) {
        // view
        etName = view.findViewById(R.id.et_name)
        etUrl = view.findViewById(R.id.et_url)

        etName?.afterTextChanged { checkFields() }
        etUrl?.afterTextChanged { checkFields() }

    }

    private fun updateState(state: GroupDialogState) {
        state ?: return
        buttonOk?.isEnabled = state.isDataValid
        state.nameError?.let {
            etName?.error = getString(it)
        }
        state.urlError?.let {
            etUrl?.error = getString(it)
        }
    }

    private fun checkFields() {
        viewModel.groupDataChanged(
            etName?.text.toString(),
            etUrl?.text.toString()
        )
    }

    private fun updateFields(group: Group) {
        etName?.setText(group.name)
        etUrl?.setText(group.url)
    }

    private fun createGroup() {
        viewModel.addGroup(
            etName?.text.toString(),
            etUrl?.text.toString()
        )
    }

    private fun updateGroup(id: Int) {
        viewModel.updateGroup(
            id,
            etName?.text.toString(),
            etUrl?.text.toString()
        )
    }
}