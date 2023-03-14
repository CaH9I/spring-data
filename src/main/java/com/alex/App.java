package com.alex;

import com.alex.config.DataSourceConfig;
import com.alex.dao.ObjectContainerRepository;
import com.alex.model.Device;
import com.alex.model.ObjectContainer;
import com.alex.service.DeviceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    public static void main( String[] args ) {
        var applicationContext = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        var ocRepository = applicationContext.getBean(ObjectContainerRepository.class);
        var deviceService = applicationContext.getBean(DeviceService.class);

        var container1 = new ObjectContainer("first_level", 1L);
        var container2 = new ObjectContainer("second_level", 2L);
        var container3 = new ObjectContainer("third_level", 3L);
        var containers = List.of(container1, container2, container3);

        ocRepository.saveAll(containers);

        var topDevice = new Device();
        topDevice.setTreeName("Azure_device");
        topDevice.setObjectContainers(containers);
        topDevice.setModified(23414L);

        deviceService.save(topDevice);

        var device = new Device();
        device.setTreeName("750d02_uswest_Azure_device");
        device.setTopLevelDevice(topDevice);
        device.setObjectContainers(List.of(container1, container2));
        device.setModified(23420L);

        deviceService.save(device);

        System.out.println("DB initialization has finished");
        System.out.println("============================================================================================================================================================");

        deviceService.execute();

        System.exit(0);
    }
}
