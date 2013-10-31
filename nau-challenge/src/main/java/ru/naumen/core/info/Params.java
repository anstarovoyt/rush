package ru.naumen.core.info;

/**
 * User: anstarovoyt
 * Date: 10/23/13
 * Time: 10:45 PM
 */
public final class Params {

    /**
     * Параметр отвественный за аутентификацию
     *
     * Нужен поскольку мы храним состояние системы "глобально"
     * и не хотим привязываться к конкретной сессии или кукам
     */
    public static final String ACCESS_KEY_PARAM = "accesskey";

    /**
     * Параметр по которому мы будем определять какая игра открыта у пользователя
     */
    public static final String GAME_ID = "gid";

    public static final String ANSWER_ID = "answer";

    private Params() {
    }

}
