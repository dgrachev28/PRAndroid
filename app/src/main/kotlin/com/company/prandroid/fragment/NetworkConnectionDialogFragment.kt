package com.company.prandroid.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.company.prandroid.R


class NetworkConnectionDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.networkConnectionDialogTitle)
                .setMessage(R.string.networkConnectionDialogMessage)
                .setPositiveButton(R.string.networkConnectionDialogButton, { _, _ -> })
        return builder.create()
    }
}
