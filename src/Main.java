import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class Main {

	public static void main(String[] args) {
		
		int i;
		//Scanner myTest = new Scanner(System.in);
//		
//		System.out.println("Print FirstName :");
//		String FirstName = myTest.nextLine();
//		System.out.println("Print SecondName :");
//		String SecondName = myTest.nextLine();
		
		String FirstNames[]={"Suhrudh Gowtham", "Mahantesh ", "Pannaga ","Gaurav bhosle","chotta bheem",
				"mighty raju","virat kohli","mahendra singh dhoni","sachin tendulkar","narendra modi","kamalesh","venkatadri",
				"purushotham", "narsimha","syed abdul","sudarshan","subrahmanya","shashikala","george francis","arun kumar",
				"arvind ramakrishna","gangadhar gangigatti","sangamesh patil", "guna sarika", "sadananda maiya"};
		
		String SecondNames[]={"Sukruth gwtham", "mantesh", "Pannaga k p","dhyaneshwar","bada bheem",
				"rajeshwar","virat mehta","mahendra singh dhoni","gaurav pandey","nirav modi","amaresh","venkatesh","sarvotham",
				"narsimha","syed pasha","avinashi","sunder krishna","chandrakala","george fernandis","anil kumar","arvind",
				"sudhakar gangigatti","sadananda gowda","niharika","sadanand maiya"};
		
		
		for(i=0;i<25;i++)
		{
			
		
		int x1= FuzzySearch.ratio(FirstNames[i], SecondNames[i]);
		int x2= FuzzySearch.partialRatio(FirstNames[i], SecondNames[i]);
		
		int y1= FuzzySearch.tokenSortRatio(FirstNames[i], SecondNames[i]);
		int y2= FuzzySearch.tokenSortPartialRatio(FirstNames[i], SecondNames[i]);
		
		int z1= FuzzySearch.tokenSetRatio(FirstNames[i], SecondNames[i]);
		int z2= FuzzySearch.tokenSetPartialRatio(FirstNames[i], SecondNames[i]);
		
		int w = FuzzySearch.weightedRatio(FirstNames[i],SecondNames[i]);
		
		
		System.out.println(FirstNames[i]);
		System.out.println(SecondNames[i]);
		
		System.out.println("ratio="+ x1);
		System.out.println("partial ratio="+ x2);
		System.out.println("token sort ratio="+ y1);
		System.out.println("token sort partial ratio="+ y2);
		System.out.println("token set ratio="+ z1);
		System.out.println("token set partial ratio="+ z2);
		System.out.println("weighted ratio="+ w);
		System.out.println("--------------------------------------------");
		
		}
		
		
	}

}
