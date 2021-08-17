/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.druid.dao;

import com.redis.example.demo.druid.domain.Account;
import java.util.List;

/**
 *
 * @author xuleyan
 * @version IAccountDAO.java, v 0.1 2020-09-24 10:53 下午
 */
public interface IAccountDAO {

    void save(Account account);

    void delete(Long id);

    void update(Account account);

    /**
     * 更新商品数量，不能小于1
     * @param id
     */
    int updateGoods(Integer id);

    Account get(Long id);

    List<Account> list();

}