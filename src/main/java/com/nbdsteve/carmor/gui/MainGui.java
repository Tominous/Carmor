package com.nbdsteve.carmor.gui;

import com.nbdsteve.carmor.Carmor;
import com.nbdsteve.carmor.file.LoadCarmorFiles;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MainGui {
    //Register the main class
    private Plugin pl = Carmor.getPlugin(Carmor.class);
    //Get the files for the plugin
    private LoadCarmorFiles lcf = ((Carmor) pl).getFiles();

    public void mainGui(Player p) {
        //Create the gui
        Inventory inven = pl.getServer().createInventory(null, lcf.getMainGui().getInt("size"),
                ChatColor.translateAlternateColorCodes('&', lcf.getMainGui().getString("name")));
        for (int i = 0; i <= 54; i++) {
            String set = "armor-set-" + String.valueOf(i);
            if (lcf.getArmorGui().getBoolean(set + ".in-gui")) {
                //Create the armor set icon
                ItemStack icon = new ItemStack(Material.valueOf(lcf.getArmorGui().getString(set + ".main" +
                        "-gui-icon.item").toUpperCase()));
                ItemMeta iconMeta = icon.getItemMeta();
                iconMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                        lcf.getArmorGui().getString(set + ".main-gui-icon.name")));
                List<String> iconLore = new ArrayList<>();
                for (String lore : lcf.getArmorGui().getStringList(set + ".main-gui-icon.lore")) {
                    iconLore.add(ChatColor.translateAlternateColorCodes('&', lore));
                }
                if (lcf.getArmorGui().getBoolean(set + ".main-gui-icon.glowing")) {
                    iconMeta.addEnchant(Enchantment.LURE, 1, true);
                    iconMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
                iconMeta.setLore(iconLore);
                icon.setItemMeta(iconMeta);
                inven.setItem(lcf.getArmorGui().getInt(set + "main-icon-gui.slot"), icon);
            }
        }
        p.openInventory(inven);
    }
}
