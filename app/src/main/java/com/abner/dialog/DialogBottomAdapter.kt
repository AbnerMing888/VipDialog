package com.abner.dialog

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abner.dialog.databinding.LayoutDialogItemBottomGridBinding

/**
 *AUTHOR:AbnerMing
 *DATE:2022/11/29
 *INTRODUCE:底部的弹出
 */
class DialogBottomAdapter : RecyclerView.Adapter<DialogBottomAdapter.BottomViewHolder>() {

    private var mContext: Context? = null
    private var mList = ArrayList<DialogBottomBean>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mContext = recyclerView.context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomViewHolder {
        val view = View.inflate(mContext, R.layout.layout_dialog_item_bottom_grid, null)
        return BottomViewHolder(view)
    }

    override fun onBindViewHolder(holder: BottomViewHolder, position: Int) {
        val bind = DataBindingUtil.bind<LayoutDialogItemBottomGridBinding>(holder.itemView)
        bind?.setVariable(BR.dialog, mList[position])
        bind?.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setList(list: ArrayList<DialogBottomBean>){
        mList=list
        notifyDataSetChanged()
    }

    class BottomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


}