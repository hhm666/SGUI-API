package net.mcpes.hhm.guiapi.window

import cn.nukkit.form.element.ElementButton
import cn.nukkit.form.element.ElementButtonImageData
import cn.nukkit.form.window.FormWindowSimple
import net.mcpes.hhm.guiapi.NukkitGraphicalUserInterface

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/10
 * version 1.0
 */
abstract class ButtonGUI(mode: ProcessMode, id: String, title: String, var content: String) : NukkitGraphicalUserInterface(GUIType.BUTTONS, mode, id, title, FormWindowSimple(title, content)) {
    /**
     * 添加普通按钮
     *
     * @param buttonID 按钮id
     * @param text 按钮内容
     * */
    open fun addButton(buttonID: String, text: String) {
        if (gui !is FormWindowSimple) return
        val button = ElementButton(text)
        this.gui.addButton(button)
        this.partIds.add(buttonID)
        this.parts[buttonID] = button
        this.update()
    }

    /**
     * 添加普通按钮
     *
     * @param buttonID 按钮id
     * @param text 按钮内容
     * @param image 按钮图片
     * */
    open fun addButton(buttonID: String, text: String, image: ElementButtonImageData) {
        if (gui !is FormWindowSimple) return
        val button = ElementButton(text, image)
        this.gui.addButton(button)
        this.partIds.add(buttonID)
        this.parts[buttonID] = button
        this.update()
    }

    /**
     * 删除按钮
     *
     * @param buttonID 按钮id
     * */
    open fun delButton(buttonID: String) {
        if (gui !is FormWindowSimple) return
        this.gui.buttons.remove(this.parts.remove(buttonID))
        this.partIds.remove(buttonID)
    }
}