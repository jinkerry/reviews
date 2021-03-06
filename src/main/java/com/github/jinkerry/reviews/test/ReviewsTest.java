package com.github.jinkerry.reviews.test;
import com.github.jinkerry.reviews.data.ReviewsData;
import com.github.jinkerry.reviews.meta.Review;
import com.github.jinkerry.reviews.utils.ReviewsClient;
import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: jinfeng
 * Date: 14-2-22
 * Time: 下午3:56
 * To change this template use File | Settings | File Templates.
 */
public class ReviewsTest {

    @Test(groups = "smoke", dataProvider = "getAll", dataProviderClass = ReviewsData.class)
    public void getAllReviewSmoke(String userId, int expect) throws IOException, DocumentException {
        ReviewsClient client = new ReviewsClient();

        ArrayList<Review> reviewList = client.getAllReviewsOfUser(userId);

        Assert.assertEquals(reviewList.size(), expect, "The number of reviews is not equal" + expect);
    }

    @Test(groups = "smoke", dataProvider = "createReview", dataProviderClass = ReviewsData.class)
    public void createReviewSmoke(String id, String content, String value, String title, int expect) throws IOException, DocumentException {
        ReviewsClient client = new ReviewsClient();

        String[] resp = client.createReview(id, content, value, title);

        Assert.assertTrue(resp[0].indexOf(expect) > 0);

        //get the
        Review review = client.getReview(id);
        Assert.assertEquals(id, review.getDbSubject().getId(), "");

    }

    @Test(groups = "smoke", dataProvider = "getOneReview", dataProviderClass = ReviewsData.class)
    public void getReviewSmoke(String id, int expect) throws IOException, DocumentException {
        ReviewsClient client = new ReviewsClient();

        Review review = client.getReview(id);
        Assert.assertEquals(id, review.getDbSubject().getId(), "");

    }

    @Test(dataProvider = "userNotExist", dataProviderClass = ReviewsData.class)
    public void getAllReviewsUserNotExist(String userId, String headerExpect, String bodyExpect) throws IOException, DocumentException {
        ReviewsClient client = new ReviewsClient();
        String[] resp = client.getAllReviewsOfUserNotExist(userId);

        Assert.assertTrue(resp[0].indexOf(headerExpect) > 0);
        Assert.assertEquals(resp[1], bodyExpect);
    }



}
