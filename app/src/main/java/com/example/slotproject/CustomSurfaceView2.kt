package com.example.slotproject

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView

class CustomSurfaceView2(private val context: Context, private val surfaceView: SurfaceView) : SurfaceHolder.Callback {


    //    val isPlaying = false
//    private val holder = surfaceView.holder

    //    private val defaultPositionY = 0f
//    private var currentPositionY = 0f
    private lateinit var animation: Animation

    init {
        surfaceView.setZOrderOnTop(true)
//        val holder = surfaceView.holder
        surfaceView.holder.setFormat(PixelFormat.TRANSLUCENT)
        surfaceView.holder.addCallback(this)
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
        Log.d("Test", "surfaceChanged")
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        Log.d("Test", "surfaceDestroyed")
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
        Log.d("Test", "surfaceCreated")

        animation = Animation(context, surfaceView)
        animation.start()
    }


    fun stop() {
        GameConfig.gameStatus = GameStatus.READY
    }

    fun start() {
        animation.setReel()
//        currentPositionY = defaultPositionY
        GameConfig.gameStatus = GameStatus.SPINNING
    }

    //    class Animation(private val context: Context, private val holder: SurfaceHolder) : Thread() {
    class Animation(private val context: Context, private val surfaceView: SurfaceView) : Thread() {

        //        private val currentPositionY: Float
        private var last_updated_time: Long = 0
//        private val delay: Long = 100
        private var currentPositionY = 0f


        override fun run() {


            while (true) {
                if (GameConfig.gameStatus === GameStatus.SPINNING) {

//                    val current_time = System.currentTimeMillis()
//                    if (current_time > last_updated_time + delay) {
                        draw()
//                        last_updated_time = current_time
//                    }
                }
            }

        }

        fun setReel() {
            currentPositionY = 0f
        }

        private fun draw() {
            val holder = surfaceView.holder
            val canvas = holder.lockCanvas()
            if (canvas != null) {
                currentPositionY += 50f
                canvas.translate(0f, currentPositionY)

                canvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR)
                val paint = Paint()
                val options: BitmapFactory.Options = BitmapFactory.Options()
                options.inSampleSize = 4


//                val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.symbol_10, options)
//                canvas!!.drawBitmap(bitmap, 0f, 0f, paint)

                for (i in 0 until 10) {
                    createReel(canvas, R.drawable.symbol_10, i, options, paint)
                }

                holder.unlockCanvasAndPost(canvas)
            }
        }

        private fun createReel(canvas: Canvas, resId: Int, index: Int, options: BitmapFactory.Options, paint: Paint) {
            val bitmap = BitmapFactory.decodeResource(context.resources, resId, options)
            val yPos: Float = (bitmap.height * index).toFloat()
            canvas.drawBitmap(bitmap, 0f, yPos, paint)

        }
    }
}
