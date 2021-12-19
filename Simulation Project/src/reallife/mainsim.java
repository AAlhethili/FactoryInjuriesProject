package reallife;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import simulation.ToolsofStrings;

import java.io.InputStream;
import java.security.SecureRandom;
import simulation.factory;
import simulation.worker;

public class mainsim {
public static void main(String[] args) {
	SecureRandom generator=new SecureRandom(); 
	Scanner input = new Scanner(System.in);
	//used to Randomize variables
	ToolsofStrings tos= new ToolsofStrings(); 
	//used to manipulate strings 
	boolean continues=true;
	int simDays=0; 
	//Number of days the simulation will run
	int workhours=0; 
	//Number of work hours of a factory, used to get minutes of simulation
	int noFactories=0; 
	//number of factories in simulation
	System.out.println("Welcome to the factory daily injuries simulator!");
	while(continues) {
				try {
				System.out.print("Please enter the number of days the simulation will run for: ");
				input=new Scanner(System.in);
				simDays=input.nextInt();
				if(!(simDays>0)) {
					throw new IntegerMustBePositiveException();	
					}
				continues=false;
				}catch(IntegerMustBePositiveException | InputMismatchException e) {
					System.out.println("Days must integer be greater than 0, try again ");
				}
	}
	continues=true;
	while(continues) {
				try {
				System.out.print("Please enter the number of daily work hours of the factories: ");
				input=new Scanner(System.in);
				workhours=input.nextInt();
				if(!(workhours>0)||workhours>24) {
					throw new IntegerMustBePositiveException();	
					}
				continues=false;
				}catch(IntegerMustBePositiveException | InputMismatchException e) {
					System.out.println("work hours must be integer greater than 0 and less than 24, try again ");
				}
	}
	continues=true;
	while(continues) {
				try {
				System.out.print("Please enter the number of factories: ");
				input=new Scanner(System.in);
				noFactories=input.nextInt();
				if(!(noFactories>0)) {
					throw new IntegerMustBePositiveException();	
					}
				continues=false;
				}catch(IntegerMustBePositiveException | InputMismatchException e) {
				System.out.println("Number of factories must be integer greater than 0, try again ");
				}
	}
	
	wait(1000);
	System.out.print("Simulation starting");
	wait(1000);
	System.out.print(".");
	wait(1000);
	System.out.print(".");
	wait(1000);
	System.out.print(".");
	wait(1000);
	System.out.println();
	System.out.println();
	
	int sim1Miutes=(workhours*60); 
	int simDay=(workhours*60); 
	//minutes of simulation
	int simAlltime=1; 
	//time counter to get total simulation time without affecting for loops
	int simMaxtime=simDays*sim1Miutes; 
	//maximum amount of minutes sim will run

	worker injuried = new worker();
	//used to hold and pass the injured worker object
	factory selectedFactory; 
	//used to hold and pass the factory object
	ArrayList<factory> factorylist = createfactory(noFactories); //used to hold all factories objects and loop through them
	for(int day=1; day<=simDays; day++) { // loop of days
		for(int minute=1; minute<=sim1Miutes; minute++) {// loop of minutes
			//header of daily injuries results
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("                                     Day:"+ day);
			System.out.println("		                           Time:"+ Time(minute));
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("Factory ID|Worker ID|Age|conditions      |Type of Injury      |Time of injury|Progression Rate|Progression|Ambulance Arrives in|\n");
		
			for(int factory = 0; factory<factorylist.size(); factory++) { //looping through each factory each minute
				selectedFactory = factorylist.get(factory); // holding factory to apply methods on it
				selectedFactory.injuryandtreatment(injuried,simDay, simAlltime, simMaxtime, Time(minute)); // a method generate injuries and try to trat them
				selectedFactory.printInjuried(simAlltime,simDay);// method to display injured people each minute
			}
		simAlltime++;
		 }
	}
	System.out.println("********************************************************************************************************************************\n\n\n\n");
	System.out.printf("\t\t\t\t\t\t\tFactory List\n"); 
	for(int factory = 0; factory<factorylist.size(); factory++) {
		selectedFactory = factorylist.get(factory);
		selectedFactory.showFactoryInfo(simDays, workhours); // method to display every factories simulation results giving the crucial info
	}	

}


public static ArrayList<factory> createfactory(int FacNo) {// Generate factory objects based on number of factories and return them in an Arraylist
	ArrayList<factory> factorylist = new ArrayList<factory>();
	for(int i=1; i<=FacNo; i++) {
		factory a = new factory(i);
		factorylist.add(a);
	}
	return factorylist;
}

public static String Time(int Time) { //gives time on standard 24 hours format
	int hour = (int)(9+Math.floor((double)(Time/60.0)));
	int minute = Time%60;
	return String.format("%02d",hour)+":"+String.format("%02d",minute); 
	}
public static void wait(int ms)
{
    try
    {
        Thread.sleep(ms);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
}
}