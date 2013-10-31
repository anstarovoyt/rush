package ru.naumen.core.game.impl.redo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class RedoGenerator
{
    public HashSet<Integer> ids = Sets.newHashSet();

    public Map<Integer, Integer> table = Maps.newHashMap();

    public static final Random RND = new Random();

    public static void main(String[] args)
    {
        if (new File("redo.txt").exists())
        {
            new File("redo.txt").delete();
        }

        try (PrintWriter writer = new PrintWriter("redo.txt", "UTF-8"))
        {
            RedoGenerator generator = new RedoGenerator(writer);

           // generator.doAction();
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    PrintWriter writer;

    public RedoGenerator(PrintWriter writer)
    {
        this.writer = writer;
    }

    public void doDelete(int count)
    {
        for (int i = 0; i < count; i++)
        {
            int id1 = RND.nextInt(5000);
            int id2 = RND.nextInt(5000);
            int id = id1 + id2;

            writer.println("DELETE from TEST_TABLE WHERE id = (" + id1 + " + " + id2 + ")");

            if (ids.contains(id))
            {
                deleteItem(id);
            }
        }
    }

    public void doInsert(int count)
    {
        for (int i = 0; i < count; i++)
        {
            int id1 = RND.nextInt(5000);
            int id2 = RND.nextInt(5000);
            int id = id1 + id2;
            if (!ids.contains(id))
            {
                ids.add(id);

                int value1 = RND.nextInt(500);
                int value2 = RND.nextInt();

                int value = value1 + value2;

                table.put(id, value);
                writer.println("INSERT INTO TEST_TABLE VALUES(" + id1 + " + " + id2 + ", " + value1 + " + " + value2
                        + ")");
                //Делаем вставку кого-то значения
            }

        }
    }

    public void doUpdate(int count)
    {
        for (int i = 0; i < count; i++)
        {
            int id1 = RND.nextInt(5000);
            int id2 = RND.nextInt(5000);
            int id = id1 + id2;
            int value1 = RND.nextInt(500);
            int value2 = RND.nextInt();

            writer.println("UPDATE TEST_TABLE set value = (" + value1 + " + " + value2 + ") WHERE id=(" + id1 + " + "
                    + id2 + ")");

            int value = value1 + value2;

            if (ids.contains(id))
            {
                table.put(id, value);

                //Делаем вставку кого-то значения
            }

        }
    }

    private void deleteItem(int id)
    {
        ids.remove(id);

        table.remove(id);
    }

    private void doAction()
    {
        int globalCycle = 100 + RND.nextInt(100);

        for (int i = 0; i < globalCycle; i++)
        {
            System.out.println("GLOBAL " + i);
            int action = RND.nextInt(2);

            switch (action)
            {
            case 0:
                doInsert(RND.nextInt(1000));
            case 1:
                doUpdate(RND.nextInt(1000));
            case 2:
                doDelete(RND.nextInt(1000));
            }
        }

        int size = ids.size();
        System.out.println(size);

        int alive = RND.nextInt(size);

        int index = 0;
        for (Object oid : ids.toArray())
        {

            int id = (Integer)oid;
            if (index != alive)
            {
                deleteItem(id);
            }
            index++;
        }
        doUpdate(RND.nextInt(100));

        System.out.println(table);
    }
}
