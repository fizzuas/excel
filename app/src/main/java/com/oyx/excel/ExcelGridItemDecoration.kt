package com.oyx.excel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
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
        val w = mDivider.minimumWidth
        val h = mDivider.minimumHeight

        val position = parent.getChildAdapterPosition(view)

        val lm: GridLayoutManager = parent.layoutManager as GridLayoutManager
        val spanCount = lm.spanCount
        val itemCount = lm.itemCount

//        Log.e("oyx",  "position"+position);
//        Log.e("oyx",  "spanCount"+spanCount);
//        Log.e("oyx",  "position%spanCount)"+(position%spanCount));
//        if(position==1){
//            //最后一列
//            outRect.set(0,0,0,h)
//        }
////        else if(position>itemCount-spanCount+1){
////            //最后一行
////            outRect.set(0,0,w,0)
////        }
//    else
//            outRect.set(w,h,w,h)



    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        draw(c, parent)

    }

    private fun draw(c: Canvas, parent: RecyclerView) {
        val childCount = parent.childCount
        var left: Int
        var top: Int
        var right: Int
        var bottom: Int

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val lp = child.layoutParams as RecyclerView.LayoutParams
            // 画下 横线
            left = child.left
            top = child.bottom
            right = child.right + mDivider.intrinsicWidth
            bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)

            // 画 右 竖线
            left= child.right
            top =child.top
            right = left+ mDivider.intrinsicWidth
            bottom=child.bottom
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)


        }

    }



}