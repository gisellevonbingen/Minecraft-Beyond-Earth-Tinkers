package net.mrscauthd.beyond_earth;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModInit
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BeyondEarthMod.MODID);

	public static final RegistryObject<Item> COAL_TORCH_ITEM = ITEMS.register("coal_torch", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> COAL_LANTERN_ITEM = ITEMS.register("coal_lantern", () -> new Item(new Item.Properties()));

}
