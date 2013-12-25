package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;

public class BlockBOPSapling extends BlockSapling
{
	private static final String[] saplings = new String[] {"apple", "yellowautumn", "bamboo", "magic", "dark", "dead", "fir", "holy", "orangeautumn", "origin", "pinkcherry", "maple", "whitecherry", "hellbark", "jacaranda", "persimmon"};
	private IIcon[] textures;
	private static final int TYPES = 15;

	public BlockBOPSapling()
	{
		setHardness(0.0F);
		setStepSound(Block.soundGrassFootstep);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[saplings.length];

		for (int i = 0; i < saplings.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:sapling_" + saplings[i]);
		}

	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (meta < 0 || meta >= saplings.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < saplings.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int id = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();

		if (itemStack.itemID == blockID && id != 0) {
			switch (meta)
			{
			case 7: // Loftwood
			return id == Blocks.holyGrass.get().blockID || id == Blocks.holyDirt.get().blockID;

			default:
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || blocksList[id].canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
			}
		} else
			return this.canPlaceBlockOnSide(world, x, y, z, side);
	}

	protected boolean canThisPlantGrowOnThisBlockID(int blockID, int metadata)
	{
		if (metadata == 7) //Loftwood
			return blockID == Blocks.holyGrass.get().blockID || blockID == Blocks.holyDirt.get().blockID;
		else
			return blockID == Block.grass.blockID || blockID == Block.dirt.blockID || blockID == Block.tilledField.blockID;
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		Block soil = blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
		if (par1World.getBlockMetadata(par2, par3, par4) != 7)
			return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) &&
					(soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this));
		else
			return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) &&
					(soil != null && (soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this) || soil.blockID == Blocks.holyGrass.get().blockID));
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (world.isRemote)
			return;

		this.checkFlowerChange(world, x, y, z);

		if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0) {
			this.growTree(world, x, y, z, random);
		}
	}

	@Override
	public void growTree(World world, int x, int y, int z, Random random)
	{
		int meta = world.getBlockMetadata(x, y, z) & TYPES;
		Object obj = null;
		int rnd = random.nextInt(8);

		if (obj == null)
		{
			switch (meta)
			{
			case 0: // Apple Tree
			obj = new WorldGenApple(false);
			break;

			case 1: // Autumn Tree
			obj = new WorldGenAutumn(false);
			break;

			case 2: // Bamboo Tree
				rnd = random.nextInt(8);

				if (rnd == 0) {
					obj = new WorldGenBambooTree(false);
				} else {
					obj = new WorldGenBambooTree2(false);
				}
				break;

			case 3: // Magic Tree
				obj = new WorldGenMystic2(false);
				break;

			case 4: // Dark Tree
				rnd = random.nextInt(8);

				if (rnd == 0) {
					obj = new WorldGenOminous2();
				} else {
					obj = new WorldGenOminous1(false);
				}
				break;

			case 5: // Dead Tree
				obj = new WorldGenDeadTree2(false);
				break;

			case 6: // Fir Tree
				obj = new WorldGenTaiga9(false);
				break;

			case 7: // Holy Tree
				obj = new WorldGenPromisedTree(false);
				break;

			case 8: // Autumn Tree
				obj = new WorldGenAutumn2(false);
				break;

			case 9: // Origin Tree
				obj = new WorldGenOriginTree(false);
				break;

			case 10: // Pink Cherry Tree
				obj = new WorldGenCherry1(false);
				break;

			case 11: // Maple Tree
				obj = new WorldGenMaple(false);
				break;

			case 12: // White Cherry Tree
				obj = new WorldGenCherry2(false);
				break;

			case 13: // Hellbark
				obj = new WorldGenNetherBush();
				break;

			case 14: // Jacaranda
				obj = new WorldGenJacaranda(false);
				break;
				
			case 15: // Persimmon
				obj = new WorldGenPersimmon(false);
				break;
			}
		}

		if (obj != null)
		{
			world.setBlockToAir(x, y, z);

			if (!((WorldGenerator)obj).generate(world, random, x, y, z)) {
				world.setBlock(x, y, z, blockID, meta, 2);
			}
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta & TYPES;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z) & TYPES;
	}
}