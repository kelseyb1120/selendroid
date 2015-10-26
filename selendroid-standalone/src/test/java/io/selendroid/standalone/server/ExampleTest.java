/*
 * Copyright 2012-2014 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.standalone.server;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.standalone.SelendroidConfiguration;
import io.selendroid.standalone.SelendroidLauncher;
import io.selendroid.standalone.android.AndroidSdk;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

// test code from:
// https://github.com/selendroid/demoproject-selendroid/blob/8aa982a3b15218a2dccb63fb56ed8425d53f9b8f/src/main/java/io/selendroid/demo/SelendroidIntegrationTest.java
public class ExampleTest {
    private static SelendroidLauncher selendroidServer = null;
    private static WebDriver driver = null;

    static {
        // NOTE: Must change path to be correct on your system if ANDROID_HOME
        // isn't defined.
        AndroidSdk.ANDROID_HOME_PATH = "/path/to/android/home";
    }

    @BeforeClass
    public static void startSelendroidServer() throws Exception {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        SelendroidConfiguration config = new SelendroidConfiguration();
        // NOTE: Must change path to be correct on your system.
        // TODO: make this relative and use the actual selendroid test apk.
        config.addSupportedApp("/path/to/Guru99App.apk");
        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.launchSelendroid(); // Null Pointer Exception

        SelendroidCapabilities caps = new SelendroidCapabilities();
        caps.setAut("com.guru99app:1.0");
        caps.setAutomationName("uiautomator");

        driver = new SelendroidDriver(caps);
    }

    @AfterClass
    public static void stopSelendroidServer() {
        if (driver != null) {
            driver.quit();
        }
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
    }

    @Test
    public void shouldStartAndStopTheServer() throws Exception {
        Assert.assertTrue(true);
    }
}
