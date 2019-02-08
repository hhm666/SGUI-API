package net.mcpes.hhm.guiapi.listener

import cn.nukkit.Server
import cn.nukkit.event.EventHandler
import cn.nukkit.event.EventPriority
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerCreationEvent
import cn.nukkit.event.player.PlayerQuitEvent
import cn.nukkit.event.server.DataPacketReceiveEvent
import cn.nukkit.network.protocol.ModalFormResponsePacket
import net.mcpes.hhm.guiapi.NukkitGraphicalUserInterface
import net.mcpes.hhm.guiapi.NukkitGraphicalUserInterface.Companion.GUIs
import net.mcpes.hhm.guiapi.NukkitGraphicalUserInterface.Companion.playerInGUI
import net.mcpes.hhm.guiapi.NukkitPlayer
import net.mcpes.hhm.guiapi.event.PlayerGUIResultEvent
import net.mcpes.hhm.guiapi.window.EventGUI
import net.mcpes.hhm.guiapi.window.LambdaGUI

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/11
 * version 1.0
 *
 * GUI NK事件监听，实现窗口回调
 */
class GUIListener : Listener {
    private val lastWindows: MutableMap<String, NukkitGraphicalUserInterface> = hashMapOf()

    @EventHandler
    fun onCreate(event: PlayerCreationEvent) {
        event.baseClass = NukkitPlayer::class.java
        event.playerClass = NukkitPlayer::class.java
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    fun onQuit(event: PlayerQuitEvent) {
        lastWindows.remove(event.player.name)
        NukkitGraphicalUserInterface.removeInGUI(event.player.name)
    }

    @EventHandler
    fun onResult(event: DataPacketReceiveEvent) {
        if (event.player !is NukkitPlayer) return
        if (event.packet !is ModalFormResponsePacket) return
        val packet = event.packet as ModalFormResponsePacket
        if (!playerInGUI.containsKey(event.player.name)) return
        event.isCancelled = true
        val id = playerInGUI[event.player.name]!!
        if (!GUIs.containsKey(id)) {
            NukkitGraphicalUserInterface.removeInGUI(event.player.name)
            return
        }
        val gui = GUIs[id]!!
        when (gui) {
            is EventGUI -> {
                val d = packet.data.trim().replace("\n", "")
                if (d == "null") {
                    Server.getInstance().pluginManager.callEvent(PlayerGUIResultEvent(event.player as NukkitPlayer, gui, true))
                    return
                }
                val data: HashMap<String, Any> = gui.analysis(d)
                Server.getInstance().pluginManager.callEvent(PlayerGUIResultEvent(event.player as NukkitPlayer, gui, false, data))
                NukkitGraphicalUserInterface.removeInGUI(event.player.name, gui.id)
            }
            is LambdaGUI -> {
                val d = packet.data.trim().replace("\n", "")
                if (d == "null") {
                    gui.callClosed(event.player)
                    return
                }
                gui.callClicked(event.player, d)
                NukkitGraphicalUserInterface.removeInGUI(event.player.name, gui.id)
            }
        }
        val parent = lastWindows.remove(event.player.name)
        if (parent != null) {
            gui.setParent(event.player as NukkitPlayer, parent)
        }
        lastWindows[event.player.name] = gui
        (event.player as NukkitPlayer).guiParams.remove(gui.id)
        event.isCancelled = true
    }
}