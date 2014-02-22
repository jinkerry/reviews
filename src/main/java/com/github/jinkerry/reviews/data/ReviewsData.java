package com.github.jinkerry.reviews.data;

import org.testng.annotations.DataProvider;

/**
 * Created with IntelliJ IDEA.
 * User: jinfeng
 * Date: 14-2-22
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
public class ReviewsData {

    @DataProvider(name = "getAll")
    public static Object[][] getAll() {

        return new Object[][]{
                //userid, number of reviews
                {CommonData.userID, 4},

        };
    }

    @DataProvider(name = "userNotExist")
    public static Object[][] userNotExist() {

        return new Object[][]{
                //userid, msg of header, msg of body
                {"1000", "404 Not Found", "wrong people id:'1000'"},

        };
    }
}
