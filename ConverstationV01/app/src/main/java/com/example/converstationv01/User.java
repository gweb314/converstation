package com.example.converstationv01;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.util.ArrayList;
import java.util.Scanner;

public class User
{
    public User(String name, String pronouns, String major, String minor, String email, String image, ArrayList<String> interests)
    {
        this.name = name;
        this.pronouns = pronouns;
        this.major = major;
        this.minor = minor;
        this.interests = new ArrayList<String>();
        this.email = email;
        this.image = image;
        for(String s : interests)
        {
            this.interests.add(s);
        }
    }

    public User(int num)
    {
        interests = new ArrayList<String>();
        if(num == 0)
        {
            name = "Eli Vazquez";
            pronouns = "He/Him";
            major = "Computer Science";
            minor = "Creative Writing";
            interests.add("Video Games");
            interests.add("Anime");
            email = "example0@stanford.edu";

        }
        else if(num == 1)
        {
            name = "Fiona Hall-Zazueta";
            pronouns = "She/Her";
            major = "SymSmy";
            minor = "";
            interests.add("D&D");
            interests.add("Creative Writing");
            interests.add("Video Games");
            interests.add("Anime");
            interests.add("Wine Tasting");
            interests.add("Rakugo");
            email = "example1@stanford.edu";
        }
        else if(num == 2)
        {
            name = "David Rathmann-Bloch";
            pronouns = "He/Him";
            major = "Art History";
            minor = "Japanese";
            interests.add("Wine Tasting");
            interests.add("Rakugo");
            email = "example2@stanford.edu";
        }
        if(num == 3)
        {
            name = "Jake Ray";
            pronouns = "They/Them";
            major = "Undeclared";
            minor = "";
            interests.add("Roller Skating");
            interests.add("Carpentry");
            email = "example3@stanford.edu";
        }

        image = "userpic" + num;
    }

    public User(int num, User match)
    {
        this(num);
        ArrayList<String> matchInterest = match.getInterests();
        int numToCopy = (int)(Math.random() * 2) + 1;

        while(numToCopy > 0 && matchInterest.size() > 0)
        {
            int index = (int)(Math.random() * matchInterest.size());
            interests.add(matchInterest.remove(index));
            numToCopy--;
        }
    }

    public User(String userString)
    {
        Scanner scanner = new Scanner(userString);
        scanner.useDelimiter(";");
        name = scanner.next();
        pronouns = scanner.next();
        major = scanner.next();
        minor = scanner.next();
        email = scanner.next();
        image = scanner.next();

        interests = new ArrayList<String>();

        while(scanner.hasNext())
        {
            interests.add(scanner.next());
        }
    }

    private String name, lastName, pronouns, major, minor, email, image;
    private ArrayList<String> interests;

    public String getName()
    {
        return name;
    }

    public String getPronouns()
    {
        return pronouns;
    }

    public String getMajor()
    {
        return major;
    }

    public String getMinor()
    {
        return minor;
    }

    public ArrayList<String> getInterests()
    {
        ArrayList<String> retVal = new ArrayList<String>();
        for(String s : interests)
        {
            retVal.add(s);
        }
        return retVal;
    }

    public String getEmail()
    {
        return email;
    }

    public String getImage()
    {
        return image;
    }

    public String toString()
    {
        String retval = "";
        retval += name + ";";
        retval += pronouns + ";";
        retval += major + ";";
        retval += minor + ";";
        retval += email + ";";
        retval += image + ";";

        for(String s: interests)
        {
            retval += s + ";";
        }

        return retval;
    }
}
