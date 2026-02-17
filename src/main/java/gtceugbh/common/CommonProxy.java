package gtceugbh.common;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import gregtech.api.unification.material.event.MaterialEvent;
import gtceugbh.Tags;
import gtceugbh.api.util.GBHLogger;
import gtceugbh.loaders.recipe.FusionRecipes;
import gtceugbh.loaders.recipe.handlers.RecipeHandler;
import gtceugbh.unification.GBHMaterials;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class CommonProxy {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerMaterials(MaterialEvent event) {
        GBHLogger.log.info("Registering Materials...");
        GBHMaterials.initGBHMaterials();
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        GBHLogger.log.info("Modifying Blast Recipe Handler...");
        RecipeHandler.load();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipesLow(RegistryEvent.Register<IRecipe> event) {
        GBHLogger.log.info("Registering Fusion Recipes...");
        FusionRecipes.registerFusionRecipes();
    }
}
