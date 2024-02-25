package cn.cscmoe.resourcepackdownloader.client;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import cn.cscmoe.resourcepackdownloader.Config;
import cn.cscmoe.resourcepackdownloader.ResourcepackDownloader;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.PackResources;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ResourcepackDownloader.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class Initialiaze {
    public static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        final Minecraft minecraft = Minecraft.getInstance();

        try {
            String minecraftResourcePackPath = minecraft.getResourcePackDirectory().toAbsolutePath().toString();
            LOGGER.info("Minecraft Resource Pack Directory >> {}", minecraftResourcePackPath);
            Config.remoteResourcepacks.forEach(
                    (remoteResourcepacks) -> LOGGER.info("Remote Resourcepacks >> {}",
                            remoteResourcepacks.toString()));
        } catch (Exception e) {
            LOGGER.error("Get Minecraft ResourcePack Directory Failed!");
        }

        Set<PackResources> resourcepacks = minecraft.getResourceManager().listPacks().collect(Collectors.toSet());
        resourcepacks.forEach((resourcepack) -> LOGGER.info("Game Resource Pack >> {}", resourcepack.packId()));
    }
}
