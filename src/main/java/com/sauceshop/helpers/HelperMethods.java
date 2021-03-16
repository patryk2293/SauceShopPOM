package com.sauceshop.helpers;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HelperMethods {

    public static void selectFromDropDown(List<WebElement> list, String expected) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains(expected)) {
                list.get(i).click();
            }
        }
    }

    public static List<String> addToListString(List<WebElement> listOfWebElements) {
        String element = "";
        List<String> listString = new ArrayList<String>();
        for (int i = 0; i < listOfWebElements.size(); i++) {
            element = listOfWebElements.get(i).getText();
            listString.add(element);
        }
        return listString;
    }

    public static List<Double> convertStringToDouble(List<String> listOfStrings) {
        String afterReplacing;
        double numberAfter;
        List<Double> numbers = new ArrayList<Double>();
        for (int i = 0; i < listOfStrings.size(); i++) {
            afterReplacing = listOfStrings.get(i).replace("$", "");
            numberAfter = Double.parseDouble(afterReplacing);
            numbers.add(numberAfter);
        }
        return numbers;
    }
}
