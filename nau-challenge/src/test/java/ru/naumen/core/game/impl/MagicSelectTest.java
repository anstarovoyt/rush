package ru.naumen.core.game.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author achernin
 * @since 31.10.13
 */
public class MagicSelectTest
{

    @Test
    @Ignore
    public void test_empty()
    {
        MagicSelect game = new MagicSelect();
        game.input("Azethbur");
        assertEquals("0", game.output());
    }

    @Test
    @Ignore
    public void test_injected()
    {
        MagicSelect game = new MagicSelect();
        game.input("Azethbur'; insert into klingon_leader (leader, start_year) values ('Azethbur', 2293); select start_year from klingon_leader where leader = 'Azethbur");
        assertEquals("0", game.output());
    }

}
