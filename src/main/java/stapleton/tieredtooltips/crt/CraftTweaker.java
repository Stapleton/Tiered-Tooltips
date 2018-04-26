package stapleton.tieredtooltips.crt;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.tieredtooltips")
public class CraftTweaker {

    @ZenMethod
    public static void colorStage(String stage, String background, String borderStart, String borderEnd) {
        CraftTweakerAPI.apply(new ActionTooltipColor(stage, background, borderStart, borderEnd));
    }
}