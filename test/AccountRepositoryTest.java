import models.Account;
import org.junit.jupiter.api.Test;
import repositories.AccountRepository;
import repositories.SultiWalletAccountRepository;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountRepositoryTest {

    private final AccountRepository accountRepository = new SultiWalletAccountRepository();

    @Test
    public void testSaveAccount() throws SQLException {
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(100_000));
        account.setName("");

        Account savedAccount = accountRepository.save(account);

        assertNotNull(savedAccount);
        assertEquals(5, savedAccount.getId());
    }
}
