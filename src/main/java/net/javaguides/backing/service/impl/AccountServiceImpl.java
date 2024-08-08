package net.javaguides.backing.service.impl;


import net.javaguides.backing.Entity.Account;
import net.javaguides.backing.Mapper.AccountMapper;
import net.javaguides.backing.Repository.AccountRepository;
import net.javaguides.backing.dto.AccountDto;
import net.javaguides.backing.service.AccountService;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository =accountRepository ;
    }

    @Override
    public AccountDto createAccount (AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account  saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {

        Account  account = accountRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Account does exists"));


        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account  account = accountRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Account does exists"));

        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account  account = accountRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Account does exists"));

        if (account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
       Account  savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
      return accounts.stream().map((account )-> AccountMapper.mapToAccountDto(account) )
              .collect(Collectors.toList());



    }

    @Override
    public void deleteAccount(Long id) {
        Account  account = accountRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Account does exists"));

        accountRepository.deleteById(id);
    }
}
