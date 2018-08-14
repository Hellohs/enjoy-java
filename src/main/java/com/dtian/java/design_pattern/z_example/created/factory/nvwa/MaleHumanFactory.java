package com.dtian.java.design_pattern.z_example.created.factory.nvwa;

import com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.BlackMaleHuman;
import com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.Human;
import com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.HumanEnum;

/**
 * 男性创建工厂
 */
public class MaleHumanFactory extends AbstractHumanFactory{
    @Override
    public Human createYellowHuman() {
        return super.createHuman(HumanEnum.YellowMaleHuman);
    }

    @Override
    public Human createWhiteHuman() {
        return super.createHuman(HumanEnum.WhiteMaleHuman);
    }

    @Override
    public Human createBlackHuman() {
        return super.createHuman(HumanEnum.BlackMaleHuman);
    }
}
