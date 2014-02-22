package com.github.jinkerry.reviews.utils;

import com.github.jinkerry.reviews.data.CommonData;
import com.github.jinkerry.reviews.meta.Review;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: jinfeng
 * Date: 14-2-21
 * Time: 上午10:06
 * To change this template use File | Settings | File Templates.
 */
public class ReviewsClient {

    private static Logger logger = Logger.getLogger(ReviewsClient.class);

    public Review getReview(String reviewId) throws IOException, DocumentException {
        String url = "http://api.douban.com/review/" + reviewId;
        UrlTools urlTools = new UrlTools(url);
        urlTools.addQueryParam("apikey", CommonData.apikey);

        String[] response = HttpTools.get(urlTools.getCompleteUrl(), null);
        logger.info(url + " : " + response[1]);

        Review review = this.parseReview(response[1]);

        return review;

    }

    public ArrayList<Review> getAllReviewsOfUser(String userID) throws IOException, DocumentException {
        String url = "http://api.douban.com/people/" + userID + "/reviews";
        Header[] headers = new Header[1];
        headers[0] = new BasicHeader("Content-Type", "text/xml");
        String[] response = HttpTools.get(url, headers);
        logger.info(url + " : " + response[0]);
        logger.info(url + " : " + response[1]);

        ArrayList<Review> reviewList = this.parseReviews(response[1]);
        for(Review rev : reviewList) {
            this.printReview(rev);
        }

        return reviewList;

    }

    public String[] getAllReviewsOfUserNotExist(String userID) throws IOException, DocumentException {
        String url = "http://api.douban.com/people/" + userID + "/reviews";
        Header[] headers = new Header[1];
        headers[0] = new BasicHeader("Content-Type", "text/xml");
        String[] response = HttpTools.get(url, headers);
        logger.info(url + " : " + response[0]);
        logger.info(url + " : " + response[1]);

        return response;
    }

    public void createReview() throws IOException {
        String url = "http://api.douban.com/reviews";
        Header[] headers = new Header[1];
        headers[0] = new BasicHeader("Content-Type", "text/xml");
        String[] response = HttpTools.post(url, headers, null);
        logger.info(url + " : " + response[1]);

    }

    public void updateReview(){

    }

    public void deleteReview(){

    }

    private ArrayList<Review> parseReviews(String response) throws UnsupportedEncodingException, DocumentException {

        ArrayList<Review> reviewList = new ArrayList<Review>();

        Document doc = HttpTools.getXML(response);

        Element root = doc.getRootElement();

        for (Iterator i = root.elementIterator("entry"); i.hasNext();) {
            Element entry = (Element) i.next();
            Review review = new Review();
            for (Iterator j = entry.elementIterator(); j.hasNext(); ) {
                Element node = (Element) j.next();
                review = this.parseNode(review, node);
            }
            reviewList.add(review);
        }

        return reviewList;
    }

    private Review parseReview(String response) throws UnsupportedEncodingException, DocumentException {

        Review review = new Review();

        Document doc = HttpTools.getXML(response);
        Element root = doc.getRootElement();

        for (Iterator j = root.elementIterator(); j.hasNext(); ) {
            Element node = (Element) j.next();
            review = this.parseNode(review, node);
        }

        return review;
    }

    private Review parseNode(Review review, Element node){

        String name = node.getName();
        String value = node.getText();

        if (name.equals("id")){
            review.setId(value);
        }
        else if (name.equals("title")){
            review.setTitle(value);
        }
        else if (name.equals("summary")){
            review.setSummary(value);
        }
        else if (name.equals("rating")){
            review.setGdRating(node.attributeValue("value"));
        }

        return review;
    }

    private void printReview(Review rev){
        logger.info("id: " + rev.getId());
        logger.info("title: " + rev.getTitle());
        logger.info("summary: " + rev.getSummary());
        logger.info("rating: " + rev.getGdRating());

    }

    public static void main(String[] argv) throws IOException, DocumentException {
        ReviewsClient client = new ReviewsClient();
        ArrayList<Review> reviewList = client.getAllReviewsOfUser(CommonData.userID);

        //Review rev = client.getReview("6273744");
        //client.printReview(rev);
    }
}
