package com.example.slotproject

import android.content.Context
import android.widget.LinearLayout
import android.widget.Toast

class ReelContainer(private val context: Context, reelContainer: LinearLayout, onUpdateWinAnimation: () -> Unit) {
    private lateinit var onStartSpinListener: () -> Unit
    private lateinit var onCompleteSpinListener: () -> Unit
    private val reels: Array<Reel?> = arrayOfNulls(GameConfig.COLUMN)
    private val playingReels = mutableListOf<Reel>()

    init {
        for (i in 0 until GameConfig.COLUMN) {
            val reel = Reel(context, i)
            reels[i] = reel
            reelContainer.addView(reel)
            reel.setStartSymbols(getStartSymbols())
            reel.setOnAnimationEnd(fun(_reel) {
                val reelIndex = playingReels.indexOf(_reel)
                playingReels.removeAt(reelIndex)
                if (playingReels.size === 0) {
                    val rand = (0..100).random()
                    if (rand < 50) {
                        onUpdateWinAnimation()
                    }
                    onCompleteSpinListener()
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

    fun setOnStartSpinListener(function: () -> Unit) {
        onStartSpinListener = function
    }

    fun setOnCompleteSpinListener(function: () -> Unit) {
        onCompleteSpinListener = function
    }

    fun startSpin() {
        if (GameConfig.balance < GameConfig.totalBet) {
            Toast.makeText(context, "You don't have balance.\nTry to recharge", Toast.LENGTH_SHORT).show()
            return
        }
        onStartSpinListener()
        reels.forEach {
            playingReels.add(it!!)
            it?.startSpin()
        }
    }
}