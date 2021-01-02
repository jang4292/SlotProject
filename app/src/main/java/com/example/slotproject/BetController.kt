package com.example.slotproject

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast

class BetController(private val context: Context, private val parent: LinearLayout) {
    private lateinit var buttonOnClickFunction: () -> Unit

    init {
        GameConfig.TOTAL_BET_LIST.forEach {
            val btn = Button(context)
            val ivLayoutParams = LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 0)
            ivLayoutParams.weight = 1f
            btn.layoutParams = ivLayoutParams
            btn.setBackgroundResource(R.drawable.bg_image_button_bet)
            btn.setTextColor(context.resources.getColor(R.color.color_font_bet_list_text))
            btn.text = it.toString()
            btn.tag = it

            btn.setOnClickListener {
                GameConfig.totalBet = it.tag as Int
                buttonOnClickFunction()
                hide()
            }
            parent.addView(btn)
        }

        parent.visibility = View.GONE
    }

    fun setOnClickButton(function: () -> Unit) {
        buttonOnClickFunction = function
    }

    fun show() {
        if (GameConfig.isAuto || GameConfig.gameStatus === GameStatus.SPINNING) {
            Toast.makeText(context, "Slot is spinning, please wait", Toast.LENGTH_SHORT).show()
            return
        }
        parent.visibility = View.VISIBLE
    }

    fun hide() {
        parent.visibility = View.GONE
    }
}