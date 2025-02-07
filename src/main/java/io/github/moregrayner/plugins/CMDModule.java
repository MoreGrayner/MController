package io.github.moregrayner.plugins;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class CMDModule implements CommandExecutor {
    HashSet<String> help = new HashSet<>();
    private final PeacetimeModule peacetimeModule;
    private final Chat chat;
    private final CfgLoader cfg;

    public CMDModule(PeacetimeModule peacetime, Chat chat, CfgLoader cfg) {
        this.peacetimeModule = peacetime;
        this.chat = chat;
        this.cfg = cfg;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) return false;
        if(command.getName().equalsIgnoreCase("help")){for (String word : help) {commandSender.sendMessage(word);}}

        if (commandSender.isOp()) {
            if (command.getName().equalsIgnoreCase("임시보호")) {
                if (args.length > 0) {
                    peacetimeModule.Protection = "ON".equalsIgnoreCase(args[0]) && !peacetimeModule.Protection || "OFF".equalsIgnoreCase(args[0]) && peacetimeModule.Protection;
                }
                return true;
            }
            if (command.getName().equalsIgnoreCase("chat")) {
                chat.Chat = "ON".equalsIgnoreCase(args[0]) && !chat.Chat || "OFF".equalsIgnoreCase(args[0]) && chat.Chat;
                return true;
            }
            if (label.equalsIgnoreCase("retry")) {
                cfg.reloadX();
                return true;
            }
        }
        return false;
    }
}
