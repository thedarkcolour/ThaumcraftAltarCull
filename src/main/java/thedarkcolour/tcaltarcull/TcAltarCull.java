package thedarkcolour.tcaltarcull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.common.FMLCommonHandler;
import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, guiFactory = "thedarkcolour.tcaltarcull.TcAltarCull$GuiFactory", acceptedMinecraftVersions = "[1.7.10]")
public class TcAltarCull {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);

    public static final Map<ChunkPosition, AltarBounds> TRACKED = new HashMap<>();

    public TcAltarCull() {
        LOGGER.info("Trying to add myself to the event bus! Let's hope it works!");
        FMLCommonHandler.instance().bus().register(this);
    }

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.initConfig(event.getSuggestedConfigurationFile());

        TcAltarCull.LOGGER.info("Found maxAltarRenderDistance " + Config.maxAltarRenderDistance);
        TcAltarCull.LOGGER.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION);
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(Tags.MODID)) {
            // idk if this works :DDDD
            LOGGER.info("hello this works");
            Config.reloadConfig();
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        World level = Minecraft.getMinecraft().theWorld;
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        if (level == null || player == null) return;

        if (event.phase == TickEvent.Phase.END && level.getWorldTime() % 10 == 0) {
            for (AltarBounds bounds : TRACKED.values()) {
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
        TRACKED.clear();
    }

    public static class GuiFactory implements IModGuiFactory {
        @Override
        public void initialize(Minecraft minecraftInstance) {

        }

        @Override
        public Class<? extends GuiScreen> mainConfigGuiClass() {
            return TcAltarCullConfigGui.class;
        }

        @Override
        public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
            return null;
        }

        @Override
        public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
            return null;
        }
    }
}
