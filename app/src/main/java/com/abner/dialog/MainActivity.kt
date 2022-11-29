package com.abner.dialog

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment.STYLE_NO_FRAME
import androidx.recyclerview.widget.GridLayoutManager
import com.abner.dialog.databinding.*
import com.vip.dialog.showVipDialog

/**
 *AUTHOR:AbnerMing
 *DATE:2022/11/22
 *INTRODUCE:主页
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.dialog_info).setOnClickListener(this)
        findViewById<TextView>(R.id.dialog_info_bind).setOnClickListener(this)
        findViewById<TextView>(R.id.dialog_custom).setOnClickListener(this)
        findViewById<TextView>(R.id.dialog_input).setOnClickListener(this)
        findViewById<TextView>(R.id.dialog_loading).setOnClickListener(this)
        findViewById<TextView>(R.id.dialog_progress).setOnClickListener(this)
        findViewById<TextView>(R.id.dialog_ios_loading).setOnClickListener(this)
        findViewById<TextView>(R.id.dialog_bottom_list).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.dialog_info -> {
                //普通的消息提示
                showVipDialog {
                    addLayout(R.layout.layout_dialog_custom)
                    set {
                        setDialogCancelable(false)//点击空白不消失
                        val btnConfirm = findView<TextView>(R.id.dialog_button_confirm)
                        btnConfirm.setOnClickListener {
                            toast("确定")
                            dismiss()
                        }
                    }
                }
            }
            R.id.dialog_info_bind -> {
                //DataBinding的消息提示
                showVipDialog {
                    addLayout(R.layout.layout_dialog_custom)
                    bind<LayoutDialogCustomBinding> {
                        it.dialogButtonConfirm.setOnClickListener {
                            toast("确定")
                            dismiss()
                        }
                    }
                }
            }
            R.id.dialog_custom -> {
                //确认提示框
                showVipDialog {
                    addLayout(R.layout.layout_dialog_custom)
                    bind<LayoutDialogCustomBinding> {
                        it.dialogButtonCancle.visibility = View.VISIBLE
                        it.dialogButtonLine.visibility = View.VISIBLE

                        it.dialogButtonConfirm.setOnClickListener {
                            //确认
                            toast("确认")
                            dismiss()
                        }
                        it.dialogButtonCancle.setOnClickListener {
                            //取消
                            toast("取消")
                            dismiss()
                        }
                    }
                }
            }

            R.id.dialog_input -> {
                //输入框
                showVipDialog {
                    addLayout(R.layout.layout_dialog_input)
                    bind<LayoutDialogInputBinding> {
                        it.tvSubmit.setOnClickListener { view ->
                            val name = it.etName.text.toString()
                            val pass = it.etPass.text.toString()
                            if (TextUtils.isEmpty(name)) {
                                toast("请输入账号")
                                return@setOnClickListener
                            }
                            if (TextUtils.isEmpty(pass)) {
                                toast("请输入密码")
                                return@setOnClickListener
                            }
                            toast("模拟登录")
                        }
                    }
                }
            }
            R.id.dialog_loading -> {
                //普通的加载框
                showVipDialog {
                    addLayout(R.layout.layout_dialog_loading)
                }
            }
            R.id.dialog_progress -> {
                //进度框
                showVipDialog {
                    addLayout(R.layout.layout_dialog_progress)
                    bind<LayoutDialogProgressBinding> {
                        it.progressBar.max = 100//设置最大进度
                        it.progressBar.progress = 50//设置进度
                    }
                }
            }
            R.id.dialog_bottom_list -> {
                //底部列表
                showVipDialog {
                    addLayout(R.layout.layout_dialog_list)
                    bind<LayoutDialogListBinding> {
                        val adapter = DialogBottomAdapter()
                        it.dialogRecycler.layoutManager = GridLayoutManager(this.context, 4)
                        it.dialogRecycler.adapter = adapter
                        adapter.setList(getDialogList())
                        it.tvCancle.setOnClickListener {
                            //取消
                            dismiss()
                        }
                    }
                    style {
                        dialog!!.window!!.setGravity(Gravity.BOTTOM)
                        dialog!!.window!!.setWindowAnimations(R.style.DialogBaseAnimation)  //添加动画
                    }
                }
            }
            R.id.dialog_ios_loading -> {
                //仿iOS菊花加载
                showVipDialog {
                    addLayout(R.layout.layout_dialog_ios_loading)
                    style {
                        setStyle(STYLE_NO_FRAME, R.style.AppDialog)
                    }
                    bind<LayoutDialogIosLoadingBinding> {
                        val animationDrawable = it.image.background as AnimationDrawable
                        it.image.post {
                            animationDrawable.start()
                        }
                    }
                }
            }
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * AUTHOR:AbnerMing
     * INTRODUCE:获取DialogList
     */
    private fun getDialogList(): ArrayList<DialogBottomBean> {
        val itemName = resources.getStringArray(R.array.dialog_bottom_array)
        val ta = resources.obtainTypedArray(R.array.dialog_bottom_ic)
        val apply = arrayListOf<DialogBottomBean>().apply {
            for (i in itemName.indices) {
                val bean = DialogBottomBean()
                bean.name = itemName[i]
                bean.drawable =
                    ContextCompat.getDrawable(this@MainActivity, ta.getResourceId(i, 0))
                add(bean)
            }
        }
        ta.recycle()
        return apply
    }
}

