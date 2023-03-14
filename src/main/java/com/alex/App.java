package com.alex;

import com.alex.config.DataSourceConfig;
import com.alex.dao.ReportRepository;
import com.alex.model.Report;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    public static void main( String[] args ) {
        var applicationContext = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        var reportRepository = applicationContext.getBean(ReportRepository.class);

        var report = new Report();
        report.setName("report1");
        reportRepository.save(report);

        new Report();
        report.setName("report2");
        report.setGenerated(true);
        reportRepository.save(report);

        new Report();
        report.setName("report3");
        report.setGenerated(true);
        reportRepository.save(report);

        System.out.println("String removing entities");
//        var r1 = new Report();
//        r1.setName("report11");
//        var r2 = new Report();
//        r2.setName("report2");
//        reportRepository.deleteInBatch(List.of(r1, r2));
        reportRepository.deleteAllByNameIn(List.of("report1", "report2"));
    }
}
