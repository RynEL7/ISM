/*
 *           Interpretive Structural Modeling
 *           
 * @LICENSE 
 * 			GNU GENERAL PUBLIC LICENSE
 * 			 Version 3, 29 June 2007
 * 
 * @AUTHOR
 * 			Riyan Saputra Irawan
 * 			riyansaputrai007@outlook.com
 * 			Github : https://github.com/RynEL7/ISM
*/



package ISM.Core;
import java.util.ArrayList;

public class Procedures 
{
	public static ArrayList<String> descElements=new ArrayList<>();
	public static String[][] relationElementsVAXO;
	public static int[][] relationElementsBinary;
	public static int nElement;
	public static int[] Dependency;
	public static int[] driverPower;
	public static int[] depedencyRank;
	public static int[] driverPowerRank;
	public static String[] charVAXO=new String[]{"V","v","A","a","X","x","O","o"};
	public static String[][] attrElements;
	
	
	public static void setElementsAttr()
	{
    	attrElements=new String[nElement][4];
		for (int i=0;i<nElement;i++)
		{
			attrElements[i][0]=descElements.get(i);
			attrElements[i][1]=String.valueOf(Dependency[i]);
			attrElements[i][2]="E"+(i+1);
			attrElements[i][3]=String.valueOf(driverPower[i]);
		}
	}
	
	public static void countDependency()
	{
		Dependency=new int[nElement];
		int sumDependency=0;
		for (int i=0;i<nElement;i++)
		{
			sumDependency=0;
			for (int j=0;j<nElement;j++)
			{
				sumDependency+=relationElementsBinary[j][i];
			}
			Dependency[i]=sumDependency;
		}
	}
	
	public static void countDriverPower()
	{
		driverPower=new int[nElement];
		int sumDriverPower=0;
		for (int i=0;i<nElement;i++)
		{
			sumDriverPower=0;
			for (int j=0;j<nElement;j++)
			{
				sumDriverPower+=relationElementsBinary[i][j];
			}
			driverPower[i]=sumDriverPower;
		}
	}
	
	// logic to sort the elements
    public static void bubble_srt(int array[]) {
        int n = array.length;
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (array[i] > array[k]) {
                    swapNumbers(i, k, array);
                }
            }
        }
    }
  
    private static void swapNumbers(int i, int j, int[] array) {
  
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        
        String temps;
        temps=attrElements[i][0];
        attrElements[i][0]=attrElements[j][0];
        attrElements[j][0]=temps;
        

        String tempss;
        tempss=attrElements[i][1];
        attrElements[i][1]=attrElements[j][1];
        attrElements[j][1]=tempss;
        
        String tem;
        tem=attrElements[i][2];
        attrElements[i][2]=attrElements[j][2];
        attrElements[j][2]=tem;
        
        String te;
        te=attrElements[i][3];
        attrElements[i][3]=attrElements[j][3];
        attrElements[j][3]=te;
    }
    
    
    public static void rankDependency()
    {
    	bubble_srt(Dependency);
    }
    
    public static void rankDriverPower()
    {
    	bubble_srt(driverPower);
    }
  
}
