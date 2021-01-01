package com.example.slotproject

import android.content.Context
import android.widget.LinearLayout
import java.util.*

class ReelContainer(context: Context, reelContainer: LinearLayout) {

    private val reels: Array<Reel?> = arrayOfNulls(GameConfig.COLUMN)

    init {

        for (i in 0 until GameConfig.COLUMN) {
            val reel = Reel(context, i)
            reels[i] = reel
            reelContainer.addView(reel)
            reel.setStartSymbols(getStartSymbols())
        }
    }

    private fun <T> List<T>.random(): T {
        val random = Random().nextInt((size))
        return get(random)
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
            it?.startSpin()
        }
    }

    fun stopSpin() {
        reels.forEach {
            it?.stopSpin()
        }
    }
}