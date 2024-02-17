package pw.stapleton.tt.crt;

import com.blamejared.crafttweaker.api.action.base.IAction;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import net.minecraft.world.item.Item;
import pw.stapleton.tt.api.Forge;

public class ActionGet implements IAction {
    private final Item item;

    public ActionGet(IIngredient ingredient) {
        this.item = ingredient.asVanillaIngredient().getItems()[0].getItem();
    }

    @Override
    public void apply() {
        Forge.get(item);
    }

    @Override
    public String describe() {
        return "Retrieved colourway for " + item.toString();
    }

}