package cn.cscmoe.resourcepackdownloader;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.io.File;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod(ResourcepackDownloader.MODID)
public class ResourcepackDownloader {
    public static final String MODID = "resourcepackdownloader";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ResourcepackDownloader() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
    public static class ServerModEvents {
        @SubscribeEvent
        public void onDedicatedServerSetup(FMLDedicatedServerSetupEvent event) {
            LOGGER.warn("THIS MOD IS CLIENT ONLY, BUT IS NOW INSTALLED ON THE SERVER! PLEASE REMOVE IT.");
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ResourcepackDownloader.init();
        }
    }

    public static void init() {
        Minecraft minecraft = Minecraft.getInstance();

        try {
            String minecraftPath = minecraft.gameDirectory.getAbsolutePath();
            LOGGER.info("Minecraft Game Directory >> {}", minecraftPath);
            Config.remoteResourcepacks.forEach(
                    (remoteResourcepacks) -> LOGGER.info("Remote Resourcepacks >> {}", remoteResourcepacks.toString()));
        } catch (Exception e) {
            LOGGER.error("Get Minecraft Game Directory Failed!");
        }
    }
}
