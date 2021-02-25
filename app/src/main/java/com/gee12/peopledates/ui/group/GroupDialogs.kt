package com.gee12.peopledates.ui.group

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.gee12.peopledates.R
import com.gee12.peopledates.Utils
import com.gee12.peopledates.data.model.Group
import com.gee12.peopledates.ui.AskDialogBuilder
import com.gee12.peopledates.ui.afterTextChanged


@Deprecated("")
object GroupDialogs {

    fun createGroupDialog(context: Context, group: Group?,
                          callback: (name: String, url: String) -> Unit) {
        val isNew = (group == null)
        val builder = AskDialogBuilder.create(context, R.layout.dialog_group)
        val view = builder.view

        // view
        val etName = view?.findViewById<EditText>(R.id.et_name)
        val etUrl = view?.findViewById<EditText>(R.id.et_url)
        if (!isNew) {
            etName?.setText(group?.name)
            etUrl?.setText(group?.url)
        }

        val dialog = builder.create();
        dialog.setTitle(if (isNew) "Новая группа" else "Изменение группы")

        // кнопки результата
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.answer_ok), null as DialogInterface.OnClickListener?)
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, context.getString(R.string.answer_cancel)) {
            dialog1: DialogInterface, _ -> dialog1.cancel()
        }

        dialog.setOnShowListener {

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                when {
                    TextUtils.isEmpty(etName?.text.toString()) -> {
                        etName?.error = "Заполните поле"
                    }
                    !Utils.isValidUrl(etUrl?.text.toString()) -> {
                        etUrl?.error = "Введите корректный Url"
                    }
                    else -> {
                        callback(
                            etName?.text.toString(),
                            etUrl?.text.toString()
                        )
                        dialog.dismiss()
                    }
                }
            }
            etName?.run { setSelection(this.text.length) }
        }
        dialog.show();

        // получаем okButton уже после вызова show()
//        val okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
//        etName?.afterTextChanged { text ->
//            TextUtils.isEmpty(text).let {
//                okButton.isEnabled = !it
//                if (it) {
//                    etName?.error = "Заполните поле"
//                }
//            }
//        }
    }

//    fun createGroupDialogOlc(context: Context, group: Group?, callback: (group: Group) -> Unit) {
//        val isNew = (group == null)
//        val dialog = Dialog(context).apply {
////            requestWindowFeature(Window.FEATURE_NO_TITLE)
//            setTitle(if (isNew) "" else "")
//            setCancelable(true)
//            setContentView(R.layout.dialog_group)
//        }
//        val tvName = dialog.findViewById<TextView>(R.id.tv_group_name)
//        val tvUrl = dialog.findViewById<TextView>(R.id.tv_group_url)
//        if (group != null) {
//            tvName.text = group.name
//            tvUrl.text = group.url
//        }
//        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
//        val noBtn = dialog.findViewById(R.id.noBtn) as TextView
//        yesBtn.setOnClickListener {
//            dialog.dismiss()
//        }
//        noBtn.setOnClickListener { dialog.dismiss() }
//        dialog.show()
//    }
}