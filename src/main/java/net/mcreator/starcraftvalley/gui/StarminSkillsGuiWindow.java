
package net.mcreator.starcraftvalley.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.starcraftvalley.SproutMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class StarminSkillsGuiWindow extends ContainerScreen<StarminSkillsGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = StarminSkillsGui.guistate;
	TextFieldWidget fl;
	TextFieldWidget fx;
	TextFieldWidget fp;

	public StarminSkillsGuiWindow(StarminSkillsGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("sprout:textures/starmin_skills.png");

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		fl.render(ms, mouseX, mouseY, partialTicks);
		fx.render(ms, mouseX, mouseY, partialTicks);
		fp.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		if (fl.isFocused())
			return fl.keyPressed(key, b, c);
		if (fx.isFocused())
			return fx.keyPressed(key, b, c);
		if (fp.isFocused())
			return fp.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		fl.tick();
		fx.tick();
		fp.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Farming", 96, 25, -13395712);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 5, this.guiTop + 4, 37, 20, new StringTextComponent("[ O ]"), e -> {
			if (true) {
				SproutMod.PACKET_HANDLER.sendToServer(new StarminSkillsGui.ButtonPressedMessage(0, x, y, z));
				StarminSkillsGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		fl = new TextFieldWidget(this.font, this.guiLeft + 59, this.guiTop + 4, 31, 20, new StringTextComponent("lvl")) {
			{
				setSuggestion("lvl");
			}

			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("lvl");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("lvl");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:fl", fl);
		fl.setMaxStringLength(32767);
		this.children.add(this.fl);
		fx = new TextFieldWidget(this.font, this.guiLeft + 97, this.guiTop + 4, 31, 20, new StringTextComponent("Xp")) {
			{
				setSuggestion("Xp");
			}

			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("Xp");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("Xp");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:fx", fx);
		fx.setMaxStringLength(32767);
		this.children.add(this.fx);
		fp = new TextFieldWidget(this.font, this.guiLeft + 135, this.guiTop + 4, 31, 20, new StringTextComponent("Prest.")) {
			{
				setSuggestion("Prest.");
			}

			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("Prest.");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("Prest.");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:fp", fp);
		fp.setMaxStringLength(32767);
		this.children.add(this.fp);
	}
}
