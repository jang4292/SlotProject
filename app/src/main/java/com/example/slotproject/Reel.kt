package com.example.slotproject

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout

class Reel(context: Context, index: Int) : FrameLayout(context) {
    private val ll: LinearLayout
    private val spinningReel: FrameLayout
    private val symbolCount: Int = GameConfig.DEFAULT_SYMBOL_COUNT + GameConfig.ADD_SYMBOL_COUNT * index
    private val imageViews: Array<ImageView?> = arrayOfNulls(GameConfig.ROW)
    private lateinit var animationEnd: () -> Unit

    val startSymbolResIds: Array<Int?> = arrayOfNulls(GameConfig.ROW)

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
                        iv.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        if (GameConfig.SYMBOL_WIDTH > 0 && GameConfig.SYMBOL_HEIGHT > 0) {
                            return
                        }
                        GameConfig.SYMBOL_WIDTH = iv.width
                        GameConfig.SYMBOL_HEIGHT = iv.height
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
        ObjectAnimator.ofFloat(spinningReel, "translationY", 0f).apply {
            duration = (symbolCount * GameConfig.DURATION_SPIN_TIME * 0.1f).toLong()
            interpolator = AccelerateDecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    setStartSymbols(startSymbolResIds)
                    spinningReel.visibility = View.GONE
                    ll.visibility = View.VISIBLE

                    spinningReel.removeAllViews()
                    animationEnd()
                }
            })
            start()
        }
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
            val name = GameConfig.resSymbols.random()
            iv.setBackgroundResource(name)
            iv.y = (GameConfig.SYMBOL_HEIGHT * i).toFloat()
            spinningReel.addView(iv)

            if (i < GameConfig.ROW) {
                startSymbolResIds[i] = name
            }
        }
    }

    fun setOnAnimationEnd(function: () -> Unit) {
        animationEnd = function
    }
}