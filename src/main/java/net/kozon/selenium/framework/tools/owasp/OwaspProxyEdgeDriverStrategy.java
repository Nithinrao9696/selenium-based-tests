package net.kozon.selenium.framework.tools.owasp;

import net.kozon.selenium.framework.tools.utils.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;

public class OwaspProxyEdgeDriverStrategy extends ProxyStrategy {

    @Override
    public WebDriver webDriver() throws IOException {
        return new EdgeDriver(owaspZAPService(), owaspZAPOptions());
    }

    private EdgeOptions owaspZAPOptions() {
        EdgeOptions options = new EdgeOptions();
        options.setProxy(proxyConfig());
        return options;
    }

    private EdgeDriverService owaspZAPService() throws IOException {
        EdgeDriverService service = new EdgeDriverService.Builder()
                .usingDriverExecutable(new File(Configuration.getPropertyFromFile("edgeDriver")))
                .usingAnyFreePort()
                .usingAnyFreePort()
                .build();
        service.start();
        return service;
    }
}
