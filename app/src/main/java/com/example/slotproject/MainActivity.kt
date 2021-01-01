package com.example.slotproject

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_actionbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var reelContainer: ReelContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)
        supportActionBar?.setLogo(R.mipmap.ic_launcher)
        supportActionBar?.setDisplayUseLogoEnabled(false)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setSpinText(!GameConfig.isAuto)

        reelContainer = ReelContainer(this, ll_reel_container)

        btn_spin.setOnClickListener {
            if (GameConfig.gameStatus === GameStatus.READY) {
                GameConfig.gameStatus = GameStatus.SPINNING
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
        btn_spin.setImageResource(if (isAuto) R.drawable.button_stop else R.drawable.button_spin)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_actionbar, menu)
        return true
    }
}