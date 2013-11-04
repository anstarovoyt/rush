package ru.naumen.core.game;

import static ru.naumen.core.game.GameSeries.closedGame;
import static ru.naumen.core.game.GameSeries.openGame;

import java.util.*;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.reflections.Reflections;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.*;

/**
 * Access to game templates
 *
 * @author astarovoyt
 */
@Component
public class GameProvider
{
    Reflections reflections = new Reflections("ru.naumen");

    public Multimap<String, String> relations = HashMultimap.create();
    public Map<Class<? extends Game>, GameType> gameClassToAnnotation = Maps.newLinkedHashMap();

    public List<GameSeries> getNewGameList()
    {
        List<GameSeries> result = Lists.newArrayList();

        try
        {
            for (Entry<Class<? extends Game>, GameType> entry : gameClassToAnnotation.entrySet())
            {
                Class<? extends Game> gameClass = entry.getKey();
                GameType gameType = entry.getValue();

                if (gameType.blockedBy().isEmpty())
                {
                    result.add(openGame(gameClass.newInstance(), gameType.seriesCount()));
                }
                else
                {
                    result.add(closedGame(gameClass.newInstance(), gameType.seriesCount()));
                }
            }
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<String> getRelatedClosedGameIds(String gameId)
    {
        return Lists.newArrayList(relations.get(gameId));
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init()
    {
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(GameType.class);

        HashSet<String> templates = Sets.newHashSet();

        for (Class<?> gameClass : annotatedClasses)
        {
            if (!Game.class.isAssignableFrom(gameClass))
            {
                throw new RuntimeException("You should implements Game interface");
            }

            try
            {
                Game game = (Game)gameClass.newInstance();
                templates.add(game.getId());

                GameType annotation = AnnotationUtils.getAnnotation(gameClass, GameType.class);

                gameClassToAnnotation.put((Class<? extends Game>)gameClass, annotation);
                addRelation(game, annotation);
            }
            catch (InstantiationException | IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }

        validateRelations(templates);
    }

    private void addRelation(Game game, GameType type)
    {
        String blockedBy = type.blockedBy();
        if (blockedBy.isEmpty())
        {
            return;
        }

        relations.put(blockedBy, game.getId());
    }

    private void validateRelations(HashSet<String> templates)
    {
        for (String key : relations.keySet())
        {
            if (!templates.contains(key))
            {
                throw new RuntimeException("Cannot find game with id=" + key + " related:" + relations.get(key));
            }
        }
    }
}
