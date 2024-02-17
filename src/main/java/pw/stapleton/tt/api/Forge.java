package pw.stapleton.tt.api;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import pw.stapleton.tt.TieredTooltips;

import java.util.Map;

public class Forge extends Common {
    public static Map<String, String> get(Item item) {
        return TieredTooltips.ITEM_MAP.get(item);
    }

    public static void setDefault(Map<String, String> colourway) {
        TieredTooltips.ITEM_MAP.clear();
        TieredTooltips.ITEM_MAP.put(Items.AIR, colourway);
    }

    public static void add(Item item, Map<String, String> colourway) {
        TieredTooltips.ITEM_MAP.put(item, colourway);
    }

    public static void remove(Item item) {
        TieredTooltips.ITEM_MAP.remove(item);
    }

}