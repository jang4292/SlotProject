package com.example.slotproject

import android.content.Context
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout

class Reel(context: Context, index: Int) : FrameLayout(context) {

    private val ll: LinearLayout
    private val spinningReel: FrameLayout
    private val symbolCount: Int = 10 + index * 10
    private val imageViews: Array<ImageView?> = arrayOfNulls(GameConfig.ROW)
    private val startSymbolResIds: Array<Int?> = arrayOfNulls(GameConfig.ROW)

    init {
        val flLayoutParams =
            LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
        flLayoutParams.weight = 1f
        layoutParams = flLayoutParams

        ll = LinearLayout(context)
        val llLayoutParams =
            LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
        ll.layoutParams = llLayoutParams
        ll.orientation = LinearLayout.VERTICAL

        addView(ll)

        spinningReel = FrameLayout(context)
        spinningReel.visibility = View.GONE
        addView(spinningReel)

        for (i in 0 until GameConfig.ROW) {
            val iv = ImageView(context)
            val ivLayoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0)
            ivLayoutParams.weight = 1f
            iv.layoutParams = ivLayoutParams
            imageViews[i] = iv
            ll.addView(iv)

            iv.viewTreeObserver.addOnGlobalLayoutListener(
                object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        if (GameConfig.SYMBOL_WIDTH > 0 && GameConfig.SYMBOL_HEIGHT > 0) {
                            return
                        }
                        GameConfig.SYMBOL_WIDTH = iv.width
                        GameConfig.SYMBOL_HEIGHT = iv.height
                        iv.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                }
            )
        }
    }

    fun setStartSymbols(data: Array<Int?>) {
        imageViews.forEachIndexed { index, imageView ->
            val resId = data[index]
            startSymbolResIds[index] = resId
            imageView?.setBackgroundResource(resId!!)
        }
    }

    fun startSpin() {
        createFakeReel()
        ll.visibility = View.GONE
        spinningReel.visibility = View.VISIBLE
        spinningReel.animate().translationY(0f).setDuration((symbolCount * 2000L * 0.1f).toLong()).start()
    }

    private fun createFakeReel() {
        val reelHeight = GameConfig.SYMBOL_HEIGHT * (GameConfig.ROW + symbolCount)
        val layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, reelHeight)
        spinningReel.layoutParams = layoutParams
        spinningReel.y = -(GameConfig.SYMBOL_HEIGHT * symbolCount).toFloat()
        startSymbolResIds.forEachIndexed { index, it ->
            val iv = ImageView(context)
            val ivLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, GameConfig.SYMBOL_HEIGHT)
            iv.y = (GameConfig.SYMBOL_HEIGHT * (index + symbolCount)).toFloat()
            iv.layoutParams = ivLayoutParams
            iv.setBackgroundResource(it!!)
            spinningReel.addView(iv)
        }

        for (i in 0 until symbolCount) {
            val iv = ImageView(context)
            val ivLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, GameConfig.SYMBOL_HEIGHT)
            iv.layoutParams = ivLayoutParams
            iv.setBackgroundResource(GameConfig.resSymbols.random())
            iv.y = (GameConfig.SYMBOL_HEIGHT * i).toFloat()
            spinningReel.addView(iv)
        }
    }

    fun stopSpin() {
        spinningReel.animate().cancel()
        spinningReel.y = 0f

        spinningReel.visibility = View.GONE
        ll.visibility = View.VISIBLE
    }
}