package net.mcpes.hhm.guiapi.window.lambda

import cn.nukkit.Player
import cn.nukkit.form.element.*
import cn.nukkit.form.window.FormWindowCustom
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.mcpes.hhm.guiapi.window.CustomGUI
import net.mcpes.hhm.guiapi.window.LambdaGUI
import java.util.function.BiConsumer
import java.util.function.Consumer

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/26
 * version 1.0
 */
open class LambdaCustomGUI @JvmOverloads constructor(id: String, title: String, imageURL: String = "") : CustomGUI(ProcessMode.LAMBDA, id, title, imageURL), LambdaGUI {
    var submittedClickedListener: BiConsumer<HashMap<String, Any>, Player>? = null
    var closedClickedListener: Consumer<Player>? = null

    override fun callClicked(player: Player, data: String) {
        if (gui !is FormWindowCustom) return
        val map = this.getDataMap(data)
        if (map.isNotEmpty()) {
            this.submittedClickedListener?.accept(map, player)
        }
    }

    override fun callClosed(player: Player) {
        if (gui !is FormWindowCustom) return
        this.closedClickedListener?.accept(player)
    }

    private fun getDataMap(data: String): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        if (gui !is FormWindowCustom) return map
        if (data == "null") return map
        val elementResponses = Gson().fromJson<List<String>>(data, object : TypeToken<List<String>>() {}.type)
        var i = 0
        for (elementData in elementResponses) {
            if (i >= this.gui.elements.size) {
                break
            }
            val e = this.gui.elements[i] ?: break
            if (e is ElementLabel) {
                i++
                continue
            }
            when (e) {
                is ElementDropdown -> {
                    val chose = elementData.toInt()
                    val the = HashMap<String, Any>()
                    the["id"] = chose
                    the["data"] = e.options[chose]
                    map[this.partIds[i]] = the
                }
                is ElementInput -> {
                    map[this.partIds[i]] = elementData
                }
                is ElementSlider -> {
                    map[this.partIds[i]] = elementData.toFloat()
                }
                is ElementStepSlider -> {
                    val chose = elementData.toInt()
                    val the = HashMap<String, Any>()
                    the["id"] = chose
                    the["data"] = e.steps[chose]
                    map[this.partIds[i]] = the
                }
                is ElementToggle -> {
                    map[this.partIds[i]] = elementData.toBoolean()
                }
            }
            i++
        }
        return map
    }
}