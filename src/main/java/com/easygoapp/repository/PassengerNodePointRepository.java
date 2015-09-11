package com.easygoapp.repository;

import com.easygoapp.domain.PassengerNodePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Станислав on 28.02.2015.
 */
@Repository
public interface PassengerNodePointRepository extends JpaRepository<PassengerNodePoint, Long> {

}
