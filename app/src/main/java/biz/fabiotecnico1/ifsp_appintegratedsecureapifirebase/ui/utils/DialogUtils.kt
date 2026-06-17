package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogUtils {

    fun showAlertDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String = "OK",
        negativeButtonText: String? = null,
        onPositiveClick: (() -> Unit)? = null,
        onNegativeClick: (() -> Unit)? = null
    ) {
        val builder = MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { _, _ ->
                onPositiveClick?.invoke()
            }

        negativeButtonText?.let {
            builder.setNegativeButton(it) { _, _ ->
                onNegativeClick?.invoke()
            }
        }

        builder.show()
    }
}