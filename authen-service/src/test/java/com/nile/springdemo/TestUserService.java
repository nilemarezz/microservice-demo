package com.nile.springdemo;

import com.nile.springdemo.exception.UserException;
import com.nile.springdemo.model.Address;
import com.nile.springdemo.model.User;
import com.nile.springdemo.model.UserContact;
import com.nile.springdemo.service.AddressService;
import com.nile.springdemo.service.UserContactService;
import com.nile.springdemo.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

    private User usertest = new User("matas" , "password" , "email1@gmail.com");
    private UserContact userContactTest = new UserContact("Matas" , "0099999" , usertest);
    private Address addressTest = new Address("123" , "456" , "10290");
    private Address addressTest2 = new Address("789" , "111" , "10291");
    @Autowired
    UserService userService;

    @Autowired
    UserContactService userContactService;

    @Autowired
    AddressService addressService;

    @Order(1)
    @Test
    void testCreate() throws UserException {
        User user = userService.createUser(usertest);

        // Check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        // Check Equal
        Assertions.assertEquals(usertest.getName() , user.getName());
        Assertions.assertEquals(usertest.getEmail() , user.getEmail());
        Assertions.assertTrue(userService.matchPassword(usertest.getPassword() , user.getPassword()));
    }

    @Order(2)
    @Test
    void testUpdate() throws UserException {
        Optional<User> opt = userService.findByEmail(usertest.getEmail());
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        User updateUser = userService.updateUserName(user.getId() , usertest.getName());

        Assertions.assertNotNull(updateUser);
        Assertions.assertEquals(user.getName() , usertest.getName());
    }

    @Order(3)
    @Test
    void testCreateContact() throws UserException {
        Optional<User> opt = userService.findByEmail(usertest.getEmail());
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();

        Assertions.assertNull(user.getContact());
        UserContact userContact =  userContactService.createContact(user , userContactTest);
        Assertions.assertNotNull(userContact);
        Assertions.assertEquals(userContact.getFacebook() , userContactTest.getFacebook());
    }

    @Order(4)
    @Test
    void testCreateAddress() throws UserException {
        Optional<User> opt = userService.findByEmail(usertest.getEmail());
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();
        List<Address> addresses = user.getAddresses();
        Assertions.assertTrue(addresses.isEmpty());
        createAddress(user , addressTest);
        createAddress(user , addressTest2);
    }

    void createAddress(User user , Address address) throws UserException {

        Address addresses = addressService.createAddress(user , address);

        Assertions.assertNotNull(addresses);
        Assertions.assertEquals(address.getLine1() , addresses.getLine1());
    }

    @Order(10)
    @Test
    void testDelete() {
        Optional<User> opt = userService.findByEmail(usertest.getEmail());
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();
        userService.deleteUser(user.getId());

        Optional<User> optDelete =   userService.findByEmail(usertest.getEmail());

        Assertions.assertTrue(optDelete.isEmpty());
    }
}
