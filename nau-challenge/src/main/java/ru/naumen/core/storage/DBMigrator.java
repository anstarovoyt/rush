package ru.naumen.core.storage;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import ru.naumen.core.game.GameProvider;
import ru.naumen.core.game.GameSeries;
import ru.naumen.core.game.GameSeriesState;
import ru.naumen.model.User;
import ru.naumen.model.dao.UserDAO;

import javax.inject.Inject;
import java.util.List;

/**
 * Add new games after restarting <br>
 *
 * We have to migrate db because of <br>
 * we store UserStorage with all games as serialized object
 *
 * @author serce
 * @since 01 no. 2013 г.
 */
@Component
public class DBMigrator implements ApplicationListener<ContextRefreshedEvent>
{
	private volatile boolean isInited = false;


    @Inject
    UserDAO userDAO;
    @Inject
    PlatformTransactionManager txManager;
    @Inject
    GameProvider gameProvider;

    public void init()
    {
        TransactionTemplate tt = new TransactionTemplate(txManager);
        tt.execute(new TransactionCallbackWithoutResult()
        {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status)
            {
                migrate();
            }
        });
    }

    void migrate()
    {
        List<User> all = userDAO.loadAll();
        List<GameSeries> gameList = gameProvider.getNewGameList();
        for (User user : all)
        {
            migrateUser(gameList, user);
        }
    }

    void migrateUser(List<GameSeries> gameList, User user)
    {
        UserGameStorage storage = user.getUserGameStorage();
        for (GameSeries game : gameList)
        {

            addNewGameIfNecessary(storage, game);

            openGameIfNecessary(storage, game);
        }
        userDAO.saveUser(user);
    }

    private void addNewGameIfNecessary(UserGameStorage storage, GameSeries templateGame)
    {
        String id = templateGame.getId();
        if (storage.get(id) == null)
        {
            storage.put(id, templateGame);
        }
    }

    private void openGameIfNecessary(UserGameStorage storage, GameSeries templateGame)
    {
        String id = templateGame.getId();
        GameSeries gameSeries = storage.get(id);

        if (gameSeries.getState() == GameSeriesState.CLOSED && templateGame.getState() != GameSeriesState.CLOSED)
        {
            gameSeries.makeOpen();
        }
    }

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		if (!isInited)
		{
			init();
		}
		isInited = true;
	}
}
