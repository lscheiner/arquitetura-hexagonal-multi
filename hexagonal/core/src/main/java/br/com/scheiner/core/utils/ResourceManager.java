package br.com.scheiner.core.utils;

import java.lang.management.ManagementFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ResourceManager  {
    
	// 2 minutos
	@Scheduled(fixedRate = 120000L)
    public void doTask() {
        
        int mb = 1024 * 1024;
        var runtime = Runtime.getRuntime();
        
        var resource = new StringBuilder();
        
        resource.append("\nMonitor de recursos:")
            .append("\n===> Memoria Utilizada: [" + (runtime.totalMemory() - runtime.freeMemory()) / mb + "m]")
            .append("\n===> Memoria Heap: [" + (ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()) / mb + "m]")
            .append("\n===> Memoria Livre: [" + runtime.freeMemory() / mb + "m]")
            .append("\n===> Total de Memoria [" + runtime.totalMemory() / mb + "m]")
            .append("\n===> Maximo de Memoria [" + runtime.maxMemory() / mb + "m]")
            .append("\n===> Processadores [" + runtime.availableProcessors() + "]")
            .append("\n===> Threads Ativas [" + ManagementFactory.getThreadMXBean().getThreadCount() + "]")
            .append("\n===> Load: [" + ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage() + "]");
        
        log.info(resource.toString());
    }

}
