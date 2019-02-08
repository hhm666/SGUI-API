# SGUI-API
Minecraft Nukkit Plugin GUI API

**Now Version: 0.1.0-Alpha**


SGUI-API requires [KotlinLib](https://nukkitx.com/resources/kotlinlib.48/)

Example usage
```kotlin
class SLogin : PluginBase(),Listener {
    private var loginGUIer = ArrayList<String>()
    private lateinit var loginGUI: LambdaCustomGUI

    override fun onLoad() {
        loginGUI = LambdaCustomGUI("SLogin_login", "$TITLE- 登录")
        loginGUI.addText("t1", "§l§6欢迎来到$SERVER_TITLE!登录即可进入服务器!")
        loginGUI.addInput("pw", "密码")
        loginGUI.submittedClickedListener = BiConsumer { data, player ->
            if (!loginGUIer.contains(player.name)) return@BiConsumer
            if (player !is NukkitPlayer) return@BiConsumer
            when (LoginUtils.login(player, data["pw"].toString())) {
                0 -> {
                    loginGUIer.remove(name)
                    player.sendMessage("$TITLE 登陆成功,稍后将把您转移到大厅服务器!")
                }
                -1 -> {
                    player.sendMessage("$TITLE 密码错误!")
                    loginGUIer.remove(name)
                    this.openRegister(player)
                }
                -2 -> {
                    player.sendMessage("$TITLE 你还没有注册!")
                    loginGUIer.remove(name)
                    this.openLogin(player)
                }
                -3 -> {
                    loginGUIer.remove(name)
                    player.sendMessage("$TITLE 发生未知错误!")
                }
                -4 -> {
                    loginGUIer.remove(name)
                    player.sendMessage("$TITLE 你已经登陆!")
                }
            }
        }
        loginGUI.closedClickedListener = Consumer {
            loginGUIer.remove(it.name)
        }
        this.server.pluginManager.registerEvents(GUIListener(), this)
        this.server.pluginManager.registerEvents(this, this)
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    fun onJoin(event: PlayerJoinEvent) {
        if (event.player !is NukkitPlayer) return
        notLogin.add(event.player.name)
        loginGUIer.add(player.name)
        player.showGUI(loginGUI)
    }
}
```