package com.imprarce.android.testtaskvalute.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.Window
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.imprarce.android.testtaskvalute.R

object CustomProgressBar {
    private var dialog: Dialog? = null


    fun showProgressBar(context: Context) {
        try {
            if (isProgressShowing(context)) {
                hideProgressBar()
            }

            setProgress(context)

        } catch (e: Exception) {
            Log.e("Exception","${e.message}")
        }
    }

    fun hideProgressBar() {
        try {
            if (dialog!!.isShowing) {
                dialog!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun isProgressShowing(context: Context): Boolean {
        return if (!isNull(dialog) && dialog!!.context == context) {
            dialog!!.isShowing
        } else false
    }

    private fun setProgress(context: Context) {
        if (!isNull(context) && !(context as Activity).isFinishing) {
            dialog = Dialog(context)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)

            dialog!!.setContentView(R.layout.progress_bar)

            val loadingImage = dialog!!.findViewById<ImageView>(R.id.iv_custom_loading)
            Glide.with(context).asGif().load(R.drawable.loading).into(loadingImage)

            dialog!!.setCancelable(false)
            dialog!!.setCanceledOnTouchOutside(false)

            if (!isNull(dialog!!.window)) {
                dialog!!.window?.setBackgroundDrawableResource(R.color.white)
            }

            dialog!!.show()
        }
    }

    fun isNull(`object`: Any?): Boolean {
        return `object` == null
    }
}