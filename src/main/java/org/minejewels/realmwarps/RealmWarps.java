package org.minejewels.realmwarps;

import lombok.Getter;
import net.abyssdev.abysslib.config.AbyssConfig;
import net.abyssdev.abysslib.patterns.registry.Registry;
import net.abyssdev.abysslib.plugin.AbyssPlugin;
import net.abyssdev.abysslib.text.MessageCache;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.minejewels.realmwarps.command.WarpCommand;
import org.minejewels.realmwarps.warp.Warp;
import org.minejewels.realmwarps.warp.registry.WarpRegistry;

@Getter
public final class RealmWarps extends AbyssPlugin {

    private final AbyssConfig langConfig = this.getAbyssConfig("lang");
    private final AbyssConfig settingsConfig = this.getAbyssConfig("settings");
    private final AbyssConfig menusConfig = this.getAbyssConfig("menus");

    private final MessageCache messageCache = new MessageCache(this.langConfig);

    private final Registry<String, Warp> warpRegistry = new WarpRegistry();

    @Override
    public void onEnable() {
        this.loadMessages(this.messageCache, this.langConfig);
        this.loadWarps();

        new WarpCommand(this).register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadWarps() {

        for (final String warp : this.settingsConfig.getSectionKeys("warps")) {

            this.warpRegistry.register(warp.toUpperCase(), new Warp(
                    warp.toUpperCase(),
                    new Location(
                            Bukkit.getWorld(this.getSettingsConfig().getString("warps." + warp + ".world")),
                            this.getSettingsConfig().getInt("warps." + warp + ".x") + 0.5,
                            this.getSettingsConfig().getInt("warps." + warp + ".y"),
                            this.getSettingsConfig().getInt("warps." + warp + ".z") + 0.5,
                            this.getSettingsConfig().getInt("warps." + warp + ".yaw"),
                            this.getSettingsConfig().getInt("warps." + warp + "pitch")
                    )));
        }
    }
}
