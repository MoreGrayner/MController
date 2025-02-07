package io.github.moregrayner.plugins;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Controller extends JavaPlugin {
    private PeacetimeModule peacetimeModule;
    private Chat chat;
    private CfgLoader cfg;
    private CMDModule cmd;


    @Override
    public void onEnable() {
        this.peacetimeModule = new PeacetimeModule();
        this.chat = new Chat();
        this.cfg = new CfgLoader(this, chat, cmd);

        Bukkit.getPluginManager().registerEvents(peacetimeModule, this);
        Bukkit.getPluginManager().registerEvents(chat, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public PeacetimeModule getPeacetimeModule() {return peacetimeModule;}
    public void setPeacetimeModule(PeacetimeModule peacetimeModule) {this.peacetimeModule = peacetimeModule;}

    public CfgLoader getCfg() {return cfg;}
    public void setCfg(CfgLoader cfg) {this.cfg = cfg;}

    public void setCmd(CMDModule cmd) {this.cmd = cmd;}
}
