package com.example.slotproject

import android.os.Bundle
import android.view.MotionEvent
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

        setSpinText(!GameConfig.isAuto)

        tv_bet_value.text = GameConfig.totalBet.toString()
        reelContainer = ReelContainer(this, ll_reel_container)
        reelContainer.setOnStartSpinListener {
            GameConfig.balance -= GameConfig.totalBet
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
                Toast.makeText(applicationContext, "Action was DOWN", Toast.LENGTH_SHORT).show()
                betController.hide()
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
}