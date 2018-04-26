package stapleton.tieredtooltips.crt;

import crafttweaker.IAction;

public class ActionTooltipColour implements IAction {

    public ActionTooltipColour(String stage, String background, String borderStart, String borderEnd) {

    }

    @Override
    public void apply() {
        throw new IllegalArgumentException("**METHOD NAME CHANGE** Use 'mods.tieredtooltips.colorStage' and not 'mods.TieredTooltips.colourStage'!");
    }

    @Override
    public String describe() {
        return "Incorrect Method.";
    }
}
