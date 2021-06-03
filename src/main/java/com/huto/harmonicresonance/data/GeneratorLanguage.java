package com.huto.harmonicresonance.data;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.init.BlockInit;
import com.huto.harmonicresonance.init.EnchantmentInit;
import com.huto.harmonicresonance.init.EntityInit;
import com.huto.harmonicresonance.init.ItemInit;
import com.hutoslib.util.TextUtils;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;

public class GeneratorLanguage extends LanguageProvider {
	public GeneratorLanguage(DataGenerator gen) {
		super(gen, HarmonicResonance.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		/***
		 * 
		 * Tabs,Tooltips,Keybinds, Etc
		 * 
		 */
		add("itemGroup.harmonicresonancetab", "Forces of Reality");
		add("key.harmonicresonance.category", "Forces of Reality");
		// Biomes
		add("biome.harmonicresonance.dream_land", "Dream Lands");
		// Death msgs
		add("death.attack.tentacle", "%1$s was tentacled to death");
		// Container
		add("container.virtuous_enchanter", "Virtuous Enchanter");
		// Jei
		add("harmonicresonance.jei.wandmaker", "Wand Maker");
		add("harmonicresonance.jei.resonator", "Vibrarory Resonator");
		add("harmonicresonance.jei.vibefuser", "Vibrarory Conglomerator");
		add("harmonicresonance.jei.chisel_station", "Chisel Station");
		// Enchantments
		for (RegistryObject<Enchantment> e : EnchantmentInit.ENCHANTS.getEntries()) {
			addEnchantment(e,
					TextUtils.convertInitToLang(e.get().getName().replace("enchantment.harmonicresonance.", "")));
		}
		for (RegistryObject<Block> b : BlockInit.BLOCKS.getEntries()) {
			addBlock(b, TextUtils
					.convertInitToLang(b.get().asItem().getTranslationKey().replace("block.harmonicresonance.", "")));
		}
		for (RegistryObject<Item> i : ItemInit.ITEMS.getEntries()) {
			addItem(i, TextUtils
					.convertInitToLang(i.get().asItem().getTranslationKey().replace("item.harmonicresonance.", "")));
		}
		for (RegistryObject<EntityType<?>> en : EntityInit.ENTITY_TYPES.getEntries()) {
			addEntityType(en,
					TextUtils.convertInitToLang(en.get().getTranslationKey().replace("entity.harmonicresonance.", "")));
		}

		/***
		 * Adornment Pattern Text
		 */
		// Misc
		add("name.RUNE", "Base Adornment");
		add("name.MAJOR", "Major Adornments");
		// Adornment Binder
		add("harmonicresonance.nbtdata", "NBT Tags");
		add("harmonicresonance.whitelist", "Whitelist");
		add("harmonicresonance.autopickupenabled", "Auto-Pickup: Enabled");
		add("harmonicresonance.autopickupdisabled", "Auto-Pickup: Disabled");
		add("harmonicresonance.autopickup", "Auto-Pickup");
		add("harmonicresonance.shift", "Press <Shift> for info.");

	}
}