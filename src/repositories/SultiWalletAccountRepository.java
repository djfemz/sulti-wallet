package repositories;

import models.Account;

import java.sql.*;

public class SultiWalletAccountRepository implements AccountRepository{
    @Override
    public Account save(Account account) {
        try(Connection connection= DatabaseConnectionManager.connect()){
            String insertSql = """
                    INSERT into accounts(`id`, `name`, `balance`) values(NULL, ?, ?)
                    """;
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setString(1, account.getName());
            insertStatement.setBigDecimal(2, account.getBalance());
            insertStatement.execute();
            return getSavedAccount(connection);
        }catch(Exception exception){
            System.err.println(exception.getMessage());
            throw new RuntimeException();
        }
    }



    private Account getSavedAccount(Connection connection) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE id=(SELECT max(id) FROM accounts)");
            ResultSet resultSet = preparedStatement.executeQuery();
            Account account = new Account();
            resultSet.next();
            account.setId(resultSet.getInt(1));
            account.setName(resultSet.getString(2));
            account.setBalance(resultSet.getBigDecimal(3));
            return account;
    }
}
