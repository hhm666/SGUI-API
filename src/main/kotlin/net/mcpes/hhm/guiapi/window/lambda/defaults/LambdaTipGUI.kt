package net.mcpes.hhm.guiapi.window.lambda.defaults

import cn.nukkit.Player
import cn.nukkit.form.element.ElementButtonImageData
import cn.nukkit.form.window.FormWindowSimple
import net.mcpes.hhm.guiapi.element.AdvancedButton
import net.mcpes.hhm.guiapi.window.lambda.LambdaButtonGUI
import java.util.function.Consumer

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/26
 * version 1.0
 *
 * Lambada型GUI模板
 * 模板:提示信息
 */
open class LambdaTipGUI(id: String, protected var tipText: String, title: String = "提示 | Announcement", imageData: ElementButtonImageData? = null) : LambdaButtonGUI(id, title, tipText) {
    init {
        if (gui is FormWindowSimple) {
            val button = if (imageData != null) {
                AdvancedButton("确定", Consumer {}, imageData)
            } else {
                AdvancedButton("确定", Consumer {})
            }
            this.gui.addButton(button)
            this.partIds.add("sure")
            this.parts["sure"] = button
            this.update()
        }
    }

    fun setTipMessage(tipText: String) {
        this.tipText = tipText
        this.content = tipText
        this.update()
    }

    fun setConfirmButtonImage(imageData: ElementButtonImageData?) {
        val button = (this.parts["tip"] as AdvancedButton)
        if (button.image != imageData) button.addImage(imageData!!)
        this.update()
    }

    fun setConfirmButtonText(text: String) {
        val button = (this.parts["tip"] as AdvancedButton)
        button.text = text
        this.update()
    }

    fun setConfirmHandle(listener: Consumer<Player>) {
        val button = (this.parts["tip"] as AdvancedButton)
        button.onClickListener = listener
    }

    @Deprecated("在Tip模板不适用")
    override fun addButton(buttonID: String, text: String, listener: Consumer<Player>) {

    }

    @Deprecated("在Tip模板不适用")
    override fun addButton(buttonID: String, text: String, listener: Consumer<Player>, image: ElementButtonImageData) {

    }

    @Deprecated("在Tip模板不适用")
    override fun addButton(buttonID: String, text: String) {

    }

    @Deprecated("在Tip模板不适用")
    override fun addButton(buttonID: String, text: String, image: ElementButtonImageData) {

    }

    @Deprecated("在Tip模板不适用")
    override fun delButton(buttonID: String) {

    }
}