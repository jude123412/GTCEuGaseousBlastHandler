package gtceugbh.loaders.recipe;

import static gregtech.api.recipes.RecipeMaps.FUSION_RECIPES;
import static gregtech.api.unification.material.Materials.Oganesson;

import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.material.Materials;

public class FusionRecipes {

    public static void registerFusionRecipes() {
        if (Oganesson.hasFluid()) {
            FUSION_RECIPES.recipeBuilder()
                    .fluidInputs(Materials.Vanadium.getFluid(FluidStorageKeys.LIQUID, 125))
                    .fluidInputs(Materials.Americium.getFluid(FluidStorageKeys.LIQUID, 125))
                    .fluidOutputs(Materials.Oganesson.getFluid(FluidStorageKeys.GAS, 250))
                    .duration(200)
                    .EUt(65565)
                    .EUToStart(600_000_000)
                    .buildAndRegister();

        }
    }
}
