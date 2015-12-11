package fr.ironcraft.nowel.blocks;


import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.ironcraft.nowel.blocks.tileentities.TileEntityPresent;


public class BlockSnowGlobe extends BlockContainer
{
	public static final PropertyEnum<SnowGlobeTypes> TYPE = PropertyEnum.<SnowGlobeTypes> create("type", SnowGlobeTypes.class);

	public BlockSnowGlobe()
	{
		super(Material.glass);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityPresent();
	}

	/**
	 * Gets the metadata of the item this Block can drop. This method is called
	 * when the block gets destroyed. It
	 * returns the metadata of the dropped item based on the old metadata of the
	 * block.
	 */
	public int damageDropped(IBlockState state)
	{
		return state.getValue(TYPE).getMetadata();
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood
	 * returns 4 blocks)
	 */
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
	{
		for (SnowGlobeTypes type : SnowGlobeTypes.values())
		{
			list.add(new ItemStack(itemIn, 1, type.getMetadata()));
		}
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(TYPE, SnowGlobeTypes.byMetadata(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(TYPE).getMetadata();
	}

	public static enum SnowGlobeTypes implements IStringSerializable
	{
		PURE_SNOW,
		CANDYCANE;

		@Override
		public String getName()
		{
			return name().toLowerCase();
		}

		public int getMetadata()
		{
			return ordinal();
		}

		public static SnowGlobeTypes byMetadata(int meta)
		{
			return values()[meta];
		}
	}
}
