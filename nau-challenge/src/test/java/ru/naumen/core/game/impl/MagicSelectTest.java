package ru.naumen.core.game.impl;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author achernin
 * @since 31.10.13
 */
public class MagicSelectTest
{

    @Test
    public void test_empty()
    {
        MagicSelect game = new MagicSelect();
        game.input("Азетбур");
        assertEquals("0", game.output());
    }

    @Test
    public void test_injected()
    {
        MagicSelect game = new MagicSelect();
        game.input("Азетбур'; insert into klingon_leader (leader, start_year) values ('Азетбур', 2293); select start_year from klingon_leader where leader = 'Азетбур");
        assertEquals("0", game.output());
    }

}
