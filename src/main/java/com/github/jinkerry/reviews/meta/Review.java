package com.github.jinkerry.reviews.meta;

/**
 * Created with IntelliJ IDEA.
 * User: jinfeng
 * Date: 14-2-21
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */
public class Review {

    private String id; //评论对应的条目id
    private String title;     //评论标题
    private String summary;   //评论内容, 评论正文字数不得少于150个字
    private String gdRating;  //评论对条目的评分，5分制

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGdRating() {
        return gdRating;
    }

    public void setGdRating(String gdRating) {
        this.gdRating = gdRating;
    }



}
