package net.mcpes.hhm.guiapi.window

import cn.nukkit.form.element.*
import cn.nukkit.form.window.FormWindowCustom
import net.mcpes.hhm.guiapi.NukkitGraphicalUserInterface

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/7/10
 * version 1.0
 */
abstract class CustomGUI(mode: ProcessMode, id: String, title: String, imageURL: String) : NukkitGraphicalUserInterface(GUIType.CUSTOM, mode, id, title, FormWindowCustom(title, ArrayList(), imageURL)) {
    /**
     * 新增GUI文字描述
     * @param id 部件id
     * @param text 文字
     * */
    fun addText(id: String, text: String) {
        this.addPart(id, ElementLabel(text))
    }

    /**
     * 新增GUI文字选择滑块(和Dropdown目的差不多,只不过形式不太一样)
     * @param id 部件id
     * @param text 描述
     * @param options 文字选项
     * @param default 默认选择
     * */
    @JvmOverloads
    fun addWordSlider(id: String, text: String, options: List<String> = ArrayList(), default: Int = 0) {
        this.addPart(id, ElementStepSlider(text, options, default))
    }

    /**
     * 新增GUI滑块
     * @param id 部件id
     * @param text 文字
     * @param min 最小值
     * @param max 最大值
     * @param step 步长(翻译的不太确定emm)(即滑动一格数值扩大的数量)
     * @param default 默认值
     * */
    @JvmOverloads
    fun addSlider(id: String, text: String, min: Float, max: Float, step: Int = -1, default: Float = -1f) {
        this.addPart(id, ElementSlider(text, min, max, step, default))
    }

    /**
     * 新增GUI选择控件(复选框/Checkbox)
     * @param id 部件id
     * @param text 文字
     * @param default 默认是否选中
     * */
    @JvmOverloads
    fun addCheckbox(id: String, text: String, default: Boolean = false) {
        this.addPart(id, ElementToggle(text, default))
    }

    /**
     * 新增GUI下拉菜单
     * @param id 部件id
     * @param text 描述
     * @param options 选项,至少要存在一个
     * @param default 默认选中第几个
     * */
    @JvmOverloads
    fun addDropdown(id: String, text: String, options: List<String> = ArrayList(), default: Int = 0) {
        this.addPart(id, ElementDropdown(text, options, default))
    }

    /**
     * 新增GUI输入框
     * @param id 部件id
     * @param text 描述
     * @param default 输入框默认内容
     * @param placeholder 占位符,暂时不知道啥用
     * */
    @JvmOverloads
    fun addInput(id: String, text: String, default: String = "", placeholder: String = "") {
        this.addPart(id, ElementInput(text, placeholder, default))
    }

    /**
     * 添加部件
     *
     * @param id 部件id
     * @param part 部件
     * */
    private fun addPart(id: String, part: Element) {
        if (gui !is FormWindowCustom) return
        this.gui.elements.add(part)
        this.partIds.add(id)
        this.parts[id] = part
        this.update()
    }

    /**
     * 删除部件
     * @param id 部件id
     * */
    fun removePart(id: String) {
        if (gui !is FormWindowCustom) return
        this.gui.elements.remove(this.parts.remove(id))
        this.partIds.remove(id)
    }
}