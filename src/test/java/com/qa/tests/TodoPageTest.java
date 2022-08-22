package com.qa.tests;

import com.qa.bases.BaseTest;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TodoPageTest extends BaseTest {

    @Test (priority = 0)
    public void CheckPageTitle(){
        Assert.assertEquals(todoPage.getTitle(),strings.get("title"));
        todoPage.todo_log.info("Title : |Passed|");

    }

    @Test (priority = 1)
    public void checkInputField(){
        todoPage.tapInputField();
        todoPage.todo_log.info("Existence of inputField : |Passed|");

    }

    @Test(priority = 2)
    public void verifyAddToDoList(){
        JSONArray jsonArray = testData.getJSONArray("Mon-Fri");
        ArrayList<Object> myTodo = new ArrayList<Object>();
        for (int i=0;i<jsonArray.length();i++){
            myTodo.add(jsonArray.get(i));
        }
        for(int i=0; i<myTodo.size(); i++) {
            todoPage.addInputText((String) myTodo.get(i));
            todoPage.pressPlus();
            todoPage.todo_log.info(String.format("TODO Added --> :%s",(String) myTodo.get(i)));

        };
        todoPage.todo_log.info("Adding to TODO: |Passed|");

    }
    @Test(priority = 3)
    public void verifyDeleteToDoList() throws InterruptedException {
        int totalToDo = todoPage.toDoListCount();
        for(int i=0; i<totalToDo; i++) {
            todoPage.deleteTodoList();
        }
        todoPage.todo_log.info("Deleting TODO: |Passed|");

    }
}
