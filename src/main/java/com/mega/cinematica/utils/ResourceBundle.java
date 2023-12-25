package com.mega.cinematica.utils;

import java.util.Locale;

public class ResourceBundle {
    private ResourceBundle(){}

    private static final java.util.ResourceBundle messagesRu =
            java.util.ResourceBundle.getBundle("message_ru",
                    Locale.forLanguageTag("ru"));

    public static String periodMessages( String key) {
            return messagesRu.getString(key);
        }

}
