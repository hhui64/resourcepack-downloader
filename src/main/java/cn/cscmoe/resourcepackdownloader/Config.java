package cn.cscmoe.resourcepackdownloader;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = ResourcepackDownloader.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> REPO_STRINGS = BUILDER
            .comment("A list of remote resourcepack repos.")
            .defineListAllowEmpty("repos", List.of(), Config::validateRepoName);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static Set<String> repos;

    private static boolean validateRepoName(final Object obj) {
        if (obj instanceof String) {
            String repo = (String) obj;
            String regex = "^(http|https)://([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}(/[a-zA-Z0-9-_.?=/#%&]*)?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(repo);

            return matcher.matches();
        }

        return false;
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        repos = REPO_STRINGS.get().stream().collect(Collectors.toSet());
    }
}
