package net.mrscauthd.beyond_earth.compat.tinkers;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.mrscauthd.beyond_earth.compat.CompatibleMod;

public class TinkersCompat extends CompatibleMod
{
	public static final String MODID = "tconstruct";

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

	@Override
	public String getModID()
	{
		return MODID;
	}

	@Override
	protected void onLoad()
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::onGatherData);
		TinkersBeyondEarthFluids.FLUIDS.register(bus);
	}

	public void onGatherData(GatherDataEvent event)
	{
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeServer() == true)
		{
			generator.addProvider(new SmelteryRecipesGenerator(generator));
			generator.addProvider(new FluidTagGenerator(generator, existingFileHelper));
		}

	}

}
