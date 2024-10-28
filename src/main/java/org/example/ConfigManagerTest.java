package org.example;

import org.pytorch.serve.util.ConfigManager;

import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
//import java.util.logging.Logger;

public class ConfigManagerTest {

    public static void main(String[] args) throws Exception {

        //Test 1
        System.out.println("Test 1: test for Allowed Urls");
        String defaultTsAllowedUrls = ConfigManager.getDefaultTsAllowedUrls();
        System.out.println("Default allowed URLs: " + defaultTsAllowedUrls);
        assertEquals(ConfigManager.getDefaultTsAllowedUrls(), "file://.*|http(s)?://.*");

        //Test 2 - test for default value
            System.out.println("Test 2 - test for default value");
            ConfigManager.Arguments arg = new ConfigManager.Arguments();
            arg.setModels(new String[]{"noop_v0.1"});
            ConfigManager.init(arg);
            ConfigManager c = ConfigManager.getInstance();
            c.setProperty("keystore", "src/test/resources/keystore.p12");
            List<String> allowedUrls = c.getAllowedUrls();
            System.out.println("Allowed URLs after setting keystore: " + allowedUrls);
            System.out.println("Default allowed URLs: " + ConfigManager.getDefaultTsAllowedUrls());
            System.out.println(c.getAllowedUrls());
            System.out.println(ConfigManager.getDefaultTsAllowedUrls());
            assertEquals(c.getAllowedUrls(), List.of(ConfigManager.getDefaultTsAllowedUrls()));

        //Test 3 - test for non default value
        System.out.println("Test 3 - test for non default value");
        ConfigManager c1 = ConfigManager.getInstance();
        Properties p1 = c1.getProp();
        p1.setProperty("allowed_urls", "1,2");
        System.out.println("Allowed URLs after setting allowed_urls: " + c1.getAllowedUrls());
        assertEquals(c1.getAllowedUrls(), List.of("1", "2"));



    }
}


