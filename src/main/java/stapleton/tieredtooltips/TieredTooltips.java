package stapleton.tieredtooltips;

import net.darkhax.gamestages.event.StagesSyncedEvent;
import net.darkhax.itemstages.ItemStages;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stapleton.tieredtooltips.util.Config;

import java.io.File;
import java.util.*;

@Mod(modid = Reference.MODID, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, clientSideOnly = true, name = Reference.MOD_NAME)
public class TieredTooltips {

    public static final Map<String, Map<String, Long>> coloredStages = new HashMap<>();
    public static Configuration config;

    private Collection<String> unlockedStages;

    private static org.apache.logging.log4j.Logger logger;
    private static Logger Logger() { return LogManager.getLogger(Reference.MOD_NAME); }
    public static void log(Object obj) {
        Logger().log(Level.INFO, obj);
    }

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
    public void onStageSync(StagesSyncedEvent event) {
        unlockedStages = event.getData().getStages();
    }

    private boolean shouldColor(String itemStage) {
        boolean stageUnlocked = unlockedStages.contains(itemStage);
        if (stageUnlocked && Config.respect) return true;
        else if (stageUnlocked && !Config.respect) return true;
        else if (!stageUnlocked && Config.respect) return false;
        else return !stageUnlocked && !Config.respect;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void tooltipColor(RenderTooltipEvent.Color event) {
        String itemStage = ItemStages.getStage(event.getStack());
        if (itemStage != null && coloredStages.containsKey(itemStage) && shouldColor(itemStage)) {
            Map<String, Long> values = coloredStages.get(itemStage);
            event.setBackground((int) (long) values.get("background"));
            event.setBorderStart((int) (long) values.get("borderStart"));
            event.setBorderEnd((int) (long) values.get("borderEnd"));
        }
    }
}
