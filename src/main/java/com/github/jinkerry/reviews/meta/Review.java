package com.github.jinkerry.reviews.meta;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: jinfeng
 * Date: 14-2-25
 * Time: 上午9:29
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class Review {

    @XmlAttribute(name = "xmlns:ns0")
    private String ns0 = "http://www.w3.org/2005/Atom";

    @XmlElement(name = "db:subject")
    private DbSubject dbSubject;

    private String content;

    @XmlElement(name = "gd:rating")
    private GdRating gdRating;

    private String title;

    public Review(){
        super();
    }

    public Review(String id, String content, String value, String title) {
        DbSubject dbSubject = new DbSubject(id);
        this.dbSubject = dbSubject;
        this.content = content;
        GdRating gdRating = new GdRating(value);
        this.gdRating = gdRating;
        this.title = title;
    }

    public String getNs0() {
        return ns0;
    }

    public void setNs0(String ns0) {
        this.ns0 = ns0;
    }

    public DbSubject getDbSubject() {
        return dbSubject;
    }

    public void setDbSubject(DbSubject dbSubject) {
        this.dbSubject = dbSubject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GdRating getGdRating() {
        return gdRating;
    }

    public void setGdRating(GdRating gdRating) {
        this.gdRating = gdRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
