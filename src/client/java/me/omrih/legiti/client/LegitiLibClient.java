package me.omrih.legiti.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LegitiLibClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("LegitiLib");
    private static final ExecutorService threadPool = Executors.newCachedThreadPool();

    @Override
    public void onInitializeClient() {
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }
}
