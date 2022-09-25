public class Polynomial{
    // i)
    double[] coefficients; 
    // ii)
    public Polynomial()
    {
        coefficients = new double [1]; 
    }
    // iii)
    public Polynomial(double[] coe)
    {
        coefficients = new double[coe.length]; 
        for(int i=0; i<coe.length; i++)
        {
            coefficients[i] = coe[i];
        }
    }

    // iv)
    public Polynomial add(Polynomial pol)
    {
        //returns the polynomial resulting from adding the calling object and the argument 
        double[] sum;
        //find the largest length 
        if(coefficients.length>=pol.coefficients.length) //self length is longer or equal to other
        {
            sum = new double[coefficients.length]; 
            for(int i=0; i < coefficients.length; i++)
            {
                if(i>pol.coefficients.length)
                {
                    //copy only from "self"
                    sum[i]=coefficients[i];
                }
                else
                {
                    sum[i]=coefficients[i]+pol.coefficients[i];
                }
            }
        }else //other length is longer self
        {
            sum = new double[pol.coefficients.length]; 
            for(int i=0; i < pol.coefficients.length; i++)
            {
                if(i>=coefficients.length)
                {
                    //copy only from other
                    sum[i]=pol.coefficients[i];
                }
                else
                {
                    sum[i]=coefficients[i]+pol.coefficients[i];
                }
            }
        }
        Polynomial s = new Polynomial(sum);
        return s; 
    }

    // v)
    public double evaluate(double x)
    {
        double sum=0; 
        double tmp=1;
        for(int i=0; i < coefficients.length; i++)
        {
            tmp=1;
            for(int j = 0; j<i; j++)
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
}