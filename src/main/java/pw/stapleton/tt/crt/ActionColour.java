package pw.stapleton.tt.crt;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.action.base.IAction;
import pw.stapleton.tt.api.Forge;
import pw.stapleton.tt.colour.Colourway;
import net.minecraft.world.item.Item;

public class ActionColour implements IAction {

    private final Item item;
    private final String bgs;
    private final String bge;
    private final String bds;
    private final String bde;

    public ActionColour(IIngredient ingredient, String backgroundStart, String backgroundEnd, String borderStart, String borderEnd) {
        this.item = ingredient.asVanillaIngredient().getItems()[0].getItem();
        this.bgs = backgroundStart;
        this.bge = backgroundEnd;
        this.bds = borderStart;
        this.bde = borderEnd;
    }

    @Override
    public void apply() {
        Forge.add(item, new Colourway(bgs,bge,bds,bde).get());
    }

    @Override
    public String describe() {
        return "Created a colourway for " + item.toString();
    }
}