package common.security;

/**
 * Константы
 *
 * @author elf
 */
public class Constants {
    /**
     * Роли
     */
    public static class Role {
        // all micro-services
        public static final String ADMIN = "admin";

        // dicts common
        public static final String DC_ADMIN = "dicts_common_admin";
        public static final String DC_READER = "dicts_common_reader";
        public static final String DC_WRITER = "dicts_common_writer";

        // dicts decstra
        public static final String DD_ADMIN = "dicts_decstra_admin";
        public static final String DD_READER = "dicts_decstra_reader";
        public static final String DD_WRITER = "dicts_decstra_writer";

        // integrations
        public static final String I_ADMIN = "integrations_admin";
        public static final String I_READER = "integrations_reader";
        public static final String I_WRITER = "integrations_writer";

        // calculations
        public static final String C_ADMIN = "calculations_admin";
        public static final String C_READER = "calculations_reader";
        public static final String C_WRITER = "calculations_writer";

        // publications
        public static final String P_ADMIN = "publications_admin";
        public static final String P_READER = "publications_reader";
        public static final String P_WRITER = "publications_writer";

        // users
        public static final String U_ADMIN = "users_admin";
        public static final String U_READER = "users_reader";
        public static final String U_WRITER = "users_writer";

        // auth
        public static final String A_ADMIN = "auth_admin";
        public static final String A_READER = "auth_reader";
        public static final String A_WRITER = "auth_writer";
    }

    /**
     * Метод аутентификации
     */
    public static class AuthMethod {
        public static final String BASIC = "BASIC";
        public static final String FORM = "FORM";
        public static final String MP_JWT = "MP-JWT";
    }

    /**
     * Реалмы
     */
    public static class Realm {
        public static final String DECSTRA = "DECSTRA";
    }

    private Constants() {
        // hide constructor
    }
}
