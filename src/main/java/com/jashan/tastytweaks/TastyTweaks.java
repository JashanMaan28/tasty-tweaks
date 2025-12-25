package com.jashan.tastytweaks;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class TastyTweaks implements ModInitializer {
	public static final String MOD_ID = "tasty-tweaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// We declare our items here as static fields
	public static Item CORN;

	@Override
	public void onInitialize() {

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


		LOGGER.info("Enjoy the tasty food!");
	}
}