import java.io.*;
import java.util.*;

public class ReadPriceList
{
    public static ArrayList<ArrayList<String>> readPriceList(String jh) {

        ArrayList<ArrayList<String>> mp = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(jh))) 
        {
            while (sc.hasNextLine()) 
            {
                String s1 = sc.nextLine();
                Scanner sc2 = new Scanner(s1);
                
                if (sc2.hasNextInt()) 
                {
                    int count = sc2.nextInt();
                    ArrayList<String> mp1 = new ArrayList<>();
                    mp1.add(s1); 
                    int i = 1;
                    
                    for (i = 1; i <= count; i++) 
                    {
                        if (sc.hasNextLine()) {
                            s1 = sc.nextLine();
                            mp1.add(s1);
                        }
                    }
                    mp.add(mp1);
                }
                sc2.close();
            }
        } 
        catch (Exception e) 
        {
            System.err.println("Error Occured");
            e.printStackTrace();
        }
        return mp;
    }
}