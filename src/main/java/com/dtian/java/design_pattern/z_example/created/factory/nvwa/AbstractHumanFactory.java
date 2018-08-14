package com.dtian.java.design_pattern.z_example.created.factory.nvwa;

import com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.Human;
import com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.HumanEnum;

/**
 * 编写一个抽象类，根据enum创建一个人类出来
 */
public abstract class AbstractHumanFactory implements HumanFactory {

    //给定一个性别人种，创建一个人类出来 专业术语是产生产品等级
    public Human createHuman(HumanEnum humanEnum) {
        Human human = null;
        // 如果传递进来不是一个Enum中具体的一个Element的话，则不处理
        if (!humanEnum.getValue().equals("")) {
            try {
                human = (Human) Class.forName(humanEnum.getValue()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return human;
    }
}
