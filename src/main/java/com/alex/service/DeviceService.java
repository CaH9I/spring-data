package com.alex.service;

import com.alex.dao.DeviceRepository;
import com.alex.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public void execute() {
        System.out.println("\nFetching 750d02_uswest_Azure_device");
        // device2.topLevelDevice will be fetched eagerly ignoring 'fetch = FetchType.LAZY'
        var device2 = deviceRepository.findDeviceByTreeName("750d02_uswest_Azure_device");

        System.out.println("\nFetching Azure_device");
        var device1 = deviceRepository.findDeviceByTreeName("Azure_device");

        System.out.println("\nInitializing Azure_device's object containers");
        // device1.objectContainers will be falsely detected as dirty
        device2.getObjectContainers().size();
    }

    public void save(Device device) {
        deviceRepository.save(device);
    }
}
