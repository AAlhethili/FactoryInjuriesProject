package reallife;
import java.util.ArrayList;
import simulation.factory;
import simulation.worker;

public class mainsim {
public static void main(String[] args) {
	int noFactories=50;
	ArrayList<factory> factorylist = createfactory(noFactories);
	for(int j=0; j<noFactories; j++) {
	System.out.println(factorylist.get(j).toString());
	}
	
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
