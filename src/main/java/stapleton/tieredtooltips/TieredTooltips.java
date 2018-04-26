package stapleton.tieredtooltips;

import net.darkhax.gamestages.event.StageDataEvent;
import net.darkhax.itemstages.ItemStages;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stapleton.tieredtooltips.util.Config;
import stapleton.tieredtooltips.util.Logger;

import java.io.File;
import java.util.*;

@Mod(modid = Reference.MODID, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, clientSideOnly = true, name = Reference.MOD_NAME)
public class TieredTooltips {

    public static final Map<String, Map<String, Long>> coloredStages = new HashMap<>();
    private Collection<String> unlockedStages;

    public static final Logger logger = new Logger("Tiered Tooltips");
    public static Configuration config;

    @Mod.Instance("Tiered Tooltips")
    public static TieredTooltips instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), Reference.MOD_NAME + ".cfg"));
        Config.readConfig();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onStageData(StageDataEvent event) {
        unlockedStages = event.getStageData().getUnlockedStages();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void tooltipColor(RenderTooltipEvent.Color event) {
        String itemStage = ItemStages.getStage(event.getStack());
        if (itemStage != null && coloredStages.containsKey(itemStage) && unlockedStages.contains(itemStage)) {
            Map<String, Long> values = coloredStages.get(itemStage);
            event.setBackground((int) (long) values.get("background"));
            event.setBorderStart((int) (long) values.get("borderStart"));
            event.setBorderEnd((int) (long) values.get("borderEnd"));
        }
    }
}
