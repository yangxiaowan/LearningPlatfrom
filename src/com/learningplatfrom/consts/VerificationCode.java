package com.learningplatfrom.consts;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public final class VerificationCode {
	private static final char[] CHARS= {  
        '0','1','2', '3', '4', '5', '6', '7', '8', '9',  
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm',   
        'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',  
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',   
        'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'  
    };  
	private static int[] mRandomColorArr={Color.rgb(255, 0, 0),Color.rgb(0, 255, 0),Color.rgb(0, 0, 255),
		Color.rgb(255,255, 0),Color.rgb(255, 0,255),Color.rgb(0, 255, 255),Color.rgb(255, 255, 255)};
	private static VerificationCode mBitmapCode = null;
	private Random random=new Random();
	private static final int DEFAULT_CODE_LENGTH=4;
	private static final int DEFAULT_FONT_SIZE=30;
	private static final int DEFAULT_LINE_NUMBER=5;
	private static final int BASE_PADDING_LEFT=20;
	private static final int RANGE_PADDING_LEFT=5;
	private static final int BASE_PADDING_TOP = 25;
	private static final int RANGE_PADDING_TOP = 5;
	private static final int DEFAULT_WIDTH = 140;
	private static final int DEFAULT_HEIGHT = 50;
	private int mCodeLength = DEFAULT_CODE_LENGTH;
	private int mFontSize = DEFAULT_FONT_SIZE;
	private int mBitmapWidth = DEFAULT_WIDTH;
	private int mBitmapHeight = DEFAULT_HEIGHT;
	private int mBasePaddingLeft = BASE_PADDING_LEFT;
	private int mRangePaddingLeft = RANGE_PADDING_LEFT;
	private int mBasePaddingTop = BASE_PADDING_TOP;
	private int mRangePaddingTop = RANGE_PADDING_TOP;
	private int mLineNumber = DEFAULT_LINE_NUMBER;
	private int mPaddingLeft = 0;
	private int mPaddingTop = 0;
	private String mRandomCode = null;//
	public static VerificationCode getInstance(){
		mBitmapCode = new VerificationCode();
		return mBitmapCode;
	}
	public String getRandomCode(){
		return mRandomCode;
	}
	public Bitmap createCodeBitmap(){
		Bitmap mBitmap = Bitmap.createBitmap(mBitmapWidth,mBitmapHeight,Config.ARGB_8888);
		Canvas mCanvas = new Canvas(mBitmap);
		Paint mPaint = new Paint();
		RectF rect = new RectF();
		rect.left = 0;
		rect.right = mBitmapWidth;
		rect.top = mBitmapHeight;
		rect.bottom = 0;
		mCanvas.drawRoundRect(rect, 10, 10, mPaint);
		mRandomCode = CreateCodeSting();  //获得随机的验证码字符串.
		mCanvas.drawColor(Color.GRAY);
		mPaint.setTextSize(mFontSize);
		for(int i=0;i<mRandomCode.length();i++){
			randomCodeTextStyle(mPaint);
			randomPadding();
			mCanvas.drawText(mRandomCode.charAt(i)+"", mPaddingLeft, mPaddingTop, mPaint);
		}
		for(int j=0;j<mLineNumber;j++){
			drawRandomLine(mCanvas,mPaint);
		}
		mCanvas.save(Canvas.ALL_SAVE_FLAG);
		mCanvas.restore();
		return mBitmap;
	}
	private void drawRandomLine(Canvas canvas, Paint paint) {
		int color = getRandomColor();  
        int startX = random.nextInt(mBitmapWidth);  
        int startY = random.nextInt(mBitmapHeight);  
        int stopX = random.nextInt(mBitmapWidth);  
        int stopY = random.nextInt(mBitmapHeight);  
        paint.setStrokeWidth(1);  
        paint.setColor(color);  
        canvas.drawLine(startX, startY, stopX, stopY, paint); 
	}
	public String CreateCodeSting(){
		StringBuilder buffer = new StringBuilder();
		for(int i=0;i<mCodeLength;i++){
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}
	private void randomPadding() {  
		mPaddingLeft += mBasePaddingLeft + random.nextInt(mRangePaddingLeft);  
		mPaddingTop = mBasePaddingTop + random.nextInt(mRangePaddingTop);  
    }  
	public int getRandomColor(){
		return mRandomColorArr[random.nextInt(7)];
	}
	public void randomCodeTextStyle(Paint paint){
		int color = getRandomColor();
		paint.setColor(color);
		paint.setFakeBoldText(random.nextBoolean());
		float skewRate = random.nextInt(11)/50+0.1f;
		skewRate = random.nextBoolean()?skewRate:-skewRate;
		paint.setTextSkewX(skewRate);
	}
	
}
