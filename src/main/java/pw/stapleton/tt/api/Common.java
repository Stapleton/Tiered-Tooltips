package pw.stapleton.tt.api;

import net.minecraft.world.item.Item;
import pw.stapleton.tt.TieredTooltips;
import pw.stapleton.tt.colour.Colourway;

import java.util.Map;

public class Common {
    public static Map<Item, Map<String, String>> getCategories() {
        return TieredTooltips.ITEM_MAP;
    }

    public static Map<String, String> colourway(String backStart, String backEnd, String bordStart, String bordEnd) {
        return new Colourway(backStart, backEnd, bordStart, bordEnd).get();
    }

    public static Map<String, String> randomColourway() {
        return TieredTooltips.RANDOM_HEX_COLOUR.randomAll();
    }

    public static String randomHexColour() {
        return TieredTooltips.RANDOM_HEX_COLOUR.get();
    }
}