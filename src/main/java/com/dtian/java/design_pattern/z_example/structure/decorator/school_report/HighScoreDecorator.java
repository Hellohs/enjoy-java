package com.dtian.java.design_pattern.z_example.structure.decorator.school_report;

public class HighScoreDecorator extends SchoolReportDecorator{
    public HighScoreDecorator(SchoolReport schoolReport) {
        super(schoolReport);
    }

    @Override
    protected void report() {
        reportHighScore();
        schoolReport.report();
    }

    //我要汇报最高成绩
    private void reportHighScore(){
        System.out.println("这次考试语文最高是75，数学是78，自然是80");
    }

    @Override
    protected void sign(String name) {
        schoolReport.sign(name);
    }
}
