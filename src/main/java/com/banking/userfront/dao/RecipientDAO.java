package com.banking.userfront.dao;

import com.banking.userfront.domain.Recipient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by AmitAgarwal on 4/22/19.
 */
public interface RecipientDAO extends CrudRepository<Recipient, Long>{
    List<Recipient> findAll();
    Recipient findByName(String recipientName);
    void deleteByName(String recipientName);
}
