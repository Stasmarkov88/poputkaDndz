package com.easygoapp.service.impl;

import com.easygoapp.domain.PassengerNodePoint;
import com.easygoapp.repository.PassengerNodePointRepository;
import com.easygoapp.service.PassengerNodePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Станислав on 28.02.2015.
 */
@Service
@Transactional(readOnly = true)
public class PassengerNodePointServiceImpl extends AbstractCrudServiceImpl<PassengerNodePoint, Long>
        implements PassengerNodePointService {

    private PassengerNodePointRepository passengerNodePointRepository;

    @Autowired
    public void setPassengerNodePointRepository(PassengerNodePointRepository passengerNodePointRepository) {
        this.passengerNodePointRepository = passengerNodePointRepository;
        super.setRepository(passengerNodePointRepository);
    }
}
