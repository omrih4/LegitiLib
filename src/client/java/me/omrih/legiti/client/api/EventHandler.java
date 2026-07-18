package me.omrih.legiti.client.api;

import me.omrih.legiti.client.World;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventHandler {
    private static EventHandler INSTANCE;

    private final Pattern whereami = Pattern.compile("--- World Info ---\\sName: (.*)\\sDescription: ([\\s\\S]*)\\sOwner: (.*)\\sVotes: (.*)\\sVisits: (.*)\\sDate Created: (.*)\\sUUID: (.*) \\(click to copy\\)");
    // Ongoing jam worlds don't have visits/votes
    private final Pattern whereamiHiddenStats = Pattern.compile("--- World Info ---\\sName: (.*)\\sDescription: ([\\s\\S]*)\\sOwner: (.*)\\sDate Created: (.*)\\sUUID: (.*) \\(click to copy\\)");

    private boolean read = false;
    private StringBuilder whereamiString = new StringBuilder();

    private EventHandler() {
        ClientPlayConnectionEvents.JOIN.register(this::onJoinServer);
    }

    public static EventHandler getInstance() {
        if (INSTANCE == null) INSTANCE = new EventHandler();
        return INSTANCE;
    }

    public boolean onSystemMessage(Component content) {
        if (content.getString().equals("--- World Info ---")) {
            read = true;
            whereamiString.append(content.getString()).append("\n");
            return false;
        }

        if (read) {
            if (content.getString().matches("UUID: (.*) \\(click to copy\\)")) {
                whereamiString.append(content.getString());
                Matcher whereamiMatcher = whereami.matcher(whereamiString);
                Matcher whereamiHiddenStatsMatcher = whereamiHiddenStats.matcher(whereamiString);
                if (whereamiMatcher.matches()) {
                    World world = new World(
                            whereamiMatcher.group(1),
                            whereamiMatcher.group(2),
                            whereamiMatcher.group(3),
                            Integer.parseInt(whereamiMatcher.group(4)),
                            Integer.parseInt(whereamiMatcher.group(5)),
                            whereamiMatcher.group(6),
                            whereamiMatcher.group(7)
                    );
                    LegitiLibAPI.getInstance().setWorld(world);
                } else if (whereamiHiddenStatsMatcher.matches()) {
                    World world = new World(
                            whereamiMatcher.group(1),
                            whereamiMatcher.group(2),
                            whereamiMatcher.group(3),
                            0,
                            0,
                            whereamiMatcher.group(4),
                            whereamiMatcher.group(5)
                    );
                    LegitiLibAPI.getInstance().setWorld(world);
                } else {
                    LegitiLibAPI.getInstace().setWorld(null);
                }
                whereamiString = new StringBuilder();
                read = false;
                return false;
            }
            whereamiString.append(content.getString()).append("\n");
            return false;
        }

        return !content.getString().equals("Unknown or incomplete command. See below for error\nwhereami<--[HERE]");
    }

    public void onJoinServer(ClientPacketListener handler, PacketSender sender, Minecraft client) {
        if (!LegitiLibAPI.getInstance().onLegitimoose()) return;
        if (!(net.fabricmc.fabric.impl.command.client.ClientCommandInternals.getActiveDispatcher() == null))
            client.getConnection().sendCommand("whereami");
    }
}
