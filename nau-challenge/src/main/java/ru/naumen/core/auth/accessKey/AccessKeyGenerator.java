package ru.naumen.core.auth.accessKey;

import java.util.UUID;

/**
 * @author serce
 * @since 25 окт. 2013 г.
 */
public class AccessKeyGenerator {
    
    public static String generateNewAccessKey() {
        return UUID.randomUUID().toString();
    }

}
