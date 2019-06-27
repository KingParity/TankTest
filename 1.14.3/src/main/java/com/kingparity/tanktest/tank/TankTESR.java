package com.kingparity.tanktest.tank;


import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

public class TankTESR extends TileEntityRenderer<TankTile>
{
    public static final float TANK_THICKNESS = 0.05f;
    
    @Override
    public void render(TankTile tileEntity, double x, double y, double z, float partialTicks, int destroyStage)
    {
        GlStateManager.pushMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.color4f(1, 1, 1, 1);
        GlStateManager.disableBlend();
        GlStateManager.translatef((float)x, (float)y, (float)z);
        
        bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
        renderFluid(tileEntity);
        
        GlStateManager.popMatrix();
    }
    
    private void renderFluid(TankTile tank)
    {
        if(tank == null)
        {
            return;
        }
        
        FluidStack fluid = tank.getTank().getFluid();
        if(fluid == null)
        {
            return;
        }
        
        Fluid renderFluid = fluid.getFluid();
        if(renderFluid == null)
        {
            return;
        }
        
        float scale = (1.0f - TANK_THICKNESS / 2 - TANK_THICKNESS) * fluid.amount / (tank.getTank().getCapacity());
        
        if(scale > 0.0f)
        {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder renderer = tessellator.getBuffer();
            ResourceLocation still = renderFluid.getStill();
            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureMap().getAtlasSprite(still.toString());
            
            net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
            
            GlStateManager.color4f(1, 1, 1, .5f);
            renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
            
            float u1 = sprite.getMinU();
            float v1 = sprite.getMinV();
            float u2 = sprite.getMaxU();
            float v2 = sprite.getMaxV();
            
            // Top
            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();
            
            // Bottom
            renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();
            
            // Sides
            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();
            
            renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();
            
            renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1 - TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(1 - TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();
            
            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, TANK_THICKNESS).tex(u1, v1).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, TANK_THICKNESS).tex(u1, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v2).color(255, 255, 255, 128).endVertex();
            renderer.pos(TANK_THICKNESS, scale + TANK_THICKNESS, 1 - TANK_THICKNESS).tex(u2, v1).color(255, 255, 255, 128).endVertex();
            
            tessellator.draw();
            
            net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
        }
    }
}