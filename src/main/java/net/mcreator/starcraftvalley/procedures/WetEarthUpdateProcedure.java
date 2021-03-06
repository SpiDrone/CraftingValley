package net.mcreator.starcraftvalley.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.block.WheatS4Block;
import net.mcreator.starcraftvalley.block.WheatS3Block;
import net.mcreator.starcraftvalley.block.WheatS2Block;
import net.mcreator.starcraftvalley.block.TilledEarthBlock;
import net.mcreator.starcraftvalley.block.TeaS4Block;
import net.mcreator.starcraftvalley.block.TeaS3Block;
import net.mcreator.starcraftvalley.block.TeaS2Block;
import net.mcreator.starcraftvalley.block.StrawberryS4Block;
import net.mcreator.starcraftvalley.block.StrawberryS3Block;
import net.mcreator.starcraftvalley.block.StrawberryS2Block;
import net.mcreator.starcraftvalley.block.PotatoS3Block;
import net.mcreator.starcraftvalley.block.PotatoS2Block;
import net.mcreator.starcraftvalley.block.PepperS3Block;
import net.mcreator.starcraftvalley.block.PepperS2Block;
import net.mcreator.starcraftvalley.block.ParsnipS4Block;
import net.mcreator.starcraftvalley.block.ParsnipS3Block;
import net.mcreator.starcraftvalley.block.ParsnipS2Block;
import net.mcreator.starcraftvalley.block.GrapesS4Block;
import net.mcreator.starcraftvalley.block.GrapesS3Block;
import net.mcreator.starcraftvalley.block.GrapesS2Block;
import net.mcreator.starcraftvalley.block.CoffeeS4Block;
import net.mcreator.starcraftvalley.block.CoffeeS3Block;
import net.mcreator.starcraftvalley.block.CoffeeS2Block;
import net.mcreator.starcraftvalley.block.CauliflowerS4Block;
import net.mcreator.starcraftvalley.block.CauliflowerS3Block;
import net.mcreator.starcraftvalley.block.CauliflowerS2Block;
import net.mcreator.starcraftvalley.block.CabbageRedS5Block;
import net.mcreator.starcraftvalley.block.CabbageRedS4Block;
import net.mcreator.starcraftvalley.block.CabbageRedS3Block;
import net.mcreator.starcraftvalley.block.CabbageRedS2Block;
import net.mcreator.starcraftvalley.SproutModVariables;
import net.mcreator.starcraftvalley.SproutMod;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class WetEarthUpdateProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SproutMod.LOGGER.warn("Failed to load dependency world for procedure WetEarthUpdate!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SproutMod.LOGGER.warn("Failed to load dependency x for procedure WetEarthUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SproutMod.LOGGER.warn("Failed to load dependency y for procedure WetEarthUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SproutMod.LOGGER.warn("Failed to load dependency z for procedure WetEarthUpdate!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		boolean returnvalue = false;
		if (SproutModVariables.MapVariables.get(world).day != new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos(x, y, z), "dayWatered")) {
			CropDecayProcedure.executeProcedure(Stream
					.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
							new AbstractMap.SimpleEntry<>("z", z))
					.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
			{
				BlockPos _bp = new BlockPos(x, y, z);
				BlockState _bs = TilledEarthBlock.block.getDefaultState();
				BlockState _bso = world.getBlockState(_bp);
				TileEntity _te = world.getTileEntity(_bp);
				CompoundNBT _bnbt = null;
				if (_te != null) {
					_bnbt = _te.write(new CompoundNBT());
					_te.remove();
				}
				world.setBlockState(_bp, _bs, 3);
				if (_bnbt != null) {
					_te = world.getTileEntity(_bp);
					if (_te != null) {
						try {
							_te.read(_bso, _bnbt);
						} catch (Exception ignored) {
						}
					}
				}
			}
			if (BlockTags.getCollection().getTagByID(new ResourceLocation("forge:spring_crop"))
					.contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock()) && SproutModVariables.MapVariables.get(world).season == 0
					|| BlockTags.getCollection().getTagByID(new ResourceLocation("forge:summer_crop")).contains(
							(world.getBlockState(new BlockPos(x, y + 1, z))).getBlock()) && SproutModVariables.MapVariables.get(world).season == 1
					|| BlockTags.getCollection().getTagByID(new ResourceLocation("forge:fall_crop")).contains(
							(world.getBlockState(new BlockPos(x, y + 1, z))).getBlock()) && SproutModVariables.MapVariables.get(world).season == 2
					|| BlockTags.getCollection().getTagByID(new ResourceLocation("forge:winter_crop")).contains(
							(world.getBlockState(new BlockPos(x, y + 1, z))).getBlock()) && SproutModVariables.MapVariables.get(world).season == 3) {
				if ((new Object() {
					public String getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y + 1, z), "crop")).equals("strawberry")) {
					if (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 9) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos(x, y + 1, z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("growthStage", (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") + 1));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 3) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = StrawberryS2Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 5) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = StrawberryS3Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 8) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = StrawberryS4Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
					}
				} else if ((new Object() {
					public String getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y + 1, z), "crop")).equals("potato")) {
					if (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 6) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos(x, y + 1, z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("growthStage", (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") + 1));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 4) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = PotatoS2Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 6) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = PotatoS3Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
					}
				} else if ((new Object() {
					public String getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y + 1, z), "crop")).equals("cauliflower")) {
					if (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 13) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos(x, y + 1, z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("growthStage", (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") + 1));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 4) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = CauliflowerS2Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 8) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = CauliflowerS3Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 12) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = CauliflowerS4Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
					}
				} else if ((new Object() {
					public String getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y + 1, z), "crop")).equals("parsnip")) {
					if (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 5) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos(x, y + 1, z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("growthStage", (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") + 1));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 2) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = ParsnipS2Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 4) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = ParsnipS3Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 5) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = ParsnipS4Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
					}
				} else if ((new Object() {
					public String getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y + 1, z), "crop")).equals("tea")) {
					if (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 20) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos(x, y + 1, z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("growthStage", (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") + 1));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 8) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = TeaS2Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 15) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = TeaS3Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 20) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = TeaS4Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
					}
				} else if ((new Object() {
					public String getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y + 1, z), "crop")).equals("coffee")) {
					if (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 10) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos(x, y + 1, z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("growthStage", (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") + 1));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 4) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = CoffeeS2Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 7) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = CoffeeS3Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 10) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = CoffeeS4Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
					}
				} else if ((new Object() {
					public String getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y + 1, z), "crop")).equals("grape")) {
					if (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 10) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos(x, y + 1, z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("growthStage", (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") + 1));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 4) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = GrapesS2Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 7) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = GrapesS3Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 10) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = GrapesS4Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
					}
				} else if ((new Object() {
					public String getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y + 1, z), "crop")).equals("pepper")) {
					if (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 6) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos(x, y + 1, z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("growthStage", (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") + 1));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 4) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = PepperS2Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 6) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = PepperS3Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
					}
				} else if ((new Object() {
					public String getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y + 1, z), "crop")).equals("cabbagered")) {
					if (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 10) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos(x, y + 1, z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("growthStage", (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") + 1));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 3) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = CabbageRedS2Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 6) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = CabbageRedS3Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 8) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = CabbageRedS4Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 10) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = CabbageRedS5Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
					}
				} else if ((new Object() {
					public String getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y + 1, z), "crop")).equals("wheat")) {
					if (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 5) {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos(x, y + 1, z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("growthStage", (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") + 1));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 3) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = WheatS2Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 4) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = WheatS3Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						} else if (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") == 5) {
							{
								BlockPos _bp = new BlockPos(x, y + 1, z);
								BlockState _bs = WheatS4Block.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
