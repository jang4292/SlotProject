package com.example.slotproject

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

class Reel(context: Context, index: Int) : LinearLayout(context) {

    private val imageViews: Array<ImageView?> = arrayOfNulls(GameConfig.ROW)

    init {
        val llLayoutParams =
            LayoutParams(
                0,
                LayoutParams.MATCH_PARENT
            )
        llLayoutParams.weight = 1f
        layoutParams = llLayoutParams
        orientation = VERTICAL

        for (i in 0 until GameConfig.ROW) {
            val iv = ImageView(context)
            val ivLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 0)
            ivLayoutParams.weight = 1f
            iv.layoutParams = ivLayoutParams
            imageViews[i] = iv
            addView(iv)
        }
    }

    fun setStartSymbols(data: Array<Int?>) {
        imageViews.forEachIndexed { index, imageView ->
            val resId = data[index]
            imageView?.setBackgroundResource(resId!!)
        }
    }

    fun startSpin() {
        animate().translationY(height.toFloat()).setDuration(500L).start()
    }

    fun stopSpin() {
        animate().cancel()
        y = 0f
    }
}