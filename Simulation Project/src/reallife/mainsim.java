package reallife;
import java.util.ArrayList;
import java.security.SecureRandom;
import simulation.factory;
import simulation.worker;

public class mainsim {
public static void main(String[] args) {
	SecureRandom generator=new SecureRandom();
	int simDays=30;
	int sim10MiutesFinal=(simDays*8*60);
	int simCurrentTime=0;
	int noFactories=100;
	worker injuried;
	ArrayList<factory> factorylist = createfactory(noFactories);
	
	while(simCurrentTime<=sim10MiutesFinal) {
		for(int i = 0; i<factorylist.size(); i++) {
		int noofinjuries = 1;
		int injuryHappened = generator.nextInt(2);
		factory selectedFactory = factorylist.get(i);
		if(selectedFactory.anyoneHealthy()) {
			if(injuryHappened==0) {
			for(int w = 0; w<noofinjuries; w++) {
			injuried=selectedFactory.chooseRandomworker();
			while(injuried.isPermenatlyinjuried()||injuried.isBeingTreated()) {
				injuried=selectedFactory.chooseRandomworker();	
				}
				injuried.addinjury();
				selectedFactory.Injuriedlist.add(injuried);
				}
			}
		}	 
		 if(selectedFactory.getInjuriedlist().size()>0) {
		for(int j = 0; j<selectedFactory.getInjuriedlist().size(); j++) {
			selectedFactory.treatWorker(selectedFactory.getInjuriedlist().get(j), simCurrentTime);
			if(selectedFactory.getInjuriedlist().get(j).isInjuired()==false){
				selectedFactory.getInjuriedlist().remove(selectedFactory.getInjuriedlist().get(j));
			}
//			selectedFactory.workerAfterTreatment(selectedFactory.getInjuriedlist().get(j));
			}
			}
			}
		simCurrentTime+=10;
		 }
	for(int i = 0; i<factorylist.size(); i++) {
		factorylist.get(i).showPermalisList();
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
}