package pw.stapleton.tt.events;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import pw.stapleton.tt.api.Forge;
import pw.stapleton.tt.colour.Colourway;
import pw.stapleton.tt.util.Config;

import java.util.Map;

public class TooltipEventHandler {

    private Map<String, String> controlDefaultColours(RenderTooltipEvent.Color event) {
        if (Config.CONTROL_DEFAULT_COLOURS.get()) {
            if (!Forge.getCategories().containsKey(Items.AIR)) {
                Forge.getCategories().putIfAbsent(Items.AIR, getOriginals(event));
            }
            return Forge.get(Items.AIR);
        }

        return getOriginals(event);
    }

    private Map<String, String> getOriginals(RenderTooltipEvent.Color event) {
        return new Colourway(
                Integer.toHexString(event.getOriginalBackgroundStart()),
                Integer.toHexString(event.getOriginalBackgroundEnd()),
                Integer.toHexString(event.getOriginalBorderStart()),
                Integer.toHexString(event.getOriginalBorderEnd())).get();
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onTooltipColor(RenderTooltipEvent.Color event) {
        Item i = event.getItemStack().getItem();

        // itemMapHexCodes is sourced from ITEM_MAP if the item exists already
        // if the item doesn't exist in ITEM_MAP then check the config if we want to even set a default colour
        // if CONTROL_DEFAULT_COLOUR is true, return first item in ITEM_MAP, if no first item, return original colours
        // if CONTROL_DEFAULT_COLOUR is false, return original colours. clearly there are possibly conflicting mods
        Map<String, String> itemMapHexCodes = Forge.getCategories().containsKey(i) ? Forge.getCategories().get(i) : controlDefaultColours(event);

        if (Config.RANDOM_ALL.get()) {
            Forge.getCategories().putIfAbsent(i, Forge.randomColourway());
        } else {
            Forge.getCategories().putIfAbsent(i, new Colourway(
                    Config.RANDOM_BACKGROUND_START.get() ? Forge.randomHexColour() : itemMapHexCodes.get("backStart"),
                    Config.RANDOM_BACKGROUND_END.get() ? Forge.randomHexColour() : itemMapHexCodes.get("backEnd"),
                    Config.RANDOM_BORDER_START.get() ? Forge.randomHexColour() : itemMapHexCodes.get("bordStart"),
                    Config.RANDOM_BORDER_END.get() ? Forge.randomHexColour() : itemMapHexCodes.get("bordEnd")).get());
        }

        colorTooltip(event, itemMapHexCodes);
    }

    public void colorTooltip(RenderTooltipEvent.Color event, Map<String, String> m) {
        event.setBackgroundStart((int) Long.parseLong(m.get("backStart"), 16));
        event.setBackgroundEnd((int) Long.parseLong(m.get("backEnd"), 16));
        event.setBorderStart((int) Long.parseLong(m.get("bordStart"), 16));
        event.setBorderEnd((int) Long.parseLong(m.get("bordEnd"), 16));
    }
}