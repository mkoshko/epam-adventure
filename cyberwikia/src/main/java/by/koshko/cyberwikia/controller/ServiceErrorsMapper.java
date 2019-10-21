package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.EntityError;

import java.util.EnumMap;

public class ServiceErrorsMapper {
    private static final EnumMap<EntityError, String> ERROR_MAP
            = new EnumMap<>(EntityError.class);

    static {
        ERROR_MAP.put(EntityError.DUPLICATE_LOGIN, "user.error.login");
        ERROR_MAP.put(EntityError.DUPLICATE_EMAIL, "user.error.email");
        ERROR_MAP.put(EntityError.DUPLICATE_NICKNAME, "player.error.nickname");
        ERROR_MAP.put(EntityError.DUPLICATE_TEAMNAME, "teamform.error.duplicatename");
        ERROR_MAP.put(EntityError.REQUIRED_NOT_NULL, "error.fillallrequired");
        ERROR_MAP.put(EntityError.GENERIC_ERROR, "error.genericerror");
        ERROR_MAP.put(EntityError.PLAYER_NOT_ACTIVE, "error.playernotactive");
        ERROR_MAP.put(EntityError.ALREADY_HAS_TEAM, "error.alreadyhasteam");
    }

    public static String getLocalizedErrorKey(final EntityError error) {
        return ERROR_MAP.get(error);
    }
}
