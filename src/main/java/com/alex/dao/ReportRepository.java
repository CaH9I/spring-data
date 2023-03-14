package com.alex.dao;

import com.alex.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, String>  {

    @Transactional
    void deleteAllByNameIn(List<String> names);
}
