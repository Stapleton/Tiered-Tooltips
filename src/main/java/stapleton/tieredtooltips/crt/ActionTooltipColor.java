package stapleton.tieredtooltips.crt;

import crafttweaker.IAction;
import stapleton.tieredtooltips.TieredTooltips;

import java.util.HashMap;
import java.util.Map;

public class ActionTooltipColor implements IAction {

    private String stage;
    private String background;
    private String borderStart;
    private String borderEnd;

    public ActionTooltipColor(String stage, String background, String borderStart, String borderEnd) {

        this.stage = stage;

        if (background.length() == 6) this.background = "0xf0" + background;
        else if (background.length() == 8) this.background = "0x" + background;
        else throw new IllegalArgumentException("backgroundColor value must be either 6 characters long (RGB Hex format) or 8 characters long (ARGB Hex format)");

        if (borderStart.length() == 6) this.borderStart = "0x50" + borderStart;
        else if (borderStart.length() == 8) this.borderStart = "0x" + borderStart;
        else throw new IllegalArgumentException("borderStart value must be either 6 characters long (RGB Hex format) or 8 characters long (ARGB Hex format)");

        if (borderEnd.length() == 6) this.borderEnd = "0x50" + borderEnd;
        else if (borderEnd.length() == 8) this.borderEnd = "0x" + borderEnd;
        else throw new IllegalArgumentException("borderEnd value must be either 6 characters long (RGB Hex format) or 8 characters long (ARGB Hex format)");
    }

    @Override
    public void apply() {

        if (this.stage.isEmpty()) throw new IllegalArgumentException("Empty stage name!");
        if (this.background.isEmpty()) throw new IllegalArgumentException("Empty background color!");
        if (this.borderStart.isEmpty()) throw new IllegalArgumentException("Empty borderStart color!");
        if (this.borderEnd.isEmpty()) throw new IllegalArgumentException("Empty borderEnd color!");

        Map<String, Long> values = new HashMap<>();

        values.put("background", Long.decode(this.background));
        values.put("borderStart", Long.decode(this.borderStart));
        values.put("borderEnd", Long.decode(this.borderEnd));

        TieredTooltips.coloredStages.put(stage, values);
    }

    @Override
    public String describe() {

        return "Changed color of stage: " + this.stage;
    }
}
