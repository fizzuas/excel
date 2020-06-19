package com.oyx.excel

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class ModelAdapter(models: MutableList<Model>) : BaseQuickAdapter<Model,BaseViewHolder> (R.layout.item,data = models) {
    override fun convert(holder: BaseViewHolder, item: Model) {
        holder.setText(R.id.tv,item.stt)



    }

}
