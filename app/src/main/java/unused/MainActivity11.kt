package unused

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.slotproject.GameConfig
import com.example.slotproject.GameStatus
import com.example.slotproject.R
import kotlinx.android.synthetic.main.activity_main11.*
import kotlinx.android.synthetic.main.custom_actionbar.*


class MainActivity11 : AppCompatActivity() {

    private val TAG: String = "AppDebug"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main11)

        setSupportActionBar(my_toolbar)
        supportActionBar?.setLogo(R.mipmap.ic_launcher)
        supportActionBar?.setDisplayUseLogoEnabled(false)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setSpinText(!GameConfig.isAuto)


//        val customSurfaceViews =
//            arrayOf(customSurfaceView0, customSurfaceView1, customSurfaceView2, customSurfaceView3, customSurfaceView4)

//        val customSurfaceViews =
//            arrayOf(sv_reel0, sv_reel1, sv_reel2, sv_reel3, sv_reel4)
////        val customSurface = arrayOfNulls<CustomSurfaceView2>(5)
//        val customSurface = mutableListOf<CustomSurfaceView2>();
//        for (i in 0 until 5) {
////            val customSurfaceView = CustomSurfaceView2(customSurfaceViews[i])
//
////            customSurface[i] = CustomSurfaceView2(this, customSurfaceViews[i])
//            customSurface.add(CustomSurfaceView2(this, customSurfaceViews[i]))
//        }

//        val layoutParams =
//            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
//        layoutParams.weight = 1f
//
//        val customSurfaceView0 = CustomSurfaceView(this, 0)
//
////        customSurfaceView.setZOrderOnTop(true)
////        customSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0)
////        customSurfaceView.getHolder().setFormat(PixelFormat.RGBA_8888)
////        customSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT)
//        ll_reel_container.addView(customSurfaceView0, layoutParams)
//
//        val customSurfaceView1 = CustomSurfaceView(this, 1)
//        ll_reel_container.addView(customSurfaceView1, layoutParams)
//
//        val customSurfaceView2 = CustomSurfaceView(this, 2)
//        ll_reel_container.addView(customSurfaceView2, layoutParams)
//        val customSurfaceView3 = CustomSurfaceView(this, 3)
//        ll_reel_container.addView(customSurfaceView3, layoutParams)
//        val customSurfaceView4 = CustomSurfaceView(this, 4)
//        ll_reel_container.addView(customSurfaceView4, layoutParams)

//        val customSurfaceViews =
//            arrayOf(customSurfaceView0, customSurfaceView1, customSurfaceView2, customSurfaceView3, customSurfaceView4)


        btn_spin.setOnClickListener {

            /*if (customSurfaceView.isPlaying) {

                customSurfaceView.stop()
            } else {
                customSurfaceView.start()
            }*/

//            customSurfaceViews.forEach { it.start() }
//            customSurface.forEach(function() {
//
//            }


//            )
//
//            { index, customSurfaceView2 -> customSurfaceView2.start() }

//            customSurface.forEach { it.start() }

            if (GameConfig.gameStatus === GameStatus.READY) {
//                customSurface.forEach { it.start() }
            } else {
//                customSurface.forEach { it.stop() }
            }


            Toast.makeText(this@MainActivity11, "Text + " + GameConfig.gameStatus, Toast.LENGTH_SHORT).show()
            //            if (GameConfig.gameStatus === GameStatus.SPINNING) {
//                return@setOnClickListener
//            }
//
//            GameConfig.gameStatus = GameStatus.SPINNING
//            Handler().postDelayed({
//                GameConfig.gameStatus = GameStatus.READY
//            }, 500)

//            MaterialDialog(this).show {
//                //                customView(R.layout.search_search)
//                showFilterDialog()
//            }

//            showFilterDialog()

        }

        btn_spin.setOnLongClickListener {
            setSpinText(!GameConfig.isAuto)
            true
        }
    }


    /*fun showFilterDialog() {

        val dialog = MaterialDialog(this)
            .noAutoDismiss()
            .customView(R.layout.layout_filter)

        // set initial preferences
//        val filter = preferences.getString(getString(R.string.key_filter), getString(R.string.filter_date))
//        if(filter.equals(getString(R.string.filter_date))){
//            dialog.findViewById<RadioGroup>(R.id.filter_group).check(R.id.filter_date)
//        }
//        else{
//            dialog.findViewById<RadioGroup>(R.id.filter_group).check(R.id.filter_author)
//        }
//
//        val order = preferences.getString(getString(R.string.key_order), getString(R.string.order_asc))
//        if(order.equals(getString(R.string.order_asc))){
//            dialog.findViewById<RadioGroup>(R.id.order_group).check(R.id.order_asc)
//        }
//        else{
//            dialog.findViewById<RadioGroup>(R.id.order_group).check(R.id.order_desc)
//        }

        // get new preferences
//        dialog.findViewById<TextView>(R.id.positive_button).setOnClickListener {
//            Log.d(TAG, "FilterDialog: apply filter.")
//
////            val selectedFilter = dialog.getCustomView().findViewById<RadioButton>(
////                dialog.getCustomView().findViewById<RadioGroup>(R.id.filter_group).checkedRadioButtonId
////            )
////            val selectedOrder = dialog.getCustomView().findViewById<RadioButton>(
////                dialog.getCustomView().findViewById<RadioGroup>(R.id.order_group).checkedRadioButtonId
////            )
//
////            editor.putString("filter", selectedFilter.text.toString())
////            editor.apply()
////            editor.putString("order", selectedOrder.text.toString())
////            editor.apply()
//
//            dialog.dismiss()
//        }

//        dialog.findViewById<TextView>(R.id.negative_button).setOnClickListener {
//            Log.d(TAG, "FilterDialog: cancelling filter.")
//            dialog.dismiss()
//        }

        dialog.show()
    }*/

    private fun setSpinText(isAuto: Boolean) {
        GameConfig.isAuto = isAuto
//        button_a.setBackgroundResource(R.drawable.after_press)
        btn_spin.setImageResource(if (isAuto) R.drawable.button_stop else R.drawable.button_spin)

//        btn_spin.background = if (isAuto) else "SPIN"
//        btn_spin.background = if (isAuto) else "SPIN"
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_actionbar, menu)
        return true
    }

}
