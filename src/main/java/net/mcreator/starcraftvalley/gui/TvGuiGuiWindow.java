
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
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.starcraftvalley.procedures.OracleDisplayConditions3Procedure;
import net.mcreator.starcraftvalley.procedures.OracleDisplayConditions2Procedure;
import net.mcreator.starcraftvalley.procedures.OracleDisplayConditions1Procedure;
import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class TvGuiGuiWindow extends ContainerScreen<TvGuiGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = TvGuiGui.guistate;

	public TvGuiGuiWindow(TvGuiGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 180;
		this.ySize = 100;
	}

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("sprout:textures/tvremote.png"));
		this.blit(ms, this.guiLeft + 0, this.guiTop + 0, 0, 0, 180, 100, 180, 100);

		if (OracleDisplayConditions2Procedure
				.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
								new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("sprout:textures/orb.png"));
			this.blit(ms, this.guiLeft + 0, this.guiTop + 0, 0, 0, 180, 100, 180, 100);
		}
		if (OracleDisplayConditions1Procedure
				.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
								new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("sprout:textures/orbr.png"));
			this.blit(ms, this.guiLeft + 0, this.guiTop + 0, 0, 0, 180, 100, 180, 100);
		}
		if (OracleDisplayConditions3Procedure
				.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
								new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("sprout:textures/orbg.png"));
			this.blit(ms, this.guiLeft + 0, this.guiTop + 0, 0, 0, 180, 100, 180, 100);
		}
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Luck : " + (SproutModVariables.MapVariables.get(world).dailyLuck) + "", -1, -13, -3355648);
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
		this.addButton(new Button(this.guiLeft + 39, this.guiTop + 101, 19, 20, new StringTextComponent("1"), e -> {
			if (true) {
				SproutMod.PACKET_HANDLER.sendToServer(new TvGuiGui.ButtonPressedMessage(0, x, y, z));
				TvGuiGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + -1, this.guiTop + 101, 31, 20, new StringTextComponent("Off"), e -> {
			if (true) {
				SproutMod.PACKET_HANDLER.sendToServer(new TvGuiGui.ButtonPressedMessage(1, x, y, z));
				TvGuiGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 60, this.guiTop + 101, 19, 20, new StringTextComponent("2"), e -> {
			if (true) {
				SproutMod.PACKET_HANDLER.sendToServer(new TvGuiGui.ButtonPressedMessage(2, x, y, z));
				TvGuiGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 81, this.guiTop + 101, 19, 20, new StringTextComponent("3"), e -> {
			if (true) {
				SproutMod.PACKET_HANDLER.sendToServer(new TvGuiGui.ButtonPressedMessage(3, x, y, z));
				TvGuiGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
	}
}
