package net.mcpes.hhm.guiapi

import cn.nukkit.form.window.FormWindow
import net.mcpes.hhm.guiapi.function.Returnable

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/10
 * version 1.0
 *
 * 对于Nukkit MC GUI的调用API <br>
 *
 * 请在Nukkit 的配合下使用，无法单独调用<br>
 * <p> 要想打开GUI，请使用
 * @see net.mcpes.hhm.nk.bedwars.NukkitPlayer#showGUI方法<br>
 *
 * <p> 要想接收返回数据进行处理，查看 EventGUI 与 LambdaGUI 的 JavaDOC
 */
abstract class NukkitGraphicalUserInterface(val type: GUIType, val mode: ProcessMode, val id: String, var title: String, protected val gui: FormWindow) : Returnable {
    var data = ""
    var partIds: ArrayList<String> = ArrayList()
    var parts: HashMap<String, Any> = HashMap()
    val openIds: HashMap<String, Int> = HashMap()
    val parents: HashMap<String, NukkitGraphicalUserInterface> = hashMapOf()

    init {
        this.init()
    }

    fun init() {
        GUIs[id] = this
        println("add gui:$id")
    }

    override fun setParent(player: NukkitPlayer, gui: NukkitGraphicalUserInterface) {
        this.parents[player.name] = gui
    }

    override fun getParent(player: NukkitPlayer) = this.parents[player.name]

    fun update() {
        this.data = gui.jsonData
    }

    fun getPartID(id: String): Int {
        var i = 0
        partIds.forEach {
            if (it == id) return i
            i++
        }
        return -1
    }

    fun close() {
        GUIs.remove(id)
    }

    enum class GUIType(val description: String) {
        CUSTOM("自定义部件"), BUTTONS("若干按钮进行选择"), JUDGE("选择对错");
    }

    enum class ProcessMode {
        EVENT, LAMBDA
    }

    companion object {
        val playerInGUI = HashMap<String, String>()

        fun addInGUI(pn: String, gi: String) {
            playerInGUI[pn] = gi
            println("add in gui:$pn $gi")
        }

        fun removeInGUI(pn: String, gi: String? = null) {
            if (gi == null) playerInGUI.remove(pn)
            else playerInGUI.remove(pn, gi)
            println("remove in gui:$pn $gi")
        }

        val GUIs = HashMap<String, NukkitGraphicalUserInterface>()
    }
}