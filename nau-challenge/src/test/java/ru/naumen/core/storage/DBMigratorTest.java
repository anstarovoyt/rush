package ru.naumen.core.storage;

import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import ru.naumen.core.SpringContext;
import ru.naumen.core.game.GameProvider;
import ru.naumen.core.game.GameSeries;
import ru.naumen.core.storage.UserGameStorage;
import ru.naumen.core.storage.UserGameStorageFactory;
import ru.naumen.core.storage.UserGameStorageImpl;
import ru.naumen.model.User;
import ru.naumen.model.dao.UserDAO;

public class DBMigratorTest {
    
    @Mock
    UserDAO userDAO;
    @Mock
    UserGameStorageFactory factory;
    @Mock
    GameProvider provider;
    
    DBMigrator migrator;
    
    @Before
    public void init() {
        initMocks(this);
        migrator = new DBMigrator();
        migrator.userDAO = userDAO;
    }
    
    @Test
    public void migrateUsers() {
        List<GameSeries> oldList = new ArrayList<>(new GameProvider().getNewGameList());
        oldList.remove(2);
        oldList.remove(3);
        Mockito.when(provider.getNewGameList()).thenReturn(oldList);
        UserGameStorageImpl value = new UserGameStorageImpl(provider);
        Mockito.when(factory.create()).thenReturn(value);
        
        List<User> users = new ArrayList<>();
        ApplicationContext mock = Mockito.mock(ApplicationContext.class);
        Mockito.when(mock.getBean("userGameStorageFactory")).thenReturn(factory);
        new SpringContext().setApplicationContext(mock);
        User user1 = User.create("1", "1", "1");
        users.add(user1);
        
        Mockito.when(userDAO.loadAll()).thenReturn(users);
        List<GameSeries> newList = new GameProvider().getNewGameList();
        Mockito.when(provider.getNewGameList()).thenReturn(oldList);
        
        // Тест
        Assert.assertEquals(newList.size() - 2, user1.getUserGameStorage().getAll().size());
        migrator.migrateUser(newList, user1);
        Assert.assertEquals(newList.size(), user1.getUserGameStorage().getAll().size());
    }

}
