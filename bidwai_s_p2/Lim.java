import java.util.Random;

public class Lim
{
    public static void main(String[] args)
    {
        try 
        {
            String N = args[0]; 
            if(args[0] == null)
            {
                System.out.println();
                System.out.println("ERROR: Entered value is not a valid Numerical Argument");
                System.out.println();
                System.out.println("Exiting Program in 3...");
                System.out.println("Exiting Program in 2...");
                System.out.println("Exiting Program in 1...");
                System.out.println();
                System.out.println("Program Terminated !");
                System.out.println();
                System.exit(0);
            }
            else
            {   
                if(Integer.parseInt(N) % 6 == 0)
                {
                    String s1 = generateRandomNumber(N);
                    String s2 = generateRandomNumber(N);
            
                    Lim l1 = new Lim();
                    // BigInteger a1 = new BigInteger(s1);
                    // BigInteger a2 = new BigInteger(s2);
                    // a2 = a2.multiply(a1);

                    String l3 = l1.Large_Int_Mul2(s1, s2);
                    String l4 = l1. Large_Int_Mul3(s1, s2);
                   
                    //System.out.println("BI "+a2);
                    //System.out.println("BM "+l3);
                    //System.out.println("BI "+a2);
                    //System.out.println("BM "+l4);

                    System.out.println();
                    System.out.println("A = "+s1);
                    System.out.println("B = "+s2);
                    System.out.println();
                    System.out.println("The large integer multiplication from the division of two smaller integers is");
                    System.out.println("A * B = "+l3);
                    System.out.println();
                    System.out.println("The large integer multiplication from the division of two smaller integers is");
                    System.out.println("A * B = "+l4);
                    System.out.println();
                }
                else
                {
                    System.out.println();
                    System.err.println("The Entered Number should be a multiple of 6");
                    System.out.println();
                    System.out.println("Exiting Program in 3...");
                    System.out.println("Exiting Program in 2...");
                    System.out.println("Exiting Program in 1...");
                    System.out.println();
                    System.out.println("Program Terminated !");
                    System.out.println();
                    System.exit(0);
                }
            }
        } 
        catch (ArrayIndexOutOfBoundsException e) 
        {
            System.out.println();
            System.out.println("ERROR: Entered value is not a valid Numerical Argument");
            System.out.println();
            System.out.println("Exiting Program in 3...");
            System.out.println("Exiting Program in 2...");
            System.out.println("Exiting Program in 1...");
            System.out.println();
            System.out.println("Program Terminated !");
            System.out.println();
            System.exit(0);
            e.printStackTrace(); 
        } 
    }

    public static String generateRandomNumber(String n)
    {
        int _n = Integer.parseInt(n);
        String s= "";

        for(int i=0; i<_n; i++)
        {
            Random R1 = new Random();
            s = s + R1.nextInt(9);
        }  
        
        return s;
    }
    
    public String Large_Int_Mul2(String a, String b)
    {
        String u = a;
        String v = b;

        String res = "";
        int Max = Math.max(u.length(), v.length()); //max between u and v
       
        int m;
        String x,y,w,z;

        if(u.equals("") || v.equals("") )
        {
            return "0";
        }
        else if(Max <= 2)
        {   
            int A = Integer.parseInt(a);
            int B = Integer.parseInt(b);
            int Res = A * B;
            
            res = String.valueOf(Res);
            return res;
        }
        else
        {
            if(a.length() > b.length())
            {
                int temp = a.length() - b.length() ;
                
                while(temp-- != 0)
                {
                    char zero = '0' ;
                    b = zero+b ;
                }
            }
            else if(a.length() < b.length())
            {
                int temp = b.length() - a.length() ;
                
                while(temp-- != 0)
                {
                    char zero = '0' ;
                    a = zero+a ;
                }
            }

            m = (int)Math.floor(Max/2);           

            x = a.substring(0, Max-m);          
            y = a.substring(Max-m);          
            w = b.substring(0, Max-m);
            z = b.substring(Max-m);
            
            String S1 = (Large_Int_Mul2(x,w) + "0".repeat(2*m));
            String S2 = AddS(Large_Int_Mul2(x, z),Large_Int_Mul2(w, y)) + "0".repeat(m);
            String S3 = Large_Int_Mul2(y, z);
            
            res = AddS(AddS(S1, S2), S3);

            return res;
        }
    }

    public String AddS(String str1, String str2)
    {
        int i = str1.length() - 1;
        int j = str2.length() - 1;
        
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        
        while(i >= 0 || j >= 0 || carry > 0)
        {
            int n1 = 0;
            int n2 = 0;
            
            if(i >= 0)
            {
                n1 = str1.charAt(i) - '0';
                i--;
            }

            if(j >= 0)
            {
                n2 = str2.charAt(j) - '0';
                j--;
            }
            
            int num = (n1 + n2 + carry) % 10;
            carry = (n1 + n2 + carry) >= 10 ? 1 : 0;
            
            sb.append(num);  
        }

        return sb.reverse().toString();
    }

    public String Large_Int_Mul3(String a, String b)
    {
        String u = a;
        String v = b;

        String res = "";
        int Max = Math.max(u.length(), v.length()); //max between u and v
        
        int m;
        String x1,x2,y1,y2,z1,z2;

        if(u.equals("") || v.equals(""))
        {
            return "0";
        }
        else if(Max <= 2)
        {   
            int A = Integer.parseInt(a);
            int B = Integer.parseInt(b);
            int Res = A * B;
            
            res = String.valueOf(Res);
            return res;
        }
        else
        {
            if(a.length() > b.length())
            {
                int temp = a.length() - b.length() ;
                
                while(temp-- != 0)
                {
                    char zero = '0' ;
                    b = zero+b ;
                }
            }
            else if(a.length() < b.length())
            {
                int temp = b.length() - a.length() ;
                
                while(temp-- != 0)
                {
                    char zero = '0' ;
                    a = zero+a ;
                }
            }

            m = (int)Math.floor(Max/3); 
                   
            x1 = a.substring(0, Max-(2*m));            
            y1 = a.substring(Max-(2*m), Max-m);          
            z1= a.substring(a.length()-m);         
            x2 = b.substring(0, Max-(2*m));          
            y2 = b.substring(Max-(2*m), Max-m);          
            z2 = b.substring(a.length()-m);         

            String S1 = (Large_Int_Mul3(x1, x2) + "0".repeat(4*m));          
            String S2 = AddS(Large_Int_Mul3(x1,y2), Large_Int_Mul3(y1,x2)) + "0".repeat(3*m);                    
            String S3 = AddS(AddS(Large_Int_Mul3(x1,z2), Large_Int_Mul3(y1,y2)), Large_Int_Mul3(x2,z1)) + "0".repeat(2*m);                  
            String S4 = AddS(Large_Int_Mul2(y1, z2), Large_Int_Mul3(z1, y2)) + "0".repeat(m);
            String S5 = Large_Int_Mul3(z1,z2);        

            res = AddS(AddS(AddS(AddS(S1, S2), S3), S4), S5);
            
            return res;
        }
    }
}