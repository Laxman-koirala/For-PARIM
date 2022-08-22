package com.qa.factoryInitilizer;

import com.microsoft.playwright.*;
import com.qa.utils.TestUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
public class Factory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties props;
    InputStream props_are;
    InputStream strings_are;
    InputStream details;
    JSONObject testdata_are;
    protected static HashMap<String, String> strings = new HashMap<>();

    public Page initBrowser(Properties prop){
        String browserName = prop.getProperty("browser").trim().toLowerCase();
        playwright = Playwright.create();
        Map<String, Object> map = new HashMap<>();
        /*
        map.put("chromium", playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
        map.put("firefox", playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
        map.put("safari", playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
        */
        map.put("chrome", playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
        if (map.containsKey(browserName)){
            browser = (Browser) map.get(browserName);
            browserContext = browser.newContext();
            page = browserContext.newPage();
            page.navigate(prop.getProperty("url"));
        }
        return page;

    }

    public Properties init_prop() {
        try{
            props = new Properties();
            String propFileName = "config.properties";
            props_are = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(props_are);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return props;
    }

    public HashMap<String, String> init_Strings() throws Exception {
        String xmlFileName = "Strings/strings.xml";
        strings_are = getClass().getClassLoader().getResourceAsStream(xmlFileName);
        TestUtils utils = new TestUtils();
        strings = utils.parseStringXML(strings_are);
        return strings;

    }

    public JSONObject init_TestData(){
        String TestDataFileName = "TestData/TodoList.json";
        details = getClass().getClassLoader().getResourceAsStream(TestDataFileName);
        JSONTokener token = new JSONTokener(details);
        testdata_are = new JSONObject(token);

        return testdata_are;
    }







}
