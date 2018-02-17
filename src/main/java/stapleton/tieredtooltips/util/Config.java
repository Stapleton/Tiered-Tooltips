package stapleton.tieredtooltips.util;

import net.minecraftforge.common.config.Configuration;
import stapleton.tieredtooltips.TieredTooltips;

public class Config {

    public static boolean respect;
    public static int configVersion = 1;

    private static final String GENERAL = "General";

    public static void readConfig() {

        Configuration config = TieredTooltips.config;

        try {
            config.load();
            initConfig(config);
        } catch (Exception e) {
            TieredTooltips.logger.error("Unable to load config file!");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    private static void initConfig(Configuration config) {

        config.addCustomCategoryComment(GENERAL, "General Configuration");
        respect = config.getBoolean("RespectLockedStages", Configuration.CATEGORY_GENERAL, false, "If true, the tooltip colouring will have respect for locked stages. (Won't colour the tooltip if the stage is not unlocked)");
        configVersion = config.getInt("Config Version", GENERAL, configVersion, configVersion, configVersion, "DO NOT CHANGE THIS!");
    }
}
