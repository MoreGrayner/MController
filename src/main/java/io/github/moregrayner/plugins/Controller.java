package io.github.moregrayner.plugins;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Controller extends JavaPlugin {
    private static Controller instance;
    private PeacetimeModule peacetimeModule;
    private Chat chat;
    private CfgLoader cfg;
    private CMDModule cmd;


    @Override
    public void onEnable() {
        instance = this;
        this.peacetimeModule = new PeacetimeModule();
        this.chat = new Chat();
        this.cfg = new CfgLoader(this, chat, null);
        this.cmd = new CMDModule(peacetimeModule, chat, cfg);
        this.cfg.setCmdModule(cmd);
        Objects.requireNonNull(getCommand("help")).setExecutor(cmd);
        Objects.requireNonNull(getCommand("임시보호")).setExecutor(cmd);
        Objects.requireNonNull(getCommand("chat")).setExecutor(cmd);
        Objects.requireNonNull(getCommand("retry")).setExecutor(cmd);
        Objects.requireNonNull(getCommand("start")).setExecutor(cmd);

        Bukkit.getPluginManager().registerEvents(peacetimeModule, this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Controller getInstance(){return instance;}

    public PeacetimeModule getPeacetimeModule() {return peacetimeModule;}
    public void setPeacetimeModule(PeacetimeModule peacetimeModule) {this.peacetimeModule = peacetimeModule;}

    public CfgLoader getCfg() {return cfg;}
    public void setCfg(CfgLoader cfg) {this.cfg = cfg;}

    public void setCmd(CMDModule cmd) {this.cmd = cmd;}
}
