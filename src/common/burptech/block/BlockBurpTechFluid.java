package burptech.block;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import burptech.client.render.EntityDropParticleFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.*;

import java.util.Random;

public class BlockBurpTechFluid extends BlockFluidClassic {
    protected float particleRed;
    protected float particleGreen;
    protected float particleBlue;

    public BlockBurpTechFluid(int id, Fluid fluid, Material material) {
        super(id, fluid, material);
    }

    @SideOnly(Side.CLIENT)
    protected Icon[] theIcon;
    protected boolean flammable;
    protected int flammability = 0;

    @Override
    public Icon getIcon(int side, int meta) {
        return side != 0 && side != 1 ? this.theIcon[1] : this.theIcon[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.theIcon = new Icon[] { iconRegister.registerIcon("burptechcore:" + fluidName + "_still"), iconRegister.registerIcon("burptechcore:" + fluidName + "_flow") };
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        super.onNeighborBlockChange(world, x, y, z, blockId);
        if (flammable && world.provider.dimensionId == -1) {
            world.newExplosion(null, x, y, z, 4F, true, true);
            world.setBlockToAir(x, y, z);
        }
    }

    public BlockBurpTechFluid setFlammable(boolean flammable) {
        this.flammable = flammable;
        return this;
    }

    public BlockBurpTechFluid setFlammability(int flammability) {
        this.flammability = flammability;
        return this;
    }

    @Override
    public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face) {
        return flammable ? 300 : 0;
    }

    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
        return flammability;
    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
        return flammable;
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side) {
        return flammable && flammability == 0;
    }

    public BlockBurpTechFluid setParticleColor(float particleRed, float particleGreen, float particleBlue) {
        this.particleRed = particleRed;
        this.particleGreen = particleGreen;
        this.particleBlue = particleBlue;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        super.randomDisplayTick(world, x, y, z, rand);

        if (rand.nextInt(10) == 0 && world.doesBlockHaveSolidTopSurface(x, y - 1, z) && !world.getBlockMaterial(x, y - 2, z).blocksMovement()) {
            double px = (double) ((float) x + rand.nextFloat());
            double py = (double) y - 1.05D;
            double pz = (double) ((float) z + rand.nextFloat());

            EntityFX fx = new EntityDropParticleFX(world, px, py, pz, particleRed, particleGreen, particleBlue);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
        }
    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        if (world.getBlockMaterial(x, y, z).isLiquid())
            return false;
        return super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
        if (world.getBlockMaterial(x, y, z).isLiquid())
            return false;
        return super.displaceIfPossible(world, x, y, z);
    }

}
