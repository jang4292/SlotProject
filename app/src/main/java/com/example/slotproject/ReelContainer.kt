package com.example.slotproject

import android.content.Context
import android.widget.LinearLayout

class ReelContainer(context: Context, reelContainer: LinearLayout) {

    private val reels: Array<Reel?> = arrayOfNulls(GameConfig.COLUMN)
    private var spinCount = 0

    init {
        for (i in 0 until GameConfig.COLUMN) {
            val reel = Reel(context, i)
            reels[i] = reel
            reelContainer.addView(reel)
            reel.setStartSymbols(getStartSymbols())
            reel.setOnAnimationEnd(fun() {
                spinCount--
                if (spinCount === 0) {
                    if (GameConfig.isAuto) {
                        GameConfig.gameStatus = GameStatus.SPINNING
                        startSpin()
                    } else {
                        GameConfig.gameStatus = GameStatus.READY
                    }
                }
            })
        }
    }

    private fun getStartSymbols(): Array<Int?> {
        val array = arrayOfNulls<Int>(GameConfig.ROW)
        for (i in 0 until GameConfig.ROW) {
            array[i] = GameConfig.resSymbols.random()
        }
        return array
    }

    fun startSpin() {
        reels.forEach {
            spinCount++
            it?.startSpin()
        }
    }
}