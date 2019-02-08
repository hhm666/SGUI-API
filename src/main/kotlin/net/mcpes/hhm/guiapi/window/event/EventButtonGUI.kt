package net.mcpes.hhm.guiapi.window.event

import cn.nukkit.form.window.FormWindowSimple
import net.mcpes.hhm.guiapi.window.ButtonGUI
import net.mcpes.hhm.guiapi.window.EventGUI

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/26
 * version 1.0
 */
open class EventButtonGUI(id: String, title: String, content: String) : ButtonGUI(ProcessMode.EVENT, id, title, content), EventGUI {
    override fun analysis(data: String): HashMap<String, Any> {
        val map: HashMap<String, Any> = HashMap()
        if (gui !is FormWindowSimple) return map
        val buttonID = data.toInt()
        map["id"] = buttonID
        if (buttonID < this.gui.buttons.size) {
            map["button"] = this.gui.buttons[buttonID]
        }
        return map
    }
}