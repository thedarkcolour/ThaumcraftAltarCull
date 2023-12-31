package thedarkcolour.tcaltarcull.mixin;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;

import org.spongepowered.asm.mixin.Mixin;

import thaumcraft.common.tiles.TileCrystal;
import thedarkcolour.tcaltarcull.AltarBounds;
import thedarkcolour.tcaltarcull.Config;
import thedarkcolour.tcaltarcull.TcAltarCull;

@Mixin({ TileCrystal.class, TileEntitySkull.class })
public class MixinTileCrystalTileEntitySkull extends TileEntity {
    public boolean shouldRenderInPass(int pass) {
        if (super.shouldRenderInPass(pass)) {
            for (AltarBounds altarBounds : TcAltarCull.TRACKED) {
                if (Config.disableRenderEntirely) return false;

                if ((xCoord >= altarBounds.minX && xCoord <= altarBounds.maxX)
                    && (yCoord >= altarBounds.minY && yCoord <= altarBounds.maxY)
                    && (zCoord >= altarBounds.minZ && zCoord <= altarBounds.maxZ)) {
                    return altarBounds.shouldRender;
                }
            }

            return true;
        }
        return false;
    }
}
