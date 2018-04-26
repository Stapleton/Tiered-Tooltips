package stapleton.tieredtooltips.crt;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.TieredTooltips")
public class DeprecatedCraftTweaker {

    @ZenMethod
    public static void colourStage(String stage, String background, String borderStart, String borderEnd) {
        CraftTweakerAPI.apply(new ActionTooltipColour(stage, background, borderStart, borderEnd));
    }
}
