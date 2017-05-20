package com.company.prandroid.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment


class NetworkConnectionDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Server is not available. Check your internet connection")
                .setTitle("Connection error")
                .setPositiveButton("OK", { _, _ ->  })
        return builder.create()
    }
}
