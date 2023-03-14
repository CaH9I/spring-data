package com.alex.dao;

import com.alex.model.ObjectContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ObjectContainerRepository extends JpaRepository<ObjectContainer, Long> {}
