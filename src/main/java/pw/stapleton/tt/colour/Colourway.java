package pw.stapleton.tt.colour;

import java.util.HashMap;
import java.util.Map;

import static pw.stapleton.tt.TieredTooltips.Logger;

public class Colourway {
    private final Map<String, String> colourway;

    public Colourway(String backStart, String backEnd, String bordStart, String bordEnd) {
        colourway = create(backStart, backEnd, bordStart, bordEnd);
    }

    public Map<String, String> get() {
        return colourway;
    }

    private Map<String, String> create(String backStart, String backEnd, String bordStart, String bordEnd) {
        Map<String, String> colourway = new HashMap<>();

        colourway.put("backStart", backStart);
        colourway.put("backEnd", backEnd);
        colourway.put("bordStart", bordStart);
        colourway.put("bordEnd", bordEnd);

        return validate(colourway);
    }

    private Map<String, String> validate(Map<String, String> colourway) {
        for (Map.Entry<String, String> colour : colourway.entrySet()) {
            String k = colour.getKey();
            String v = colour.getValue().toLowerCase();

            if (v.startsWith("#")) v = v.replace("#", "");
            if (v.startsWith("0x")) v = v.replace("0x", "");

            if (v.length() == 8) continue;
            if (v.length() == 6){
                colourway.put(k, "cc"+v);
                continue;
            }
            Logger.error("Invalid hex in one of your colourways. Setting hex to 'ff0000' (BrightRed)" +
                    "\nInvalid Colourway: '" + colourway + "'" +
                    "\nInvalid Hex: '" + k + ": " + v);
            colourway.put(k, "ccff0000");
        }

        return colourway;
    }
}