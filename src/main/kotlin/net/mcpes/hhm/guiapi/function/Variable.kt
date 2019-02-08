package net.mcpes.hhm.guiapi.function

import net.mcpes.hhm.guiapi.NukkitPlayer

/**
 * FoundHi
 *
 * @author hhm Copyright (c) 2018/8/14
 * version 1.0
 */
interface Variable {
    fun open(player: NukkitPlayer, params: Array<out Any>): Int
}