package cn.cscmoe.resourcepackdownloader;

import com.mojang.logging.LogUtils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

import org.slf4j.Logger;

@Mod(ResourcepackDownloader.MODID)
public class ResourcepackDownloader {
    public static final String MODID = "resourcepackdownloader";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ResourcepackDownloader() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
    public static class DedicatedServerModEvents {
        @SubscribeEvent
        public void onDedicatedServerSetup(FMLDedicatedServerSetupEvent event) {
            LOGGER.warn("THIS MOD IS CLIENT ONLY, BUT IS NOW INSTALLED ON THE SERVER! PLEASE REMOVE IT.");
        }
    }
}
