package stapleton.tieredtooltips;

import crafttweaker.annotations.ZenRegister;
import net.darkhax.itemstages.ItemStages;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.*;

@ZenRegister
@ZenClass("mods.TieredTooltips")
@Mod(modid = Reference.MODID, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, clientSideOnly = true)
public class TieredTooltips {

    public static Map<String, Map<String, String>> colouredStages = new HashMap<>();
    private String itemStage, stage, background, borderStart, borderEnd;

    private static final Logger logger = new Logger("Tiered Tooltips");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @ZenMethod
    public static void colourStage(String stage, String background, String borderStart, String borderEnd) {
    }

    public void addToMap(String stage, String background, String borderStart, String borderEnd) {
        Map<String, String> values = new HashMap<>();
        values.put("background", background);
        values.put("borderStart", borderStart);
        values.put("borderEnd", borderEnd);
        colouredStages.put(stage, values);
        logger.info("colourStage was called!");
    }

    @SubscribeEvent
    public void onTooltip(RenderTooltipEvent event) {
        this.itemStage = ItemStages.getStage(event.getStack());
    }

    @SubscribeEvent
    public void onColor(RenderTooltipEvent.Color color) {
        if (colouredStages.containsKey(this.itemStage)) {
            Map<String, String> values = colouredStages.get(this.itemStage);
            color.setBackground((int) (long) Long.decode(values.get("background")));
            color.setBorderStart((int) (long) Long.decode(values.get("borderStart")));
            color.setBorderEnd((int) (long) Long.decode(values.get("borderEnd")));
            logger.info("Has stage, setting tooltip colours.");
        } else {
            color.setBackground(color.getOriginalBackground());
            color.setBorderStart(color.getOriginalBorderStart());
            color.setBorderEnd(color.getOriginalBorderEnd());
            logger.info("Does not have stage, setting default tooltip colours.");
            logger.info(this.itemStage);
        }
    }
}
