package n.electricdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by huangchuanyi on 11/3/16.
 * <p>
 * 地鼠
 */

public class NewMouseView extends View {

    private Context mContext;

    public NewMouseView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public NewMouseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public NewMouseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private boolean isStart = true;
    private CustomPaint mCustomPaint;
    private float mX;
    private float mY;

    //背景；
    private Bitmap bitmapBg;
    private Bitmap bitmapHole;
    private Bitmap bitmapCloud;

    private int bitmapBgWidth;
    private int bitmapBgHeight;
    private int bitmapHoleWidth;
    private int bitmapHoleHeight;
    private int bitmapCloudWidth;
    private int bitmapCloudHeight;


    //地鼠
    private Paint mousePaint;
    private Bitmap bitmap0;
    private Bitmap bitmap1;
    private Bitmap bitmap;
    private Bitmap bitmapSel;
    private int mBitmapWidth0;
    private int mBitmapHeight0;
    private int mBitmapWidth1;
    private int mBitmapHeight1;
    private int mBitmapWidth;
    private int mBitmapHeight;

    private DisplayMetrics mDisplayMetrics;
    private int mWidth;
    private int mHeight;

    //打击的个数；
    private int mHitCount = 0;
    private Paint mHitPaint;

    //到计时 60秒
    private int timeCount = 60 * 100;
    private Paint mTimePaint;

    //出场的个数。8秒内1，16秒内3，24秒内5，32秒内6
    private int speedCount = 0;

    //1
    private int mMouseCount1 = 1000;
    private int mMouseSelCount1 = 1000;
    private boolean isMouseShow1 = false;
    private boolean isElectric1 = false;
    private int mPointX1 = 0;
    private int mPointY1 = 0;

    //2
    private int mMouseCount2 = 1000;
    private int mMouseSelCount2 = 1000;
    private boolean isMouseShow2 = false;
    private boolean isElectric2 = false;
    private int mPointX2 = 0;
    private int mPointY2 = 0;

    //3
    private int mMouseCount3 = 1000;
    private int mMouseSelCount3 = 1000;
    private boolean isMouseShow3 = false;
    private boolean isElectric3 = false;
    private int mPointX3 = 0;
    private int mPointY3 = 0;

    //4
    private int mMouseCount4 = 1000;
    private int mMouseSelCount4 = 1000;
    private boolean isMouseShow4 = false;
    private boolean isElectric4 = false;
    private int mPointX4 = 0;
    private int mPointY4 = 0;

    //5
    private int mMouseCount5 = 1000;
    private int mMouseSelCount5 = 1000;
    private boolean isMouseShow5 = false;
    private boolean isElectric5 = false;
    private int mPointX5 = 0;
    private int mPointY5 = 0;

    //6
    private int mMouseCount6 = 1000;
    private int mMouseSelCount6 = 1000;
    private boolean isMouseShow6 = false;
    private boolean isElectric6 = false;
    private int mPointX6 = 0;
    private int mPointY6 = 0;

    //7
    private int mMouseCount7 = 1000;
    private int mMouseSelCount7 = 1000;
    private boolean isMouseShow7 = false;
    private boolean isElectric7 = false;
    private int mPointX7 = 0;
    private int mPointY7 = 0;

    private void init() {
        //地鼠
        this.mCustomPaint = new CustomPaint();
        bitmapBg = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);
        bitmapHole = BitmapFactory.decodeResource(getResources(), R.mipmap.hole);
        bitmapCloud = BitmapFactory.decodeResource(getResources(), R.mipmap.cloud);

        bitmap0 = BitmapFactory.decodeResource(getResources(), R.mipmap.mole_d0);
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.mole_d1);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.app_normal_icon);
        bitmapSel = BitmapFactory.decodeResource(getResources(), R.mipmap.app_sel_icon);
        mousePaint = new Paint();
        mousePaint.setColor(Color.RED);

        bitmapBgWidth = bitmapBg.getWidth();
        bitmapBgHeight = bitmapBg.getHeight();
        bitmapHoleWidth = bitmapHole.getWidth();
        bitmapHoleHeight = bitmapHole.getHeight();
        bitmapCloudWidth = bitmapCloud.getWidth();
        bitmapCloudHeight = bitmapCloud.getHeight();

        mBitmapWidth0 = bitmap0.getWidth();
        mBitmapHeight0 = bitmap0.getHeight();
        mBitmapWidth1 = bitmap1.getWidth();
        mBitmapHeight1 = bitmap1.getHeight();
        mBitmapWidth = bitmap.getWidth();
        mBitmapHeight = bitmap.getHeight();

        mDisplayMetrics = mContext.getResources().getDisplayMetrics();
        mWidth = mDisplayMetrics.widthPixels;
        mHeight = mDisplayMetrics.heightPixels;

        //计数
        mHitPaint = new Paint();
        mHitPaint.setColor(Color.BLACK);
        mHitPaint.setTextSize(dip2px(18));

        //时间
        mTimePaint = new Paint();
        mTimePaint.setColor(Color.RED);
        mTimePaint.setTextSize(dip2px(12));

        mPointX1 = mWidth / 8;
        mPointY1 = mHeight * 5 / 8;

        mPointX2 = mWidth * 3 / 8;
        mPointY2 = mHeight * 5 / 8;

        mPointX3 = mWidth * 5 / 8;
        mPointY3 = mHeight * 5 / 8;

        mPointX4 = mWidth * 7 / 8;
        mPointY4 = mHeight * 5 / 8;

        mPointX5 = mWidth / 4;
        mPointY5 = mHeight * 7 / 8;

        mPointX6 = mWidth * 2 / 4;
        mPointY6 = mHeight * 7 / 8;

        mPointX7 = mWidth * 3 / 4;
        mPointY7 = mHeight * 7 / 8;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg1) {
        mX = arg1.getX();
        mY = arg1.getY();
        switch (arg1.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isMouseShow1
                        && (mPointX1 - mBitmapWidth / 2) <= mX
                        && mX <= (mPointX1 + mBitmapWidth / 2)
                        && (mPointY1 - mBitmapHeight / 2) <= mY
                        && mY <= (mPointY1 + mBitmapHeight / 2)) {
                    isElectric1 = true;
                    isMouseShow1 = false;
                    mMouseSelCount1 = 0;
                    mHitCount++;
                } else if (isMouseShow2
                        && (mPointX2 - mBitmapWidth / 2) <= mX
                        && mX <= (mPointX2 + mBitmapWidth / 2)
                        && (mPointY2 - mBitmapHeight / 2) <= mY
                        && mY <= (mPointY2 + mBitmapHeight / 2)) {
                    isElectric2 = true;
                    isMouseShow2 = false;
                    mMouseSelCount2 = 0;
                    mHitCount++;
                } else if (isMouseShow3
                        && (mPointX3 - mBitmapWidth / 2) <= mX
                        && mX <= (mPointX3 + mBitmapWidth / 2)
                        && (mPointY3 - mBitmapHeight / 2) <= mY
                        && mY <= (mPointY3 + mBitmapHeight / 2)) {
                    isElectric3 = true;
                    isMouseShow3 = false;
                    mMouseSelCount3 = 0;
                    mHitCount++;
                } else if (isMouseShow4
                        && (mPointX4 - mBitmapWidth / 2) <= mX
                        && mX <= (mPointX4 + mBitmapWidth / 2)
                        && (mPointY4 - mBitmapHeight / 2) <= mY
                        && mY <= (mPointY4 + mBitmapHeight / 2)) {
                    isElectric4 = true;
                    isMouseShow4 = false;
                    mMouseSelCount4 = 0;
                    mHitCount++;
                } else if (isMouseShow5
                        && (mPointX5 - mBitmapWidth / 2) <= mX
                        && mX <= (mPointX5 + mBitmapWidth / 2)
                        && (mPointY5 - mBitmapHeight) <= mY) {
                    isElectric5 = true;
                    isMouseShow5 = false;
                    mMouseSelCount5 = 0;
                    mHitCount++;
                } else if (isMouseShow6
                        && (mPointX6 - mBitmapWidth / 2) <= mX
                        && mX <= (mPointX6 + mBitmapWidth / 2)
                        && (mPointY6 - mBitmapHeight) <= mY) {
                    isElectric6 = true;
                    isMouseShow6 = false;
                    mMouseSelCount6 = 0;
                    mHitCount++;
                } else if (isMouseShow7
                        && (mPointX7 - mBitmapWidth / 2) <= mX
                        && mX <= (mPointX7 + mBitmapWidth / 2)
                        && (mPointY7 - mBitmapHeight) <= mY) {
                    isElectric7 = true;
                    isMouseShow7 = false;
                    mMouseSelCount7 = 0;
                    mHitCount++;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            default:
                break;
        }
        return super.onTouchEvent(arg1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(0xFFC8FBFE);//背景
        drawBg(canvas);//背景的草地
        drawMouse(canvas); //地鼠控制
        if (isStart) {
            postInvalidateDelayed(10); //刷新
        }
    }

    private void drawBg(Canvas canvas) {
        timeCount--;
        if (timeCount <= 0) {
            isStart = false;
            onFinishClickListener.finish(mHitCount);
        }
        canvas.drawText("游戏结束剩余时间：" + (timeCount / 100) + "秒", 20, 60, mTimePaint);
        canvas.drawText("score:" + mHitCount, mWidth / 2 - 20, 60, mHitPaint);
        canvas.drawBitmap(bitmapBg, mWidth / 2 - bitmapBgWidth / 2, mHeight - bitmapBgHeight, mousePaint);


        canvas.drawBitmap(bitmapHole, mPointX1 - bitmapHoleWidth / 2, mPointY1 - bitmapHoleHeight / 2, mousePaint);
        canvas.drawBitmap(bitmapHole, mPointX2 - bitmapHoleWidth / 2, mPointY2 - bitmapHoleHeight / 2, mousePaint);
        canvas.drawBitmap(bitmapHole, mPointX3 - bitmapHoleWidth / 2, mPointY3 - bitmapHoleHeight / 2, mousePaint);
        canvas.drawBitmap(bitmapHole, mPointX4 - bitmapHoleWidth / 2, mPointY4 - bitmapHoleHeight / 2, mousePaint);
        canvas.drawBitmap(bitmapHole, mPointX5 - bitmapHoleWidth / 2, mPointY5 - bitmapHoleHeight, mousePaint);
        canvas.drawBitmap(bitmapHole, mPointX6 - bitmapHoleWidth / 2, mPointY6 - bitmapHoleHeight, mousePaint);
        canvas.drawBitmap(bitmapHole, mPointX7 - bitmapHoleWidth / 2, mPointY7 - bitmapHoleHeight, mousePaint);
    }

    private void drawMouse(Canvas canvas) {
        int[] ints = new int[]{};
        boolean isIn = false;
        if (speedCount % 160 == 0 && speedCount <= 800) { //随机1个
            ints = randomArray(0, 6, 1);
            isIn = true;
        } else if (speedCount % 160 == 0 && speedCount <= 1600) {//随机3个
            ints = randomArray(0, 6, 3);
            isIn = true;
        } else if (speedCount % 160 == 0 && speedCount <= 2400) {//随机5个
            ints = randomArray(0, 6, 5);
            isIn = true;
        } else  if(speedCount > 2400){
            if (mMouseCount1 > 180) {
                mMouseCount1 = 0;
            }
            if (mMouseCount2 > 180) {
                mMouseCount2 = 0;
            }
            if (mMouseCount3 > 180) {
                mMouseCount3 = 0;
            }
            if (mMouseCount4 > 180) {
                mMouseCount4 = 0;
            }
            if (mMouseCount5 > 180) {
                mMouseCount5 = 0;
            }
            if (mMouseCount6 > 180) {
                mMouseCount6 = 0;
            }
            if (mMouseCount7 > 180) {
                mMouseCount7 = 0;
            }
        }
        if (isIn && ints.length > 0) {
            for (int i : ints) {
                if (i == 0) {
                    if (mMouseCount1 > 160) {
                        mMouseCount1 = 0;
                    }
                    if (110 <= mMouseCount2 && mMouseCount2 <= 999) {
                        mMouseCount2 = 1000;
                    }
                    if (110 <= mMouseCount3 && mMouseCount3 <= 999) {
                        mMouseCount3 = 1000;
                    }
                    if (110 <= mMouseCount4 && mMouseCount4 <= 999) {
                        mMouseCount3 = 1000;
                    }
                    if (110 <= mMouseCount5 && mMouseCount5 <= 999) {
                        mMouseCount3 = 1000;
                    }
                    if (110 <= mMouseCount6 && mMouseCount6 <= 999) {
                        mMouseCount3 = 1000;
                    }
                    if (110 <= mMouseCount7 && mMouseCount7 <= 999) {
                        mMouseCount3 = 1000;
                    }

                } else if (i == 1) {
                    if (110 <= mMouseCount1 && mMouseCount1 <= 999) {
                        mMouseCount1 = 1000;
                    }
                    if (mMouseCount2 > 160) {
                        mMouseCount2 = 0;
                    }
                    if (110 <= mMouseCount3 && mMouseCount3 <= 999) {
                        mMouseCount3 = 1000;
                    }
                    if (110 <= mMouseCount4 && mMouseCount4 <= 999) {
                        mMouseCount4 = 1000;
                    }
                    if (110 <= mMouseCount5 && mMouseCount5 <= 999) {
                        mMouseCount5 = 1000;
                    }
                    if (110 <= mMouseCount6 && mMouseCount6 <= 999) {
                        mMouseCount6 = 1000;
                    }
                    if (110 <= mMouseCount7 && mMouseCount7 <= 999) {
                        mMouseCount7 = 1000;
                    }

                } else if (i == 2) {
                    if (110 <= mMouseCount1 && mMouseCount1 <= 999) {
                        mMouseCount1 = 1000;
                    }
                    if (110 <= mMouseCount2 && mMouseCount2 <= 999) {
                        mMouseCount2 = 1000;
                    }
                    if (mMouseCount3 > 160) {
                        mMouseCount3 = 0;
                    }
                    if (110 <= mMouseCount4 && mMouseCount4 <= 999) {
                        mMouseCount4 = 1000;
                    }
                    if (110 <= mMouseCount5 && mMouseCount5 <= 999) {
                        mMouseCount5 = 1000;
                    }
                    if (110 <= mMouseCount6 && mMouseCount6 <= 999) {
                        mMouseCount6 = 1000;
                    }
                    if (110 <= mMouseCount7 && mMouseCount7 <= 999) {
                        mMouseCount7 = 1000;
                    }
                } else if (i == 3) {
                    if (110 <= mMouseCount1 && mMouseCount1 <= 999) {
                        mMouseCount1 = 1000;
                    }
                    if (110 <= mMouseCount2 && mMouseCount2 <= 999) {
                        mMouseCount2 = 1000;
                    }
                    if (110 <= mMouseCount3 && mMouseCount3 <= 999) {
                        mMouseCount3 = 1000;
                    }
                    if (mMouseCount4 > 160) {
                        mMouseCount4 = 0;
                    }
                    if (110 <= mMouseCount5 && mMouseCount5 <= 999) {
                        mMouseCount5 = 1000;
                    }
                    if (110 <= mMouseCount6 && mMouseCount6 <= 999) {
                        mMouseCount6 = 1000;
                    }
                    if (110 <= mMouseCount7 && mMouseCount7 <= 999) {
                        mMouseCount7 = 1000;
                    }
                } else if (i == 4) {
                    if (110 <= mMouseCount1 && mMouseCount1 <= 999) {
                        mMouseCount1 = 1000;
                    }
                    if (110 <= mMouseCount2 && mMouseCount2 <= 999) {
                        mMouseCount2 = 1000;
                    }
                    if (110 <= mMouseCount3 && mMouseCount3 <= 999) {
                        mMouseCount3 = 1000;
                    }
                    if (110 <= mMouseCount4 && mMouseCount4 <= 999) {
                        mMouseCount4 = 1000;
                    }
                    if (mMouseCount5 > 160) {
                        mMouseCount5 = 0;
                    }
                    if (110 <= mMouseCount6 && mMouseCount6 <= 999) {
                        mMouseCount6 = 1000;
                    }
                    if (110 <= mMouseCount7 && mMouseCount7 <= 999) {
                        mMouseCount7 = 1000;
                    }
                } else if (i == 5) {
                    if (110 <= mMouseCount1 && mMouseCount1 <= 999) {
                        mMouseCount1 = 1000;
                    }
                    if (110 <= mMouseCount2 && mMouseCount2 <= 999) {
                        mMouseCount2 = 1000;
                    }
                    if (110 <= mMouseCount3 && mMouseCount3 <= 999) {
                        mMouseCount3 = 1000;
                    }
                    if (110 <= mMouseCount4 && mMouseCount4 <= 999) {
                        mMouseCount4 = 1000;
                    }
                    if (110 <= mMouseCount5 && mMouseCount5 <= 999) {
                        mMouseCount5 = 1000;
                    }
                    if (mMouseCount6 > 160) {
                        mMouseCount6 = 0;
                    }
                    if (110 <= mMouseCount7 && mMouseCount7 <= 999) {
                        mMouseCount7 = 1000;
                    }
                } else if (i == 6) {
                    if (110 <= mMouseCount1 && mMouseCount1 <= 999) {
                        mMouseCount1 = 1000;
                    }
                    if (110 <= mMouseCount2 && mMouseCount2 <= 999) {
                        mMouseCount2 = 1000;
                    }
                    if (110 <= mMouseCount3 && mMouseCount3 <= 999) {
                        mMouseCount3 = 1000;
                    }
                    if (110 <= mMouseCount4 && mMouseCount4 <= 999) {
                        mMouseCount4 = 1000;
                    }
                    if (110 <= mMouseCount5 && mMouseCount5 <= 999) {
                        mMouseCount5 = 1000;
                    }
                    if (110 <= mMouseCount6 && mMouseCount6 <= 999) {
                        mMouseCount6 = 1000;
                    }
                    if (mMouseCount7 > 160) {
                        mMouseCount7 = 0;
                    }
                }
            }
        }

        status1(canvas, isElectric1 ? 1 : 0, mPointX1, mPointY1);
        status2(canvas, isElectric2 ? 1 : 0, mPointX2, mPointY2);
        status3(canvas, isElectric3 ? 1 : 0, mPointX3, mPointY3);
        status4(canvas, isElectric4 ? 1 : 0, mPointX4, mPointY4);
        status5(canvas, isElectric5 ? 1 : 0, mPointX5, mPointY5);
        status6(canvas, isElectric6 ? 1 : 0, mPointX6, mPointY6);
        status7(canvas, isElectric7 ? 1 : 0, mPointX7, mPointY7);

        speedCount++;
    }

    // 画线
    public void drawLine(Canvas canvas, float x1, float y1, float x2, float y2) {
        mCustomPaint.drawLightning(x1, y1, x2, y2, new Random().nextInt(40), canvas);
        mCustomPaint.drawLightningBold(x2, y2, x2, y2, new Random().nextInt(40), canvas);
        mCustomPaint.drawLightningBold(x2, y2, x2, y2, new Random().nextInt(40), canvas);
    }

    //0、正常；1、击中；
    private void status1(Canvas canvas, int status, int x, int y) {

        switch (status) {
            case 0:
                int mm = mMouseCount1;
                if (mm <= 999 && mm > 180) {
                    mMouseCount1 = 0;
                }

                if (mm < 2) {
                    isMouseShow1 = true;
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0 / 2, mousePaint);
                } else if (mm < 4) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1 / 2, mousePaint);
                } else if (mm < 106) {
                    canvas.drawBitmap(bitmap, x - mBitmapWidth / 2, y - mBitmapHeight / 2, mousePaint);
                } else if (mm < 108) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1 / 2, mousePaint);
                } else if (mm < 110) {
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0 / 2, mousePaint);
                } else {
                    isMouseShow1 = false;
                }
                break;
            case 1:
                if (mMouseSelCount1 < 30) {
                    canvas.drawBitmap(bitmapSel, x - mBitmapWidth / 2, y - mBitmapHeight / 2, mousePaint);

                    drawLine(canvas, x, bitmapCloudHeight, x, y);
                    canvas.drawBitmap(bitmapCloud, x - bitmapCloudWidth / 2, bitmapCloudHeight * 2 / 3, mousePaint);
                } else {
                    mMouseCount1 = 111;
                    isElectric1 = false;
                }
                break;
            default:
                break;

        }

        mMouseCount1++;
        mMouseSelCount1++;
    }

    //0、正常；1、击中；
    private void status2(Canvas canvas, int status, int x, int y) {
        switch (status) {
            case 0:
                int mm = mMouseCount2;
                if (mm <= 999 && mm > 180) {
                    mMouseCount2 = 0;
                }

                if (mm < 2) {
                    isMouseShow2 = true;
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0 / 2, mousePaint);
                } else if (mm < 4) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1 / 2, mousePaint);
                } else if (mm < 106) {
                    canvas.drawBitmap(bitmap, x - mBitmapWidth / 2, y - mBitmapHeight / 2, mousePaint);
                } else if (mm < 108) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1 / 2, mousePaint);
                } else if (mm < 110) {
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0 / 2, mousePaint);
                } else {
                    isMouseShow2 = false;
                }

                break;
            case 1:
                if (mMouseSelCount2 < 20) {
                    canvas.drawBitmap(bitmapSel, x - mBitmapWidth / 2, y - mBitmapHeight / 2, mousePaint);
                    drawLine(canvas, x, bitmapCloudHeight, x, y);
                    canvas.drawBitmap(bitmapCloud, x - bitmapCloudWidth / 2, bitmapCloudHeight * 2 / 3, mousePaint);
                } else {
                    mMouseCount2 = 111;
                    isElectric2 = false;
                }
                break;
            default:
                break;

        }

        mMouseCount2++;
        mMouseSelCount2++;
    }

    //0、正常；1、击中；
    private void status3(Canvas canvas, int status, int x, int y) {
        switch (status) {
            case 0:
                int mm = mMouseCount3;
                if (mm <= 999 && mm > 180) {
                    mMouseCount3 = 0;
                }

                if (mm < 2) {
                    isMouseShow3 = true;
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0 / 2, mousePaint);
                } else if (mm < 4) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1 / 2, mousePaint);
                } else if (mm < 106) {
                    canvas.drawBitmap(bitmap, x - mBitmapWidth / 2, y - mBitmapHeight / 2, mousePaint);
                } else if (mm < 108) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1 / 2, mousePaint);
                } else if (mm < 110) {
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0 / 2, mousePaint);
                } else {
                    isMouseShow3 = false;
                }

                break;
            case 1:
                if (mMouseSelCount3 < 20) {
                    canvas.drawBitmap(bitmapSel, x - mBitmapWidth / 2, y - mBitmapHeight / 2, mousePaint);
                    drawLine(canvas, x, bitmapCloudHeight, x, y);
                    canvas.drawBitmap(bitmapCloud, x - bitmapCloudWidth / 2, bitmapCloudHeight * 2 / 3, mousePaint);
                } else {
                    mMouseCount3 = 111;
                    isElectric3 = false;
                }
                break;
            default:
                break;

        }

        mMouseCount3++;
        mMouseSelCount3++;
    }

    //0、正常；1、击中；
    private void status4(Canvas canvas, int status, int x, int y) {
        switch (status) {
            case 0:

                int mm = mMouseCount4;
                if (mm <= 999 && mm > 180) {
                    mMouseCount4 = 0;
                }

                if (mm < 2) {
                    isMouseShow4 = true;
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0 / 2, mousePaint);
                } else if (mm < 4) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1 / 2, mousePaint);
                } else if (mm < 106) {
                    canvas.drawBitmap(bitmap, x - mBitmapWidth / 2, y - mBitmapHeight / 2, mousePaint);
                } else if (mm < 108) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1 / 2, mousePaint);
                } else if (mm < 110) {
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0 / 2, mousePaint);
                } else {
                    isMouseShow4 = false;
                }

                break;
            case 1:
                if (mMouseSelCount4 < 20) {
                    canvas.drawBitmap(bitmapSel, x - mBitmapWidth / 2, y - mBitmapHeight / 2, mousePaint);

                    drawLine(canvas, x, bitmapCloudHeight, x, y);
                    canvas.drawBitmap(bitmapCloud, x - bitmapCloudWidth / 2, bitmapCloudHeight * 2 / 3, mousePaint);
                } else {
                    mMouseCount4 = 111;
                    isElectric4 = false;
                }
                break;
            default:
                break;

        }

        mMouseCount4++;
        mMouseSelCount4++;
    }

    //0、正常；1、击中；
    private void status5(Canvas canvas, int status, int x, int y) {
        switch (status) {
            case 0:

                int mm = mMouseCount5;
                if (mm <= 999 && mm > 180) {
                    mMouseCount5 = 0;
                }

                if (mm < 2) {
                    isMouseShow5 = true;
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0, mousePaint);
                } else if (mm < 4) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1, mousePaint);
                } else if (mm < 106) {
                    canvas.drawBitmap(bitmap, x - mBitmapWidth / 2, y - mBitmapHeight, mousePaint);
                } else if (mm < 108) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1, mousePaint);
                } else if (mm < 110) {
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0, mousePaint);
                } else {
                    isMouseShow5 = false;
                }

                break;
            case 1:
                if (mMouseSelCount5 < 20) {
                    canvas.drawBitmap(bitmapSel, x - mBitmapWidth / 2, y - mBitmapHeight, mousePaint);
                    drawLine(canvas, x, bitmapCloudHeight, x, y);
                    canvas.drawBitmap(bitmapCloud, x - bitmapCloudWidth / 2, bitmapCloudHeight * 2 / 3, mousePaint);
                } else {
                    mMouseCount5 = 111;
                    isElectric5 = false;
                }
                break;
            default:
                break;

        }

        mMouseCount5++;
        mMouseSelCount5++;
    }

    //0、正常；1、击中；
    private void status6(Canvas canvas, int status, int x, int y) {
        switch (status) {
            case 0:
                int mm = mMouseCount6;
                if (mm <= 999 && mm > 180) {
                    mMouseCount6 = 0;
                }

                if (mm < 2) {
                    isMouseShow6 = true;
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0, mousePaint);
                } else if (mm < 4) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1, mousePaint);
                } else if (mm < 106) {
                    canvas.drawBitmap(bitmap, x - mBitmapWidth / 2, y - mBitmapHeight, mousePaint);
                } else if (mm < 108) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1, mousePaint);
                } else if (mm < 110) {
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0, mousePaint);
                } else {
                    isMouseShow6 = false;
                }

                break;
            case 1:
                if (mMouseSelCount6 < 20) {
                    canvas.drawBitmap(bitmapSel, x - mBitmapWidth / 2, y - mBitmapHeight, mousePaint);
                    drawLine(canvas, x, bitmapCloudHeight, x, y);
                    canvas.drawBitmap(bitmapCloud, x - bitmapCloudWidth / 2, bitmapCloudHeight * 2 / 3, mousePaint);
                } else {
                    mMouseCount6 = 111;
                    isElectric6 = false;
                }
                break;
            default:
                break;

        }

        mMouseCount6++;
        mMouseSelCount6++;
    }

    //0、正常；1、击中；
    private void status7(Canvas canvas, int status, int x, int y) {
        switch (status) {
            case 0:
                int mm = mMouseCount7;
                if (mm <= 999 && mm > 180) {
                    mMouseCount7 = 0;
                }

                if (mm < 2) {
                    isMouseShow7 = true;
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0, mousePaint);
                } else if (mm < 4) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1, mousePaint);
                } else if (mm < 106) {
                    canvas.drawBitmap(bitmap, x - mBitmapWidth / 2, y - mBitmapHeight, mousePaint);
                } else if (mm < 108) {
                    canvas.drawBitmap(bitmap1, x - mBitmapWidth1 / 2, y - mBitmapHeight1, mousePaint);
                } else if (mm < 110) {
                    canvas.drawBitmap(bitmap0, x - mBitmapWidth0 / 2, y - mBitmapHeight0, mousePaint);
                } else {
                    isMouseShow7 = false;
                }

                break;
            case 1:
                if (mMouseSelCount7 < 20) {
                    canvas.drawBitmap(bitmapSel, x - mBitmapWidth / 2, y - mBitmapHeight, mousePaint);
                    drawLine(canvas, x, bitmapCloudHeight, x, y);
                    canvas.drawBitmap(bitmapCloud, x - bitmapCloudWidth / 2, bitmapCloudHeight * 2 / 3, mousePaint);
                } else {
                    mMouseCount7 = 111;
                    isElectric7 = false;
                }
                break;
            default:
                break;

        }
        mMouseCount7++;
        mMouseSelCount7++;
    }

    public void startAgainGame() {
        isStart = true;
        timeCount = 60 * 100;
        mHitCount = 0;
        speedCount = 0;

        mMouseCount1 = 1000;
        mMouseSelCount1 = 1000;
        isMouseShow1 = false;
        isElectric1 = false;

        mMouseCount2 = 1000;
        mMouseSelCount2 = 1000;
        isMouseShow2 = false;
        isElectric2 = false;

        mMouseCount3 = 1000;
        mMouseSelCount3 = 1000;
        isMouseShow3 = false;
        isElectric3 = false;

        mMouseCount4 = 1000;
        mMouseSelCount4 = 1000;
        isMouseShow4 = false;
        isElectric4 = false;

        mMouseCount5 = 1000;
        mMouseSelCount5 = 1000;
        isMouseShow5 = false;
        isElectric5 = false;

        mMouseCount6 = 1000;
        mMouseSelCount6 = 1000;
        isMouseShow6 = false;
        isElectric6 = false;

        mMouseCount7 = 1000;
        mMouseSelCount7 = 1000;
        isMouseShow7 = false;
        isElectric7 = false;

        invalidate();
    }

    public void startGame() {
        isStart = true;
        invalidate();
    }

    public void stopGame() {
        isStart = false;
    }

    private OnFinishClickListener onFinishClickListener;

    interface OnFinishClickListener {
        void finish(int count);
    }

    public void setOnFinishClickListener(OnFinishClickListener onFinishClickListener) {
        this.onFinishClickListener = onFinishClickListener;
    }

    public int px2dip(int i) {
        float f = mDisplayMetrics.density;
        return (int) (((double) i - 0.5D) / (double) f);
    }

    public int dip2px(int i) {
        return (int) (0.5D + (double) (mDisplayMetrics.density * (float) i));
    }

    /**
     * 随机指定范围内N个不重复的数
     * 在初始化的无重复待选数组中随机产生一个数放入结果中，
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
     * 然后从len-2里随机产生下一个随机数，如此类推
     *
     * @param max 指定范围最大值
     * @param min 指定范围最小值
     * @param n   随机数个数
     * @return int[] 随机数结果集
     */
    public static int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;

        if (max < min || n > len) {
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }


}
