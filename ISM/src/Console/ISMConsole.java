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



package Console;

import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ISMConsole extends ISM.Core.Procedures {
	static Scanner scn = new Scanner(System.in);
	static boolean isElementsHasRelation=false,isGenerateSSIM=false,isGenerateRM=false;

	static int menu=0;

	public static void main(String[] args) {
		main_menu();
	}
	
	public static void main_menu()
	{
		menu=0;
		while (menu == 0) {
			separator(40,'#',true);
			System.out.println("Interpretive Structural Model");
			System.out.println("    Modelling & Simulation   ");
			System.out.println("         Riyan S.I           ");
			System.out.println("        Version 1.0          ");
			System.out.println("   github.com/RynEL7/ISM     ");

			separator(40,'-',true);
			separator(40,'#',true);
			System.out.print("Step-Step : \n1.Input Elements\n2.Input Relationships(VAXO)\n3.Structure-Self Interaction Matrix (SSIM)\n4.Reachable Matrix (RM)\n5.Dependency Rank\n6.Driver Power Rank\n7.Reset Data\n8.Exit\nAnswer : ");
			try
			{
				menu = scn.nextInt();
				switch(menu)
				{
					case 1:
					{
						cls(3);
						input_elements();
						break;
					}
					case 2:
					{
						if (nElement>1)
						{
							cls(3);
							input_relations();
						}
						else
						{
							System.out.println("Please input elements first !");
							separator(40,'#',true);
							cls(3);
							menu=0;
						}
						break;
					}
					case 3:
					{
						if (nElement>1 && isElementsHasRelation==true)
						{
							isGenerateSSIM=true;
							cls(3);
							makeSSIM();
						}
						else
						{

							isGenerateSSIM=false;
							System.out.println("Please input elements and relations first !");
							separator(40,'#',true);
							cls(3);
							menu=0;
						}
						break;
					}
					case 4:
					{
						if (nElement>1 && isElementsHasRelation==true && isGenerateSSIM==true )
						{
							isGenerateRM=true;
							cls(3);
							makeRM();
						}
						else
						{
							isGenerateRM=false;
							System.out.println("Please do previous step before generating RM !");
							separator(40,'#',true);
							cls(3);
							menu=0;
						}
						break;
					}
					case 5:
					{
						if (nElement>1 && isElementsHasRelation==true && isGenerateSSIM==true && isGenerateRM==true)
						{
							cls(3);
							rankingDependency();
						}
						else
						{
							System.out.println("Please do previous step before generating Rank Dependency !");
							separator(40,'#',true);
							cls(3);
							menu=0;
						}
						break;
					}
					case 6:
					{
						if (nElement>1 && isElementsHasRelation==true && isGenerateSSIM==true && isGenerateRM==true)
						{
							cls(3);
							rankingDriverPower();
						}
						else
						{
							System.out.println("Please do previous step before generating Rank Driver Power !");
							separator(40,'#',true);
							cls(3);
							menu=0;
						}
						break;
					}
					case 7:
					{
						System.out.println("Data Successfully Reset !");
						nElement=0;
						descElements.clear();
						menu=0;
						break;
					}
					case 8:
					{
						separator(40,'#',true);
						System.out.println("Thanks for using program :) !");
						separator(40,'#',true);
						System.exit(0);
						break;
					}
					default:
					{
						menu=0;
						break;
					}
				}
			}catch(InputMismatchException e)
			{
				System.out.println("Wrong input !, please try again !");
				separator(40,'#',true);
				waitForEnter();
				cls(3);
				scn.nextLine();
				main_menu();
				break;
			}
			
		}
	}
	
	public static void input_elements()
	{
		//Much Elements
		separator(40,'#',true);
		System.out.println("Input Elements");
		separator(40,'#',true);
		
		nElement=0;
		while (nElement==0)
		{
			System.out.print("How much elements ? : ");
			nElement=scn.nextInt();
			if (nElement<=1)
			{
				System.out.println("Elements can't less than 1 !, try again !");
				cls(1);
				separator(40,'#',true);
			}
		}
		
		//Elements Description
		cls(2);
		separator(40,'#',true);
		System.out.println("Input Elements Descriptions");
		separator(40,'#',true);
		if (!descElements.isEmpty()){descElements.clear();}
		scn.nextLine();
		for (int i=0;i<nElement;i++)
		{
			System.out.print("Description for element "+(i+1)+"(E"+(i+1)+")\nAnswer : ");
			descElements.add(scn.nextLine());
			separator(30,'-',true);
		}
		
		separator(40,'#',true);
		cls(4);
		main_menu();
		
	}
	
	public static void input_relations()
	{

		relationElementsVAXO=new String[nElement][nElement];
		separator(40,'#',true);
		System.out.println("Input Elements Relationships (VAXO)");
		separator(40,'#',true);
		
		boolean isVaxoFormat=false;
		int border=0;

		scn.nextLine();
		for (int i=0;i<nElement-1;i++)
		{
			border+=1;
			for(int j=0;j<nElement-border;j++)
			{
				isVaxoFormat=false;
				while(isVaxoFormat==false)
				{
					System.out.print("Relation between "+descElements.get(i)+" and "+descElements.get(border+j)+"\nAnswer(VAXO) : ");
					relationElementsVAXO[i][j]=scn.nextLine();
					
					for(int k=0;k<charVAXO.length;k++)
					{
						if(relationElementsVAXO[i][j].equals(charVAXO[k]))
						{
							relationElementsVAXO[i][j]=relationElementsVAXO[i][j].toUpperCase();
							isVaxoFormat=true;
							break;
						}
						else
						{
							if (k==charVAXO.length-1)
							{
								System.out.println("Please insert V/A/X/O only !");
								cls(2);
							}
							isVaxoFormat=false;
						}
					}
				}
				separator(40,'-',true);
			}
		}
		isElementsHasRelation=true;

		separator(40,'#',true);
		cls(4);
		main_menu();
	}
	
	public static void makeSSIM()
	{
		separator(40,'#',true);
		System.out.println("Structure-Self Interaction Matrix (SSIM)");
		separator(40,'#',true);
		

		separator(nElement*8,'=',true);
		

		String varElement="E";
		int border=0;
		for (int i=0;i<nElement;i++)
		{
			if (i==0)
			{
				System.out.print("   ");
			}
			if(i<9)
			{
				System.out.print("|  "+varElement+(i+1)+" |");
			}
			else
			{

				System.out.print("|  "+varElement+(i+1)+"|");	
			}
			
		}
		System.out.println();

		for (int i=0;i<nElement;i++)
		{
			if (i<9)
			{
				System.out.print(varElement+(i+1)+" ");	
			}
			else
			{
				System.out.print(varElement+(i+1));	
			}
			border+=1;
			int k=0;
			for(int j=0;j<nElement;j++)
			{
				if(j<border)
				{
					System.out.print("|  *  |");
				}
				else
				{
					System.out.print("|  "+relationElementsVAXO[i][k]+"  |");
					k+=1;
				}
				
			}
			System.out.println();
		}

		separator(nElement*8,'=',true);

		cls(1);
		separator(40,'#',true);
		System.out.println("Elements Variable Descriptions ");
		separator(40,'#',true);
		for(int i=0;i<nElement;i++)
		{
			System.out.println("E"+(i+1)+" : "+descElements.get(i));
			separator(40,'-',true);
		}
		separator(40,'#',true);
		
		waitForEnter();
		cls(2);
		main_menu();
	}
	
	public static void makeRM()
	{
		separator(40,'#',true);
		System.out.println("Reachable Matrix (RM)");
		separator(40,'#',true);
		

		separator(nElement*8,'=',true);
		

		String varElement="E";
		int border=0;
		int sumDP=0;
		relationElementsBinary=new int[nElement][nElement];
		
		for (int i=0;i<nElement;i++)
		{
			if (i==0)
			{
				System.out.print("   ");
			}
			if(i<9)
			{
				System.out.print("|  "+varElement+(i+1)+" |");
			}
			else
			{

				System.out.print("|  "+varElement+(i+1)+"|");	
			}
			
		}

		System.out.print("|  P  |");
		System.out.println();

		for (int i=0;i<nElement;i++)
		{
			if (i<9)
			{
				System.out.print(varElement+(i+1)+" ");	
			}
			else
			{
				System.out.print(varElement+(i+1));	
			}
			border+=1;
			int k=0;
			int l=0;
			//Driver Power
			sumDP=0;
			for(int j=0;j<nElement;j++)
			{
				if(j<border)
				{
					if (i==j)
					{
						System.out.print("|  1  |");
						relationElementsBinary[i][j]=1;
					}
					else
					{
						l=i-(j+1);
						switch(relationElementsVAXO[j][l])
						{
							case "V":
							{
								System.out.print("|  0  |");
								relationElementsBinary[i][j]=0;
								break;
							}
							case "A":
							{
								System.out.print("|  1  |");
								relationElementsBinary[i][j]=1;
								break;
							}
							case "X":
							{
								System.out.print("|  1  |");
								relationElementsBinary[i][j]=1;
								break;
							}
							case "O":
							{
								System.out.print("|  0  |");
								relationElementsBinary[i][j]=0;
								break;
							}
						}
					}
				}
				else
				{
					switch(relationElementsVAXO[i][k])
					{
						case "V":
						{
							System.out.print("|  1  |");
							relationElementsBinary[i][j]=1;
							break;
						}
						case "A":
						{
							System.out.print("|  0  |");
							relationElementsBinary[i][j]=0;
							break;
						}
						case "X":
						{
							System.out.print("|  1  |");
							relationElementsBinary[i][j]=1;
							break;
						}
						case "O":
						{
							System.out.print("|  0  |");
							relationElementsBinary[i][j]=0;
							break;
						}
					}
					k+=1;
				}
				sumDP+=relationElementsBinary[i][j];
			}
			if(sumDP<=9)
			{
				System.out.print("|  "+sumDP+"  |");	
			}
			else
			{
				System.out.print("|  "+sumDP+" |");
			}
			System.out.println();
		}

		separator(nElement*8,'-',true);
		countDependency();
		System.out.print("D  ");
		for (int i=0;i<nElement;i++)
		{
			if(Dependency[i]<=9)
			{
				System.out.print("|  "+Dependency[i]+"  |");	
			}
			else
			{

				System.out.print("|  "+Dependency[i]+" |");
			}
		}
		System.out.println();
		separator(nElement*8,'=',true);
	
		cls(1);
		separator(40,'#',true);
		System.out.println("Elements Variable Descriptions ");
		separator(40,'#',true);
		for(int i=0;i<nElement;i++)
		{
			System.out.println("E"+(i+1)+" : "+descElements.get(i));
			separator(40,'-',true);
		}
		separator(40,'#',true);
		
		waitForEnter();
		cls(2);
		main_menu();
	}
	
	
	public static void rankingDependency()
	{
		countDriverPower();
		setElementsAttr();
		separator(40,'#',true);
		System.out.println("Rank Dependency");
		separator(40,'#',true);
		
		rankDependency();
		int r=1;
		for (int i=nElement;i>0;i--)
		{
			System.out.println("Rank "+r+" : "+attrElements[i-1][0]+"\nVariable Name : "+attrElements[i-1][2]+"\nDepedency Value : "+attrElements[i-1][1]);
			separator(40,'-',true);
			if(i>1)
			{
				if (!attrElements[i-1][1].equals(attrElements[i-2][1]))
				{
					r+=1;	
				}	
			}
			else
			{
				r+=1;
			}
		}

		waitForEnter();
		cls(2);
		main_menu();
	}
	
	public static void rankingDriverPower()
	{
		countDriverPower();
		setElementsAttr();
		separator(40,'#',true);
		System.out.println("Rank Driver Power");
		separator(40,'#',true);
		
		rankDriverPower();
		int r=1;
		for (int i=nElement;i>0;i--)
		{
			System.out.println("Rank "+r+" : "+attrElements[i-1][0]+"\nVariable Name : "+attrElements[i-1][2]+"\nDriver Power Value : "+attrElements[i-1][3]);
			separator(40,'-',true);
			if(i>1)
			{
				if (!attrElements[i-1][3].equals(attrElements[i-2][3]))
				{
					r+=1;	
				}	
			}
			else
			{
				r+=1;
			}
		}

		waitForEnter();
		cls(2);
		main_menu();
	}
	
	public static void cls(int space)
	{
		for (int i=0;i<space;i++)
		{
			System.out.println();
		}
	}
	
	public static void separator(int lenght,char c,boolean newline)
	{
		for (int i=0;i<lenght;i++)
		{
			System.out.print(c);
		}
		if (newline==true)
		{
			System.out.println();
		}
	}
	
	public static void waitForEnter()
	{
		try
		{
			System.out.print("Press enter to continue...");
			System.in.read();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
