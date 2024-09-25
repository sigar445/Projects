package org.sigarLLD.URLShortener.Helper;

public class Base62Encoder {

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = BASE62.length();

    public static String encode(long id) {
        StringBuilder encodedString = new StringBuilder();
        while (id > 0) {
            encodedString.append(BASE62.charAt((int) (id % BASE)));
            id /= BASE;
        }
        return encodedString.reverse().toString();
    }
}
