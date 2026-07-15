package me.omrih.legiti.client.api.event;

import me.omrih.legiti.client.World;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface WorldChangedEvent {
    Event<WorldChangedEvent> EVENT = EventFactory.createArrayBacked(WorldChangedEvent.class,
            (listeners) -> (world) -> {
                for (WorldChangedEvent listener : listeners) {
                    listener.onWorldJoin(world);
                }
            });


    void onWorldJoin(World world);
}
