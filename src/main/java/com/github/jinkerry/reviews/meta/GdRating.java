package com.github.jinkerry.reviews.meta;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: jinfeng
 * Date: 14-2-25
 * Time: 上午9:42
 * To change this template use File | Settings | File Templates.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class GdRating {

    @XmlAttribute(name = "xmlns:gd")
    private String gd = "http://schemas.google.com/g/2005";
    @XmlAttribute
    private String value;

    public GdRating() {
        super();
    }

    public GdRating(String value) {
        this.value = value;
    }

    public String getGd() {
        return gd;
    }

    public void setGd(String gd) {
        this.gd = gd;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
