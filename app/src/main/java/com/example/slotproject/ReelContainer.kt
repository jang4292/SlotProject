package com.example.slotproject

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class ReelContainer(private val context: Context, reelContainer: LinearLayout, tvBalance: TextView) {
    private lateinit var onStartSpinListener: () -> Unit
    private val reels: Array<Reel?> = arrayOfNulls(GameConfig.COLUMN)
    private var spinCount = -1

    init {
        for (i in 0 until GameConfig.COLUMN) {
            val reel = Reel(context, i)
            reels[i] = reel
            reelContainer.addView(reel)
            reel.setStartSymbols(getStartSymbols())
            reel.setOnAnimationEnd(fun() {
                spinCount--
                if (spinCount === 0) {
                    val rand = (0..100).random()
                    if (rand < 50) {
                        GameConfig.balance += (GameConfig.totalBet * 2)
                        val sharedPreferences =
                            context.getSharedPreferences(
                                GameConfig.KEY_DEFAULT_BALANCE,
                                Context.MODE_PRIVATE
                            )    // test 이름의 기본모드 설정
                        val editor = sharedPreferences.edit() //sharedPreferences를 제어할 editor를 선언
                        editor.putInt(GameConfig.KEY_BALANCE, GameConfig.balance) // key,value 형식으로 저장
                        editor.commit()    //최종 커밋. 커밋을 해야 저장이 된다.
                        tvBalance.text = GameConfig.balance.toString()
                    }

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

    fun setOnStartSpinListener(function: () -> Unit) {
        onStartSpinListener = function
    }

    fun startSpin() {
        if (GameConfig.balance < GameConfig.totalBet) {
            Toast.makeText(context, "You don't have balance.\nTry to recharge", Toast.LENGTH_SHORT).show()
            return
        }
        onStartSpinListener()
        reels.forEach {
            spinCount++
            it?.startSpin()
        }
    }
}