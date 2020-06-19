package com.oyx.excel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 *@Author oyx
 *@date 2020/6/19 10:34
 *@description
 */
class ExcelGridItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    val mDivider: Drawable = context.getDrawable(R.drawable.divider)!!

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
//        super.getItemOffsets(outRect, view, parent, state)
        val w = mDivider.minimumWidth
        val h = mDivider.minimumHeight

        //1行1列          l   t   r   b
        //1行2..N列       0   t   r   b
        //2..N行 1列      l   0   r   b
        //2..n行2..n列    0   0   r   b
        val position = parent.getChildAdapterPosition(view)

        val lm: GridLayoutManager = parent.layoutManager as GridLayoutManager
        val spanCount = lm.spanCount
        val itemCount = lm.itemCount


        if (position == 0) {
            Log.e("oyx", "1行1列    " + position)
            outRect.set(w, h, w, h)
        } else if (position in 1 until spanCount) {
            Log.e("oyx", "1行2..N列 " + position)
            outRect.set(0, h, w, h+5)
        } else if (position % spanCount == 0) {
            Log.e("oyx", "2..N行 1列  " + position)
            outRect.set(0, 0, w, h)

        }
        else{
            Log.e("oyx", "2..n行2..n列  " + position)
            outRect.set(0, 0, w, h)
        }

    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        drawH(c, parent)

    }

    private fun drawH(c: Canvas, parent: RecyclerView) {
        val childCount = parent.childCount
        var left: Int
        var top: Int
        var right: Int
        var bottom: Int

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val lp = child.layoutParams as RecyclerView.LayoutParams
            // 画下 横线
            left = child.left - lp.leftMargin
            top = child.bottom + lp.bottomMargin
            right = child.right + lp.rightMargin
            bottom = top + mDivider.minimumHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)

            //画上横线
            left = child.left -lp.leftMargin
            top =child.top -lp.topMargin - mDivider.minimumHeight
            right =child.right + lp.rightMargin
            bottom= child.top - lp.topMargin

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)


            //画 左竖线
            left= child.left - lp.leftMargin - mDivider.minimumWidth
            top =child.top - lp.topMargin
            right = left+ mDivider.intrinsicWidth
            bottom=child.bottom +lp.marginEnd
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)

            // 画 右 竖线
            left= child.right + lp.leftMargin
            top =child.top - lp.topMargin
            right = left+ mDivider.minimumWidth
            bottom=child.bottom +lp.marginEnd
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)


        }

    }



}