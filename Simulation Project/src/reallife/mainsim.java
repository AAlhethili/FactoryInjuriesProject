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
	int noFactories=20;
	ArrayList<factory> factorylist = createfactory(noFactories);
	
	while(simCurrentTime<=sim10MiutesFinal) {
		for(int i = 0; i<factorylist.size(); i++) {
		int injuryHappened = generator.nextInt(5);
		factory selectedFactory = factorylist.get(i);
		if(injuryHappened==2) {
			worker injuried=selectedFactory.chooseRandomworker();
			injuried.addinjury();
			selectedFactory.workeroutofWork(injuried);
		}
		 
		 if(selectedFactory.getInjuriedlist().size()>0) {
		for(int j = 0; j<selectedFactory.getInjuriedlist().size(); j++) {
			selectedFactory.treatWorker(selectedFactory.getInjuriedlist().get(j), simCurrentTime);
			}
//			for(int k = 0; k<selectedFactory.MedicalIntervention.size(); j++) {
//				
//			}
				
			}
			}
		 }
		
		
		
	simCurrentTime=+10;
}
//	}
//	for(int j=0; j<noFactories; j++) {
//	System.out.println(factorylist.get(j).toString());
//	}
//	System.out.println(factorylist.get(0).toString());
//	factorylist.get(0).showWorkerList();
//	for(int j=0; j<factorylist.get(0).getNoworkers(); j++) {
//	System.out.println(factorylist.get(0).wlist.get(j).getAge());
//	
//	}
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