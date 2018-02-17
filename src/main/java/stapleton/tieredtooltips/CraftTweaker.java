package stapleton.tieredtooltips;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.TieredTooltips")
public class CraftTweaker {

    @ZenMethod
    public static void colourStage(String stage, String background, String borderStart, String borderEnd) {
        CraftTweakerAPI.apply(new ActionTooltipColor(stage, background, borderStart, borderEnd));
    }
}
