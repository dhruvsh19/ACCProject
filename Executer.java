package ACCProject;
import java.util.Scanner;



public class Executer {
	//It will display menu for the selecting the action user wants to perform
	public static void displaymenu() {		
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		boolean menu = true;
		while(menu) {
			System.out.println("\n\n-----------------------------------------\nSearch Engine\n-----------------------------------------");
			System.out.println("Press 1 to search for a keyword");
			System.out.println("Press 2 to crawl the pages ");
			System.out.println("Press 3 to search for similar words");
			System.out.println("Press 0 to exit");
			System.out.println("-----------------------------------------");
			System.out.print("Select an option: ");
			String ans = sc.nextLine();
			
			switch(ans) {
				case "1":
					System.out.print("Enter keyword to search: ");
					Search.searchPhrase(sc2.nextLine(),10);
					break;
				case "2":
					System.out.println("Executing crawl on default links");
					WebCrawler.crawlDefault();
					break;
					
				case "3":
					System.out.print("Enter starting key to search: ");
					Search.searchKeys(sc2.nextLine());
					break;
				case "0":
					System.out.println("thanks for using the application");
					System.exit(0);
					break;
				default:
					System.out.println("Wrong Input, Try again.");
			}
		}
		sc.close();
		sc2.close();
		
	}
	
	public static void main(String[] args) {
		displaymenu();
	}
}
