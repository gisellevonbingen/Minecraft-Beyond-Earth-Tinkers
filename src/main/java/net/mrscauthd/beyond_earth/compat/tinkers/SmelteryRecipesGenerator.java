package net.mrscauthd.beyond_earth.compat.tinkers;

import java.util.function.Consumer;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.Tag.Named;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.ModInit;
import slimeknights.mantle.recipe.data.ConsumerWrapperBuilder;
import slimeknights.tconstruct.common.data.BaseRecipeProvider;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ICommonRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;

public class SmelteryRecipesGenerator extends BaseRecipeProvider implements ISmelteryRecipeHelper, ICommonRecipeHelper
{
	private static final String COMPRESSEDS = "compresseds";

	public SmelteryRecipesGenerator(DataGenerator generator)
	{
		super(generator);
	}

	@Override
	public String getModId()
	{
		return BeyondEarthMod.MODID;
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
	{
		this.addMeltingRecipes(consumer);
		this.addCastingRecipes(consumer);
	}

	@Override
	public ICondition tagCondition(String name)
	{
		if (name.startsWith(COMPRESSEDS) == true)
		{
			return new NotCondition(new TagEmptyCondition(this.getModId(), name));
		}
		else
		{
			return super.tagCondition(name);
		}

	}

	@Override
	public Named<Item> getTag(String modId, String name)
	{
		if (name.startsWith(COMPRESSEDS) == true)
		{
			return super.getTag(this.getModId(), name);
		}
		else
		{
			return super.getTag(modId, name);
		}

	}

	@Override
	public Consumer<FinishedRecipe> withCondition(Consumer<FinishedRecipe> consumer, ICondition... conditions)
	{
		ConsumerWrapperBuilder builder = ConsumerWrapperBuilder.wrap();

		for (ICondition condition : conditions)
		{
			builder.addCondition(condition);
		}

		builder.addCondition(modLoaded(TinkersCompat.MODID));
		return builder.build(consumer);
	}

	private void addMeltingRecipes(Consumer<FinishedRecipe> consumer)
	{
		String folder = "smeltery/melting/";
		String metalFolder = folder + "metal/";

		for (BeyondEarthSmelteryCompat compat : BeyondEarthSmelteryCompat.values())
		{
			ForgeFlowingFluid fluid = compat.getFluid().get();
			String name = compat.getName();
			this.metalMelting(consumer, fluid, name, compat.isOre(), compat.hasDust(), metalFolder, true, compat.getByproducts());
			this.compressedMelting(consumer, metalFolder, compat.getFluid().get(), name);
		}

		this.compressedMelting(consumer, metalFolder, TinkerFluids.moltenSteel.get(), "steel");

		MeltingRecipeBuilder.melting(Ingredient.of(ModInit.COAL_LANTERN_ITEM.get()), TinkerFluids.moltenIron.get(), FluidValues.NUGGET * 8).save(this.withCondition(consumer), modResource(metalFolder + "iron/coal_lantern"));
	}

	private void compressedMelting(Consumer<FinishedRecipe> consumer, String folder, Fluid fluid, String name)
	{
		String prefix = folder + "/" + name + "/";
		String tag = COMPRESSEDS;
		this.tagMelting(consumer, fluid, FluidValues.INGOT, tag + "/" + name, 1.0F, prefix + tag, true);
	}

	private void addCastingRecipes(Consumer<FinishedRecipe> consumer)
	{
		String folder = "smeltery/casting/";
		String metalFolder = folder + "metal/";

		for (BeyondEarthSmelteryCompat compat : BeyondEarthSmelteryCompat.values())
		{
			this.metalTagCasting(consumer, compat.getFluid(), compat.getName(), metalFolder, false);
		}

		ItemCastingRecipeBuilder.tableRecipe(ModInit.COAL_LANTERN_ITEM.get()).setFluidAndTime(TinkerFluids.moltenIron, true, FluidValues.NUGGET * 8).setCast(ModInit.COAL_TORCH_ITEM.get(), true).save(this.withCondition(consumer), modResource(metalFolder + "iron/coal_lantern"));
	}

	@Override
	public String getName()
	{
		return BeyondEarthMod.rl("smelteryrecipesgenerator").toString();
	}

}
