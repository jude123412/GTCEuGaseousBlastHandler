package gtceugbh.loaders.recipe.handlers;

import static gregtech.api.GTValues.MV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES;
import static gregtech.api.unification.material.Materials.Argon;
import static gregtech.api.unification.material.Materials.Helium;
import static gregtech.api.unification.material.Materials.Krypton;
import static gregtech.api.unification.material.Materials.Neon;
import static gregtech.api.unification.material.Materials.Nitrogen;
import static gregtech.api.unification.material.Materials.Oganesson;
import static gregtech.api.unification.material.Materials.Radon;
import static gregtech.api.unification.material.Materials.Xenon;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.ingot;
import static gregtech.api.unification.ore.OrePrefix.ingotHot;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.IngotProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;

public class MaterialRecipeHandler {

    static void register() {
        dust.addProcessingHandler(PropertyKey.BLAST, MaterialRecipeHandler::processEBFRecipe);
    }

    static void processEBFRecipe(OrePrefix dustPrefix, Material material, BlastProperty property) {
        boolean hasHotIngot = OrePrefix.ingotHot.doGenerateItem(material);
        ItemStack ingotStack = OreDictUnifier.get(hasHotIngot ? OrePrefix.ingotHot : OrePrefix.ingot, material);

        int blastTemp = property.getBlastTemperature();
        int duration = property.getDurationOverride();
        int energy = property.getEUtOverride();
        int vacuumEnergy = property.getVacuumEUtOverride() != -1 ? property.getVacuumEUtOverride() : VA[MV];
        int vacuumDuration = property.getVacuumDurationOverride() != -1 ? property.getVacuumDurationOverride() :
                (int) material.getMass() * 3;

        IngotProperty ingotProperty = material.getProperty(PropertyKey.INGOT);

        if (duration <= 0) {
            duration = Math.max(1, (int) (material.getMass() * blastTemp / 50L));
        }

        if (energy <= 0) {
            energy = VA[MV];
        }

        // Recipe Removal to solve Conflicts in Electric Blast Furnace
        // Must Be run After Processing Handler Recipes are made!
        // Removes all Recipes that produce an Ingot or Hot Ingot With either Programmed Circuit 1 or 2

        if (material.hasProperty(PropertyKey.BLAST)) {
            removeRecipesByInputs(BLAST_RECIPES,
                    OreDictUnifier.get(dustPrefix, material),
                    IntCircuitIngredient.getIntegratedCircuit(1));

            removeRecipesByInputs(BLAST_RECIPES, new ItemStack[] {
                    OreDictUnifier.get(dustPrefix, material),
                    IntCircuitIngredient.getIntegratedCircuit(2)
            }, new FluidStack[] {
                    Nitrogen.getFluid(1000)
            });

            removeRecipesByInputs(BLAST_RECIPES, new ItemStack[] {
                    OreDictUnifier.get(dustPrefix, material),
                    IntCircuitIngredient.getIntegratedCircuit(2)
            }, new FluidStack[] {
                    Helium.getFluid(100)
            });

            removeRecipesByInputs(BLAST_RECIPES, new ItemStack[] {
                    OreDictUnifier.get(dustPrefix, material),
                    IntCircuitIngredient.getIntegratedCircuit(2)
            }, new FluidStack[] {
                    Argon.getFluid(50)
            });

            removeRecipesByInputs(BLAST_RECIPES, new ItemStack[] {
                    OreDictUnifier.get(dustPrefix, material),
                    IntCircuitIngredient.getIntegratedCircuit(2)
            }, new FluidStack[] {
                    Neon.getFluid(25)
            });

            removeRecipesByInputs(BLAST_RECIPES, new ItemStack[] {
                    OreDictUnifier.get(dustPrefix, material),
                    IntCircuitIngredient.getIntegratedCircuit(2)
            }, new FluidStack[] {
                    Krypton.getFluid(10)
            });

            removeRecipesByInputs(VACUUM_RECIPES, new ItemStack[] {
                    OreDictUnifier.get(ingotHot, material),
            }, new FluidStack[] {
                    Helium.getFluid(FluidStorageKeys.LIQUID, 500)
            });

            if (ingotProperty.getMagneticMaterial() != null) {
                removeRecipesByInputs(BLAST_RECIPES,
                        OreDictUnifier.get(dustPrefix, ingotProperty.getMagneticMaterial()),
                        IntCircuitIngredient.getIntegratedCircuit(1));

                removeRecipesByInputs(BLAST_RECIPES, new ItemStack[] {
                        OreDictUnifier.get(dustPrefix, ingotProperty.getMagneticMaterial()),
                        IntCircuitIngredient.getIntegratedCircuit(2)
                }, new FluidStack[] {
                        Nitrogen.getFluid(1000)
                });

                removeRecipesByInputs(BLAST_RECIPES, new ItemStack[] {
                        OreDictUnifier.get(dustPrefix, ingotProperty.getMagneticMaterial()),
                        IntCircuitIngredient.getIntegratedCircuit(2)
                }, new FluidStack[] {
                        Helium.getFluid(100)
                });

                removeRecipesByInputs(BLAST_RECIPES, new ItemStack[] {
                        OreDictUnifier.get(dustPrefix, ingotProperty.getMagneticMaterial()),
                        IntCircuitIngredient.getIntegratedCircuit(2)
                }, new FluidStack[] {
                        Argon.getFluid(50)
                });

                removeRecipesByInputs(BLAST_RECIPES, new ItemStack[] {
                        OreDictUnifier.get(dustPrefix, ingotProperty.getMagneticMaterial()),
                        IntCircuitIngredient.getIntegratedCircuit(2)
                }, new FluidStack[] {
                        Neon.getFluid(25)
                });

                removeRecipesByInputs(BLAST_RECIPES, new ItemStack[] {
                        OreDictUnifier.get(dustPrefix, ingotProperty.getMagneticMaterial()),
                        IntCircuitIngredient.getIntegratedCircuit(2)
                }, new FluidStack[] {
                        Krypton.getFluid(10)
                });
            }
        }

        // New Magnetic Material Smelting
        if (ingotProperty.getMagneticMaterial() != null) {
            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, ingotProperty.getMagneticMaterial(), 1)
                    .outputs(ingotStack)
                    .circuitMeta(1)
                    .blastFurnaceTemp(blastTemp)
                    .duration(duration).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, ingotProperty.getMagneticMaterial(), 1)
                    .outputs(ingotStack)
                    .fluidInputs(Nitrogen.getFluid(1000))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.80)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, ingotProperty.getMagneticMaterial(), 1)
                    .outputs(ingotStack)
                    .fluidInputs(Helium.getFluid(1000))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.72)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, ingotProperty.getMagneticMaterial(), 1)
                    .outputs(ingotStack)
                    .fluidInputs(Argon.getFluid(850))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.64)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, ingotProperty.getMagneticMaterial(), 1)
                    .outputs(ingotStack)
                    .fluidInputs(Radon.getFluid(700))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.56)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, ingotProperty.getMagneticMaterial(), 1)
                    .outputs(ingotStack)
                    .fluidInputs(Neon.getFluid(550))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.48)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, ingotProperty.getMagneticMaterial(), 1)
                    .outputs(ingotStack)
                    .fluidInputs(Krypton.getFluid(400))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.40)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, ingotProperty.getMagneticMaterial(), 1)
                    .outputs(ingotStack)
                    .fluidInputs(Xenon.getFluid(250))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.32)).EUt(energy)
                    .buildAndRegister();

            if (Oganesson.hasFluid()) BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, ingotProperty.getMagneticMaterial(), 1)
                    .outputs(ingotStack)
                    .fluidInputs(Oganesson.getFluid(100))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.24)).EUt(energy)
                    .buildAndRegister();
        }

        if (material.hasProperty(PropertyKey.BLAST)) {
            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, material, 1)
                    .outputs(ingotStack)
                    .circuitMeta(1)
                    .blastFurnaceTemp(blastTemp)
                    .duration(duration).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, material, 1)
                    .outputs(ingotStack)
                    .fluidInputs(Nitrogen.getFluid(1000))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.8)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, material, 1)
                    .outputs(ingotStack)
                    .fluidInputs(Helium.getFluid(1000))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.72)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, material, 1)
                    .outputs(ingotStack)
                    .fluidInputs(Argon.getFluid(850))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.64)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, material, 1)
                    .outputs(ingotStack)
                    .fluidInputs(Radon.getFluid(700))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.56)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, material, 1)
                    .outputs(ingotStack)
                    .fluidInputs(Neon.getFluid(550))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.48)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, material, 1)
                    .outputs(ingotStack)
                    .fluidInputs(Krypton.getFluid(400))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.40)).EUt(energy)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, material, 1)
                    .outputs(ingotStack)
                    .fluidInputs(Xenon.getFluid(250))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.32)).EUt(energy)
                    .buildAndRegister();

            if (Oganesson.hasFluid()) BLAST_RECIPES.recipeBuilder()
                    .input(dustPrefix, material, 1)
                    .outputs(ingotStack)
                    .fluidInputs(Oganesson.getFluid(100))
                    .circuitMeta(2)
                    .blastFurnaceTemp(blastTemp)
                    .duration((int) (duration * 0.24)).EUt(energy)
                    .buildAndRegister();

            VACUUM_RECIPES.recipeBuilder()
                    .input(ingotHot, material, 1)
                    .output(ingot, material, 1)
                    .duration(vacuumDuration)
                    .EUt(vacuumEnergy)
                    .buildAndRegister();
        }
    }
}
