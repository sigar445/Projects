package org.sigarLLD.URLShortnerOldCode;

public class URLValues {
    private String longURL;
    private String shortURL;
    private String ClientId;

    public URLValues(String longURL, String shortURL, String clientId) {
        this.longURL = longURL;
        this.shortURL = shortURL;
        ClientId = clientId;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }
}
