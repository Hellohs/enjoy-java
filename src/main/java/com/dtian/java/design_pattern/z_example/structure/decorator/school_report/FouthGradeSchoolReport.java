package com.dtian.java.design_pattern.z_example.structure.decorator.school_report;

public class FouthGradeSchoolReport extends SchoolReport{
    public FouthGradeSchoolReport(String student) {
        this.student = student;
    }

    @Override
    protected void report() {
        System.out.println("尊敬的" + this.student + "家长：");
        System.out.println("........");
        System.out.println("语文： 62   数学：65   体育：98   自然：63");
        System.out.println("..........");
        System.out.println("家长签字：");
    }

    @Override
    protected void sign(String name) {
        System.out.println("家长签名：" + name);
    }
}
