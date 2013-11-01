package ru.naumen.core.storage;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ru.naumen.core.game.GameProvider;
import ru.naumen.core.game.GameSeries;
import ru.naumen.model.User;
import ru.naumen.model.dao.UserDAO;

/**
 * Добавляет уже созданным пользователям информацию о играх
 * 
 * @author serce
 * @since 01 нояб. 2013 г.
 */
@Component
public class DBMigrator {
    
    @Inject
    UserDAO userDAO;
    @Inject
    PlatformTransactionManager txManager;
    @Inject
    GameProvider gameProvider;
    
    @PostConstruct
    public void init() {
        TransactionTemplate tt = new TransactionTemplate(txManager);
        tt.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                migrate();
            }
        });
    }
    
    void migrate() {
        List<User> all = userDAO.loadAll();
        List<GameSeries> gameList = gameProvider.getNewGameList();
        for(User user : all) {
            migrateUser(gameList, user);
        }
    }

    void migrateUser(List<GameSeries> gameList, User user) {
        UserGameStorage storage = user.getUserGameStorage();
        for(GameSeries game : gameList) {
            if(storage.get(game.getId()) == null) {
                storage.put(game.getId(), game);
            }
        }
        userDAO.saveUser(user);
    }
}
