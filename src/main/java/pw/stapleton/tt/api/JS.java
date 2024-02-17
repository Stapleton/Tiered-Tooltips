package pw.stapleton.tt.api;

import dev.latvian.mods.kubejs.item.ItemStackJS;
import net.minecraft.world.item.Items;
import pw.stapleton.tt.TieredTooltips;

import java.util.HashMap;
import java.util.Map;

public class JS extends Common {
    public static Map<String, String> get(ItemStackJS item) {
        return TieredTooltips.ITEM_MAP.get(item.getItem());
    }

    public static void setDefault(HashMap<String, String> colourway) {
        TieredTooltips.ITEM_MAP.clear();
        TieredTooltips.ITEM_MAP.put(Items.AIR, colourway);
    }

    public static void add(ItemStackJS item, HashMap<String, String> colourway) {
        TieredTooltips.ITEM_MAP.put(item.getItem(), colourway);
    }

    public static void remove(ItemStackJS item) {
        TieredTooltips.ITEM_MAP.remove(item.getItem());
    }
}