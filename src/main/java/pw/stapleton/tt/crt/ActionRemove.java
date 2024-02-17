package pw.stapleton.tt.crt;

import com.blamejared.crafttweaker.api.action.base.IAction;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import net.minecraft.world.item.Item;
import pw.stapleton.tt.api.Forge;

public class ActionRemove implements IAction {
    private final Item item;

    public ActionRemove(IIngredient ingredient) {
        this.item = ingredient.asVanillaIngredient().getItems()[0].getItem();
    }

    @Override
    public void apply() {
        Forge.remove(item);
    }

    @Override
    public String describe() {
        return "Removed a colourway for " + item.toString();
    }
}