package com.czh.studyproject.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.czh.studyproject.R
import com.czh.studyproject.util.dp2px
import com.czh.studyproject.util.rv.DataSetChangeObserver
import kotlin.math.roundToInt

/**
 * @Description: 圆形指示器
 * @Author: czh
 * @CreateDate: 2022/2/22 23:19
 */
class RoundIndicator : View {

    private var mSelectorColor: Int = 0
    private var mUnSelectorColor: Int = 0
    private var mRadius: Float = 0f
    private var mSpacing: Float = 0f

    private var mCount: Int = 0
    private var mPos: Int = 0
    private lateinit var mSelectorPaint: Paint
    private lateinit var mUnSelectorPaint: Paint
    private val dp3 = dp2px(3)

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RoundIndicator)
        mSelectorColor = ta.getColor(R.styleable.RoundIndicator_selectorColor, Color.DKGRAY)
        mUnSelectorColor = ta.getColor(R.styleable.RoundIndicator_unSelectorColor, Color.LTGRAY)
        mRadius = ta.getDimension(R.styleable.RoundIndicator_radius, dp3)
        mSpacing = ta.getDimension(R.styleable.RoundIndicator_spacing, dp3)
        ta.recycle()
        init()
    }

    private fun init() {
        mSelectorPaint = Paint()
        mSelectorPaint.color = mSelectorColor
        mSelectorPaint.isAntiAlias = true
        mSelectorPaint.style = Paint.Style.FILL

        mUnSelectorPaint = Paint()
        mUnSelectorPaint.color = mUnSelectorColor
        mUnSelectorPaint.isAntiAlias = true
        mUnSelectorPaint.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        val width = if (widthSpecMode == MeasureSpec.EXACTLY) {
            widthSpecSize
        } else {
            if (mCount == 0) {
                0
            } else {
                (2 * mRadius * mCount + mSpacing * (mCount - 1)).roundToInt()
            }
        }
        val height = if (heightSpecMode == MeasureSpec.EXACTLY) {
            heightSpecSize
        } else {
            2 * mRadius.roundToInt()
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (mCount == 0) {
            return
        }
        canvas?.apply {
            val start = (width - 2 * mRadius * mCount - mSpacing * (mCount - 1)) / 2f + mRadius
            repeat(mCount) {
                drawCircle(
                    start + (2 * mRadius + mSpacing) * it,
                    height / 2f,
                    mRadius,
                    if (it == mPos) mSelectorPaint else mUnSelectorPaint
                )
            }
        }
    }

    /**
     * 这里只是做比较简单的绑定，中途如果重新设置adapter时，需要重新绑定
     */
    fun bindVp(vp: ViewPager2) {
        checkNotNull(vp.adapter) { "请先为viewpager2设置适配器" }.apply {
            registerAdapterDataObserver(object : DataSetChangeObserver() {
                override fun onChanged() {
                    mCount = vp.adapter?.itemCount ?: 0
                    mPos = vp.currentItem
                    requestLayout()
                }
            })
            vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    mPos = position
                    requestLayout()
                }
            })
        }
    }
}