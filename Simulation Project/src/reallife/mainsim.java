package reallife;
import java.util.ArrayList;
import simulation.factory;
import simulation.worker;

public class mainsim {
public static void main(String[] args) {
	int noFactories=50;
	ArrayList<factory> factorylist = createfactory(noFactories);
	factorylist.get(1).createworkers();;
	factorylist.get(1).showWorkerList();
	factorylist.toString();
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
