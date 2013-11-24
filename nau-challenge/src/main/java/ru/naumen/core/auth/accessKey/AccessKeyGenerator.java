package ru.naumen.core.auth.accessKey;

import java.util.UUID;

/**
 * Generator for access key
 * <br>
 * Access key - unique id for access without login
 *
 * @author serce
 * @since 25 oct. 2013 г.
 */
public class AccessKeyGenerator {
    
    public static String generateNewAccessKey() {
        return UUID.randomUUID().toString();
    }

}
