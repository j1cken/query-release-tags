package it.targz.restclient;

/**
 * Release
 */
public class Release {

    public Release() {
    }

    public Release(String name) {
        super();
        this.name = name;
    }

    // public long id;
    public String url;
    public String html_url;
    public String name;
    public String tag_name;
}