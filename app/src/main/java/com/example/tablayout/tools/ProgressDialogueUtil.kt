package com.example.tablayout.tools

import android.app.ProgressDialog
import android.content.Context

class ProgressDialogueUtil(val context: Context) {

    private var progressDialog: ProgressDialog? = null

    fun show(message: String) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
            progressDialog!!.setCancelable(false)
            progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        }
        progressDialog!!.setTitle("Please wait")
        progressDialog!!.setMessage(message)
        progressDialog!!.show()
    }

    fun hide() {
        progressDialog?.dismiss()
        progressDialog = null
    }
}