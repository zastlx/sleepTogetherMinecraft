package club.zastix.sleeptogether.mixin;

import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.block.BlockState;
import net.minecraft.block.BedBlock;

@Mixin(BedBlock.class)
public class BedBlockMixin {
    @Redirect(
            method = "onUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;",
                    ordinal = 3
            )
    )
    private Comparable<?> allowMultiplayerSleeping(BlockState state, Property<?> property) {
        if (property == BedBlock.OCCUPIED) return Boolean.FALSE;

        return state.get(property);
    }
}
