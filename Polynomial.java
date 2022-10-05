import java.util.Scanner;
import java.util.Arrays;
import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;

public class Polynomial{
    // a)
    double[] coefficients; 
    int[] exponents; 
    // ii)
    public Polynomial()
    {
        coefficients = null; 
        exponents = null; 
    }
    // iii)
    public Polynomial(double[] coe, int[] exp)
    {
        coefficients = new double[coe.length]; 
        exponents = new int[exp.length]; 
        for(int i=0; i<coe.length; i++)
        {
            coefficients[i] = coe[i];
            exponents[i] = exp[i];
        }
    }
    // d)
    public Polynomial(File f) throws FileNotFoundException 
    { 
    
        //read content from file 
        Scanner input = new Scanner(f); 
        int indexAdd=0; // index of "+" 
        int indexArray=0; // index of the current index of the array add 
        int index=0; // index used to find the index of "+" 
        String str = "";
        while( input.hasNext() ) 
        { 
            str = str + input.nextLine(); 
        } 
        //find indexs of "+" 
        int[] add = new int[str.length()]; 
        while(index < str.length()) 
        { 
            indexAdd = str.indexOf("+", index); 
            add[indexArray] = indexAdd; 
            index = indexAdd; 
            indexArray++; 
        } 
        //get rid of trailing zeros 
        int len=str.length();
        for( int i=1; i< str.length(); i++)
        {
            if(add[i] == 0)
            {
                len--; 
            }
        }
        int[] addFin = new int[len]; 
        for( int i=0; i< len; i++)
        {
            addFin[i]=add[i];
        }
        //figgure out if it contains x and set var 
        coefficients = new double [addFin.length+1]; 
        exponents = new int [addFin.length+1]; 
        int indexx=0; 
        // beginning
        if(addFin[0]==3) // has x 
        {
            double c = (double)str.charAt(addFin[0]-3); 
            int e = (int)str.charAt(addFin[0]-1); 
            coefficients[indexx] = c; 
            exponents[indexx] = e;
            indexx++; 
        }else // does not have x
        {
            double c = (double)str.charAt(addFin[0]-3); 
            int e = 0;
            coefficients[indexx] = c; 
            exponents[indexx] = e;
            indexx++; 
        }
        for( int i=1; i<addFin.length; i++)
        {
            if(addFin[i]-addFin[i-1] > 2) // has x
            {
                double c = (double)str.charAt(addFin[i-1]+1); 
                int e = (int)str.charAt(addFin[i]-1); 
                coefficients[indexx] = c; 
                exponents[indexx] = e;
                indexx++; 
            }else // does not have x
            {
                double c = (double)str.charAt(addFin[i-1]+1); 
                int e = 0;
                coefficients[indexx] = c; 
                exponents[indexx] = e;
                indexx++; 
            }
        }
        // end 
        if(addFin.length-1 - addFin[addFin.length-1] == 4) // has x 
        {
            double c = (double)str.charAt(addFin[addFin.length-1]+1); 
            int e = (int)str.charAt(addFin[addFin.length]-1); 
            coefficients[indexx] = c; 
            exponents[indexx] = e; 
            indexx++; 
        }else // does not have x
        {
            double c = (double)str.charAt(addFin[addFin.length-1]+1); 
            int e = 0; 
            coefficients[indexx] = c; 
            exponents[indexx] = e; 
            indexx++; 
        }
    } 

    private Polynomial removeZero(double[] c, int[] e) 
    { 
        //find the number of zeros there are in coefficients 
        int zero = 0; 
        for(int i=0; i<c.length; i++) 
        { 
            //System.out.println("c[i] is: "+ c[i]);
            if(c[i] == 0) 
            { 
                zero++; 
            } 
        }
        int len = c.length-zero; //new length
        double[] coeFin = new double[len]; //final coefficient array
        int[] expFin = new int[len]; //final exponent array
        int ind=0;
        //System.out.println(len);
        for(int i=0; i<c.length; i++)
        {
            if(c[i] != 0) //if it is not zero add it to the final array
            {
                coeFin[ind]=c[i]; 
                //System.out.println("coeFin[ind] is: "+ coeFin[ind]);
		        ind++;
            }
            
        }
        ind=0;
        for(int i=0; i<e.length; i++)
        {
            if(e[i] != 0) //if it is not zero add it to the final array
            {
                expFin[ind]=e[i];
                //System.out.println("expFin[ind] is: "+ expFin[ind]);
		        ind++;
            }else if(e[i] == 0 && i ==0)
            {
                //System.out.println("expFin[ind] is: "+ expFin[ind]);
                ind++;
            }
            
        }
        Polynomial s = new Polynomial(coeFin, expFin);
        return s; 
    }

    //6-2x+5x^3 would be represented using the arrays [6, -2, 5] and [0, 1, 3] 
    // iv)
    public Polynomial add(Polynomial pol)
    {
        //returns the polynomial resulting from adding the calling object and the argument 
        double[] sumCoe;
        int[] exp;
        int index=0;
        int largest=exponents[0];
	  //find the largest length 
        for(int i=1; i<exponents.length; i++)
        {
            if(largest<exponents[i])
            {
                largest=exponents[i];
            }
        }
        for(int i=0; i<pol.exponents.length; i++)
        {
            if(largest<pol.exponents[i])
            {
                largest=pol.exponents[i];
            }
        }
        largest++;
        sumCoe = new double[largest];
        exp = new int[largest];
        for(int i=0; i < largest; i++)
        {
            for(int j=0; j < coefficients.length; j++)
            {
		    //System.out.println("j is: "+ j);
		    //System.out.println("coefficients[j] is: "+ coefficients[j]);
                if(exponents[j] == i)
                {
                    sumCoe[index]=sumCoe[index]+coefficients[j];
			  //System.out.println("sumCoe["+index+"]="+sumCoe[index]);
                    exp[index]=i;
			  //System.out.println("exp["+index+"]="+exp[index]);
                }
                
            }
            for(int j=0; j < pol.coefficients.length; j++)
            {
                if(pol.exponents[j] == i)
                {
                    sumCoe[index]=sumCoe[index]+pol.coefficients[j];
			  //System.out.println("2: sumCoe["+index+"]="+sumCoe[index]);
                    exp[index]=i;
			  //System.out.println("2: exp["+index+"]="+exp[index]);
                }
                
            }
            index++;
        }
        return removeZero(sumCoe, exp);
    }

    // v)
    public double evaluate(double x)
    {
        double sum=0; 
        double tmp=1;
        for(int i=0; i < coefficients.length; i++)
        {
            tmp=1;
            for(int j = 0; j<exponents[i]; j++)
            {
                tmp=tmp*x; 
            }
            sum=sum+coefficients[i]*tmp;
        }
        return sum; 
    }

    // vi)
    public boolean hasRoot(double root)
    {
        if( evaluate(root) == 0)
        {
            return true;
        }
        return false; 
    }

    // c)
    public Polynomial multiply(Polynomial p)
    {
        int index=0;
        double[] c = new double[coefficients.length * p.coefficients.length]; 
        int[] e = new int[exponents.length * p.exponents.length]; 
        //obtain new coefficients after multiplying 
        for(int i=0; i<coefficients.length; i++)
        {
            for(int j=0; j<p.coefficients.length; j++)
            {
                c[index]=coefficients[i]*p.coefficients[j];
                //System.out.println("index: " +index+ ", c[index]: "+c[index]);
                index++;
            }
        }
        //obtain new exponents after multiplying 
        index=0;
        for(int i=0; i<exponents.length; i++)
        {
            for(int j=0; j<p.exponents.length; j++)
            {
                e[index]=exponents[i]+p.exponents[j];
                //System.out.println("index: " +index+ ", e[index]: "+e[index]);
                index++;
            }
        }
        //add the common factors 
        double[] cc = new double[coefficients.length * p.coefficients.length]; 
        int[] ee = new int[exponents.length * p.exponents.length]; 
        for(int i=0; i<e.length; i++)
        {
            cc[i]=c[i];
            ee[i]=e[i];
            for(int j=i+1; j<ee.length; j++)
            {
                if(e[i]==e[j])
                {
                    cc[i]=cc[i]+c[j];
                    c[j]=0;
                    e[j]=0;
                }
            }
            // System.out.println( "cc[i]: "+cc[i]);
            // System.out.println( "ee[i]: "+ee[i]);
        }
        //if coefficient is zero then remove it from both list
        return removeZero(cc, ee);
    }

    // e) 
    public void saveToFile(String name) throws FileNotFoundException 
    {
        // convert to text form 
        String poly="";
        if(coefficients.length>0)
        {
            poly=poly+coefficients[0]+"x"+exponents[0]; 
        }
        for(int i=1; i<coefficients.length; i++)
        {
            if(exponents[i]!=0)
            {
                poly=poly+"+"+coefficients[i]+"x"+exponents[i]; 
            }else
            {
                poly=poly+"+"+coefficients[i]; 
            }
        }
        // write in file
        PrintStream output = new PrintStream(name);
        output.println(poly);
    }
}