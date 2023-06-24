package com.gm2.crypto.repository;

import com.gm2.crypto.entity.Coin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@EnableAutoConfiguration
public class CoinRepository {

    //private static String INSERT = "insert into coin (name, price, quantity, datetime) values (?,?,?,?)";
    private static String INSERT2 = "insert into coin (name, price, quantity, datetime) values(?,?,?,?)";

    private final JdbcTemplate jdbcTemplate;

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
        jdbcTemplate.update(INSERT2, attr);
        return coin;
    }
}
