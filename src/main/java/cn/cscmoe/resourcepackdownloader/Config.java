package cn.cscmoe.resourcepackdownloader;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.cscmoe.resourcepackdownloader.util.ValidationHelper;

@Mod.EventBusSubscriber(modid = ResourcepackDownloader.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> REMOTE_RESOURCEPACKS_STRINGS = BUILDER
            .comment("A list of remote resourcepacks.")
            .defineListAllowEmpty("remoteResourcepacks", List.of(),
                    (obj) -> obj instanceof String && ValidationHelper.isURL((String) obj));

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static Set<String> remoteResourcepacks;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        remoteResourcepacks = REMOTE_RESOURCEPACKS_STRINGS.get().stream().collect(Collectors.toSet());
    }
}
