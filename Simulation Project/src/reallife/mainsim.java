package reallife;
import java.util.ArrayList;
import java.security.SecureRandom;
import simulation.factory;
import simulation.worker;

public class mainsim {
public static void main(String[] args) {
	SecureRandom generator=new SecureRandom();
	int simDays=30;
	int sim1Miutes=(1*8*60);
	int simAlltime=1;
	int simMaxtime=simDays*sim1Miutes;
	int noFactories=10;
	worker injuried = new worker();
	factory selectedFactory;
	ArrayList<factory> factorylist = createfactory(noFactories);
	for(int day=1; day<=simDays; day++) {
		for(int minute=1; minute<=sim1Miutes; minute++) {
//			if(simAlltime%480==0) {
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("                                     Day:"+ day);
			System.out.println("		                           Time:"+ Time(minute));
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("Factory ID|Worker ID|Age|conditions      |Type of Injury      |Time of injury|Progression Rate|Progression|Ambulance Arrives in|\n");
//			}
		for(int factory = 0; factory<factorylist.size(); factory++) {
				selectedFactory = factorylist.get(factory);
				selectedFactory.injuryandtreatment(injuried, simAlltime, simMaxtime, Time(minute));
				selectedFactory.printInjuried(simAlltime);
			}
		simAlltime++;
		 }
	}
		

}


public static ArrayList<factory> createfactory(int FacNo) {
	ArrayList<factory> factorylist = new ArrayList<factory>();
	for(int i=1; i<=FacNo; i++) {
		factory a = new factory(i);
		factorylist.add(a);
	}
	return factorylist;
}
public static ArrayList<Boolean> generateInjuries(ArrayList<factory> FactoriesList){
	SecureRandom generator=new SecureRandom();
	ArrayList<Boolean> injuryHappening = new ArrayList<>();
	for(int i=0; i<=FactoriesList.size(); i++) {
	injuryHappening.add(generator.nextBoolean());
}
	return injuryHappening;
}
public static String padRight(String s, int n) {
    return String.format("%-" + n + "s", s);  
}

public static String padLeft(String s, int n) {
   return String.format("%" + n + "s", s);  
}
public static String Time(int Time) {
	int hour = (int)(9+Math.floor((double)(Time/60.0)));
	int minute = Time%60;
	return String.format("%02d",hour)+":"+String.format("%02d",minute); 
	}
}