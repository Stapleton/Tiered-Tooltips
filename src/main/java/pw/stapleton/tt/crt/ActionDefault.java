package pw.stapleton.tt.crt;

import net.minecraft.world.item.Items;
import com.blamejared.crafttweaker.api.action.base.IAction;
import pw.stapleton.tt.colour.Colourway;
import pw.stapleton.tt.api.Forge;

public class ActionDefault implements IAction {
    private final String bgs;
    private final String bge;
    private final String bds;
    private final String bde;

    public ActionDefault(String backgroundStart, String backgroundEnd, String borderStart, String borderEnd) {
        this.bgs = backgroundStart;
        this.bge = backgroundEnd;
        this.bds = borderStart;
        this.bde = borderEnd;
    }

    @Override
    public void apply() {
        Forge.getCategories().clear();
        Forge.add(Items.AIR, new Colourway(bgs,bge,bds,bde).get());
    }

    @Override
    public String describe() {
        return "Cleared all colourways.";
    }

}