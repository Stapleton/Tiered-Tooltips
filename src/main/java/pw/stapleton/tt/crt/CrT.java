package pw.stapleton.tt.crt;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Map;

@ZenRegister
@ZenCodeType.Name("mods.tt.CrT")
public class CrT {

    @ZenCodeType.Method
    public static void colour(IIngredient[] ingredients, String backgroundStart, String backgroundEnd, String borderStart, String borderEnd) {
        for (IIngredient ingredient : ingredients) {
            CraftTweakerAPI.apply(new ActionColour(ingredient, backgroundStart, backgroundEnd, borderStart, borderEnd));
        }
    }

    @ZenCodeType.Method
    public static void setDefault(String backgroundStart, String backgroundEnd, String borderStart, String borderEnd) {
        CraftTweakerAPI.apply(new ActionDefault(backgroundStart, backgroundEnd, borderStart, borderEnd));
    }

    @ZenCodeType.Method
    public static void remove(IIngredient[] ingredients) {
        for (IIngredient ingredient : ingredients) {
            CraftTweakerAPI.apply(new ActionRemove(ingredient));
        }
    }

    @ZenCodeType.Method
    public static void get(IIngredient ingredient) {
        CraftTweakerAPI.apply(new ActionGet(ingredient));
    }

    @ZenCodeType.Method
    public static void random(IIngredient[] ingredients) {
        for (IIngredient ingredient : ingredients) {
            CraftTweakerAPI.apply(new ActionRandom(ingredient));
        }
    }
}