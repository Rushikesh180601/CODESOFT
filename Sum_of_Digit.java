package MyPacakage;

import java.util.Scanner;

public class Sum_of_Digit 
{
	public static void main(String[] args) 
	{
//		int a=555;
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter the no");
		
		int a=sc.nextInt();
		
		int sum=0,r=0;
		while(a>0)
		{
			r=a%10;
			sum=sum+r;
			a=a/10;
		}
		System.out.println("sum of digits of given no is = "+sum);
		
	}
}
