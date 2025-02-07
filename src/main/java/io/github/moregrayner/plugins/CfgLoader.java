package io.github.moregrayner.plugins;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class CfgLoader {
    private final Controller plugin;
    private final Chat chat;
    private final CMDModule cmdModule;
    private static final Logger LOGGER = Logger.getLogger("CFGLOADER");

    private static File Folder;
    private final File ListS;
    private final File mute;
    private final File bannedWords;

    public CfgLoader(Controller plugin, Chat chat, CMDModule cmdModule) {
        this.plugin = plugin;
        this.chat = chat;
        this.cmdModule = cmdModule;
        Folder = new File(plugin.getDataFolder(), "MoreGrayner");
        this.ListS = new File(Folder, "messages.yml");
        this.mute = new File(Folder, "mute.yml");
        this.bannedWords = new File(Folder, "bannedWords.yml");
        createFiles();
    }

    private void createFiles() {
        if (!Folder.exists()) {
            if (Folder.mkdirs()) {LOGGER.info("폴더가 생성되었습니다.");
            } else {LOGGER.warning("폴더를 생성하지 못했습니다.");}
        }

        if (!ListS.exists()) {
            try {if (ListS.createNewFile()) {LOGGER.info("yml 파일이 생성되었습니다.");}
            } catch (IOException e) {LOGGER.severe("파일 생성 중 오류 발생: " + e.getMessage());}
        }
        if (!mute.exists()) {
            try {if (mute.createNewFile()) {LOGGER.info("yml 파일이 생성되었습니다.");}
            } catch (IOException e) {LOGGER.severe("파일 생성 중 오류 발생: " + e.getMessage());}
        }
        if (!bannedWords.exists()) {
            try {if (bannedWords.createNewFile()) {LOGGER.info("yml 파일이 생성되었습니다.");}
            } catch (IOException e) {LOGGER.severe("파일 생성 중 오류 발생: " + e.getMessage());}
        }
    }

    void loadMessagesConfig() {//플러그인 인스턴스 받아와서 써먹게 만듦 - 두번째줄 SaveResources 이슈
        cmdModule.help.clear();
        File messagesFile = new File(Folder, "messages.yml");

        if (!messagesFile.exists()) {plugin.saveResource("messages.yml", false);}

        FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        List<String> words = messagesConfig.getStringList("help");
        cmdModule.help.addAll(words);
    }
    void loadBan(){
        File muteFile = new File(Folder, "mute.yml");
        File bannedWords = new File(Folder, "bannedWords.yml");

        if (!bannedWords.exists()) {plugin.saveResource("bannedWords.yml", false);}
        if (!muteFile.exists()) {plugin.saveResource("mute.yml", false);}

        FileConfiguration muteConfig = YamlConfiguration.loadConfiguration(muteFile);
        FileConfiguration bannedWordsConfig = YamlConfiguration.loadConfiguration(bannedWords);

        chat.mute = muteConfig.getLong("mute-duration", 30);
        List<String> words = bannedWordsConfig.getStringList("banned-words");
        chat.bannedWords.clear();
        chat.bannedWords.addAll(words);
    }

    public void reloadX() {
        loadMessagesConfig();
        loadBan();
        LOGGER.info("리로드 시작.");
    }
}
