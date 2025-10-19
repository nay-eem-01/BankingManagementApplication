package org.example.bankingManagementApplication.constatnt;

public final class AppTables {

    public AppTables() {
    }
    public static final String USER_NAME = "users";
    public static final String ROLE_NAME = "role";
    public static final String USER_ROLE_NAME = "user_role";
    public static final String PRIVILEGE_NAME = "privilege";
    public static final String ROLE_PRIVILEGE_NAME =  "role_privilege";
    public static final String BANK_ACCOUNT_NAME =  "bank_accounts";
    public static final String TRANSACTION_NAME =  "transactions";

    public static final class AuditModelTable {
        public static final String ID = "id";
        public static final String CREATED_BY = "created_by";
        public static final String CREATION_DATE = "creation_date";
        public static final String LAST_MODIFIED_BY = "last_modified_by";
        public static final String LAST_MODIFIED_DATE = "last_modified_date";
    }

    public static final class UserTable {
        public static final String EMAIL = "email";
        public static final String NAME = "full_name";
        public static final String USER_ID = "user_id";
        public static final String PASSWORD = "password";
    }

    public static final class BankAccountTable {
    public static final String ACCOUNT_NUMBER = "account_number";
    public static final String BALANCE = "balance";
    }

    public static final class TransactionTable {
        public static final String TRANSACTION_ID = "transaction_Id";
        public static final String TO_ACCOUNT_NUMBER = "to_account_number";
        public static final String FROM_ACCOUNT_NUMBER = "from_account_number";
        public static final String TRANSACTION_AMOUNT = "amount";
    }

    public static final  class RoleTable{

        public static final String ROLE_ID = "role_id";
        public static final String ROLE_TYPE = "role_type";
        public static final String ROLE_NAME = "role_name";
    }

    public static final class PrivilegeTable {
        public static final String PRIVILEGE_ID = "privilege_id";
        public static final String NAME = "name";
        public static final String DESC_NAME = "desc_name";
    }
}
