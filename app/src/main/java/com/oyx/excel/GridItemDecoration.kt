package com.oyx.excel

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 *
 * @ClassName: GridItemDecoration
 * @Description:
 */
class GridItemDecoration : ItemDecoration() {
    private val mPaint = Paint()
    private fun init() {
        mPaint.color = Color.RED
    }

    override fun onDraw(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.onDraw(c, parent, state)
        val manager = parent.layoutManager
        if (manager is GridLayoutManager) {
            //我们知道表格的边框主要是由分割线和下分割线
            for (i in 0 until parent.childCount) {
                //获取子元素
                val child = parent.getChildAt(i)
                //我们首先来制作子元素右分割线，或者说纵向分割线
                setUpVerticalDivider(child, c)
                //制作横向分割线
                setUpHorizontalDivider(child, c)
            }
        } else {
            throw IllegalArgumentException("LayoutManager使用错误")
        }
    }

    private fun setUpVerticalDivider(
        child: View,
        canvas: Canvas
    ) {
        val left = child.right.toFloat()
        val top = child.top.toFloat()
        val right = left + DIVIDER_SIZE
        val bottom =
            child.bottom + DIVIDER_SIZE.toFloat()
        canvas.drawRect(left, top, right, bottom, mPaint)
    }

    private fun setUpHorizontalDivider(
        child: View,
        canvas: Canvas
    ) {
        val left = child.left.toFloat()
        val top = child.bottom.toFloat()
        val right = child.right.toFloat()
        val bottom = top + DIVIDER_SIZE
        canvas.drawRect(left, top, right, bottom, mPaint)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect[0, 0, DIVIDER_SIZE] = DIVIDER_SIZE
    }

    companion object {
        //分割线的宽度
        private const val DIVIDER_SIZE = 5
    }

    init {
        init()
    }
}