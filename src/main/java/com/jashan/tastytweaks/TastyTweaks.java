package com.jashan.tastytweaks;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
public class TastyTweaks implements ModInitializer {
	public static final String MOD_ID = "tasty-tweaks";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Item CORN = new Item(new Item.Properties()
		.food(new FoodProperties.Builder()
			.nutrition(4)
			.saturationModifier(0.5f)
			.build()
	));

	public static final Item APPLE_PIE = new Item(new Item.Properties()
		.food(new FoodProperties.Builder()
			.nutrition(8)
			.saturationModifier(0.9f)
			.build()
	));

	

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Enjoy the tasty food!");
	}
}