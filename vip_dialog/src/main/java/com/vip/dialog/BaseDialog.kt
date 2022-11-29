package com.vip.dialog

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

/**
 *AUTHOR:AbnerMing
 *DATE:2022/11/22
 *INTRODUCE:Dialog父类
 */
abstract class BaseDialog : DialogFragment() {

    private val mViewSparseArray = SparseArray<View>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (getLayoutId() != 0) {
            initStyle()
            val view: View = inflater.inflate(getLayoutId(), container, false)
            initView(view)
            return view
        }
        return container
    }

    private var mView: View? = null
    private fun initView(view: View) {
        mView = view
    }


    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:获取View视图
     */
    fun <V> findView(id: Int): View {
        var view = mViewSparseArray[id]
        if (view == null) {
            view = mView?.findViewById(id)
            mViewSparseArray.put(id, view)
        }
        return view
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:获取当前View视图
     */
    fun getDialogView(): View {
        return mView!!
    }


    override fun onResume() {
        //去除左右边距 和背景 然后需要自己来定义
        val win = dialog!!.window!!
        dialog!!.window!!.decorView.setPadding(0, 0, 0, 0)
        val lp = win.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        win.attributes = lp
        win.setBackgroundDrawable(null)
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }


    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:初始化数据
     */
    abstract fun initData()

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:初始化样式
     */
    abstract fun initStyle()

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:传递的视图
     */
    abstract fun getLayoutId(): Int


    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:点击空白不消失
     */
    fun setDialogCancelable(isCancelable: Boolean) {
        dialog?.setCancelable(isCancelable)
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:显示dialog
     */
    fun showDialog(manger: FragmentManager) {
        val transaction = manger.beginTransaction()
        val dialog = manger.findFragmentByTag("dialog")
        if (dialog != null) {
            transaction.remove(dialog)
        }
        transaction.addToBackStack(null)
        show(transaction, "dialog")
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:隐藏dialog
     */
    fun hintDialog(manger: FragmentManager) {
        val prev = manger.findFragmentByTag("dialog")
        if (prev != null) {
            (prev as DialogFragment).dismiss()
        }
    }
}