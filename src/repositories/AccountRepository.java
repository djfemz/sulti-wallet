package repositories;

import models.Account;

import java.sql.SQLException;

public interface AccountRepository {
    Account save(Account account) throws SQLException;
}
