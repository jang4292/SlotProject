package com.example.slotproject

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
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

        val settingPopupDialog = SettingPopupDialog(this)
        val customActionbar = CustomActionBar(this, my_toolbar, GameConfig.balance)
        customActionbar.setOnClickMenuListener {
            settingPopupDialog.show()
        }
        setSpinText(!GameConfig.isAuto)

        tv_bet_value.text = GameConfig.totalBet.toString()
        reelContainer = ReelContainer(this, ll_reel_container) {
            val winBalance = GameConfig.totalBet * 2
            GameConfig.balance += winBalance
            tv_win_balance.text = winBalance.toString()
            customActionbar.updateBalance(GameConfig.balance)
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
            customActionbar.updateBalance(GameConfig.balance)
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