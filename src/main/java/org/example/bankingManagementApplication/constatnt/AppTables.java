package org.example.bankingManagementApplication.constatnt;

public final class AppTables {

    public AppTables() {
    }
    public static final String USER_NAME = "USERS";
    public static final String ROLE_NAME = "ROLE";
    public static final String PAGE_NAME = "PAGE";
    public static final String DB_FILE_NAME = "DB_FILE";
    public static final String SECTION_NAME = "SECTION";
    public static final String USER_ROLE_NAME = "USER_ROLE";
    public static final String PRIVILEGE_NAME = "PRIVILEGE";
    public static final String NOTIFICATION_NAME = "notification";
    public static final String REFRESH_TOKEN_NAME = "REFRESH_TOKEN";
    public static final String ROLE_PRIVILEGE_NAME =  "ROLE_PRIVILEGE";

    public static final class AuditModelTable {
        public static final String ID = "ID";
        public static final String CREATED_BY = "CREATED_BY";
        public static final String CREATION_DATE = "CREATION_DATE";
        public static final String LAST_MODIFIED_BY = "LAST_MODIFIED_BY";
        public static final String LAST_MODIFIED_DATE = "LAST_MODIFIED_DATE";
    }

    public static final class UserTable {
        public static final String EMAIL = "EMAIL";
        public static final String NAME = "NAME";
        public static final String BANNED = "BANNED";
        public static final String ROLE_ID = "ROLE_ID";
        public static final String USER_ID = "USER_ID";
        public static final String VERIFIED = "VERIFIED";
        public static final String password = "PASSWORD";
        public static final String PASSWORD_RESET_TOKEN = "PASSWORD_RESET_TOKEN";
    }

    public static final  class RoleTable{

        public static final String ROLE_ID = "ROLE_ID";
        public static final String ROLE_TYPE = "ROLE_TYPE";
        public static final String ROLE_NAME = "ROLE_NAME";
    }

    public static final class PrivilegeTable {
        public static final String PRIVILEGE_ID = "PRIVILEGE_ID";
        public static final String NAME = "NAME";
        public static final String DESC_NAME = "DESC_NAME";
    }


}
