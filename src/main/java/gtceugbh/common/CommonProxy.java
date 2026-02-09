package gtceugbh.common;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import gtceugbh.Tags;
import gtceugbh.api.util.GBHLogger;
import gtceugbh.loaders.recipe.handlers.RecipeHandler;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class CommonProxy {

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        GBHLogger.log.info("Modifying Blast Recipe Handler...");

        RecipeHandler.load();
    }
}
