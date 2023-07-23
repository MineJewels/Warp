package org.minejewels.realmwarps.warp;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Data
public class Warp {

    private final String warpName;
    private final Location location;

    public void teleport(final Player player) {
        player.teleport(this.location);
    }
}
