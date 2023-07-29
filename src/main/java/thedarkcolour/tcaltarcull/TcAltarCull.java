package thedarkcolour.tcaltarcull;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(
    modid = Tags.MODID,
    version = Tags.VERSION,
    name = Tags.MODNAME,
    dependencies = "required-after:Thaumcraft",
    guiFactory = "thedarkcolour.tcaltarcull.TcAltarCullGuiFactory", acceptedMinecraftVersions = "[1.7.10]"
)
public class TcAltarCull {
    public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);
    public static final List<AltarBounds> TRACKED = new ArrayList<>();

    public TcAltarCull() {
        FMLCommonHandler.instance().bus().register(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.initConfig(event.getSuggestedConfigurationFile());
    }
}
