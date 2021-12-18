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
	int noFactories=10;
	worker injuried;
	factory selectedFactory;
	ArrayList<factory> factorylist = createfactory(noFactories);
	for(int day=1; day<=simDays; day++) {
		for(int minute=1; minute<=sim1Miutes; minute++) {
//			if(simAlltime==1||simAlltime%480<=30&&simAlltime%480!=0) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.print("Day:"+ day);
			System.out.println("		Time:"+ Time(minute));
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println(padRight("FID", 3)+" | "+padRight("ID", 3)+" | "+" | "+"perma"+" | "+" | "+"PR"+" | "+" | "+"Prg"+" | "+"Smart"+" | "+"f"+" | "+"HAT"+" | "+"useAK"+" | "+" | "+"Midkit"+" | "+" | "+"inury"+" | ");
//			}
		for(int factory = 0; factory<factorylist.size(); factory++) {
				selectedFactory = factorylist.get(factory);
				for(int in=0; in<selectedFactory.getInjuriedlist().size();in++) {
//					if(simAlltime==1||simAlltime%480<=30&&simAlltime%480!=0) {
//					if(selectedFactory.getInjuriedlist().get(in).hat==0) {
//						
//
//					}
					if(selectedFactory.getInjuriedlist().get(in).hat!=0) {
					System.out.println("Factory: "+ selectedFactory.getID()+" " + selectedFactory.getInjuriedlist().get(in).getId()+" " + selectedFactory.getInjuriedlist().get(in).isInjuired()+" " + selectedFactory.getInjuriedlist().get(in).isPermenatlyinjuried()+" "+ selectedFactory.getInjuriedlist().get(in).getProgressionRate()+" "+ selectedFactory.getInjuriedlist().get(in).getProgressionofInjury()+" "+ selectedFactory.getInjuriedlist().get(in).getHospitalarraivalTime()+" "+ simAlltime+" "+ selectedFactory.getInjuriedlist().get(in).hat+" "+selectedFactory.getTimetohospital());
//					}
					}
					
				}
					
				if(generator.nextInt((10-selectedFactory.getDensity())*3)==0) {
				if(selectedFactory.getInjuriedlist().isEmpty()) {
						injuried=selectedFactory.chooseRandomworker();
							injuried.addinjury(selectedFactory);
							selectedFactory.Injuriedlist.add(injuried);
					}
				}
				if(!selectedFactory.Injuriedlist.isEmpty()) {
					selectedFactory.LoopTreating(simAlltime);
				}
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