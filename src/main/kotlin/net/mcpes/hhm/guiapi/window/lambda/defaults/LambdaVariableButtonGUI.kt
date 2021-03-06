package net.mcpes.hhm.guiapi.window.lambda.defaults

import net.mcpes.hhm.guiapi.NukkitPlayer
import net.mcpes.hhm.guiapi.function.Variable
import net.mcpes.hhm.guiapi.window.lambda.LambdaButtonGUI

class LambdaVariableButtonGUI(id: String, title: String, content: String) : LambdaButtonGUI(id, title, content), Variable {
    override fun open(player: NukkitPlayer, params: Array<out Any>): Int {
        var f = this.content
        params.forEachIndexed { index, s -> f = f.replace("%$index", s.toString()) }
        return player.showGUI(this.data.replace(this.content, f), this)
    }
}