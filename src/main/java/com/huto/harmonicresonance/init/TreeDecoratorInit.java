package com.huto.harmonicresonance.init;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.worldgen.AkebiTreeDecorator;

import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TreeDecoratorInit {

	public static TreeDecoratorType<AkebiTreeDecorator> AKEBI;
	public static final DeferredRegister<TreeDecoratorType<?>> TREEDECORATORS = DeferredRegister
			.create(ForgeRegistries.TREE_DECORATOR_TYPES, HarmonicResonance.MOD_ID);
	public static final RegistryObject<TreeDecoratorType<AkebiTreeDecorator>> akebi = TREEDECORATORS.register("akebi",
			() -> new TreeDecoratorType<>(AkebiTreeDecorator.field_236866_a_));

}
