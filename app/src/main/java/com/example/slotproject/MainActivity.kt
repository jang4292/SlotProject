package com.example.slotproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_actionbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var reelContainer: ReelContainer
    private lateinit var betController: BetController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)
        supportActionBar?.setLogo(R.mipmap.ic_launcher)
        supportActionBar?.setDisplayUseLogoEnabled(false)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val sharedPreferences =
            getSharedPreferences(GameConfig.KEY_DEFAULT_BALANCE, Context.MODE_PRIVATE)

        GameConfig.balance = sharedPreferences.getInt(GameConfig.KEY_BALANCE, -1)
        if (GameConfig.balance < 0) {
            GameConfig.balance = GameConfig.DEFAULT_BALANCE
        }
        tv_balance.text = GameConfig.balance.toString()
        setSpinText(!GameConfig.isAuto)

        tv_bet_value.text = GameConfig.totalBet.toString()
        reelContainer = ReelContainer(this, ll_reel_container) {
            val winBalance = GameConfig.totalBet * 2
            GameConfig.balance += winBalance
            val sharedPreferences =
                getSharedPreferences(GameConfig.KEY_DEFAULT_BALANCE, Context.MODE_PRIVATE)    // test 이름의 기본모드 설정
            val editor = sharedPreferences.edit() //sharedPreferences를 제어할 editor를 선언
            editor.putInt(GameConfig.KEY_BALANCE, GameConfig.balance) // key,value 형식으로 저장
            editor.commit()    //최종 커밋. 커밋을 해야 저장이 된다.
            tv_win_balance.text = winBalance.toString()
            tv_balance.text = GameConfig.balance.toString()
            fl_win_balance.visibility = View.VISIBLE
        }

        reelContainer.setOnCompleteSpinListener {
            GameConfig.gameStatus = GameStatus.READY
            if (GameConfig.isAuto) {
                btn_spin.callOnClick()
            }
        }
        reelContainer.setOnStartSpinListener {
            GameConfig.balance -= GameConfig.totalBet
            val sharedPreferences =
                getSharedPreferences(GameConfig.KEY_DEFAULT_BALANCE, Context.MODE_PRIVATE)    // test 이름의 기본모드 설정
            val editor = sharedPreferences.edit() //sharedPreferences를 제어할 editor를 선언
            editor.putInt(GameConfig.KEY_BALANCE, GameConfig.balance) // key,value 형식으로 저장
            editor.commit()    //최종 커밋. 커밋을 해야 저장이 된다.

            tv_balance.text = GameConfig.balance.toString()
        }
        betController = BetController(this, ll_bet_list)
        betController.setOnClickButton {
            tv_bet_value.text = GameConfig.totalBet.toString()
        }

        cl_bet_btn.setOnClickListener {
            betController.show()
        }

        btn_spin.setOnClickListener {
            if (GameConfig.gameStatus === GameStatus.READY) {
                GameConfig.gameStatus = GameStatus.SPINNING
                betController.hide()
                fl_win_balance.visibility = View.GONE
                reelContainer.startSpin()
            }
        }

        btn_spin.setOnLongClickListener {
            setSpinText(!GameConfig.isAuto)
            btn_spin.callOnClick()
            true
        }
    }

    private fun setSpinText(isAuto: Boolean) {
        GameConfig.isAuto = isAuto
        btn_spin.setBackgroundResource(if (isAuto) R.drawable.button_stop else R.drawable.button_spin)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (MotionEventCompat.getActionMasked(event)) {
            MotionEvent.ACTION_DOWN -> {
                betController.hide()
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
}