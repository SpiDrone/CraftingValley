package net.mcreator.starcraftvalley.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.block.BlockState;

import net.mcreator.starcraftvalley.block.TilledEarthBlock;
import net.mcreator.starcraftvalley.block.StrawberryS4Block;
import net.mcreator.starcraftvalley.block.StrawberryS3Block;
import net.mcreator.starcraftvalley.block.StrawberryS2Block;
import net.mcreator.starcraftvalley.block.PotatoS3Block;
import net.mcreator.starcraftvalley.block.PotatoS2Block;
import net.mcreator.starcraftvalley.block.CauliflowerS4Block;
import net.mcreator.starcraftvalley.block.CauliflowerS3Block;
import net.mcreator.starcraftvalley.block.CauliflowerS2Block;
import net.mcreator.starcraftvalley.StarcraftvalleyModVariables;
import net.mcreator.starcraftvalley.StarcraftvalleyMod;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class WetEarthUpdateProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency world for procedure WetEarthUpdate!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency x for procedure WetEarthUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency y for procedure WetEarthUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				StarcraftvalleyMod.LOGGER.warn("Failed to load dependency z for procedure WetEarthUpdate!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		boolean returnvalue = false;
		if (StarcraftvalleyModVariables.MapVariables.get(world).day != new Object() {
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
					.contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock())) {
				if (StarcraftvalleyModVariables.MapVariables.get(world).season == 0) {
					returnvalue = (true);
				} else if (StarcraftvalleyModVariables.MapVariables.get(world).season == 1
						&& BlockTags.getCollection().getTagByID(new ResourceLocation("forge:summer_crop"))
								.contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock())) {
					returnvalue = (true);
				} else if (StarcraftvalleyModVariables.MapVariables.get(world).season == 2 && BlockTags.getCollection()
						.getTagByID(new ResourceLocation("forge:fall_crop")).contains((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock())) {
					returnvalue = (true);
				}
				if (returnvalue) {
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
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 8) {
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
						}.getValue(world, new BlockPos(x, y + 1, z), "growthStage") < 12) {
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
					}
				}
			}
		}
	}
}
