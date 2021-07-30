
package com.huto.harmonicresonance.jei;

import javax.annotation.Nonnull;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.init.BlockInit;
import com.huto.harmonicresonance.init.ItemInit;
import com.huto.harmonicresonance.recipe.ModFuserRecipies;
import com.huto.harmonicresonance.recipe.ModHarmonizerRecipes;
import com.huto.harmonicresonance.recipe.ModInscriberRecipes;
import com.huto.harmonicresonance.recipe.ModResonatorRecipies;
import com.huto.harmonicresonance.recipe.ModWandRecipies;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {
	private static final ResourceLocation ID = new ResourceLocation(HarmonicResonance.MOD_ID, "main");

	@Override
	public void registerItemSubtypes(@Nonnull ISubtypeRegistration registry) {
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new WandMakerRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new ResonatorRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new VibeFuserRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new AutoInscriberRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new HarmonizerRecipeCategory(registry.getJeiHelpers().getGuiHelper()));

	}

	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
	}

	@Override
	public void registerRecipes(@Nonnull IRecipeRegistration registry) {
		registry.addRecipes(ModWandRecipies.wandMakerRecipies, WandMakerRecipeCategory.UID);
		registry.addRecipes(ModResonatorRecipies.resonatorRecipies, ResonatorRecipeCategory.UID);
		registry.addRecipes(ModFuserRecipies.fuserRecipies, VibeFuserRecipeCategory.UID);
		registry.addRecipes(ModInscriberRecipes.inscriberRecipies, AutoInscriberRecipeCategory.UID);
		registry.addRecipes(ModHarmonizerRecipes.harmonizerRecipes, HarmonizerRecipeCategory.UID);
		registry.addIngredientInfo(new ItemStack(ItemInit.anti_tear.get()), VanillaTypes.ITEM,
				"Can also be created by tossing Somnolent dust on the ground with glowstone dust and redstone dust");
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registry) {
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {

		registry.addRecipeCatalyst(new ItemStack(BlockInit.wand_maker.get()), WandMakerRecipeCategory.UID);
		registry.addRecipeCatalyst(new ItemStack(BlockInit.vibe_resonator.get()), ResonatorRecipeCategory.UID);
		registry.addRecipeCatalyst(new ItemStack(BlockInit.vibratory_fuser.get()), VibeFuserRecipeCategory.UID);
		registry.addRecipeCatalyst(new ItemStack(BlockInit.auto_inscriber.get()), AutoInscriberRecipeCategory.UID);
		registry.addRecipeCatalyst(new ItemStack(BlockInit.crystal_harmonizer.get()), HarmonizerRecipeCategory.UID);

	}

	@Nonnull
	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}
}