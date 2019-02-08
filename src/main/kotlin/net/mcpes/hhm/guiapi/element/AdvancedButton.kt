package net.mcpes.hhm.guiapi.element

import cn.nukkit.Player
import cn.nukkit.form.element.ElementButton
import cn.nukkit.form.element.ElementButtonImageData
import java.util.function.Consumer

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/26
 * version 1.0
 *
 * 高级版的按钮.可在 LambdaButtonGUI 使用,全部使用此类按钮则不用设置buttonClickedListener
 */
class AdvancedButton : ElementButton {
    @Transient
    var onClickListener: Consumer<Player>

    constructor(text: String, onClickListener: Consumer<Player>) : super(text) {
        this.onClickListener = onClickListener
    }

    constructor(text: String, onClickListener: Consumer<Player>, imageData: ElementButtonImageData) : super(text, imageData) {
        this.onClickListener = onClickListener
    }

    fun callClick(player: Player) {
        this.onClickListener.accept(player)
    }
}