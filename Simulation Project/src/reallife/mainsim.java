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
	int simAlltime=0;
	int noFactories=5;
	worker injuried;
	ArrayList<factory> factorylist = createfactory(noFactories);
	for(int day=1; day<=simDays; day++) {
		for(int minute=1; minute<=sim1Miutes; minute++) {
			simAlltime++;
			if(simAlltime==1||simAlltime%480==0||simAlltime%480<=10) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.print("Day:"+ day);
			System.out.println("		Minute:"+ minute);
			System.out.println("-------------------------------------------------------------------------------");}
		for(int factory = 0; factory<factorylist.size(); factory++) {
				factory selectedFactory = factorylist.get(factory);
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
}