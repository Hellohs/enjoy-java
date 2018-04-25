package com.dtian.java.design_pattern.z_example.structure.decorator.school_report;

public class Father {
    public static void main(String[] args) {
        SchoolReport schoolReport = new HighScoreDecorator(new FouthGradeSchoolReport("xiaom"));
        schoolReport.report();
        schoolReport.sign("maxm");
    }
}
