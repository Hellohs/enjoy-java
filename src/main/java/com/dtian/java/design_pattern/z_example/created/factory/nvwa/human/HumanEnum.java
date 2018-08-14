package com.dtian.java.design_pattern.z_example.created.factory.nvwa.human;

public enum HumanEnum {


    YellowMaleHuman("com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.YellowMaleHuman"),
    YellowFemaleHuman("com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.YellowFemaleHuman"),

    WhiteMaleHuman("com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.WhiteMaleHuman"),
    WhiteFemaleHuman("com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.WhiteFemaleHuman"),

    BlackMaleHuman("com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.BlackMaleHuman"),
    BlackFemaleHuman("com.dtian.java.design_pattern.z_example.created.factory.nvwa.human.BlackFemaleHuman");

    private String value = "";

    HumanEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
