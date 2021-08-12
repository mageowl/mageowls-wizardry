package com.seattleowl.blocks;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.seattleowl.mageowls_wizardry.MageowlsWizardry;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ArcaneTableScreen extends AbstractContainerScreen<ArcaneTableContainer> {
    private ResourceLocation GUITexture = new ResourceLocation(MageowlsWizardry.MODID, "textures/gui/arcane_table.png");
    private final int guiHeight = 180;
    private final int guiWidth = 176;

    private ArcaneTableContainer container;

    public ArcaneTableScreen(ArcaneTableContainer container, Inventory inv, Component name)  {
        super(container, inv, name);
        this.container = container;
        this.inventoryLabelY = 78;
        this.titleLabelY = -1;
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUITexture);
        int relX = (this.width - guiWidth) / 2;
        int relY = (this.height - guiHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, guiWidth, guiHeight);
    }
}
