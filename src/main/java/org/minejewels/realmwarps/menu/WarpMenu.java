package org.minejewels.realmwarps.menu;

import net.abyssdev.abysslib.menu.MenuBuilder;
import net.abyssdev.abysslib.menu.templates.AbyssMenu;
import net.abyssdev.abysslib.placeholder.PlaceholderReplacer;
import net.abyssdev.abysslib.utils.WordUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.minejewels.realmwarps.RealmWarps;
import org.minejewels.realmwarps.warp.Warp;

public class WarpMenu extends AbyssMenu {

    private final RealmWarps plugin;

    public WarpMenu(final RealmWarps plugin) {
        super(plugin.getMenusConfig(), "warp-menu.");

        this.plugin = plugin;
    }

    @Override
    public void open(Player player) {

        final MenuBuilder builder = this.createBase();

        for (final String key : this.plugin.getMenusConfig().getSectionKeys("warp-menu.warps")) {

            final int slot = this.plugin.getMenusConfig().getInt("warp-menu.warps." + key + ".slot");
            final ItemStack item = this.plugin.getMenusConfig().getItemStack("warp-menu.warps." + key + ".item");
            final Warp warp = this.plugin.getWarpRegistry().get(key.toUpperCase()).get();

            builder.setItem(slot, item);

            builder.addClickEvent(slot, event -> {

                this.plugin.getMessageCache().sendMessage(player, "messages.warped", new PlaceholderReplacer().addPlaceholder("%warp%", WordUtils.formatText(key.replace("_", ""))));
                warp.teleport(player);
            });
        }

        player.openInventory(builder.build());
    }
}
