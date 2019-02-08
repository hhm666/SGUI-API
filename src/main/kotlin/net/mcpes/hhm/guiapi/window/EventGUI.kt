package net.mcpes.hhm.guiapi.window

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/26
 * version 1.0
 *
 * 事件型GUI，收到玩家回复后触发PlayerGUIResultEvent事件
 * @see net.mcpes.hhm.nk.bedwars.gui.event.PlayerGUIResultEvent <br>
 */
interface EventGUI {
    fun analysis(data: String): HashMap<String, Any>
}