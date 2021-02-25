package com.gee12.peopledates.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog

@Deprecated("")
class AskDialogBuilder : AlertDialog.Builder {
    private var mView: View? = null

    constructor(context: Context) : super(context) {}
    constructor(context: Context, themeResId: Int) : super(context, themeResId) {}

    override fun setView(view: View?): AlertDialog.Builder {
        mView = view
        return super.setView(view)
    }

    val view: View?
        get() = mView

    companion object {
        fun create(context: Context, layoutResId: Int): AskDialogBuilder {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            val inflater = LayoutInflater.from(context)
            val dialogBuilder = AskDialogBuilder(context)
            val dialogView: View = inflater.inflate(layoutResId, null)
            dialogBuilder.setView(dialogView)
            dialogBuilder.setCancelable(true)
            return dialogBuilder
        }
    }
}