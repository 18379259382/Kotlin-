package com.example.aubrey.myapplication


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import java.util.jar.Attributes

class Cric :View{
    val mPaint :Paint=Paint()
    constructor(mContext: Context) : super(mContext) {
        val context = mContext
    }
    constructor(mContext: Context, mAttributeSet: AttributeSet) : super(mContext, mAttributeSet) {
        initPaint()
        val context = mContext
    }
    private fun initPaint() {
        mPaint.isAntiAlias=true
        mPaint.color=Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
       val  radiso= Math.min(width,height)/2
        canvas?.drawCircle(width/2.toFloat(),height/2.toFloat(),radiso.toFloat(),mPaint)
    }

}