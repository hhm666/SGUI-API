package net.mcpes.hhm.guiapi.window.lambda.defaults

import net.mcpes.hhm.guiapi.NukkitPlayer
import net.mcpes.hhm.guiapi.function.Variable
import net.mcpes.hhm.guiapi.window.lambda.LambdaCustomGUI

class LambdaVariableCustomGUI(id: String, title: String, imageURL: String = "") : LambdaCustomGUI(id, title, imageURL), Variable {
    override fun open(player: NukkitPlayer, params: Array<out Any>): Int {
        var d = this.data
        params.forEachIndexed { index, s -> d = d.replace("%$index", s.toString()) }
        return player.showGUI(d, this)
    }
}