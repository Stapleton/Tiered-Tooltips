package pw.stapleton.tt.crt;

import com.blamejared.crafttweaker.api.action.base.IAction;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import net.minecraft.world.item.Item;
import pw.stapleton.tt.api.Forge;

public class ActionRandom implements IAction {
    private final Item item;

    public ActionRandom(IIngredient ingredient) {
        item = ingredient.asVanillaIngredient().getItems()[0].getItem();
    }


    @Override
    public void apply() {
        Forge.add(item, Forge.randomColourway());
    }

    @Override
    public String describe() {
        return "Created a random colourway for " + item.toString();
    }
}