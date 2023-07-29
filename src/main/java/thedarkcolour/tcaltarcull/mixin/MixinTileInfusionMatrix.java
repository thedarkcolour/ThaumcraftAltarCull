package thedarkcolour.tcaltarcull.mixin;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;

import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thedarkcolour.tcaltarcull.AltarBounds;
import thedarkcolour.tcaltarcull.TcAltarCull;

@Mixin(TileInfusionMatrix.class)
public class MixinTileInfusionMatrix extends TileThaumcraft {
    @Override
    public void validate() {
        super.validate();

        World level = this.worldObj;
        if (level != null && level.isRemote) {
            ChunkPosition pos = new ChunkPosition(xCoord, yCoord, zCoord);
            AltarBounds box = new AltarBounds(
                xCoord - 12, yCoord - 10, zCoord - 12,
                xCoord + 12, yCoord + 5, zCoord + 12
            );

            TcAltarCull.TRACKED.put(pos, box);
            TcAltarCull.LOGGER.debug("Now tracking altar at (" + pos.chunkPosX + ", " + pos.chunkPosY + ", " + pos.chunkPosZ + ")");
        }
    }
}
