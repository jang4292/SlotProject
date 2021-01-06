package unused;

import android.content.Context;
import android.graphics.*;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.slotproject.R;

public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "CustomSurfaceView";
    private boolean isPlaying = false;
    private int xPos = 0;

    private Animation animation;


    private int index;

//    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSurfaceView(Context context, int index) {
        super(context);
//        this.setZOrderOnTop(true);
//        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);
//        setEGLConfigChooser(8,8,8,8,16,0);
//        getHolder().setFormat(PixelFormat.RGBA_8888);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        getHolder().addCallback(this);


        this.index = index;
//        animation = new Animation().start();
        animation = new Animation();
        animation.start();
    }

    public void init() {

    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void start() {
        xPos = 0;
        isPlaying = true;
//        animation.start();

    }

    public void stop() {
        isPlaying = false;
//        animation.stop();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
//        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.dr);
//        float scale = (float) background.getHeight() / (float) getHeight();
//        int newWidth = Math.round(background.getWidth() / scale);
//        int newHeight = Math.round(background.getHeight() / scale);
//        scaled = Bitmap.createScaledBitmap(background, newWidth, newHeight, true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "OnDraw ");
    }

    private class Animation extends Thread {
        int counter = 0;

        @Override
        public void run() {

            long last_updated_time = 0;
            long delay = 500;
            int symbolIds[] = {
//                    R.drawable.slot_02,
//                    R.drawable.slot_03,
//                    R.drawable.slot_04,
//                    R.drawable.slot_05,
//                    R.drawable.slot_06,
//                    R.drawable.slot_07
                    R.drawable.symbol_10,
                    R.drawable.symbol_a,
                    R.drawable.symbol_cap,
                    R.drawable.symbol_coin,
                    R.drawable.symbol_fish,
                    R.drawable.symbol_frog,
                    R.drawable.symbol_j,
                    R.drawable.symbol_k,
                    R.drawable.symbol_q,
                    R.drawable.symbol_turtle,
                    R.drawable.symbol_scatter,
            };


            while (true) {
                if (isPlaying) {
                    long current_time = System.currentTimeMillis();
                    if (current_time > last_updated_time + delay) {
                        if (counter >= symbolIds.length) {
                            counter = 0;
                        }

//
////                        draw(symbolIds[counter], symbolIds[counter + 1]);
                        draw(symbolIds, counter);
//                    try {
//                        sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } finally {
//
//                    }
                        last_updated_time = current_time;
                        counter++;

                    }
                }
            }
        }

        //        private void draw(int resId) {

        private void draw(int[] list, int index1) {
            SurfaceHolder holder = getHolder();
            Canvas canvas = holder.lockCanvas();

            if (canvas != null) {
//                canvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);

                canvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);
//                canvas.drawColor(Color.WHITE);
//                Log.d(TAG, "draw index : " + index);
//                canvas.drawColor(index % 2 == 0 ? Color.WHITE : Color.BLACK);


                Paint paint = new Paint();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), list[index], options);
                canvas.drawBitmap(bitmap, 0, 0, paint);





                /*Paint paint = new Paint();

//                xPos += 100;
//                canvas.translate(xPos, 0);
                xPos += 50;
                canvas.translate(0, xPos);


                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
//                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), resId, options);
//                canvas.drawBitmap(bitmap, 100, 100, paint);
//                holder.unlockCanvasAndPost(canvas);


//                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), list[index], options);
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), list[0], options);
                canvas.drawBitmap(bitmap, 100, 100, paint);
//                holder.unlockCanvasAndPost(canvas);

//                if (++index >= list.length) {
//                    index = 0;
//                }

//                bitmap = BitmapFactory.decodeResource(getContext().getResources(), list[index], options);
                bitmap = BitmapFactory.decodeResource(getContext().getResources(), list[1], options);
//                canvas.drawBitmap(bitmap, 100 + bitmap.getWidth(), 100, paint);
                canvas.drawBitmap(bitmap, 100, 100 + (bitmap.getHeight() * 1), paint);

                bitmap = BitmapFactory.decodeResource(getContext().getResources(), list[2], options);
//                canvas.drawBitmap(bitmap, 100 + bitmap.getWidth(), 100, paint);
                canvas.drawBitmap(bitmap, 100, 100 + (bitmap.getHeight() * 2), paint);

                bitmap = BitmapFactory.decodeResource(getContext().getResources(), list[3], options);
//                canvas.drawBitmap(bitmap, 100 + bitmap.getWidth(), 100, paint);
                canvas.drawBitmap(bitmap, 100, 100 + (bitmap.getHeight() * 3), paint);

                bitmap = BitmapFactory.decodeResource(getContext().getResources(), list[4], options);
//                canvas.drawBitmap(bitmap, 100 + bitmap.getWidth(), 100, paint);
                canvas.drawBitmap(bitmap, 100, 100 + (bitmap.getHeight() * 4), paint);

*/
                holder.unlockCanvasAndPost(canvas);


            }
        }
    }
}
