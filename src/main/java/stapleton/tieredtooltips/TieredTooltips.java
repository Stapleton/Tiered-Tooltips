package stapleton.tieredtooltips;

import net.darkhax.itemstages.ItemStages;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.*;

@Mod(modid = Reference.MODID, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, clientSideOnly = true, name = Reference.MOD_NAME)
public class TieredTooltips {

    public static final Map<String, Map<String, Long>> colouredStages = new HashMap<>();
    private static String itemStage;

    public static final Logger logger = new Logger("Tiered Tooltips");

    @Mod.Instance("Tiered Tooltips")
    public static TieredTooltips instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        itemStage = ItemStages.getStage(event.getItemStack());
    }

    @SubscribeEvent
    public void onColor(RenderTooltipEvent.Color event) {

        if (colouredStages.containsKey(itemStage)) {
            Map<String, Long> values = colouredStages.get(itemStage);
            event.setBackground((int) (long) values.get("background"));
            event.setBorderStart((int) (long) values.get("borderStart"));
            event.setBorderEnd((int) (long) values.get("borderEnd"));
            //logger.info("Has stage, setting tooltip colours.");
        } else {
            event.setBackground(event.getOriginalBackground());
            event.setBorderStart(event.getOriginalBorderStart());
            event.setBorderEnd(event.getOriginalBorderEnd());
            //logger.info("Does not have stage, setting default tooltip colours.");
            //logger.info(this.itemStage);
            //logger.info(colouredStages);
        }
    }
}
