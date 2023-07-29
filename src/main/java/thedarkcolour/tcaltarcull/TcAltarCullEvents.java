package thedarkcolour.tcaltarcull;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;

public class TcAltarCullEvents {
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(Tags.MODID)) {
            Config.reloadConfig();
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        World level = Minecraft.getMinecraft().theWorld;
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        if (level == null || player == null) return;

        if (event.phase == TickEvent.Phase.END && level.getWorldTime() % 10 == 0) {
            for (AltarBounds bounds : TcAltarCull.TRACKED) {
                bounds.shouldRender = (player.posX >= (bounds.minX - Config.maxAltarRenderDistance)
                    && player.posX <= (bounds.maxX + Config.maxAltarRenderDistance))
                    && (player.posY >= (bounds.minY - Config.maxAltarRenderDistance)
                    && player.posY <= (bounds.maxY + Config.maxAltarRenderDistance))
                    && (player.posZ >= (bounds.minZ - Config.maxAltarRenderDistance)
                    && player.posZ <= (bounds.maxZ + Config.maxAltarRenderDistance));
            }
        }
    }

    @SubscribeEvent
    public void onLeave(WorldEvent.Unload event) {
        TcAltarCull.TRACKED.clear();
    }
}
