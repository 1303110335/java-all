/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.druid.dao.impl;

import com.redis.example.demo.druid.BeanHandler;
import com.redis.example.demo.druid.JdbcTemplate;
import com.redis.example.demo.druid.dao.IAccountDAO;
import com.redis.example.demo.druid.domain.Account;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class AccountDAOImpl implements IAccountDAO {

    @Override
    public void save(Account account) {
        String sql = "INSERT INTO t_account(name, age, phone, address) VALUES(?, ?, ?, ?)";
        Object[] arguments = new Object[] { account.getName(), account.getAge(), account.getPhone(), account.getAddress() };
        JdbcTemplate.update(sql, arguments);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM t_account WHERE id = ?";
        JdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE t_account SET name = ?, age = ?, phone = ?, address = ? WHERE id = ?";
        Object[] arguments = new Object[] { account.getName(), account.getAge(), account.getPhone(), account.getAddress(), account.getId() };
        JdbcTemplate.update(sql, arguments);
    }

    @Override
    public Account get(Long id) {
        String sql = "SELECT id, name, age, phone, address FROM t_account WHERE id = ?";
        List<Account> accountList = JdbcTemplate.query(sql, new BeanHandler<>(Account.class), id);
        if (CollectionUtils.isEmpty(accountList)) {
            return new Account();
        }
        return accountList.get(0);
    }

    @Override
    public List<Account> list() {
        String sql = "SELECT id, name, age, phone, address FROM t_account";
        return JdbcTemplate.query(sql, new BeanHandler<>(Account.class));
    }

}