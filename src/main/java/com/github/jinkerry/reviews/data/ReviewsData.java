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

    @DataProvider(name = "getOneReview")
    public static Object[][] getOneReview() {

        return new Object[][]{

                //id
                {"10086", },

        };
    }

    @DataProvider(name = "createReview")
    public static Object[][] createReview() {
        String id = "http://api.douban.com/movie/subject/1424406";
        String content = "渡边唯一的仁慈，是让小鬼艾德带走了小狗...";
        String value = "4";
        String title = "终点之后";

        return new Object[][]{
                //id, content, gdValue, title, correct response
                {id, content, value, title, "201 CREATED"},

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
