package com.moxdroid.bottombarexample;

import java.util.ArrayList;

/**
 * Created by pritesh.patel on 2016-11-18, 9:04 AM.
 * ADESA, Canada
 */
public class MySampleSingleton
{
    private static MySampleSingleton ourInstance;
    ArrayList<String> nameArrayList;

    private MySampleSingleton()
    {
        nameArrayList = new ArrayList<>();
    }

    public static synchronized MySampleSingleton getInstance()
    {
        if (ourInstance == null)
            ourInstance = new MySampleSingleton();

        return ourInstance;
    }

    public String getNameByIndex(int index)
    {
        if (index < 0 || index >= nameArrayList.size())
            return null;

        return nameArrayList.get(index);
    }

    public void setName(String name)
    {
        nameArrayList.add(name);
    }

    public ArrayList<String> getAllName()
    {
        return nameArrayList;
    }

    public boolean deleteNameByIndex(int index)
    {
        if (!(index < 0 || index >= nameArrayList.size()))
        {
            nameArrayList.remove(index);
            return true;
        }

        return false;
    }
}
