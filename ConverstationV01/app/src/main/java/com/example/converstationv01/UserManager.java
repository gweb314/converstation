package com.example.converstationv01;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;

public class UserManager{
    /*
    public static boolean saveUser( Context context, User user)
    {
        String saveData = user.toString();

        try {
            FileOutputStream fos = context.openFileOutput("CurrentUser", MODE_PRIVATE);
            fos.write(saveData.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static User loadUser(Context context)
    {
        String userString = "";

        FileInputStream fis = null;
        try {
            fis = context.openFileInput("CurrentUser");
            Scanner scanner = new Scanner(fis);
            while(scanner.hasNext())
            {
                userString += scanner.next();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new User(userString);
    }

    public static void logout(Context context)
    {
        context.deleteFile("CurrentUser");
    }
     */

    public static void setUser(User newUser)
    {
        user = new User(newUser.toString());
    }

    public static boolean userExists()
    {
        return user != null;
    }

    public static User getUser()
    {
        if(user == null) user = new User("", "", "", "", "", "userpic0", new ArrayList<String>());
        return new User(user.toString());
    }

    private static User user;
}
