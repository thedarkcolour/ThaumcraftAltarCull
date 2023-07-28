package thedarkcolour.tcaltarcull;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static Configuration configuration;

    public static int maxAltarRenderDistance = 5;
    public static int cullUpdateInterval = 10;
    public static boolean disableRenderEntirely = false;

    public static void initConfig(File configFile) {
        configuration = new Configuration(configFile);

        reloadConfig();
    }

    public static void reloadConfig() {
        Configuration configuration = Config.configuration;
        if (configuration == null) return;

        maxAltarRenderDistance = configuration.getInt(
            "maxAltarRenderDistance",
            Configuration.CATEGORY_GENERAL,
            maxAltarRenderDistance,
            0,
            1024,
            "The maximum distance the player can be from the altar before its stabilizers are culled. (default 5)");
        cullUpdateInterval = configuration.getInt(
            "cullUpdateInterval",
            Configuration.CATEGORY_GENERAL,
            cullUpdateInterval,
            1,
            20,
            "The N value to use for running culling checks every N ticks");
        disableRenderEntirely = configuration.getBoolean(
            "disableRenderEntirely",
            Configuration.CATEGORY_GENERAL,
            disableRenderEntirely,
            "Whether to just disable rendering of altar stabilizers entirely without using culling checks. If you don't care about what your altar room looks like, then use this option!"
        );

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
