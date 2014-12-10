package com.bazinga.hakaton.repository;

import com.bazinga.hakaton.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ivan on 23.11.2014.
 */
@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
}
