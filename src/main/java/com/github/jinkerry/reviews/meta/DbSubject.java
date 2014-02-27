package com.github.jinkerry.reviews.meta;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: jinfeng
 * Date: 14-2-25
 * Time: 上午9:41
 * To change this template use File | Settings | File Templates.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DbSubject {
    @XmlAttribute(name = "xmlns:db")
    private String db = "http://www.douban.com/xmlns/";

    private String id;

    public DbSubject() {
        super();
    }

    public DbSubject(String id) {
        this.id = id;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
