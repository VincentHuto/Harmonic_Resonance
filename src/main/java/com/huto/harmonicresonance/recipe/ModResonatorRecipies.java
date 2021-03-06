package com.huto.harmonicresonance.recipe;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.huto.harmonicresonance.init.BlockInit;
import com.huto.harmonicresonance.init.ItemInit;
import com.huto.harmonicresonance.tile.util.EnumEssecenceType;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class ModResonatorRecipies {
	public static final List<RecipeResonator> resonatorRecipies = new ArrayList<>();

	public static RecipeResonator recipeAntiTear, recipeResonantFuel, recipeEnhancedMagatama, recipeNullRod,
			recipeNullCrystal, recipeNullPowder, recipeEnhancedPick, recipeDualPick1, recipeDualPick2, recipeDualPick3,
			recipeEnhancedSword, recipeEnhancedShovel, recipeEnhancedAxe, recipeEnhancedHoe, recipeEnhancedHelm,
			recipeEnhancedChestplate, recipeEnhancedPants, recipeEnhancedBoots, recipeManaGem, recipeChannelingRod,
			recipeManaDust, recipeEnchantedEarth, recipeEnchantedStone, recipeEnchantedMedia, recipeEnchantedSapling,
			recipieGreyIngot, recipieGreyDust, recipeElderHelm, recipeElderChestplate, recipeElderPants,
			recipeElderBoots, recipeAnnoBar, recipeNullSapling, recipeNulEarth, recipeNulStone, recipeNullMedia,
			recipeShattered, recipeEmanating;

	// ReversionRecipies
	public static RecipeResonator recipeAntiTearREVERT, recipeResonantFuelREVERT;

	public static void init() {

		recipeShattered = registerRecipe(new ResourceLocation("recipeshattered"),
				new ItemStack(ItemInit.shattered_ingot.get(), 1), 50, EnumEssecenceType.NULL,
				Ingredient.fromItems(ItemInit.null_ingot.get()));
		recipeEmanating = registerRecipe(new ResourceLocation("recipeemanating"),
				new ItemStack(ItemInit.emanating_ingot.get(), 1), 50, EnumEssecenceType.SOMNOLENT,
				Ingredient.fromItems(ItemInit.channeling_ingot.get()));
		recipeNulStone = registerRecipe(new ResourceLocation("recipenullstone"),
				new ItemStack(BlockInit.anti_stone.get(), 1), 10, EnumEssecenceType.NULL,
				Ingredient.fromItems(Blocks.STONE));
		recipeNulEarth = registerRecipe(new ResourceLocation("recipenullearth"),
				new ItemStack(BlockInit.anti_earth.get(), 1), 10, EnumEssecenceType.NULL,
				Ingredient.fromItems(Blocks.GRASS_BLOCK));
		recipeNullMedia = registerRecipe(new ResourceLocation("recipenullmedia"),
				new ItemStack(BlockInit.anti_media.get(), 1), 10, EnumEssecenceType.NULL,
				Ingredient.fromItems(Blocks.COBBLESTONE));
		recipeAntiTear = registerRecipe(new ResourceLocation("recipeantitear"),
				new ItemStack(ItemInit.anti_tear.get(), 1), 30, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(ItemInit.essence_drop.get()));
		recipeResonantFuel = registerRecipe(new ResourceLocation("reciperesonantfuel"),
				new ItemStack(ItemInit.resonant_fuel.get(), 1), 30, EnumEssecenceType.SOMNOLENT,
				Ingredient.fromItems(Items.COAL));
		recipeEnhancedMagatama = registerRecipe(new ResourceLocation("recipeenhancedmagatama"),
				new ItemStack(ItemInit.enhanced_magatama.get(), 1), 30, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(ItemInit.magatamabead.get()));
		recipeNullRod = registerRecipe(new ResourceLocation("recipenullrod"), new ItemStack(ItemInit.null_rod.get(), 1),
				50, EnumEssecenceType.KARMIC, Ingredient.fromItems(ItemInit.channeling_rod.get()));
		recipeNullCrystal = registerRecipe(new ResourceLocation("recipenullcrystal"),
				new ItemStack(ItemInit.null_crystal.get(), 1), 50, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(ItemInit.somnolent_crystal.get()));
		recipeNullPowder = registerRecipe(new ResourceLocation("recipenullpowder"),
				new ItemStack(ItemInit.nullifying_powder.get(), 1), 50, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(ItemInit.somnolent_powder.get()));
		recipeEnhancedPick = registerRecipe(new ResourceLocation("recipeenhancedpick"),
				new ItemStack(ItemInit.null_pickaxe.get(), 1), 100, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.DIAMOND_PICKAXE));
		recipeEnhancedSword = registerRecipe(new ResourceLocation("recipeenhancedsword"),
				new ItemStack(ItemInit.null_sword.get(), 1), 100, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.DIAMOND_SWORD));
		recipeEnhancedShovel = registerRecipe(new ResourceLocation("recipeenhancedshovel"),
				new ItemStack(ItemInit.null_shovel.get(), 1), 100, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.DIAMOND_SHOVEL));
		recipeEnhancedAxe = registerRecipe(new ResourceLocation("recipeenhancedaxe"),
				new ItemStack(ItemInit.null_axe.get(), 1), 100, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.DIAMOND_AXE));
		recipeEnhancedHoe = registerRecipe(new ResourceLocation("recipeenhancedhoe"),
				new ItemStack(ItemInit.null_hoe.get(), 1), 100, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.DIAMOND_HOE));
		recipeDualPick1 = registerRecipe(new ResourceLocation("recipedualpick1"),
				new ItemStack(ItemInit.duality_pick.get(), 1), 100, EnumEssecenceType.SOMNOLENT,
				Ingredient.fromItems(ItemInit.null_pickaxe.get()));
		recipeDualPick2 = registerRecipe(new ResourceLocation("recipedualpick2"),
				new ItemStack(ItemInit.duality_pick.get(), 1), 200, EnumEssecenceType.BOTH,
				Ingredient.fromItems(Items.DIAMOND_PICKAXE));
		recipeDualPick3 = registerRecipe(new ResourceLocation("recipedualpick3"),
				new ItemStack(ItemInit.duality_pick.get(), 1), 50, EnumEssecenceType.GREY,
				Ingredient.fromItems(Items.NETHERITE_PICKAXE));
		recipeManaGem = registerRecipe(new ResourceLocation("recipesomnolentgem"),
				new ItemStack(ItemInit.somnolent_crystal.get(), 1), 15, EnumEssecenceType.SOMNOLENT,
				Ingredient.fromItems(ItemInit.grey_crystal.get()));
		recipeManaDust = registerRecipe(new ResourceLocation("recipesomnolentdust"),
				new ItemStack(ItemInit.somnolent_powder.get(), 1), 10, EnumEssecenceType.SOMNOLENT,
				Ingredient.fromItems(ItemInit.grey_powder.get()));
		recipeEnchantedStone = registerRecipe(new ResourceLocation("recipesomnolentstone"),
				new ItemStack(BlockInit.somnolent_stone.get(), 1), 10, EnumEssecenceType.SOMNOLENT,
				Ingredient.fromItems(Blocks.STONE));
		recipeEnchantedEarth = registerRecipe(new ResourceLocation("recipesomnolentearth"),
				new ItemStack(BlockInit.somnolent_earth.get(), 1), 10, EnumEssecenceType.SOMNOLENT,
				Ingredient.fromItems(Blocks.GRASS_BLOCK));
		recipeEnchantedMedia = registerRecipe(new ResourceLocation("recipesomnolentmedia"),
				new ItemStack(BlockInit.somnolent_media.get(), 1), 10, EnumEssecenceType.SOMNOLENT,
				Ingredient.fromItems(Blocks.COBBLESTONE));
		recipeEnchantedSapling = registerRecipe(new ResourceLocation("recipesomnolentsapling"),
				new ItemStack(BlockInit.somnolent_sapling.get(), 1), 10, EnumEssecenceType.SOMNOLENT,
				Ingredient.fromItems(Blocks.OAK_SAPLING));
		recipeNullSapling = registerRecipe(new ResourceLocation("recipenullsapling"),
				new ItemStack(BlockInit.anti_sapling.get(), 1), 10, EnumEssecenceType.NULL,
				Ingredient.fromItems(Blocks.OAK_SAPLING));
		recipeEnhancedHelm = registerRecipe(new ResourceLocation("recipeenhancedhelm"),
				new ItemStack(ItemInit.null_helmet.get(), 1), 200, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.DIAMOND_HELMET));
		recipeEnhancedChestplate = registerRecipe(new ResourceLocation("recipeenhancedchestplate"),
				new ItemStack(ItemInit.null_chestplate.get(), 1), 200, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.DIAMOND_CHESTPLATE));
		recipeEnhancedPants = registerRecipe(new ResourceLocation("recipeenhancedpants"),
				new ItemStack(ItemInit.null_leggings.get(), 1), 200, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.DIAMOND_LEGGINGS));
		recipeEnhancedBoots = registerRecipe(new ResourceLocation("recipeenhancedboots"),
				new ItemStack(ItemInit.null_boots.get(), 1), 200, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.DIAMOND_BOOTS));
		recipeElderHelm = registerRecipe(new ResourceLocation("recipeelderhelm"),
				new ItemStack(ItemInit.elder_helmet.get(), 1), 200, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.NETHERITE_HELMET));
		recipeElderChestplate = registerRecipe(new ResourceLocation("recipeelderchestplate"),
				new ItemStack(ItemInit.elder_chestplate.get(), 1), 200, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.NETHERITE_CHESTPLATE));
		recipeElderPants = registerRecipe(new ResourceLocation("recipeelderpants"),
				new ItemStack(ItemInit.elder_leggings.get(), 1), 200, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.NETHERITE_LEGGINGS));
		recipeElderBoots = registerRecipe(new ResourceLocation("recipeelderboots"),
				new ItemStack(ItemInit.elder_boots.get(), 1), 200, EnumEssecenceType.KARMIC,
				Ingredient.fromItems(Items.NETHERITE_BOOTS));
		recipieGreyDust = registerRecipe(new ResourceLocation("recipiegreydust"),
				new ItemStack(ItemInit.grey_powder.get(), 1), 20, EnumEssecenceType.GREY,
				Ingredient.fromItems(ItemInit.somnolent_powder.get()));
		recipieGreyIngot = registerRecipe(new ResourceLocation("recipiegreyingot"),
				new ItemStack(ItemInit.grey_ingot.get(), 1), 20, EnumEssecenceType.GREY,
				Ingredient.fromItems(ItemInit.channeling_ingot.get()));

	}

	public static RecipeResonator createReversionRecipe(RecipeResonator origRecipe) {
		RecipeResonator newRecipe = new RecipeResonator(new ResourceLocation(origRecipe.getId().getPath() + "revert"),
				origRecipe.getInputs().get(0).getMatchingStacks()[0], origRecipe.getVibeUsage() * 1.5f,
				EnumEssecenceType.REVERT, Ingredient.fromStacks(origRecipe.getOutput()));
		return newRecipe;
	}

	public static RecipeResonator registerRecipe(ResourceLocation rl, ItemStack output, float vibes,
			EnumEssecenceType type, Ingredient... inputs) {
		Preconditions.checkArgument(inputs.length <= 1);
		RecipeResonator recipe = new RecipeResonator(rl, output, vibes, type, inputs);
		resonatorRecipies.add(recipe);
		resonatorRecipies.add(createReversionRecipe(recipe));
		return recipe;
	}

}
