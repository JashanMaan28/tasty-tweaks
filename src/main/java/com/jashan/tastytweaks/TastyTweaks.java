package com.jashan.tastytweaks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class TastyTweaks implements ModInitializer {
	public static final String MOD_ID = "tasty-tweaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// We declare our items here as static fields
	public static Item CORN;
	public static Item APPLE_PIE;

	@Override
	public void onInitialize() {

		// CORN
		// Define the consumable component for corn (handles the effect)
		Consumable cornConsumable = Consumables.defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
				new MobEffectInstance(MobEffects.REGENERATION, 20 * 10, 1), // Regeneration II for 10 seconds
				0.8f)) // 80% chance
			.build();

		// Define the food properties for corn (handles nutrition and saturation)
		FoodProperties cornFood = new FoodProperties.Builder()
			.nutrition(4)		// nutrition value
			.saturationModifier(0.5f)		// saturation modifier
			.alwaysEdible()                  // can eat even when full
			.build();

		// Register the corn item
		Identifier cornId = Identifier.fromNamespaceAndPath(MOD_ID, "corn");
		ResourceKey<Item> cornKey = ResourceKey.create(Registries.ITEM, cornId);
		CORN = Registry.register(
			BuiltInRegistries.ITEM,
			cornId,
			new Item(new Item.Properties()
				.setId(cornKey)
				.component(DataComponents.CONSUMABLE, cornConsumable)
				.component(DataComponents.FOOD, cornFood)
			)
		);

		// Add corn to the Food and Drinks creative tab
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS)
			.register(entries -> entries.accept(CORN));

		// APPLE PIE
		// Define the consumable component for apple pie (handles the effect)
		Consumable applePieConsumable = Consumables.defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
				new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20 * 15, 0), // Fire Resistance I for 15 seconds
				0.8f)) // 80% chance
			.build();
		
		// Define the food properties for apple pie (handles nutrition and saturation)
		FoodProperties applePieFood = new FoodProperties.Builder()
			.nutrition(8)
			.saturationModifier(0.9f)
			.alwaysEdible()
			.build();
		
		// Register the apple pie item
		Identifier applePieId = Identifier.fromNamespaceAndPath(MOD_ID, "apple_pie");
		ResourceKey<Item> applePieKey = ResourceKey.create(Registries.ITEM, applePieId);
		APPLE_PIE = Registry.register(
			BuiltInRegistries.ITEM,
			applePieId,
			new Item(new Item.Properties()
				.setId(applePieKey)
				.component(DataComponents.CONSUMABLE, applePieConsumable)
				.component(DataComponents.FOOD, applePieFood)
			)
		);

		// Add apple pie to the Food and Drinks creative tab
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS)
			.register(entries -> entries.accept(APPLE_PIE));

		// CHEESE

		// PIZZA SLICE

		// TOMATO

		// WAFFLE

		// DONUTS

		LOGGER.info("Enjoy the tasty food!");
	}
}