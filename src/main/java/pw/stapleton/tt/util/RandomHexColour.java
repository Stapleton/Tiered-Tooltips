package pw.stapleton.tt.util;

import pw.stapleton.tt.colour.Colourway;

import java.util.Map;
import java.util.Random;

public class RandomHexColour {
    Random r;
    public RandomHexColour() {
        r = new Random();
    }

    private String generateColor(Random r) {
        int newColor = 0x1000000 + r.nextInt(0x1000000);
        return "tt" + Integer.toHexString(newColor).substring(1, 7);
    }

    public Map<String, String> randomAll() {
        return new Colourway(this.get(), this.get(), this.get(), this.get()).get();
    }

    public String get() {
        return generateColor(r);
    }
}