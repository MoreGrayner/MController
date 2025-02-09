package io.github.moregrayner.plugins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
                    String mode = args[0].toUpperCase(); // 대소문자 구분 없이 처리
                    if (mode.equals("ON")) {
                        peacetimeModule.Protection = true;
                        Bukkit.broadcastMessage(ChatColor.AQUA + "임시보호 ON");
                    } else if (mode.equals("OFF")) {
                        peacetimeModule.Protection = false;
                        Bukkit.broadcastMessage(ChatColor.RED + "임시보호 OFF");
                    } else {
                        commandSender.sendMessage("사용법: /임시보호 ON 또는 /임시보호 OFF");
                    }
                } else {
                    commandSender.sendMessage("사용법: /임시보호 ON 또는 /임시보호 OFF");
                }
                return true;
            }
            if (command.getName().equalsIgnoreCase("chat")) {
                chat.Chat = "ON".equalsIgnoreCase(args[0]) && !chat.Chat || "OFF".equalsIgnoreCase(args[0]) && chat.Chat;
                return true;
            }
            if (command.getName().equalsIgnoreCase("retry")) {
                cfg.reloadX();
                return true;
            }
            if (command.getName().equalsIgnoreCase("start")) {
                if (args.length == 0) {
                    commandSender.sendMessage("사용법: /start <숫자>");
                    return false;
                }

                try {
                    int time = Integer.parseInt(args[0]);
                    peacetimeModule.peace(time);
                    commandSender.sendMessage("게임을 시작합니다.");
                } catch (NumberFormatException e) {
                    commandSender.sendMessage("숫자를 입력하세요.");
                    return false;
                }
            }

        }
        return false;
    }

}
