package thedarkcolour.tcaltarcull;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;

public class TcAltarCullConfigGui extends GuiConfig {
    public TcAltarCullConfigGui(GuiScreen parentScreen) {
        super(parentScreen, new ArrayList<>(new ConfigElement<>(Config.configuration.getCategory("general")).getChildElements()), Tags.MODID, false, false, I18n.format("forge.configgui.forgeConfigTitle"));
    }
}
