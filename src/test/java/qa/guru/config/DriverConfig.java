package qa.guru.config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

import static qa.guru.config.Project.isRemoteWebDriver;

public class DriverConfig {
    public static void configure() {
        //System.setProperty("switch", "remote");

        Properties props = System.getProperties();
        String system = props.getProperty("switch");
        if (system == null) {
            System.setProperty("switch", "local");
        }

        Configuration.browser = Project.webConfig.browserName();
        Configuration.browserVersion = Project.webConfig.BrowserVersion();
        Configuration.browserSize = Project.webConfig.BrowserSize();
        Configuration.baseUrl = Project.webConfig.getBaseUrl();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (isRemoteWebDriver()) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = Project.webConfig.remoteUrl();
        }
        Configuration.browserCapabilities = capabilities;

    }
}
