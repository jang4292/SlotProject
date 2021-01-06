package com.example.slotproject

import android.animation.ValueAnimator
import android.content.Context
import android.widget.TextView


class CustomActionBar(val context: Context, private val tvBalance: TextView, balance: Int) {
    private var currentBalance = balance
    private val sharedPreferences =
        context.getSharedPreferences(GameConfig.KEY_DEFAULT_BALANCE, Context.MODE_PRIVATE)    // test 이름의 기본모드 설정
    private val editor = sharedPreferences.edit() //sharedPreferences를 제어할 editor를 선언

    init {
        tvBalance.text = currentBalance.toString()
    }

    fun updateBalance(balance: Int) {
        val prevBalance = currentBalance
        currentBalance = balance
        with(ValueAnimator.ofInt(prevBalance, currentBalance)) {
            duration = 1500//Duration is in milliseconds
            addUpdateListener { animation -> tvBalance.text = animation.animatedValue.toString() }
            start()
        }
        with(editor) {
            putInt(GameConfig.KEY_BALANCE, GameConfig.balance) // key,value 형식으로 저장
            commit()    //최종 커밋. 커밋을 해야 저장이 된다.
        }
    }
}