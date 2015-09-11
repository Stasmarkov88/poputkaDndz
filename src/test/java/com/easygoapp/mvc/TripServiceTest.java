package com.easygoapp.mvc;

import com.easygoapp.config.RootConfig;
import com.easygoapp.domain.PassengerNodePoint;
import com.easygoapp.domain.Trip;
import com.easygoapp.domain.User;
import com.easygoapp.domain.UserRole;
import com.easygoapp.service.PassengerNodePointService;
import com.easygoapp.service.TripService;
import com.easygoapp.service.UserService;
import com.easygoapp.type.Gender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Admin on 04.03.15.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = RootConfig.class)
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @Autowired
    private UserService userService;

    @Autowired
    private PassengerNodePointService passengerNodePointService;

    private Long driver_id, passenger1_id, passenger2_id, trip_id,
            passengerNodePoint1_id, passengerNodePoint2_id;

    @Before
    public void initForTest() {
        //get driver
        User driver = new User();
        driver.setName("Luke Driver");
        driver.setPhoneNumber("1234567");
        driver.setEmail("luke@gmail.com");
        driver.setCar("Car");
        driver.setGender(Gender.MALE);
        driver.setLogin("bro");
        driver.setPassword("bro999");
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        List<UserRole> userRoles = new ArrayList<UserRole>();
        userRoles.add(userRole);
        driver.setUserRoles(userRoles);
        driver = userService.save(driver);
        driver_id = driver.getId();
        //get passengers
        User passenger1 = new User();
        passenger1.setName("Jack");
        passenger1.setLogin("black_jack");
        passenger1.setEmail("jack@jack.mi");
        passenger1.setPhoneNumber("123456789");
        passenger1.setGender(Gender.MALE);
        passenger1.setPassword("blabla");
        UserRole userRole1 = new UserRole();
        userRole1.setRole("ROLE_USER");
        List<UserRole> userRoles1 = new ArrayList<UserRole>();
        userRoles1.add(userRole1);
        passenger1.setUserRoles(userRoles1);
        passenger1 = userService.save(passenger1);
        passenger1_id = passenger1.getId();

        User passenger2 = new User();
        passenger2.setName("Jane");
        passenger2.setLogin("janee");
        passenger2.setEmail("jane@ij.mi");
        passenger2.setPhoneNumber("7896543");
        passenger2.setGender(Gender.FEMALE);
        passenger2.setPassword("blabla");
        UserRole userRole2 = new UserRole();
        userRole2.setRole("ROLE_USER");
        List<UserRole> userRoles2 = new ArrayList<UserRole>();
        userRoles2.add(userRole2);
        passenger2.setUserRoles(userRoles2);
        passenger2 = userService.save(passenger2);
        passenger2_id = passenger2.getId();

        // create pnps
        PassengerNodePoint passengerNodePoint1 = passengerNodePointService.findOne(1l);
        passengerNodePoint1_id = passengerNodePoint1.getId();

        PassengerNodePoint passengerNodePoint2 = passengerNodePointService.findOne(40l);
        passengerNodePoint2_id = passengerNodePoint2.getId();

        Trip trip = new Trip();
        trip.setCarCapacity(3);
        trip.setDriver(driver);
        trip.setPrice(5d);
        trip.setStartTime(new Timestamp(System.currentTimeMillis()));

        List<User> companions = new ArrayList<User>();
        companions.add(userService.findOne(driver_id));
        companions.add(userService.findOne(passenger1_id));
        trip.setCompanions(companions);

        List<PassengerNodePoint> points = new ArrayList<PassengerNodePoint>();
        points.add(passengerNodePoint1);
        trip.setPassengerNodePoints(points);

        trip = tripService.save(trip);
        trip_id = trip.getId();
    }

//    @Test
//    public void testAddPassenger() {
//        tripService.addPassenger(trip_id, passenger2_id);
//        Trip trip = tripService.findOneEager(trip_id);
//        assertEquals(3, trip.getCompanions().size());
//    }

//    @Test
//    public void testRemovePassenger(){
//        tripService.removePassenger(trip_id, passenger1_id);
//        Trip trip = tripService.findOneEager(trip_id);
//        assertEquals(1, trip.getCompanions().size());
//    }

    @Test
    public void testAddPassengerNodePoint() {
        tripService.addPassengerNodePoint(trip_id, passengerNodePoint2_id);
        Trip tripAfter = tripService.findOneEager(trip_id);
        assertEquals(2, tripAfter.getPassengerNodePoints().size());
    }

    @Test
    public void testRemovePassengerNodePoint() {
        tripService.removePassengerNodePoint(trip_id, passengerNodePoint2_id);
        Trip tripAfter = tripService.findOneEager(trip_id);
        assertEquals(1, tripAfter.getPassengerNodePoints().size());
    }

    @Test
    public void testModifyTrip() {
        Trip modifiedTrip;
        int wantedCapacity;
        int realCapacity;
        Trip trip = tripService.findOneEager(trip_id);
        List<User> companions = trip.getCompanions();
        companions.add(userService.findOne(passenger2_id));
        trip = tripService.save(trip);
        //modify1
        trip.setPrice(40d);
        trip.setStartTime(new Timestamp(System.currentTimeMillis()));
        trip.setCarCapacity(10);
        wantedCapacity = trip.getCarCapacity();
        modifiedTrip = tripService.modifyTrip(trip);
        realCapacity = modifiedTrip.getCarCapacity();
        assertEquals(wantedCapacity, realCapacity);
        assertEquals(trip.getPrice(), modifiedTrip.getPrice());
        assertEquals(trip.getStartTime(), trip.getStartTime());
        //modify2
        trip.setCarCapacity(1);
        wantedCapacity = trip.getCarCapacity();
        modifiedTrip = tripService.modifyTrip(trip);
        realCapacity = modifiedTrip.getCarCapacity();
        assertNotEquals(wantedCapacity, realCapacity);
        assertEquals(trip.getPrice(), modifiedTrip.getPrice());
        assertEquals(trip.getStartTime(), trip.getStartTime());
    }
//
//    @Test
//    public void testCancelTrip() {
//        Trip trip = tripService.findOneEager(trip_id);
//        tripService.cancelTrip(trip.getId());
//        List<Trip> trips = tripService.findAll();
//        assertTrue(!trips.contains(trip));
//    }

    @Test
    public void testSetPassengerNodePointsList() {
        List<PassengerNodePoint> points = new ArrayList<PassengerNodePoint>();
        points.add(passengerNodePointService.findOne(1l));
        points.add(passengerNodePointService.findOne(40l));
        tripService.setPassengerNodePointsList(trip_id, points);
        Trip trip = tripService.findOneEager(trip_id);
        assertTrue(trip.getPassengerNodePoints().size() == points.size());
    }

//    @Test
//    public void testGetBetweenStartAndEnd(){
//        Timestamp startDate = new Timestamp(System.currentTimeMillis()-10000000);
//        Timestamp endDate = new Timestamp(System.currentTimeMillis());
//        List<Trip> trips = tripService.getBetweenStartAndEnd(startDate,endDate);
//        assertTrue(trips.size()>0);
//
//    }

//    @After
//    public void finalizeForTest() {
//        tripService.delete(trip_id);
//        userService.delete(driver_id);
//        userService.delete(passenger1_id);
//        userService.delete(passenger2_id);
//    }
}
