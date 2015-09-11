package com.easygoapp.mvc;

import com.easygoapp.config.RootConfig;
import com.easygoapp.service.PassengerLandingService;
import com.easygoapp.service.PassengerNodePointService;
import com.easygoapp.service.TripService;
import com.easygoapp.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = RootConfig.class)
public class DaoTripTest {

    @Autowired
    private TripService tripService;

    @Autowired
    private UserService userService;

    @Autowired
    private PassengerNodePointService passengerNodePointService;

    @Autowired
    private PassengerLandingService passengerLandingService;

//    @Before
//    public void saveUser(){
//        User user = new User();
//        user.setPhoneNumber("000-000-00-00");
//        user.setCar("lexus");
//        user.setGender(Gender.MALE);
//        user.setLogin("Markov");
//        user.setPassword("1234");
//        User user1 = userService.saveUser(user);
//        System.out.println(user1);
//
//        User passanger = new User();
//        passanger.setPhoneNumber("000-000-00-00");
//        passanger.setCar("lexus");
//        passanger.setGender(Gender.MALE);
//        passanger.setLogin("adf");
//        passanger.setPassword("1234");
//        User saved = userService.saveUser(passanger);
//        System.out.println(saved);
//    }

//    @Test
//    public void saveTripWithRatio() {
//        Trip trip = new Trip();
//        trip.setCarCapacity(3);
//        User driver = userService.findOne(1l);
//        System.out.println(driver);
//        trip.setDriver(driver);
//        trip.setPrice(5d);
//        trip.setStartTime(new Timestamp(System.currentTimeMillis()));
//
//        List<User> companions = new ArrayList<>();
//        //User user2 = userService.findOne(2l);
//        companions.add(driver);
//        // companions.add(user2);
//        trip.setCompanions(companions);
//        tripService.save(trip);
//    }

//    @Test
//    public void getAllTrips() throws ParseException {
//        System.out.println("getAllTrips");
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = dateFormat.parse("02/03/2015");
//        long time = date.getTime();
//        Timestamp start = new Timestamp(time);
//        List<Trip> trips = tripService.getBetweenStartAndEnd(start, new Timestamp(System.currentTimeMillis()));
//        for (Trip trip: trips){
//        System.out.println(trip);
//        }
//    }

//    @Test
//    public void saveTripWithDriverCompanionsAndAllPoints(){
//        Trip trip = new Trip();
//        trip.setCarCapacity(3);
//        User driver = userService.getById(1l);
//        System.out.println(driver);
//        trip.setDriver(driver);
//        trip.setPrice(5d);
//        trip.setStartTime(new Timestamp(System.currentTimeMillis()));
//
//        List<User> companions = new ArrayList<>();
//        User user2 = userService.getById(2l);
//        companions.add(user2);
//        companions.add(driver);
//
//        trip.setCompanions(companions);
//
//        List<PassengerNodePoint> points = passengerNodePointService.getAll();
//
//        trip.setPassengerNodePoints(points);
//
//        Trip savedTrip = tripService.save(trip);
//        System.out.println(savedTrip);
//    }

//    @Test
//    public void saveWithPnp(){
//        Trip trip = new Trip();
//        trip.setCarCapacity(3);
//        User driver = userService.findOne(1l);
//        trip.setDriver(driver);
//        trip.setPrice(5d);
//        trip.setStartTime(new Timestamp(System.currentTimeMillis()));
//        List<PassengerNodePoint> points = new ArrayList<>();
//        PassengerNodePoint point = passengerNodePointService.findOne(1l);
//        points.add(point);
//        trip.setPassengerNodePoints(points);
//        tripService.save(trip);
//    }

//    @Test
//    public void saveNewPassenger(){
//        Trip trip = tripService.findOneEager(1l);
//        User passenger = userService.findOne(2l);
//        trip.getCompanions().add(passenger);
//		PassengerLanding landing = new PassengerLanding();
//		landing.setTrip(trip);
//		landing.setUser(passenger);
//		landing.setDescription("Остановка");
//		passengerLandingService.save(landing);
//        tripService.save(trip);
//    }

//	@Test
//	public void showTripInfo(){
//		Trip trip = tripService.findOneEager(1l);
//		System.out.println(trip);
//	}
}
