package com.example.aubrey.myapplication

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class SampleView : View {
    var mViewWidth= 0
    var mViewHeight= 0
    var mViewCenterX = 0;
    var mViewCenterY = 0;
    var mMinRadio = 0;
    var mRingWidth = 0f;
    var mSelect = 0;
    var mSelectAngle = 0;
    var mMaxCircleColor = 0;
    var mMinCircleColor=0;
    lateinit var mPaint: Paint
    var mRingNormalColor =0
    var mRingAngleWidth = 0f
    var isShowSelect = false
    var mSelectRing = 0
    lateinit var mRectF :RectF


    constructor(mContext: Context) : super(mContext) {

    }

    constructor(mContext: Context, mAttributeSet: AttributeSet) : super(mContext, mAttributeSet) {


        val a = mContext.obtainStyledAttributes(mAttributeSet, R.styleable.SuperCircleView)

        mMinRadio = a.getInteger(R.styleable.SuperCircleView_min_circle_radio, 400);

        mRingWidth = a.getFloat(R.styleable.SuperCircleView_ring_width, 40.0f);

        mSelect = a.getInteger(R.styleable.SuperCircleView_select, 7);
        mSelectAngle = a.getInteger(R.styleable.SuperCircleView_selec_angle, 3);

        mMinCircleColor = a.getColor(R.styleable.SuperCircleView_circle_color, context.getResources().getColor(R.color.white));
        mMaxCircleColor = a.getColor(R.styleable.SuperCircleView_max_circle_color, context.getResources().getColor(R.color.huise2));
        mRingNormalColor = a.getColor(R.styleable.SuperCircleView_ring_normal_color, context.getResources().getColor(R.color.huise));

        isShowSelect = a.getBoolean(R.styleable.SuperCircleView_is_show_select, false);
        mSelectRing = a.getInt(R.styleable.SuperCircleView_ring_color_select, 0);
        a.recycle();
        mPaint =  Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        this.setWillNotDraw(false);
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mViewCenterX = mViewWidth / 2;
        mViewCenterY = mViewHeight / 2;
        mRectF = RectF(mViewCenterX - mMinRadio - mRingWidth / 2, mViewCenterY - mMinRadio - mRingWidth / 2, mViewCenterX + mMinRadio + mRingWidth / 2, mViewCenterY + mMinRadio + mRingWidth / 2)

        mRingAngleWidth = (360 - mSelect * mSelectAngle) / mSelect .toFloat();


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isShowSelect && mSelectRing > mSelect) {
            return;
        }
        mPaint.setColor(mMaxCircleColor);
        canvas?.drawCircle(mViewCenterX.toFloat(), mViewCenterY.toFloat(), mMinRadio + mRingWidth + 20, mPaint);
        mPaint.color=mMinCircleColor
        canvas?.drawCircle(mViewCenterX.toFloat(), mViewCenterY.toFloat(), mMinRadio.toFloat(), mPaint);
        drawNormalRing(canvas);
        drawColorRing(canvas);

    }

    private fun drawColorRing(canvas: Canvas?) {

       var ringColorPaint =  Paint(mPaint);
        ringColorPaint.setStyle(Paint.Style.STROKE);
        ringColorPaint.setStrokeWidth(mRingWidth);
        var  texts=SweepGradient(mViewCenterX.toFloat(), mViewCenterX.toFloat(), intArrayOf(Color.parseColor("#8EE484"), Color.parseColor("#97C0EF"),Color.parseColor("#8EE484")),null)
        ringColorPaint.setShader(texts);

        if (!isShowSelect) {
            canvas?.drawArc(mRectF, 270.toFloat(), mSelectRing.toFloat(), false, ringColorPaint);
            return;
        }

        if (mSelect == mSelectRing && mSelectRing != 0 && mSelect != 0) {
            canvas?.drawArc(mRectF, 270.toFloat(), 360.toFloat(), false, ringColorPaint);
        } else {
            Log.d(TAG, "${(mRingAngleWidth * mSelectRing + mSelectAngle + mSelectRing)}");
            canvas?.drawArc(mRectF, 270.toFloat(), mRingAngleWidth * mSelectRing + mSelectAngle * mSelectRing, false, ringColorPaint);
        }
        ringColorPaint.setShader(null);
        ringColorPaint.setColor(mMaxCircleColor);
        for ( i in 0..mSelectRing ){
            canvas?.drawArc(mRectF, 270 + (i * mRingAngleWidth + (i) * mSelectAngle), mSelectAngle.toFloat(), false, ringColorPaint);
        }

    }

    private fun drawNormalRing(canvas: Canvas?) {
   var ringNormalPaint = Paint(mPaint);
        ringNormalPaint.setStyle(Paint.Style.STROKE);
        ringNormalPaint.setStrokeWidth(mRingWidth);
        ringNormalPaint.setColor(mRingNormalColor);
        canvas?.drawArc(mRectF, 270.toFloat(), 360.toFloat(), false, ringNormalPaint);
        if (!isShowSelect) {
            return;
        }
        ringNormalPaint.setColor(mMaxCircleColor);
        for ( i in 0..mSelect ){
            canvas?.drawArc(mRectF, 270 + (i * mRingAngleWidth + (i) * mSelectAngle), mSelectAngle.toFloat(), false, ringNormalPaint);     canvas?.drawArc(mRectF, 270 + (i * mRingAngleWidth + (i) * mSelectAngle), mSelectAngle.toFloat(), false, ringNormalPaint);
        }

    }

}