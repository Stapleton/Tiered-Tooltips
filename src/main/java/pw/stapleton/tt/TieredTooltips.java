package pw.stapleton.tt;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pw.stapleton.tt.colour.Colourway;
import pw.stapleton.tt.events.TooltipEventHandler;
import pw.stapleton.tt.util.RandomHexColour;
import pw.stapleton.tt.util.Config;
import pw.stapleton.tt.util.Reference;

import java.util.*;
import java.util.regex.Pattern;

@Mod(Reference.MODID)
public class TieredTooltips {

    public static final Map<Item, Map<String, String>> ITEM_MAP = new HashMap<>();
    public static final RandomHexColour RANDOM_HEX_COLOUR = new RandomHexColour();
    public static Logger Logger = LogManager.getLogger(Reference.MOD_NAME);
    public static TieredTooltips INSTANCE;

    public TieredTooltips() {
        IEventBus ModEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(new TooltipEventHandler());
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);

        ModEventBus.register(this);
        ModEventBus.addListener(this::processConfig);
        ModEventBus.addListener(this::reloadConfig);
    }

    public void reloadConfig(ModConfigEvent.Reloading event) {
        ITEM_MAP.clear();
        loadConfig();
    }

    public void processConfig(FMLCommonSetupEvent event) {
        loadConfig();
    }

    private void loadConfig() {
        for (String category : Config.COLOUR_CATEGORIES.get()) {
            //ForgeConfigSpec.BooleanValue randomAll = Config.RANDOM_ALL;

            String[] categories = category.split(Pattern.quote("|"));
            Map<String, String> colourway = parseColourway(categories[0]);
            ArrayList<Item> items = parseCategory(categories[1]);

            for (Item item : items) {
                /*if (randomAll.get().equals(true)) {
                    ITEM_MAP.put(i, RANDOM_HEX_COLOUR.randomAll());
                    continue;
                }*/
                ITEM_MAP.put(item, colourway);
            }
            //Logger.info(ITEM_MAP.toString());
        }
    }

    private ArrayList<Item> parseCategory(String rawCategory) {
        String[] parsedCategory = rawCategory.split(",");
        ArrayList<Item> items = new ArrayList<>();

        for (String category : parsedCategory) {
            try {
                items.add(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(category))).getItem());
            } catch (Error e) {
                Logger.error("Malformed Item IDs in one of your coloured categories." +
                        "\nInvalid Category: '" + category + "'");
            }
        }

        return items;
    }

    private Map<String, String> parseColourway(String rawColourway) {
        String[] parsedColourway = rawColourway.split(",");
        return new Colourway(parsedColourway[0], parsedColourway[1], parsedColourway[2], parsedColourway[3]).get();
    }
}