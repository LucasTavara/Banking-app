package net.javaguides.backing.service.impl;

import net.javaguides.backing.Repository.AccountRepository;
import net.javaguides.backing.dto.AccountDto;
import net.javaguides.backing.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountServiceImpl accountRepository;

    public AccountServiceImpl(AccountServiceImpl accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto account) {
        return null;
    }
}
