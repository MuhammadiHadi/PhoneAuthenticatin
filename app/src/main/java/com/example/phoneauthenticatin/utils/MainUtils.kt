package com.example.phoneauthenticatin.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast







class MainUtils {
        fun  showDialog(
                context: Context?,
                title: String?,
                msg: String?,
                ok: DialogInterface.OnClickListener?,
                close: DialogInterface.OnClickListener?
        ) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(msg)
                        .setPositiveButton("OK", ok)
                        .setNegativeButton("CLOSE", close)
                val alert: AlertDialog = builder.create()
                alert.show()
        }
        fun showToast(context: Context?, msg: String?) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
}