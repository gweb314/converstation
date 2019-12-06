package edu.stanford.converstationv0;

import java.util.ArrayList;
import java.util.HashSet;

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

    public static void setUserExists(boolean val)
    {
        userExists = val;
    }

    public static boolean userExists()
    {
        return userExists && user != null;
    }

    public static boolean isUserNull()
    {
        return user == null;
    }

    public static User getUser()
    {
        if(user == null) user = new User("", "", "", "", "", "userpic0", new ArrayList<String>());
        return new User(user.toString());
    }

    public static void exchangeEmail(String name)
    {
        if(exchangedUsers == null) exchangedUsers = new HashSet<String>();

        if(!exchangedUsers.contains(name))
        {
            exchangedUsers.add(name);
        }
    }

    public static boolean hasExchanged(String name)
    {
        if(exchangedUsers == null) return false;
        return exchangedUsers.contains(name);
    }

    private static User user;

    private static HashSet<String> exchangedUsers;

    private static boolean userExists;
}
