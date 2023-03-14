package com.alex.dao;

import com.alex.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Device findDeviceByTreeName(String name);
}
