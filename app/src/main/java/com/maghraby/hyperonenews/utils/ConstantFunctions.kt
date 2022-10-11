package com.maghraby.hyperonenews.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AlertDialog


fun enableLogIn(sharedPreferences: SharedPreferences){

    val myEdit = sharedPreferences.edit()

    myEdit.putBoolean("keepLogged", true)
    myEdit.apply()
}
fun disableLogIn(sharedPreferences: SharedPreferences){

    val myEdit = sharedPreferences.edit()

    myEdit.putBoolean("keepLogged", false)
    myEdit.apply()
}
fun confirmDialog(
    context: Context,
    title: String,
    message: String,
    positiveButtonText: String,
    negativeButtonText: String,
    confirm: () -> Unit, reject: () -> Unit
) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setMessage(message)

    builder.setPositiveButton(positiveButtonText) { dialog, which ->
        confirm()
    }
    builder.setNegativeButton(negativeButtonText) { dialog, _ ->
        reject()
    }

    builder.show()
}