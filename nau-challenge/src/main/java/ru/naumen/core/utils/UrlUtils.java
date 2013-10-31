package ru.naumen.core.utils;

import ru.naumen.core.info.Params;
import ru.naumen.model.User;

/**
 * @author scelovalnikov
 *
 */
public class UrlUtils
{

    public static String createAKParam(User user)
    {
        return Params.ACCESS_KEY_PARAM + "=" + user.getAccessKey();
    }

}
