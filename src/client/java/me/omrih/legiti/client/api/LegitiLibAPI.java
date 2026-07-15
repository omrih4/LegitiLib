package me.omrih.legiti.client.api;


import me.omrih.legiti.client.World;
import me.omrih.legiti.client.api.event.WorldChangedEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;

public class LegitiLibAPI {
    private static LegitiLibAPI INSTANCE;
    private World world;

    private LegitiLibAPI() {

    }

    public static LegitiLibAPI getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LegitiLibAPI();
        }
        return INSTANCE;
    }

    public boolean onLegitimoose() {
        ClientPacketListener connection = Minecraft.getInstance().getConnection();

        return connection != null
                && connection.getConnection().getRemoteAddress() instanceof InetSocketAddress address
                && address.getAddress().getHostAddress().equals("170.205.24.42");
    }

    /**
     * @return The legitimoose {@link World} the client is currently online in.
     */
    public @Nullable World getWorld() {
        return world;
    }

    void setWorld(World world) {
        this.world = world;
        WorldChangedEvent.EVENT.invoker().onWorldJoin(world);
    }
}
