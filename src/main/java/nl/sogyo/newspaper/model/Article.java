package nl.sogyo.newspaper.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wkerrebijn on 28-11-2017.
 */
@XmlRootElement(name="article")
public class Article {
    @XmlElement private String id;
    @XmlElement private String title;
    @XmlElement private String body;

    public Article() {
        id = "1";
        title = "Title";
        body = "Body";
    }

    public Article(String id) {
        this.id = id;
    }

    public Article(String id, String body) {
        this.id = id;
        this.body = body;
    }

    public Article(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
