package net.mrscauthd.beyond_earth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.mrscauthd.beyond_earth.compat.CompatibleManager;

@Mod(BeyondEarthMod.MODID)
public class BeyondEarthMod
{
	public static final String MODID = "beyond_earth";
	public static final Logger LOGGER = LogManager.getLogger();

	public BeyondEarthMod()
	{
		IEventBus fml_bus = FMLJavaModLoadingContext.get().getModEventBus();

		ModInit.ITEMS.register(fml_bus);
		CompatibleManager.visit();
	}

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

}
