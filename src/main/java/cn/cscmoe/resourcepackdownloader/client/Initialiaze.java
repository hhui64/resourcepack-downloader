package cn.cscmoe.resourcepackdownloader.client;

import cn.cscmoe.resourcepackdownloader.Config;
import cn.cscmoe.resourcepackdownloader.ResourcepackDownloader;
import net.minecraft.client.Minecraft;

public class Initialiaze {
    public static void init() {
        Minecraft minecraft = Minecraft.getInstance();

        try {
            String minecraftPath = minecraft.gameDirectory.getAbsolutePath();
            ResourcepackDownloader.LOGGER.info("Minecraft Game Directory >> {}", minecraftPath);
            Config.remoteResourcepacks.forEach(
                    (remoteResourcepacks) -> ResourcepackDownloader.LOGGER.info("Remote Resourcepacks >> {}",
                            remoteResourcepacks.toString()));
        } catch (Exception e) {
            ResourcepackDownloader.LOGGER.error("Get Minecraft Game Directory Failed!");
        }
    }
}
