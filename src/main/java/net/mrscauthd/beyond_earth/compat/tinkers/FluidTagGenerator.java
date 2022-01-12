package net.mrscauthd.beyond_earth.compat.tinkers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.TinkerTags;

public class FluidTagGenerator extends FluidTagsProvider
{
	public FluidTagGenerator(DataGenerator generator, ExistingFileHelper helper)
	{
		super(generator, BeyondEarthMod.MODID, helper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addTags()
	{
		this.tagAll(TinkersBeyondEarthFluids.MOLTEN_DESH);
		this.tagAll(TinkersBeyondEarthFluids.MOLTEN_OSTRUM);

		this.tag(TinkerTags.Fluids.METAL_TOOLTIPS).addTags(TinkersBeyondEarthFluids.MOLTEN_DESH.getForgeTag(), TinkersBeyondEarthFluids.MOLTEN_OSTRUM.getForgeTag());
	}

	/** Tags this fluid using local tags */
	private void tagLocal(FluidObject<?> fluid)
	{
		this.tag(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
	}

	/** Tags this fluid with local and forge tags */
	private void tagAll(FluidObject<?> fluid)
	{
		this.tagLocal(fluid);
		this.tag(fluid.getForgeTag()).addTag(fluid.getLocalTag());
	}

}