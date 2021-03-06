package com.huto.harmonicresonance.recipe;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.huto.harmonicresonance.init.BlockInit;
import com.huto.harmonicresonance.init.ItemInit;
import com.hutoslib.common.item.HutosLibItemInit;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class ModInscriberRecipes {
	public static final List<RecipeAutoInscriber> inscriberRecipies = new ArrayList<>();

	public static RecipeAutoInscriber recipeAdornmentdObsidianIron, recipeAdornmentdObsidianObsidian,
			recipeActivatedObsidian;

	public static void init() {

		recipeAdornmentdObsidianIron = registerRecipe(new ResourceLocation("runed_obsidian_iron"),
				new ItemStack(BlockInit.runed_obsidian.get(), 1), 50, Ingredient.fromItems(Blocks.OBSIDIAN),
				Ingredient.fromItems(HutosLibItemInit.iron_knapper.get()));
		recipeAdornmentdObsidianObsidian = registerRecipe(new ResourceLocation("runed_obsidian_obsidian"),
				new ItemStack(BlockInit.runed_obsidian.get(), 1), 25, Ingredient.fromItems(Blocks.OBSIDIAN),
				Ingredient.fromItems(ItemInit.obsidian_knapper.get()));
		recipeActivatedObsidian = registerRecipe(new ResourceLocation("activated_obsidian_obsidian"),
				new ItemStack(BlockInit.activated_obsidian.get(), 1), 25, Ingredient.fromItems(Blocks.CRYING_OBSIDIAN),
				Ingredient.fromItems(ItemInit.obsidian_knapper.get()));

	}

	public static RecipeAutoInscriber registerRecipe(ResourceLocation rl, ItemStack output, float vibes,
			Ingredient... inputs) {
		Preconditions.checkArgument(inputs.length <= 2);
		RecipeAutoInscriber recipe = new RecipeAutoInscriber(rl, output, vibes, inputs);
		inscriberRecipies.add(recipe);
		return recipe;
	}

}
