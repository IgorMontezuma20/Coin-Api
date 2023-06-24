package com.gm2.crypto.repository;

import com.gm2.crypto.entity.Coin;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Array;

public class CoinRepository {

    private static String INSERT = "insert into coin (name, price, quantity, datetime) values=(?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    public CoinRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Coin insert(Coin coin){
        Object[] attr = new Object[] {
                coin.getName(),
                coin.getPrice(),
                coin.getQuantity(),
                coin.getDateTime()
        };
        jdbcTemplate.update(INSERT, attr);
        return coin;
    }
}
