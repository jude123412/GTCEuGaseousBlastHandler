package gtceugbh.unification;

import static gregtech.api.unification.material.Materials.Oganesson;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;
import gtceugbh.Tags;
import gtceugbh.api.util.GBHLogger;

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
