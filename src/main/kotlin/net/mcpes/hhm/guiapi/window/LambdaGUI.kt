package net.mcpes.hhm.guiapi.window

import cn.nukkit.Player

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/26
 * version 1.0
 *
 * Lambda表达式型GUI,相比EventGUI 使用方法更加简便
 * 要想使用此类型GUI,请设置{buttonClickedListener 或 submittedClickedListener} 与 closedClickedListener
 * 设置完毕后在玩家返回数据后自动会调用 ,Very NB
 */
interface LambdaGUI {
    /**
     * 在玩家点击按钮提交表单后调用.
     * */
    fun callClicked(player: Player, data: String)

    /**
     * 在玩家关闭窗口而没有点击按钮提交表单后调用.
     * */
    fun callClosed(player: Player)
}