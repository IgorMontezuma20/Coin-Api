package com.gm2.crypto.controller;

import com.gm2.crypto.entity.Coin;
import com.gm2.crypto.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@RestController
@RequestMapping("/coin")
public class CoinController {

    @Autowired
    private CoinRepository coinRepository;

    @Bean
    public Coin init(){
        Coin c1 =  new Coin();
        Coin c2 =  new Coin();
        Coin c3 =  new Coin();

        c1.setName("BITCOIN");
        c1.setPrice(new BigDecimal(100));
        c1.setQuantity(new BigDecimal(0.0005));
        c1.setDateTime(new Timestamp(System.currentTimeMillis()));

        c2.setName("BITCOIN");
        c2.setPrice(new BigDecimal(150));
        c2.setQuantity(new BigDecimal(0.0025));
        c2.setDateTime(new Timestamp(System.currentTimeMillis()));

        c3.setName("ETHEREUM");
        c3.setPrice(new BigDecimal(500));
        c3.setQuantity(new BigDecimal(0.0045));
        c3.setDateTime(new Timestamp(System.currentTimeMillis()));

        coinRepository.insert(c1);
        coinRepository.insert(c2);
        coinRepository.insert(c3);

        return  c1;
    }


    @PostMapping()
    public ResponseEntity post(@RequestBody Coin coin){
        try{
            coin.setDateTime(new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>(coinRepository.insert(coin), HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity get(){
        return new ResponseEntity<>(coinRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity getByName( @PathVariable String name){

        try{
            return new ResponseEntity<>(coinRepository.getByName(name), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        try {
            return new ResponseEntity<>(coinRepository.remove(id), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NO_CONTENT);
        }
    }
}
