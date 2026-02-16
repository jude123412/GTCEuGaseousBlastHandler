package gtceugbh.unification;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gtceugbh.Tags;
import gtceugbh.api.util.GBHLogger;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.unification.material.Materials.CertusQuartz;
import static gregtech.api.unification.material.Materials.NetherQuartz;
import static gregtech.api.unification.material.Materials.Oganesson;
import static gregtech.api.unification.material.Materials.Oxygen;
import static gregtech.api.unification.material.Materials.Redstone;
import static gregtech.api.unification.material.Materials.Silicon;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class GBHMaterials {

    @NotNull
    public static ResourceLocation gtceugbhId(@NotNull String path) {
        return new ResourceLocation(Tags.MODID, path);
    }

    public static void initGBHMaterials() {
        GBHLogger.log.info("Beginning Material Registry");

        if (!Oganesson.hasFluid()) {
            Oganesson = new Material.Builder(0, gtceugbhId("oganesson"))
                    .gas()
                    .color(0x002857)
                    .element(Elements.Og)
                    .build();
        }
    }
}
