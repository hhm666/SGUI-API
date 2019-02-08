package net.mcpes.hhm.guiapi

import cn.nukkit.plugin.PluginBase
import net.mcpes.hhm.guiapi.listener.GUIListener

class SGUIAPI : PluginBase() {
    override fun onEnable() {
        this.server.pluginManager.registerEvents(GUIListener(), this)
        this.logger.info("SGUI-API Startup Successful!")
    }
}