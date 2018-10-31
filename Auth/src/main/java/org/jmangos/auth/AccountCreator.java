package org.jmangos.auth;

import org.jmangos.auth.entities.AccountEntity;
import org.jmangos.auth.wow.services.AccountService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class AccountCreator implements InitializingBean {
    private AccountService accountService;
    @Override
    public void afterPropertiesSet() throws Exception {
        new AccountEntity();
        accountService.createOrUpdateAccount();
    }
}
