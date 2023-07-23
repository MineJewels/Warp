package org.minejewels.realmwarps.command;

import net.abyssdev.abysslib.command.AbyssCommand;
import net.abyssdev.abysslib.command.context.CommandContext;
import org.bukkit.entity.Player;
import org.minejewels.realmwarps.RealmWarps;
import org.minejewels.realmwarps.menu.WarpMenu;

public class WarpCommand extends AbyssCommand<RealmWarps, Player> {

    public WarpCommand(final RealmWarps plugin) {
        super(plugin, "warp", Player.class);
    }

    @Override
    public void execute(CommandContext<Player> context) {

        final Player player = context.getSender();

        new WarpMenu(this.plugin).open(player);
    }
}
