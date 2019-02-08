package net.mcpes.hhm.guiapi.window.lambda

import cn.nukkit.Player
import cn.nukkit.form.element.ElementButtonImageData
import cn.nukkit.form.window.FormWindowSimple
import net.mcpes.hhm.guiapi.element.AdvancedButton
import net.mcpes.hhm.guiapi.window.ButtonGUI
import net.mcpes.hhm.guiapi.window.LambdaGUI
import java.util.function.BiConsumer
import java.util.function.Consumer

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/26
 * version 1.0
 */
open class LambdaButtonGUI(id: String, title: String, content: String) : ButtonGUI(ProcessMode.LAMBDA, id, title, content), LambdaGUI {
    var buttonClickedListener: BiConsumer<Int, Player>? = null
    var closedClickedListener: Consumer<Player>? = null

    /**
     * 添加高级版按钮
     *
     * @param buttonID 按钮id
     * @param text 按钮内容
     * @param listener 回调方法
     * */
    open fun addButton(buttonID: String, text: String, listener: Consumer<Player>) {
        if (gui !is FormWindowSimple) return
        val button = AdvancedButton(text, listener)
        this.gui.addButton(button)
        this.partIds.add(buttonID)
        this.parts[buttonID] = button
        this.update()
    }

    /**
     * 添加高级版按钮
     *
     * @param buttonID 按钮id
     * @param text 按钮内容
     * @param listener 回调方法
     * @param image 按钮图片
     * */
    open fun addButton(buttonID: String, text: String, listener: Consumer<Player>, image: ElementButtonImageData) {
        if (gui !is FormWindowSimple) return
        val button = AdvancedButton(text, listener, image)
        this.gui.addButton(button)
        this.partIds.add(buttonID)
        this.parts[buttonID] = button
        this.update()
    }

    override fun callClicked(player: Player, data: String) {
        if (gui !is FormWindowSimple) return
        if (data == "null") return
        val id = data.toInt()
        val button = this.gui.buttons[id]
        if (button is AdvancedButton) {
            button.callClick(player)
        }
        this.buttonClickedListener?.accept(id, player)
    }

    override fun callClosed(player: Player) {
        if (gui !is FormWindowSimple) return
        if (closedClickedListener == null) return
        this.closedClickedListener!!.accept(player)
    }
}