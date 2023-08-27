package com.xxdraggy.datamanager;

import com.xxdraggy.utils.Creator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class YMLFile<ListType> extends YamlConfiguration {
    File fileData;
    String name;
    String path;

    @Override
    public void set(String path, Object value) {
        if(value == null) {
            Bukkit.getLogger().log(
                    Level.ALL,
                    "[DATAMANAGER] Warning: Given value is null! Removing element with path \"" + path + "\" in file " + name + "in " + path + "."
            );

            remove(path);
            return;
        }

        super.set(path, value);

        save();
    }

    public void remove(String path) {
        super.set(path, null);

        save();
    }

    public void addListItem(String listPath, ListType item) {
        List<ListType> list = (List<ListType>) getList(listPath);

        if(list == null) {
            Bukkit.getLogger().log(
                    Level.ALL,
                    "[DATAMANAGER] Error: Can not find list with path \"" + listPath + "\" in file " + name + "in " + path + "."
            );

            return;
        }

        list.add(item);

        set(listPath, list);
    }

    public void save() {
        try {
            super.save(this.fileData);
        } catch (IOException e) {
            Bukkit.getLogger().log(
                Level.ALL,
                Creator.text(
                    "[DATAMANAGER] Error: Can not save file " + name + "in " + path + ".",
                    ChatColor.DARK_RED
                )
            );
        }

        this.fileData = new YMLFile(this.name, this.path).fileData;
    }

    public YMLFile(String name, String path) {
        this.fileData = new File(path, name);

        if(!fileData.exists()) {
            try {
                fileData.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().log(
                    Level.ALL,
                    Creator.text(
                        "[DATAMANAGER] Error: Can not load file " + name + "in " + path + ".",
                        ChatColor.DARK_RED
                    )
                );
            }
        }

        this.name = name;
        this.path = path;
    }
}
