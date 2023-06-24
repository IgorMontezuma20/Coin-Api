package com.gm2.crypto.repository;

import com.gm2.crypto.dto.CoinDTO;
import com.gm2.crypto.entity.Coin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@EnableAutoConfiguration
public class CoinRepository {

    //private static String INSERT = "insert into coin (name, price, quantity, datetime) values (?,?,?,?)";
    private static String INSERT2 = "insert into coin (name, price, quantity, datetime) values(?,?,?,?)";

    private static String SELECT_ALL = "select name, sum(quantity) as quantity from coin group by name";

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

    public List<CoinDTO> getAll(){
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CoinDTO>() {
            @Override
            public CoinDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CoinDTO coin = new CoinDTO();
                coin.setName(rs.getString("name"));
                coin.setQuantity(rs.getBigDecimal("quantity"));

                return coin;
            }
        });
    }
}
