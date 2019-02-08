package net.mcpes.hhm.guiapi.window

import cn.nukkit.form.window.FormWindowModal
import net.mcpes.hhm.guiapi.NukkitGraphicalUserInterface

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/10
 * version 1.0
 */
abstract class ModalGUI(mode: ProcessMode, id: String, title: String, var content: String) : NukkitGraphicalUserInterface(GUIType.JUDGE, mode, id, title, FormWindowModal(title, content, "", "")) {
    fun setTrueButtonText(text: String) {
        if (gui !is FormWindowModal) return
        this.gui.button1 = text
        this.update()
    }

    fun setFalseButtonText(text: String) {
        if (gui !is FormWindowModal) return
        this.gui.button2 = text
        this.update()
    }
}