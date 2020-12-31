package com.example.slotproject

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main11.btn_spin
import kotlinx.android.synthetic.main.custom_actionbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)
        supportActionBar?.setLogo(R.mipmap.ic_launcher)
        supportActionBar?.setDisplayUseLogoEnabled(false)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val reels = arrayOf(fl_reel0, fl_reel1, fl_reel2, fl_reel3, fl_reel4)
        setSpinText(!GameConfig.isAuto)
        btn_spin.setOnClickListener {
            reels.forEach {
                if (GameConfig.gameStatus === GameStatus.READY) {
                    it.animate().translationY(fl_reel0.height.toFloat()).setDuration(2000L).start()
                } else {
                    it.animate().cancel()
                    it.y = 0f
                }
            }

            if (GameConfig.gameStatus === GameStatus.READY) {
                GameConfig.gameStatus = GameStatus.SPINNING
            } else {
                GameConfig.gameStatus = GameStatus.READY
            }
        }

        btn_spin.setOnLongClickListener {
            setSpinText(!GameConfig.isAuto)
            true
        }

    }

    private fun setSpinText(isAuto: Boolean) {
        GameConfig.isAuto = isAuto
        btn_spin.setImageResource(if (isAuto) R.drawable.button_stop else R.drawable.button_spin)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_actionbar, menu)
        return true
    }
}