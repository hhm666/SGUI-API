package net.mcpes.hhm.guiapi.event

import cn.nukkit.event.Event
import net.mcpes.hhm.guiapi.NukkitGraphicalUserInterface
import net.mcpes.hhm.guiapi.NukkitPlayer

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/11
 * version 1.0
 *
 * EventGUI 当收到回复后将触发此事件
 *
 * @param player 玩家
 * @param gui GUI对象
 * @param closed 是否直接关闭窗口
 * @param data 表单信息
 */
class PlayerGUIResultEvent(val player: NukkitPlayer, val gui: NukkitGraphicalUserInterface, val closed: Boolean, val data: HashMap<String, Any> = HashMap()) : Event()