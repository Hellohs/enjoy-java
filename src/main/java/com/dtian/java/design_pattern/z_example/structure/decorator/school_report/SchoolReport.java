package com.dtian.java.design_pattern.z_example.structure.decorator.school_report;

/**抽象基类，成绩报告单*/
public abstract class SchoolReport {
    protected String student = "unknow";

    /**成绩单展示成绩*/
    protected abstract void report();

    /**家长签字*/
    protected abstract void sign(String name);
}
