package com.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;

public class TodoPage {
    public static Logger todo_log = LogManager.getLogger(TodoPage.class.getName());
    private Page page;

    private String todo_inputField = "input[placeholder^='Type your']";
    private String todo_plusButton = "div[class='App-inputs'] button";
    private String todo_allList= "ol>li:nth-of-type(n)";
    private String todo_last= "ol>li:first-of-type";
    private String todo_first= "ol>li:last-of-type";
    private String deleteToDoList = "button>>nth=1";
    private String placeholder = "placeholder";

    public TodoPage(Page page) {
        this.page=page;
    }

    public String getTitle(){
        return page.title();
    }

    public void tapInputField(){
        page.click(todo_inputField);
    }

    public String getPlaceHolderName(){
        Locator PlaceHoldertext = page.locator(todo_inputField);
        PlaceHoldertext.getAttribute("placeholder");
        return page.textContent(placeholder);
    }

    public void addInputText(String text){
        page.fill(todo_inputField, text);
    }

    public int toDoListCount(){
       return page.locator(todo_allList).count();

    }
    public void pressPlus(){
        page.click(todo_plusButton);
    }

    public void deleteTodoList(){
        page.click(deleteToDoList);
    }

}
