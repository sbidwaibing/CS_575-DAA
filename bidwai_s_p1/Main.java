import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws FileNotFoundException
    {
        
        Main n1 = new Main();
        
        String MPFPath=new String();
        String PLFPath=new String();

        ReadMarketFile rmp = new ReadMarketFile();
        ArrayList<ArrayList<String>> f0 = rmp.readMarketList(MPFPath);
        
        if (args.length > 0) 
        {
            MPFPath=args[0];
            PLFPath=args[1];
        }
        else
        {
            System.out.println("Please use arguments as follows:\njava Main.java MarketPriceFilePath PriceListFilePath \n & Press Enter ! ");
            System.exit(0);
        }

        ReadPriceList f = new ReadPriceList();
        ArrayList<ArrayList<String>> f1 = f.readPriceList(PLFPath);
        
        for(int i = 0;i<f1.size();i++)
        {
            ArrayList<String> t1 = f1.get(i);
            n1.Compute(t1,MPFPath,PLFPath);
        }       
    }

    public void Compute(ArrayList<String> z1,String fh,String jh)
    {
        long startTime = System.currentTimeMillis();

        ArrayList<String> w = new ArrayList<>();

        ReadMarketFile l1 = new ReadMarketFile();
        ArrayList<ArrayList<String>> s4 = l1.readMarketList(fh);
        
        ReadPriceList f = new ReadPriceList();
        ArrayList<ArrayList<String>> f1 = f.readPriceList(jh);

        String[] s1 = z1.get(0).split(" ");
        int length1 = Integer.valueOf(s1[0]);
        int mass = Integer.valueOf(s1[1]);
        ArrayList<String> s9 =new ArrayList<String>();
        
        for(int i=1;i<z1.size();i++)
        {
            s9.add(z1.get(i));
        }

        int n = s9.size();
        int ts = (int) Math.pow(2, n);
        int res = 0;
        ArrayList<String> maxSubset=new ArrayList<String>();
        for(int sm = 0; sm < ts; sm++)
        {
            ArrayList<String> Sub = new ArrayList<>();
            for(int z=0; z<n; z++)
            {
                if((sm & (1 << z)) != 0)
                {
                    Sub.add(s9.get(z));
                }
            }
            int ans = processSubset(Sub,s4,mass,fh);
            //res = Math.max(res, ans);
            if(ans>res){
                res=ans;
                maxSubset=Sub;
            }
        }

        long endTime = System.currentTimeMillis();
        double t =1000.0;
        double executionTime = (endTime - startTime)/t;
        String.format("%.5f",executionTime);
        
        String Slength = String.valueOf(length1);
        String Sres = String.valueOf(res);
        String Smss = String.valueOf(maxSubset.size());
        String Set = String.valueOf(executionTime);
        //System.out.print(Slength+" "+Sres+" "+Smss);
        //System.out.println(" " + executionTime);
       // maxSubset.forEach(System.out::println);   

        w.add(Slength);
        w.add(Sres);
        w.add(Smss);
        w.add(Set);
        w.addAll(maxSubset);
               
       try 
       {
        FileWriter writer = new FileWriter("output.txt",true);
        writer.write(Slength+" "+Sres+" "+Smss+" ");
        
        for (int i = 3; i < w.size(); i++) {
            
            writer.write(w.get(i) + "\n");
        }

        System.out.println();
        writer.flush();
        writer.close();
       }         
       catch (Exception e) 
       {
        System.err.println("Fatal Error Output File Not Created");
        e.printStackTrace();
       }
       
       System.out.println("Output File has been created and written !");
    }

    private static int processSubset(ArrayList<String> subset,ArrayList<ArrayList<String>> ft1, int mass,String fh) 
    {       
        ReadMarketFile rmp = new ReadMarketFile();
        ArrayList<ArrayList<String>> f0 = rmp.readMarketList(fh);

        HashMap<String,String> map=new HashMap<String,String>();
        //ArrayList<ArrayList<String>> mgv =new ArrayList<ArrayList<String>>(); 
       
        for (int i = 0; i < f0.get(0).size(); i++) 
        {
            String[] p=f0.get(0).get(i).split(" ",2);
            map.put(p[0], p[1]);
        }
        
        ArrayList<ArrayList<String>> mgf =new ArrayList<ArrayList<String>>();  
    
        for (int i = 0; i < subset.size(); i++) 
        {
            String playerInfo = subset.get(i);
            ArrayList<String> dw =new ArrayList<String>(); 
            String[] dq = playerInfo.split(" ",2);
            dw.add(0,dq[0]);
            dw.add(1,dq[1]);
            
            if(map.containsKey(dq[0])){
                int profit=Integer.parseInt(map.get(dq[0]))-Integer.valueOf(dq[1]);
                dw.add(2, String.valueOf(profit));
            }
            mgf.add(i,dw); 
        }

        int p=0;
        int l=0;
        for(int i=0;i<mgf.size();i++)
        {
            p=Integer.valueOf(mgf.get(i).get(2))+p;
            l=Integer.valueOf(mgf.get(i).get(1))+l;
        }
        if(l>mass)return 0;
        return p;
    }   
}
