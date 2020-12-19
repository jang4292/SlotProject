package com.example.slotproject

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_actionbar.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)
        supportActionBar?.setLogo(R.mipmap.ic_launcher)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setSpinText(GameConfig.isAuto)
        btn_spin.setOnClickListener {
            if (GameConfig.gameStatus === GameStatus.SPINNING) {
                return@setOnClickListener
            }

            GameConfig.gameStatus = GameStatus.SPINNING
            Handler().postDelayed({
                GameConfig.gameStatus = GameStatus.READY
            }, 500)
        }

        btn_spin.setOnLongClickListener {
            setSpinText(GameConfig.isAuto)
            true
        }
    }

    fun setSpinText(isAuto: Boolean) {
        GameConfig.isAuto = !GameConfig.isAuto
        btn_spin.text = if (isAuto) "STOP" else "SPIN"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_actionbar, menu)
        return true
    }

}
