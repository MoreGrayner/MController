package io.github.moregrayner.plugins;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Controller extends JavaPlugin {
    private PeacetimeModule peacetimeModule;
    private Chat chat;
    private CfgLoader cfg;
    private CMDModule cmd;


    @Override
    public void onEnable() {
        this.peacetimeModule = new PeacetimeModule();
        this.chat = new Chat();
        this.cfg = new CfgLoader(this, chat, null);
        this.cmd = new CMDModule(peacetimeModule, chat, cfg);
        this.cfg.setCmdModule(cmd);
        Objects.requireNonNull(getCommand("help")).setExecutor(cmd);
        Objects.requireNonNull(getCommand("임시보호")).setExecutor(cmd);
        Objects.requireNonNull(getCommand("chat")).setExecutor(cmd);
        Objects.requireNonNull(getCommand("retry")).setExecutor(cmd);
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
