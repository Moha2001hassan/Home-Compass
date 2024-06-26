package com.mohassan.homecompass.core.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.mohassan.homecompass.R
import com.mohassan.homecompass.auth_feature.presentation.activity.IntroActivity
import com.mohassan.homecompass.auth_feature.presentation.viewmodel.UserViewModel
import com.mohassan.homecompass.home_compass_feature.presentation.interfaces.CustomDialogListener

object ShowCustomDialog {

    fun showCustomDialog(imageId: Int, context: Context, onResult: (Boolean) -> Unit) {
        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnYes = dialog.findViewById<Button>(R.id.btn_yes_dialog)
        val btnCancel = dialog.findViewById<Button>(R.id.btn_cancel_dialog)
        val imgDialog = dialog.findViewById<ImageView>(R.id.img_dialog)

        imgDialog.setImageResource(imageId)

        btnYes.setOnClickListener {
            when (imageId) {
                R.drawable.ic_delete_account -> {
                    // Handle delete account if needed
                    onResult(true)
                }

                R.drawable.ic_logout_24 -> {
                    // logout
                    onResult(true)
                }

                else -> dialog.dismiss()
            }
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            onResult(false)
            dialog.dismiss()
        }
        dialog.show()
    }

    /*
    fun showCustomDialog(imageId: Int, context: Context, listener: CustomDialogListener) {
        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnYes = dialog.findViewById<Button>(R.id.btn_yes_dialog)
        val btnCancel = dialog.findViewById<Button>(R.id.btn_cancel_dialog)
        val imgDialog = dialog.findViewById<ImageView>(R.id.img_dialog)

        imgDialog.setImageResource(imageId)

        btnYes.setOnClickListener {
            when (imageId) {
                R.drawable.ic_delete_account -> {
                    // delete account and go to login
                    listener.onDeleteAccount()
                }

                R.drawable.ic_logout_24 -> {
                    val intent = Intent(context, IntroActivity::class.java)
                    context.startActivity(intent)
                    if (context is Activity) {
                        context.finish()
                    }
                }

                else -> dialog.dismiss()
            }
        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }*/
}