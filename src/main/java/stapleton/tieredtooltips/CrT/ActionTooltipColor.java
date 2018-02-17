package stapleton.tieredtooltips.CrT;

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
        this.background = "0x" + background;
        this.borderStart = "0x" + borderStart;
        this.borderEnd = "0x" + borderEnd;
    }

    @Override
    public void apply() {

        if (this.stage.isEmpty()) throw new IllegalArgumentException("Empty stage name!");
        if (this.background.isEmpty()) throw new IllegalArgumentException("Empty background colour!");
        if (this.borderStart.isEmpty()) throw new IllegalArgumentException("Empty borderStart colour!");
        if (this.borderEnd.isEmpty()) throw new IllegalArgumentException("Empty borderEnd colour!");

        Map<String, Long> values = new HashMap<>();

        values.put("background", Long.decode(this.background));
        values.put("borderStart", Long.decode(this.borderStart));
        values.put("borderEnd", Long.decode(this.borderEnd));

        TieredTooltips.colouredStages.put(stage, values);
    }

    @Override
    public String describe() {

        return "Changed colour of stage: " + this.stage;
    }
}
