package com.huto.harmonicresonance.screen;

import java.util.ArrayList;
import java.util.List;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.init.ItemInit;
import com.hutoslib.client.gui.GuiButtonTextured;
import com.hutoslib.client.gui.GuiUtils;
import com.hutoslib.client.ClientUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.Button.IPressable;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiTomePageTOC extends GuiTomePage {
	final ResourceLocation texture = new ResourceLocation(HarmonicResonance.MOD_ID, "textures/gui/book.png");
	int guiWidth = 175;
	int guiHeight = 228;
	int left, top;
	final int ARROWF = 0, ARROWB = 1, TITLEBUTTON = 2, CLOSEBUTTON = 3;
	GuiButtonBookArrow arrowF;
	GuiButtonBookArrow arrowB;
	GuiButtonTextured buttonTitle;
	GuiButtonTextured buttonCloseTab;
	GuiButtonTextured buttonTOC;
	public static List<GuiTomePage> chapterPages = new ArrayList<GuiTomePage>();
	GuiButtonTextured[] buttonArray = new GuiButtonTextured[chapterPages.size()];
	public static List<GuiButtonTextured> buttonList = new ArrayList<GuiButtonTextured>();
	ItemStack icon;
	EnumTomeCatagories catagory;

	@OnlyIn(Dist.CLIENT)
	public GuiTomePageTOC(EnumTomeCatagories catagoryIn, ItemStack iconIn) {
		super(0, catagoryIn, "Table of Contents", "", iconIn, "");
		this.icon = iconIn;
		this.catagory = catagoryIn;
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		int centerX = (width / 2) - guiWidth / 2;
		int centerY = (height / 2) - guiHeight / 2;
		this.renderBackground(matrixStack);

		GlStateManager.pushMatrix();
		{
			GlStateManager.color4f(1, 1, 1, 1);
			Minecraft.getInstance().getTextureManager().bindTexture(texture);
			GuiUtils.drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth - 1, guiHeight);
		}
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef((width / 2) - 40, centerY + 10, 10);
			GlStateManager.scalef(1, 1, 1);
			drawString(matrixStack, font, title, -5, 0, 8060954);
		}
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		{
			// Draw Strings
		}
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		{
			GlStateManager.color4f(1, 1, 1, 1);

			buttonTitle.render(matrixStack, mouseX, mouseY, 311);
			buttonCloseTab.render(matrixStack, mouseX, mouseY, 411);

			for (int i = 1; i < buttonList.size(); i++) {
				buttonList.get(i).render(matrixStack, mouseX, mouseY, 511);
				GlStateManager.translatef(0, 0, 10);
				drawString(matrixStack, font, "Pg." + i, (buttonList.get(i).posX + 2), buttonList.get(i).posY + 2,
						8060954);
				drawString(matrixStack, font, getMatchingChapter().get(i).title, (int) (buttonList.get(i).posX + 30),
						buttonList.get(i).posY + 2, 8060954);

			}

			arrowF.render(matrixStack, mouseX, mouseY, 511);
			arrowB.render(matrixStack, mouseX, mouseY, 511);

		}
		GlStateManager.popMatrix();

		GlStateManager.translatef(3, 0, 0);
		GlStateManager.pushMatrix();
		{
			GlStateManager.translatef(centerX, centerY, 0);
			GlStateManager.translatef(3, 3, 0);
			GlStateManager.scalef(1.9f, 1.7f, 1.9f);
			RenderHelper.enableStandardItemLighting();
			Minecraft.getInstance().getItemRenderer().renderItemAndEffectIntoGUI(icon, 0, 2);
		}
		GlStateManager.popMatrix();

		if (!(mouseX >= 0 && mouseX <= (16 * 2) + 16 + width && mouseY >= (16 * 2) + 20
				&& mouseY <= (16 * 2) + 20 + height)) {
			List<ITextComponent> text = new ArrayList<ITextComponent>();
			text.add(new StringTextComponent(I18n.format(icon.getDisplayName().getString())));
			func_243308_b(matrixStack, text, centerX, centerY);
		}
		List<ITextComponent> titlePage = new ArrayList<ITextComponent>();
		titlePage.add(new StringTextComponent(I18n.format("Title")));
		titlePage.add(new StringTextComponent(I18n.format("Return to Catagories")));
		if (buttonTitle.isHovered()) {
			func_243308_b(matrixStack, titlePage, mouseX, mouseY);
		}
		List<ITextComponent> ClosePage = new ArrayList<ITextComponent>();
		ClosePage.add(new StringTextComponent(I18n.format("Close Book")));
		if (buttonCloseTab.isHovered()) {
			func_243308_b(matrixStack, ClosePage, mouseX, mouseY);
		}
	}

	@Override
	protected void init() {
		left = width / 2 - guiWidth / 2;
		top = height / 2 - guiHeight / 2;
		int sideLoc = left + guiWidth;
		int verticalLoc = top + guiHeight;
		buttons.clear();
		buttonList.clear();
		checkChapter();
		this.addButton(buttonTitle = new GuiButtonTextured(texture, TITLEBUTTON, left - guiWidth + 150,
				top + guiHeight - 209, 24, 16, 174, 32, null, (press) -> {
					if (ClientUtils.getClientPlayer().getHeldItemMainhand().getItem() == ItemInit.elder_tome.get()) {
						mc.displayGuiScreen(new GuiTomeTitle(true));
					} else {
						mc.displayGuiScreen(new GuiTomeTitle(false));
					}
				}));

		this.addButton(buttonCloseTab = new GuiButtonTextured(texture, CLOSEBUTTON, left - guiWidth + 150,
				top + guiHeight - 193, 24, 16, 174, 64, null, (press) -> {
					this.closeScreen();
				}));

		for (int i = 0; i < chapterPages.size(); i++) {
			buttonList.add(new GuiButtonTextured(texture, i, sideLoc - (guiWidth - 5), (verticalLoc - 210) + (i * 15),
					163, 14, 5, 228, null, new IPressable() {
						@Override
						public void onPress(Button press) {
							if (press instanceof GuiButtonTextured) {
								GuiButtonTextured button = (GuiButtonTextured) press;
								tableButtonCheck((button.getId()));
							}
						}
					}));
		}

		for (int i = 0; i < buttonList.size(); i++) {
			this.addButton(buttonList.get(i));
		}

		this.addButton(arrowF = new GuiButtonBookArrow(ARROWF, left + guiWidth - 18, top + guiHeight - 10, 16, 14, 175,
				1, new IPressable() {
					@Override
					public void onPress(Button p_onPress_1_) {
						mc.displayGuiScreen(getMatchingChapter().get(1));
					}
				}));
		this.addButton(
				arrowB = new GuiButtonBookArrow(ARROWB, left, top + guiHeight - 10, 16, 14, 192, 1, new IPressable() {
					@Override
					public void onPress(Button p_onPress_1_) {
						if (ClientUtils.getClientPlayer().getHeldItemMainhand().getItem() == ItemInit.elder_tome
								.get()) {
							mc.displayGuiScreen(new GuiTomeTitle(true));
						} else {
							mc.displayGuiScreen(new GuiTomeTitle(false));
						}
					}
				}));

	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		return false;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		for (IGuiEventListener iguieventlistener : this.getEventListeners()) {
			if (iguieventlistener.mouseClicked(mouseX, mouseY, mouseButton)) {
				this.setListener(iguieventlistener);
				if (mouseButton == 0) {
					this.setDragging(true);
				}
				return true;
			}
		}
		return false;
	}

	public void tableButtonCheck(int page) {
		mc.displayGuiScreen(this.getMatchingChapter().get(page));

	}

	public void checkChapter() {
		switch (this.catagory) {
		case INTRO:
			chapterPages = TomePageLib.getIntroPageList();
			break;
		case KARMA:
			chapterPages = TomePageLib.getKarmaPageList();
			break;
		case WORLDGEN:
			chapterPages = TomePageLib.getWorldGenPageList();
			break;
		case EQUIPS:
			chapterPages = TomePageLib.getArmorPageList();
			break;
		case WANDS:
			chapterPages = TomePageLib.getWandsPageList();
			break;
		case GENERATION:
			chapterPages = TomePageLib.getGeneratePageList();
			break;
		case MACHINES:
			chapterPages = TomePageLib.getBlocksPageList();
			break;
		case ELDER:
			chapterPages = TomePageLib.getElderPageList();
			break;
		default:
			break;

		}
	}

	public List<GuiTomePage> getMatchingChapter() {
		switch (this.catagory) {
		case INTRO:
			return TomePageLib.getIntroPageList();
		case KARMA:
			return TomePageLib.getKarmaPageList();
		case WORLDGEN:
			return TomePageLib.getWorldGenPageList();
		case EQUIPS:
			return TomePageLib.getArmorPageList();
		case WANDS:
			return TomePageLib.getWandsPageList();

		case GENERATION:
			return TomePageLib.getGeneratePageList();
		case MACHINES:
			return TomePageLib.getBlocksPageList();
		case ELDER:
			return TomePageLib.getElderPageList();
		default:
			break;

		}
		return null;
	}

}
