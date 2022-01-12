package net.mrscauthd.beyond_earth.compat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.mrscauthd.beyond_earth.compat.tinkers.TinkersCompat;

public class CompatibleManager
{
	public static final List<CompatibleMod> MODS;
	public static final TinkersCompat TINKERS;

	static
	{
		List<CompatibleMod> mods = new ArrayList<>();
		mods.add(TINKERS = new TinkersCompat());

		for (CompatibleMod mod : mods)
		{
			mod.tryLoad();
		}

		MODS = Collections.unmodifiableList(mods);
	}

	public static void visit()
	{

	}

}
