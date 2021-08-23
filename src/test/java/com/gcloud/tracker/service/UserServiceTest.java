package com.gcloud.tracker.service;

import com.gcloud.tracker.dao.UserDAO;
import com.gcloud.tracker.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDAO dao;

    @InjectMocks
    private UserService userService;

    private List<User> listUsers = new ArrayList<>();
    private Class<UserService> serviceClass;

    @Before
    public void setUp() throws Exception {
        listUsers.clear();

        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Ivan");
        user1.setLastName("Sidorov");
        user1.setLogin("ivan");
        user1.setPassword("password");
        user1.setRoleID(1);
        listUsers.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Sergey");
        user2.setLastName("Petrov");
        user2.setLogin("sergey");
        user2.setPassword("password");
        user2.setRoleID(2);
        listUsers.add(user2);
    }

    @Test
    public void findAllUsersTest() throws NoSuchFieldException {
        serviceClass = UserService.class;
        serviceClass.getDeclaredField("userDAO").setAccessible(true);
        //Field modifiersField = Field.class.getDeclaredField("modifiers");

        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);

        //ReflectionTestUtils.setField(service, "excelSettingService", excelSettingService);

        when(dao.findAll()).thenReturn(listUsers);
        //given(dao.findAll()).willReturn(listUsers);

        List<User> userExist = userService.findAllUsers();

        assertThat(userExist).isEqualTo(listUsers);
        // assertEquals
    }
}