package ru.naumen.core.game.impl.redo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class RedoGenerator
{
    public static final Random RND = new Random();

    public static void main(String [] args)
    {
        try(PrintWriter writer = new PrintWriter("redo.txt", "UTF-8"))
        {
            writer.println("The first line");
            writer.println("The second line");
            writer.close();
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    public void doInsert(int count)
    {

    }

    public void doUpdate(int count)
    {

    }

}
