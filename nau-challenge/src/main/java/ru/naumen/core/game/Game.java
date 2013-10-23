package ru.naumen.core.game;

/**
 * <p>Интерфейс игры (задания) пока что отражает только аспект взаимодействия с GUI.
 * Логику сериализации/десериализации для сохранения промежуточных состояний
 * надо определить дополнительно.</p>
 *
 * <p>Игровой движок работает с этой игрой тривиальным образом:</p>
 * <ol>
 *  <li>показывает описание игры</li>
 *  <li>получает пользовательский ввод и скармливает его игре</li>
 *  <li>проверяет состояние игры</li>
 *  <li>если игра ещё в процессе, то показывает выхлоп игры</li>
 *  <li>если игра завершена (выиграна или проиграна), то конец цикла</li>
 * </ol>
 *
 * <p>Изначально у меня также было ощущение, что может потребоваться отделить саму игру
 * от её описания. Игра (инстанс игры), по идее, - это просто какой-то конечный автомат,
 * который перемалывает данные. А описание может хранить метод для создания автоматов, а
 * также статическую информацию (правила, подсказки и т.п.). Из текущих тестов это разделение
 * никак не вытекало, поэтому я оставил один класс, чтобы сохранить максимальную простоту.</p>
 *
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public interface Game
{
    String getId();

    String getDescription();

    void input(String userInput);

    GameState state();

    String output();
}
