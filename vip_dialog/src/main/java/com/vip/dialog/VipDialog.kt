package com.vip.dialog

import androidx.databinding.ViewDataBinding

/**
 *AUTHOR:AbnerMing
 *DATE:2022/11/22
 *INTRODUCE:实例化功能
 */
class VipDialog : BaseBindingDialog<ViewDataBinding>() {

    companion object {
        fun init(): VipDialog {
            return VipDialog()
        }
    }

    private var mLayoutId = 0

    override fun initVMData() {
        mOnDialogDataCallbackListener?.dataCallback()
    }

    override fun initStyle() {
        mOnStyleCallBackListener?.styleCallback()
    }

    override fun getLayoutId(): Int {
        return mLayoutId
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:获取ViewDataBinding
     */
    fun <VB : ViewDataBinding> getDataBinding(): VB {
        return mBinding as VB
    }


    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:设置layout
     * @param mLayoutId xml布局
     */
    fun addLayout(mLayoutId: Int): VipDialog {
        this.mLayoutId = mLayoutId
        return this
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:初始化数据
     */
    fun <VB : ViewDataBinding> bind(block: (bind: VB) -> Unit): VipDialog {
        setDataCallBackListener(object : OnDialogDataCallbackListener {
            override fun dataCallback() {
                block.invoke(getDataBinding())
            }
        })
        return this
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:初始化数据
     */
    fun set(block: () -> Unit): VipDialog {
        setDataCallBackListener(object : OnDialogDataCallbackListener {
            override fun dataCallback() {
                block.invoke()
            }
        })
        return this
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:设置样式
     */
    fun style(style: () -> Unit): VipDialog {
        setStyleCallBackListener(object : OnStyleCallBackListener {
            override fun styleCallback() {
                style.invoke()
            }
        })
        return this
    }

    private var mOnDialogDataCallbackListener: OnDialogDataCallbackListener? = null
    private fun setDataCallBackListener(mOnDialogDataCallbackListener: OnDialogDataCallbackListener) {
        this.mOnDialogDataCallbackListener = mOnDialogDataCallbackListener
    }

    private var mOnStyleCallBackListener: OnStyleCallBackListener? = null
    private fun setStyleCallBackListener(mOnStyleCallBackListener: OnStyleCallBackListener) {
        this.mOnStyleCallBackListener = mOnStyleCallBackListener
    }

}