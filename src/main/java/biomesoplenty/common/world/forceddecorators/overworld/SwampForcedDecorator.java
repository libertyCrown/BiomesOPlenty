package biomesoplenty.common.world.forceddecorators.overworld;

import net.minecraft.init.Blocks;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class SwampForcedDecorator extends ForcedDecorator
{
	public SwampForcedDecorator(int id)
	{
        super(id);

		this.bopWorldFeatures.setFeature("mudPerChunk", 3);
		this.bopWorldFeatures.setFeature("seaweedPerChunk", 10);
		this.bopWorldFeatures.setFeature("cattailsPerChunk", 10);
		this.bopWorldFeatures.setFeature("highCattailsPerChunk", 5);
		this.bopWorldFeatures.setFeature("algaePerChunk", 3);
		this.bopWorldFeatures.setFeature("koruPerChunk", 25);
		this.bopWorldFeatures.setFeature("waterReedsPerChunk", 5);
		this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 1);
		this.bopWorldFeatures.setFeature("blueMilksPerChunk", 1);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 2);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 4);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 5);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 1), 15);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}
}