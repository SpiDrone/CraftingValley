
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

import net.mcreator.starcraftvalley.StarcraftvalleyMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class StarminGuiGuiWindow extends ContainerScreen<StarminGuiGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = StarminGuiGui.guistate;
	TextFieldWidget year;
	TextFieldWidget season;
	TextFieldWidget day;
	TextFieldWidget coins;

	public StarminGuiGuiWindow(StarminGuiGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("starcraftvalley:textures/starmin_gui.png");

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		year.render(ms, mouseX, mouseY, partialTicks);
		season.render(ms, mouseX, mouseY, partialTicks);
		day.render(ms, mouseX, mouseY, partialTicks);
		coins.render(ms, mouseX, mouseY, partialTicks);
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
		if (year.isFocused())
			return year.keyPressed(key, b, c);
		if (season.isFocused())
			return season.keyPressed(key, b, c);
		if (day.isFocused())
			return day.keyPressed(key, b, c);
		if (coins.isFocused())
			return coins.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		year.tick();
		season.tick();
		day.tick();
		coins.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "D/S/Y", 78, 24, -16777216);
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
		year = new TextFieldWidget(this.font, this.guiLeft + 104, this.guiTop + 3, 20, 20, new StringTextComponent("0")) {
			{
				setSuggestion("0");
			}

			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("0");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("0");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:year", year);
		year.setMaxStringLength(32767);
		this.children.add(this.year);
		season = new TextFieldWidget(this.font, this.guiLeft + 82, this.guiTop + 3, 20, 20, new StringTextComponent("0")) {
			{
				setSuggestion("0");
			}

			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("0");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("0");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:season", season);
		season.setMaxStringLength(32767);
		this.children.add(this.season);
		day = new TextFieldWidget(this.font, this.guiLeft + 60, this.guiTop + 3, 20, 20, new StringTextComponent("1")) {
			{
				setSuggestion("1");
			}

			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("1");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("1");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:day", day);
		day.setMaxStringLength(32767);
		this.children.add(this.day);
		this.addButton(new Button(this.guiLeft + 2, this.guiTop + 3, 56, 20, new StringTextComponent("Set Date"), e -> {
			if (true) {
				StarcraftvalleyMod.PACKET_HANDLER.sendToServer(new StarminGuiGui.ButtonPressedMessage(0, x, y, z));
				StarminGuiGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 3, this.guiTop + 39, 82, 20, new StringTextComponent("Set Balance"), e -> {
			if (true) {
				StarcraftvalleyMod.PACKET_HANDLER.sendToServer(new StarminGuiGui.ButtonPressedMessage(1, x, y, z));
				StarminGuiGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		coins = new TextFieldWidget(this.font, this.guiLeft + 86, this.guiTop + 39, 47, 20, new StringTextComponent("10000")) {
			{
				setSuggestion("10000");
			}

			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("10000");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("10000");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:coins", coins);
		coins.setMaxStringLength(32767);
		this.children.add(this.coins);
		this.addButton(new Button(this.guiLeft + 4, this.guiTop + 71, 103, 20, new StringTextComponent("Display Overlay"), e -> {
			if (true) {
				StarcraftvalleyMod.PACKET_HANDLER.sendToServer(new StarminGuiGui.ButtonPressedMessage(2, x, y, z));
				StarminGuiGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 126, this.guiTop + 3, 22, 20, new StringTextComponent("+1"), e -> {
			if (true) {
				StarcraftvalleyMod.PACKET_HANDLER.sendToServer(new StarminGuiGui.ButtonPressedMessage(3, x, y, z));
				StarminGuiGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 116, this.guiTop + 143, 56, 20, new StringTextComponent("Skills >"), e -> {
			if (true) {
				StarcraftvalleyMod.PACKET_HANDLER.sendToServer(new StarminGuiGui.ButtonPressedMessage(4, x, y, z));
				StarminGuiGui.handleButtonAction(entity, 4, x, y, z);
			}
		}));
	}
}
