package com.vip.dialog

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import java.lang.Exception

/**
 *AUTHOR:AbnerMing
 *DATE:2022/11/22
 *INTRODUCE:ViewDataBinding形式
 */
abstract class BaseBindingDialog<VB : ViewDataBinding> : BaseDialog() {

    lateinit var mBinding: VB
    override fun initData() {
        try {
            mBinding = DataBindingUtil.bind(getDialogView())!!
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            initVMData()
        }

    }

    inline fun <reified T> setPendingBindings(variableId: Int) {
        val t = T::class.java.newInstance()
        mBinding.setVariable(variableId, t)
        mBinding.executePendingBindings()
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:初始化数据
     */
    abstract fun initVMData()
}