package com.huto.harmonicresonance.data;

import java.util.function.Consumer;

import com.huto.harmonicresonance.init.BlockInit;
import com.huto.harmonicresonance.init.ItemInit;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

public class GeneratorRecipes extends RecipeProvider {
	public GeneratorRecipes(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.essence_drop.get(), 2)
				.addIngredient(ItemInit.somnolent_powder.get(), 1)
				.addCriterion("has_somnolent_powder", hasItem(ItemInit.somnolent_powder.get())).build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.anti_tear.get(), 2)
				.addIngredient(ItemInit.nullifying_powder.get(), 1)
				.addCriterion("has_nullifying_powder", hasItem(ItemInit.nullifying_powder.get())).build(consumer);

		// Misc

		ShapelessRecipeBuilder.shapelessRecipe(BlockInit.vibratory_capacitor.get(), 3)
				.addIngredient(BlockInit.vibratory_storage_drum.get())
				.addCriterion("has_vibratory_storage_drum", hasItem(BlockInit.vibratory_storage_drum.get()))
				.build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.singeri_soup.get())
				.addIngredient(BlockInit.singeri_mushroom.get(), 3).addIngredient(Items.BOWL)
				.addCriterion("has_singeri_mushroom", hasItem(BlockInit.singeri_mushroom.get())).build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.somnolent_tome.get())
				.addIngredient(ItemInit.somnolent_powder.get(), 3).addIngredient(Items.BOOK)
				.addCriterion("has_somnolent_powder", hasItem(ItemInit.somnolent_powder.get())).build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.raw_morel_on_a_stick.get())
				.addIngredient(BlockInit.morel_mushroom.get(), 3).addIngredient(Items.STICK)
				.addCriterion("has_morel_mushroom", hasItem(BlockInit.morel_mushroom.get())).build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.somnolent_bottle.get())
				.addIngredient(BlockInit.passion_flower.get()).addIngredient(BlockInit.akebi.get())
				.addIngredient(ItemInit.somnolent_powder.get(), 2).addIngredient(Items.GLASS_BOTTLE)
				.addCriterion("has_passion_flower", hasItem(BlockInit.passion_flower.get())).build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.channeling_ingot.get()).addIngredient(Tags.Items.INGOTS_IRON)
				.addIngredient(ItemInit.somnolent_powder.get(), 2)
				.addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON)).build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.grey_powder.get(), 3).addIngredient(Tags.Items.GUNPOWDER)
				.addIngredient(Tags.Items.GEMS_QUARTZ).addIngredient(Items.BONE_MEAL, 3)
				.addCriterion("has_runed_obsidian", hasItem(Items.BONE_MEAL)).build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.nullifying_powder.get(), 3)
				.addIngredient(ItemInit.somnolent_powder.get()).addIngredient(ItemInit.grey_powder.get())
				.addIngredient(Tags.Items.GUNPOWDER).addIngredient(Items.REDSTONE, 3)
				.addCriterion("has_runed_obsidian", hasItem(Items.BONE_MEAL)).build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(BlockInit.runed_obsidian.get()).addIngredient(Items.FLINT)
				.addIngredient(Tags.Items.OBSIDIAN).addCriterion("has_flint", hasItem(Items.FLINT)).build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(BlockInit.anti_planks.get(), 4).addIngredient(BlockInit.anti_log.get())
				.addCriterion("has_anti_log", hasItem(BlockInit.anti_log.get())).build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(BlockInit.somnolent_planks.get(), 4)
				.addIngredient(BlockInit.somnolent_log.get())
				.addCriterion("has_somnolent_log", hasItem(BlockInit.somnolent_log.get())).build(consumer);

		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.channeling_rod.get(), 1)
				.addIngredient(ItemInit.somnolent_powder.get(), 3).addIngredient(Items.STICK)
				.addCriterion("has_somnolent_powder", hasItem(ItemInit.somnolent_powder.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.contained_magma.get()).key('N', Tags.Items.INGOTS_NETHER_BRICK)
				.key('O', Blocks.MAGMA_BLOCK).key('P', ItemInit.nullifying_powder.get()).patternLine("NON")
				.patternLine("OPO").patternLine("NON")
				.addCriterion("has_nullifying_powder", hasItem(ItemInit.nullifying_powder.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.contained_crying_obsidian.get())
				.key('N', Tags.Items.INGOTS_NETHER_BRICK).key('O', Blocks.CRYING_OBSIDIAN)
				.key('P', ItemInit.nullifying_powder.get()).patternLine("NON").patternLine("OPO").patternLine("NON")
				.addCriterion("has_crying_obsidian", hasItem(Blocks.CRYING_OBSIDIAN)).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.contained_obsidian.get()).key('N', Tags.Items.INGOTS_NETHER_BRICK)
				.key('O', Tags.Items.OBSIDIAN).key('P', ItemInit.nullifying_powder.get()).patternLine("NON")
				.patternLine("OPO").patternLine("NON")
				.addCriterion("has_nullifying_powder", hasItem(ItemInit.nullifying_powder.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.energy_focus.get()).key('N', BlockInit.phantasmal_pane.get())
				.key('O', Tags.Items.INGOTS_GOLD).key('P', ItemInit.nullifying_powder.get()).patternLine("NON")
				.patternLine("OPO").patternLine("NON")
				.addCriterion("has_phantasmal_pane", hasItem(BlockInit.phantasmal_pane.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.absorber_configurer.get()).key('S', ItemInit.null_rod.get())
				.key('Q', Tags.Items.GEMS_QUARTZ).key('E', Tags.Items.GEMS_EMERALD)
				.key('A', ItemInit.gem_amethyst.get()).key('R', ItemInit.gem_ruby.get()).patternLine(" EQ")
				.patternLine(" RA").patternLine("S  ").addCriterion("has_null_rod", hasItem(ItemInit.null_rod.get()))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.frequency_matcher.get()).key('S', ItemInit.null_rod.get())
				.key('B', ItemInit.upgrade_blank.get()).key('Q', ItemInit.gem_opal.get()).patternLine(" BQ")
				.patternLine(" SB").patternLine("S  ").addCriterion("has_gem_opal", hasItem(ItemInit.gem_opal.get()))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.grand_purging_stone.get()).key('K', ItemInit.purging_stone.get())
				.key('S', ItemInit.karmic_drop.get()).patternLine("SSS").patternLine("SKS").patternLine("SSS")
				.addCriterion("has_purging_stone", hasItem(ItemInit.purging_stone.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.purging_stone.get()).key('K', Tags.Items.STONE)
				.key('S', ItemInit.karmic_drop.get()).key('A', ItemInit.nullifying_powder.get()).patternLine("SAS")
				.patternLine("SKS").patternLine("SSS")
				.addCriterion("has_karmic_drop", hasItem(ItemInit.karmic_drop.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.somnolent_stone.get()).key('K', Tags.Items.STONE)
				.key('S', ItemInit.somnolent_powder.get()).patternLine("SSS").patternLine("SKS").patternLine("SSS")
				.addCriterion("has_somnolent_powder", hasItem(ItemInit.somnolent_powder.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.vibratory_accelerometer.get())
				.key('R', BlockInit.runed_obsidian.get()).key('E', ItemInit.energy_focus.get())
				.key('A', BlockInit.activated_obsidian.get()).key('N', BlockInit.contained_obsidian.get())
				.key('O', Blocks.OBSIDIAN).patternLine("EOE").patternLine("RNR").patternLine("AAA")
				.addCriterion("has_runed_obsidian", hasItem(BlockInit.runed_obsidian.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.obsidian_knapper.get()).key('N', ItemInit.null_rod.get())
				.key('P', BlockInit.runed_obsidian.get()).patternLine("  P").patternLine(" N ").patternLine("N  ")
				.addCriterion("has_runed_obsidian", hasItem(BlockInit.runed_obsidian.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.karmic_altar.get()).key('R', BlockInit.runed_obsidian.get())
				.key('E', ItemInit.energy_focus.get()).key('A', BlockInit.activated_obsidian.get())
				.key('N', BlockInit.contained_obsidian.get()).key('B', Items.BLAZE_POWDER).patternLine("EBE")
				.patternLine("RAR").patternLine("NRN")
				.addCriterion("has_energy_focus", hasItem(ItemInit.energy_focus.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.magatamabead.get()).key('N', Tags.Items.OBSIDIAN)
				.key('P', ItemInit.somnolent_powder.get()).patternLine("NNN").patternLine("NNN").patternLine(" PN")
				.addCriterion("has_obsidian", hasItem(Tags.Items.OBSIDIAN)).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.phantasmal_pane.get()).key('N', BlockInit.phantasmal_glass.get())
				.patternLine("NNN").patternLine("NNN")
				.addCriterion("has_phantasmal_glass", hasItem(BlockInit.phantasmal_glass.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.maker_activator.get()).key('N', BlockInit.contained_obsidian.get())
				.key('F', ItemInit.energy_focus.get()).key('C', ItemInit.somnolent_crystal.get())
				.key('S', BlockInit.somnolent_stone_smooth.get()).patternLine("F F").patternLine("NCN")
				.patternLine("SSS").addCriterion("has_energy_focus", hasItem(ItemInit.energy_focus.get()))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.vibration_debug_tool.get()).key('C', ItemInit.channeling_ingot.get())
				.key('U', ItemInit.upgrade_blank.get()).key('E', ItemInit.essence_drop.get()).patternLine("EUE")
				.patternLine(" C ").patternLine(" C ")
				.addCriterion("has_essence_drop", hasItem(ItemInit.essence_drop.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.vibratory_extractor.get()).key('N', BlockInit.contained_obsidian.get())
				.key('F', ItemInit.energy_focus.get()).key('C', ItemInit.somnolent_crystal.get())
				.key('R', BlockInit.runed_obsidian.get()).key('P', ItemInit.somnolent_powder.get()).patternLine("F F")
				.patternLine("NCN").patternLine("RPR")
				.addCriterion("has_energy_focus", hasItem(ItemInit.energy_focus.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.vibratory_fuser.get()).key('A', BlockInit.activated_obsidian.get())
				.key('E', ItemInit.energy_focus.get()).key('N', BlockInit.contained_obsidian.get())
				.key('R', BlockInit.runed_obsidian.get()).patternLine("ERE").patternLine("N N").patternLine("NAN")
				.addCriterion("has_energy_focus", hasItem(ItemInit.energy_focus.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.vibrational_seer.get()).key('G', Tags.Items.INGOTS_GOLD)
				.key('E', ItemInit.essence_drop.get()).patternLine("GGG").patternLine("GEG").patternLine(" G ")
				.addCriterion("has_essence_drop", hasItem(ItemInit.essence_drop.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.readied_pane.get(), 4).key('R', Items.REDSTONE)
				.key('G', Tags.Items.GLASS).key('O', Tags.Items.OBSIDIAN).patternLine("ORO").patternLine("RGR")
				.patternLine("ORO").addCriterion("has_glass", hasItem(Tags.Items.GLASS)).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.wand_maker.get()).key('N', BlockInit.contained_obsidian.get())
				.key('F', ItemInit.energy_focus.get()).key('R', BlockInit.runed_obsidian.get())
				.key('M', ItemInit.magatamabead.get()).patternLine("MFM").patternLine("MNM").patternLine("RRR")
				.addCriterion("has_energy_focus", hasItem(ItemInit.energy_focus.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.vibe_resonator.get()).key('N', BlockInit.contained_obsidian.get())
				.key('F', ItemInit.energy_focus.get()).key('R', BlockInit.activated_obsidian.get()).patternLine("FRF")
				.patternLine("NFN").addCriterion("has_energy_focus", hasItem(ItemInit.energy_focus.get()))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.upgrade_wrench.get()).key('R', ItemInit.channeling_rod.get())
				.key('C', ItemInit.channeling_ingot.get()).key('U', ItemInit.upgrade_blank.get()).patternLine("  U")
				.patternLine(" C ").patternLine("R  ")
				.addCriterion("has_upgrade_blank", hasItem(ItemInit.upgrade_blank.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.upgrade_blank.get()).key('R', ItemInit.nullifying_powder.get())
				.key('G', Tags.Items.INGOTS_GOLD).patternLine("GGG").patternLine("GRG").patternLine("GGG")
				.addCriterion("has_nullifying_powder", hasItem(ItemInit.nullifying_powder.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.teleporter.get()).key('Q', Blocks.QUARTZ_BLOCK)
				.key('C', Blocks.CHISELED_QUARTZ_BLOCK).key('P', Blocks.QUARTZ_PILLAR).key('O', ItemInit.gem_opal.get())
				.patternLine("QQQ").patternLine("POP").patternLine("CCC")
				.addCriterion("has_opal", hasItem(ItemInit.gem_opal.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.harmonic_imprint.get()).key('G', Items.GOLD_INGOT)
				.key('N', ItemInit.nullifying_powder.get()).key('O', ItemInit.gem_opal.get()).patternLine("NON")
				.patternLine("GOG").patternLine("NGN").addCriterion("has_opal", hasItem(ItemInit.gem_opal.get()))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.reversion_catalyst.get()).key('G', Items.GOLD_INGOT)
				.key('N', ItemInit.nullifying_powder.get()).key('O', ItemInit.gem_opal.get())
				.key('E', ItemInit.essence_drop.get()).patternLine("GEG").patternLine("GOG").patternLine("GNG")
				.addCriterion("has_opal", hasItem(ItemInit.gem_opal.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.mysterious_mask.get()).key('L', Items.LEATHER)
				.key('S', ItemInit.channeling_ingot.get()).patternLine("LLL").patternLine("SSS").patternLine("S S")
				.addCriterion("has_channeling_ingot", hasItem(ItemInit.channeling_ingot.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.opal_block.get()).key('O', ItemInit.gem_opal.get())
				.patternLine("OOO").patternLine("OOO").patternLine("OOO")
				.addCriterion("has_opal", hasItem(ItemInit.gem_opal.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.lector_table.get()).key('M', BlockInit.contained_magma.get())
				.key('C', BlockInit.contained_obsidian.get()).key('O', BlockInit.opal_block.get())
				.key('S', Blocks.DARK_OAK_LOG).key('A', BlockInit.activated_obsidian.get()).patternLine("MOM")
				.patternLine("SCS").patternLine("A A").addCriterion("has_opal", hasItem(ItemInit.gem_opal.get()))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.vibe_gatherer.get()).key('A', BlockInit.activated_obsidian.get())
				.key('O', BlockInit.contained_obsidian.get()).key('S', Ingredient.fromTag(ItemTags.LOGS))
				.key('L', BlockInit.somnolent_leaves.get()).patternLine(" L ").patternLine("ASA").patternLine("OOO")
				.addCriterion("has_contained_obsidian", hasItem(BlockInit.contained_obsidian.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.wave_gatherer.get()).key('M', BlockInit.morel_cap.get())
				.key('N', BlockInit.contained_obsidian.get()).key('G', BlockInit.phantasmal_glass.get())
				.key('F', ItemInit.energy_focus.get()).patternLine("NMN").patternLine("FNF").patternLine("NGN")
				.addCriterion("has_contained_obsidian", hasItem(BlockInit.contained_obsidian.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.somnolent_hopper.get()).key('A', BlockInit.activated_obsidian.get())
				.key('H', Blocks.HOPPER).key('L', BlockInit.somnolent_log.get())
				.key('C', ItemInit.channeling_ingot.get()).patternLine("CHC").patternLine("CAC").patternLine(" L ")
				.addCriterion("has_channeling_ingot", hasItem(ItemInit.channeling_ingot.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.crystal_harmonizer.get())
				.key('C', BlockInit.contained_crying_obsidian.get()).key('N', BlockInit.contained_obsidian.get())
				.key('M', BlockInit.contained_magma.get()).key('F', ItemInit.energy_focus.get()).patternLine("CFC")
				.patternLine("MNM").patternLine("C C")
				.addCriterion("has_contained_crying_obsidian", hasItem(BlockInit.contained_crying_obsidian.get()))
				.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.thermal_influxer.get()).key('A', BlockInit.activated_obsidian.get())
				.key('O', BlockInit.contained_obsidian.get()).key('M', BlockInit.contained_magma.get())
				.key('F', ItemInit.energy_focus.get()).key('T', ItemInit.anti_tear.get()).patternLine("OAO")
				.patternLine("OFO").patternLine("MTM")
				.addCriterion("has_contained_magma", hasItem(BlockInit.contained_magma.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.auto_inscriber.get()).key('A', BlockInit.activated_obsidian.get())
				.key('R', BlockInit.runed_obsidian.get()).key('N', BlockInit.contained_obsidian.get())
				.key('M', BlockInit.contained_magma.get()).patternLine("NAN").patternLine("M M").patternLine("MRM")
				.addCriterion("has_contained_magma", hasItem(BlockInit.contained_magma.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.vibe_concentrator.get()).key('N', BlockInit.contained_obsidian.get())
				.key('A', BlockInit.activated_obsidian.get()).key('L', BlockInit.somnolent_log.get())
				.key('F', ItemInit.energy_focus.get()).patternLine("NAN").patternLine("AFA").patternLine("LFL")
				.addCriterion("has_energy_focus", hasItem(ItemInit.energy_focus.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.tectonic_absorber.get()).key('A', BlockInit.activated_obsidian.get())
				.key('O', BlockInit.contained_obsidian.get()).key('C', BlockInit.contained_crying_obsidian.get())
				.key('M', BlockInit.contained_magma.get()).key('F', ItemInit.energy_focus.get()).patternLine("MFM")
				.patternLine("ACA").patternLine("MOM")
				.addCriterion("has_contained_magma", hasItem(BlockInit.contained_magma.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockInit.virtuous_enchanter.get())
				.key('N', BlockInit.contained_obsidian.get()).key('A', ItemInit.anti_tear.get())
				.key('S', ItemInit.essence_drop.get()).key('D', Items.DIAMOND).key('F', ItemInit.energy_focus.get())
				.key('E', Blocks.ENCHANTING_TABLE).patternLine("AES").patternLine("DND").patternLine("NFN")
				.addCriterion("has_enchanting_table", hasItem(Blocks.ENCHANTING_TABLE)).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.node_of_actualization.get()).key('O', Blocks.OBSIDIAN)
				.key('Q', Blocks.QUARTZ_BLOCK).key('K', ItemInit.karmic_drop.get()).patternLine("OOO")
				.patternLine("OKQ").patternLine("QQQ")
				.addCriterion("has_karmic_drop", hasItem(ItemInit.karmic_drop.get())).build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ItemInit.karmic_harvester.get()).key('E', ItemInit.essence_drop.get())
				.key('A', ItemInit.anti_tear.get()).key('I', Items.IRON_INGOT).key('S', Items.STICK).patternLine("II")
				.patternLine("EI").patternLine("AS").addCriterion("has_iron_ingot", hasItem(Items.IRON_INGOT))
				.build(consumer);

	}
}