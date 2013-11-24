package ru.naumen.core.game;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Required annotation for game
 * <p/>
 * Game provider finds classes with the annotation
 *
 * @author astarovoyt
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GameType
{
	/**
	 * Id of parent game
	 * <p/>
	 * if is not empty then game will be discovered after solving parent game
	 */
	String blockedBy() default "";

	/**
	 * Count won
	 */
	int seriesCount() default 1;
}
