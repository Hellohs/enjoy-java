package com.dtian.java.design_pattern.z_example.created.factory.nvwa;

import com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.Human;

/**
 * 抽象工厂 客户端
 * 女娲建立起了两条生产线，分别是：
 * 男性生产线
 * 女性生产线
 */
// 参考 ： https://www.jianshu.com/p/579e67c5703f
public class NvWa {
    public static void main(String[] args) {
        //男性生产线
        HumanFactory maleFactory = new MaleHumanFactory();

        //女性生产线
        HumanFactory femaleFactory = new FemaleHumanFactory();

        //开始生产
        Human blackMale = maleFactory.createBlackHuman();
        Human blackFemale = femaleFactory.createBlackHuman();

        blackMale.laugh();
        blackMale.cry();
        blackMale.talk();
        blackMale.sex();

        blackFemale.laugh();
        blackFemale.cry();
        blackFemale.talk();
        blackFemale.sex();

        //等等 等等，其他的人被造了出来
    }
}
