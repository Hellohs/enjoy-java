package com.dtian.java.design_pattern.z_example.structure.decorator.school_report;

public class SchoolReportDecorator extends SchoolReport{
    protected SchoolReport schoolReport;

    public SchoolReportDecorator(SchoolReport schoolReport) {
        this.schoolReport = schoolReport;
    }

    @Override
    protected void report() {
        schoolReport.report();
    }

    @Override
    protected void sign(String name) {
        schoolReport.sign(name);
    }
}
