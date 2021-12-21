package reallife;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import reaLifePhase2.phase2Simu;
import simulation.factory;
import simulation.worker;

public class mainsim {
public static void main(String[] args) {
	phase2Simu begin = new phase2Simu();
	//phase 2 constructor
	Scanner input = new Scanner(System.in);
	//used to Randomize variables
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
	
	System.out.print("Simulation starting");//cosmetic effect :)
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
			printheader(day, minute);
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
	//Get Average data per factory
	for(int factory = 0; factory<factorylist.size(); factory++) {
		selectedFactory = factorylist.get(factory);	}	
		ArrayList<ArrayList<String>> Data = giveHeader();
		for(int factory = 0; factory<factorylist.size(); factory++) {
			selectedFactory = factorylist.get(factory);
			Data.add(selectedFactory.stringOfFactroyInfo());
		}
		//2d Array list to 2d Array
		String[][] DataArray = twoDArrayListToArray(Data);
		printTable(DataArray);
		//Store All data from phase 1 to pass to phase 2
		ArrayList<Object> phase1= new ArrayList<Object>();
		phase1=gettAllData(factorylist);




		//Input to start phase 2
	continues=true;
	while(continues) {
		try {
		System.out.print("Please enter 1 to start phase 2 or 0 to stop. ");
		input=new Scanner(System.in);
		int phase2begin=input.nextInt();
		if(!(phase2begin!=1||phase2begin!=0)) {
			throw new IntegerMustBePositiveException();	
			}
	if(phase2begin==1) {
		begin.startPhase2(simDays, workhours, noFactories, phase1);
	}
		continues=false;
		}catch(IntegerMustBePositiveException | InputMismatchException e) {
			System.out.println("0 or 1 please, try again ");
		}
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
public static void printheader(int day, int minute) {
	System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
	System.out.print("                                     Day:"+ day);
	System.out.println("		      |                     Time:"+ Time(minute));
	System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
	System.out.printf("Factory ID|Worker ID|Age|conditions      |Type of Injury      |Time of injury|Progression Rate|Progression|Ambulance Arrives in|\n");

}



public static void printTable(String[][] table) {

  int maxColumns = 0;
  for (int i = 0; i < table.length; i++) {
    maxColumns = Math.max(table[i].length, maxColumns);
  }

  int[] lengths = new int[maxColumns];
  for (int i = 0; i < table.length; i++) {
    for (int j = 0; j < table[i].length; j++) {
      lengths[j] = Math.max(table[i][j].length(), lengths[j]);
    }
  }

  String[] formats = new String[lengths.length];
  for (int i = 0; i < lengths.length; i++) {
   formats[i] = "%-1" + lengths[i] + "s" 
       + (i + 1 == lengths.length ? "\n" : "");
 }

  for (int i = 0; i < table.length; i++) {
    for (int j = 0; j < table[i].length; j++) {
      System.out.printf(formats[j], table[i][j]);
    }
  }
}
public static ArrayList<Object> gettAllData(ArrayList<factory> factorylist){
	 ArrayList<Object> list = new ArrayList<Object>();
	 list.add("Phase 1");
	 double age=0;
	 double injurycount=0;
	 double injuryseverity=0;
	 double permainj=0;
	 String SMk = "No";
	 String Reg = "No";
	 double cur=0;
	 double infread=0;
	 double inftrt=0;
	 double workpre=0;
	 double usemk=0;
	 double mktreat=0;
	 double mw=0;
	 double mwtrt=0;
	 double max=0;
	 double min=0;
	for(int factory = 0; factory<factorylist.size(); factory++) {
		simulation.factory selectedFactory = factorylist.get(factory);
	age+=Math.round(selectedFactory.calculateAvgAge());
	injurycount+=selectedFactory.getCounterInjuries();
	injuryseverity+=selectedFactory.getCounterInjuryLevel();
	permainj+=selectedFactory.calculateNumofPerma();
	cur+=selectedFactory.getAvgCheckUpRate();
	infread+=selectedFactory.getFactoryInfermaryRoom().getRangeofcaplbility();
	inftrt+=selectedFactory.getCounterTreatmentByCapableInfermaryRoom();
	workpre+=selectedFactory.getCounterNoOfWorkerWhenInjuryHappened();
	usemk+=selectedFactory.CalculatWhocanuseMK();
	mktreat+=selectedFactory.getCounterTreatmentByFirstAidKit();
	mw+=selectedFactory.getNoOfFirstResponders();
	mwtrt+=selectedFactory.getCounterTreatmentByMedicalWorker();
	max+=selectedFactory.getMaxProgressedInjury();
	min+=selectedFactory.getMinProgressedInjury();
		}
	list.add(age/=factorylist.size());
	list.add(injurycount/=factorylist.size());
	list.add(injuryseverity/=factorylist.size());
	list.add(permainj/=factorylist.size());
	list.add(SMk);
	list.add(Reg);
	list.add(cur/=factorylist.size());
	list.add(infread/=factorylist.size());
	list.add(inftrt/=factorylist.size());
	list.add(workpre/=factorylist.size());
	list.add(usemk/=factorylist.size());
	list.add(mktreat/=factorylist.size());
	list.add(mw/=factorylist.size());
	list.add(mwtrt/=factorylist.size());
	list.add(max/=factorylist.size());
	list.add(min/=factorylist.size());
	list.add(age/=factorylist.size());
	

	return list;
	

}

public static ArrayList<ArrayList<String>> giveHeader() {
	ArrayList<String> Header = new ArrayList<String>(
		    Arrays.asList(" "," "," "," "," "," "," "," "," "," ","Workers","Workers", " "," "," ", " ", " "));
			ArrayList<String> Header2 = new ArrayList<String>(
				    Arrays.asList(" "," "," "," "," "," ","Imposes","Average"," ","Injuries","Present","Who Can", "Injuries","Workers","Injuries", " ", " "));
					ArrayList<String> Header3 = new ArrayList<String>(
						    Arrays.asList(" ","Workers"," ","injury"," ","Uses","Regular","Medical","Infirmary","treated","Upon","Use", "Slowed","with","Slowed by", "Maximum", "Minimum"));
							ArrayList<String> Header4 = new ArrayList<String>(
								    Arrays.asList("Factory","Age","Injury","Severity","Permenant","Smart","Medical","Checkup","Rating","by","Happend","MidKit", "by","Medical","Trained", "Injury", "Injury"));
									ArrayList<String> Header5 = new ArrayList<String>(
											Arrays.asList("ID","Average","Count","Average","Injuries","Aid Kit","Checkup","Rate","Out of 7","Infirmary","Average","", "Midkit","Training","Workers", "progress", "Progress"));
	ArrayList<ArrayList<String>> Data = new ArrayList<ArrayList<String>>();
	Data.add(Header);
	Data.add(Header2);
	Data.add(Header3);
	Data.add(Header4);
	Data.add(Header5);
	return Data;
}
public static String[][] twoDArrayListToArray(ArrayList<ArrayList<String>> arrayList2D){
	String[][] Array2D = new String[arrayList2D.size()][];
	for (int i = 0; i < arrayList2D.size(); i++) {
	    ArrayList<String> row = arrayList2D.get(i);
	    Array2D[i] = row.toArray(new String[row.size()]);};
	    return Array2D;
	}
}
