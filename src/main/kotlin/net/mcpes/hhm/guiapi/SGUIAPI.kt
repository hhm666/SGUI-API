package net.mcpes.hhm.guiapi

import cn.nukkit.plugin.PluginBase
import net.mcpes.hhm.guiapi.listener.GUIListener

class SGUIAPI : PluginBase() {
    override fun onLoad() {
        this.server.pluginManager.registerEvents(GUIListener(), this)
    }

    override fun onEnable() {
        this.logger.info("SGUI-API Startup Successful!")
    }
}