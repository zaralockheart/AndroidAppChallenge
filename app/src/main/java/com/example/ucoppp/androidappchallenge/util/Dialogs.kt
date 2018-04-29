package com.example.ucoppp.androidappchallenge.util

import android.app.AlertDialog
import android.view.View


fun setUiMobileDialog(dialog: AlertDialog.Builder, title: String, view: View) {
    dialog.setTitle(title)
    dialog.setView(view)
}