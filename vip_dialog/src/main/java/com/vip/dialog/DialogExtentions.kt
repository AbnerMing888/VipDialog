package com.vip.dialog

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 *AUTHOR:AbnerMing
 *DATE:2022/11/22
 *INTRODUCE:扩展函数
 */

/**
 * AUTHOR:AbnerMing
 * INTRODUCE:Activity显示Dialog
 */
fun AppCompatActivity.showVipDialog(vipDialog: VipDialog.() -> Unit): VipDialog {
    val dialog = VipDialog.init()
    dialog.apply(vipDialog)
    setActivityDialog(this.supportFragmentManager, dialog)
    return dialog
}

/**
 * AUTHOR:AbnerMing
 * INTRODUCE:Fragment显示Dialog
 */
fun Fragment.showVipDialog(vipDialog: VipDialog.() -> Unit): VipDialog {
    val dialog = VipDialog.init()
    dialog.apply(vipDialog)
    setActivityDialog(this.childFragmentManager, dialog)
    return dialog
}

private fun setActivityDialog(manger: FragmentManager, dialog: VipDialog) {
    dialog.showDialog(manger)
}
