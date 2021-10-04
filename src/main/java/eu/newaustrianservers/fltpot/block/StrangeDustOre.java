package eu.newaustrianservers.fltpot.block;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class StrangeDustOre extends OreBlock {

    public StrangeDustOre(Properties properties) {

        super(properties);

    }

    @Override
    protected int getExperience(Random p_220281_1_) {

        return MathHelper.nextInt(p_220281_1_, 3, 7);

    }

}
