package com.qa.bases;

import com.microsoft.playwright.Page;
import com.qa.factoryInitilizer.Factory;
import com.qa.pages.TodoPage;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Properties;

public class BaseTest {
    Factory pwf;
    protected Page page;
    protected Properties prop;
    protected HashMap<String, String > strings;
    protected TodoPage todoPage;
    protected JSONObject testData;

    @BeforeTest
    public void setup() throws Exception {
        pwf = new Factory();
        prop = pwf.init_prop();
        strings = pwf.init_Strings();
        testData = pwf.init_TestData();
        page = pwf.initBrowser(prop);
        todoPage = new TodoPage(page);
    }

    @AfterTest
    public void tearDown(){
        page.context().browser().close();
    }
}