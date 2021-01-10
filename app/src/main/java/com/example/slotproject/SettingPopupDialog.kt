package com.example.slotproject

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast

class SettingPopupDialog(context: Context) {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val view = inflater.inflate(R.layout.setting_popup_dialog, null)

    private val alertDialog = AlertDialog.Builder(context)
        .setTitle("Temp")
        .setPositiveButton("Save") { dialog, which ->
            Toast.makeText(context, "Toast", Toast.LENGTH_SHORT)
        }
        .setNegativeButton("Cancel", null)
        .create()

    init {
        alertDialog.setView(this.view)
    }

    fun show() {
        alertDialog.show()
    }
}