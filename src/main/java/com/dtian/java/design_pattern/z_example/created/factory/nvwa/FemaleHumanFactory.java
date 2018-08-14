package com.dtian.java.design_pattern.z_example.created.factory.nvwa;

import com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.Human;
import com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.HumanEnum;

/**
 * 女性创建工厂
 */
public class FemaleHumanFactory extends AbstractHumanFactory{
    @Override
    public Human createYellowHuman() {
        return super.createHuman(HumanEnum.YellowFemaleHuman);
    }

    @Override
    public Human createWhiteHuman() {
        return super.createHuman(HumanEnum.WhiteFemaleHuman);
    }

    @Override
    public Human createBlackHuman() {
        return super.createHuman(HumanEnum.BlackFemaleHuman);
    }
}
