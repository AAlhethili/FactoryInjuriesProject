package simulation;
import java.util.ArrayList;
import java.security.SecureRandom;
import simulation.worker;

public class factory {
SecureRandom generator = new SecureRandom();
public int ID;
public int noworkers;
public ArrayList<worker> wlist = new ArrayList<worker>();
public boolean regularcheckup;
public double avgcheckuprate;
public double avgage;
public int noOfinjuried;
public int timetohospital;
public factory(int iD) {

	ID = iD;
	this.noworkers = generator.nextInt(451)+50;
	this.regularcheckup = generator.nextBoolean();
	this.timetohospital = generator.nextInt(51)+10;
	
}
public void createworkers() {
	
	for(int i=1; i<=noworkers; i++) {
		worker a = new worker(i);
		wlist.add(a);
	}
	if(regularcheckup==false) {
		for(int i=0; i<=noworkers-1; i++) {
			wlist.get(i).regularcheckuprate=0;
		}
	}
		
}
public void calculateAvgAge() {
	int sum=0;
	double avgage;
	for(int i=0; i<=noworkers-1; i++) {
		sum+=wlist.get(i).age;
	}
	avgage=sum/noworkers;
	this.avgage=avgage;
}
public void calculateAvgcheckup() {
	int sum=0;
	double avgage;
	for(int i=0; i<=noworkers-1; i++) {
		sum+=wlist.get(i).regularcheckuprate;
	}
	avgage=sum/noworkers;
	avgcheckuprate=avgage;
}
public void showWorkerList() {
	System.out.println("ID		Age		Checkup");
	for(int i=0; i<=noworkers-1; i++) {
		System.out.println(wlist.get(i).id+"		"+wlist.get(i).age+"		"+wlist.get(i).regularcheckuprate);
	}
}

public int getNoworkers() {
	return noworkers;
}
public void setNoworkers(int noworkers) {
	this.noworkers = noworkers;
}
public boolean isRegularcheckup() {
	return regularcheckup;
}
public void setRegularcheckup(boolean regularcheckup) {
	this.regularcheckup = regularcheckup;
}
public int getNoOfinjuried() {
	return noOfinjuried;
}
public void setNoOfinjuried(int noOfinjuried) {
	this.noOfinjuried = noOfinjuried;
}
public int getTimetohospital() {
	return timetohospital;
}
public void setTimetohospital(int timetohospital) {
	this.timetohospital = timetohospital;
}
public int getID() {
	return ID;
}
public double getAvgcheckuprate() {
	return avgcheckuprate;
}
public double getAvgage() {
	return avgage;
}
@Override
public String toString() {
	return "factory [ID=" + ID + ", noworkers=" + noworkers + ", regularcheckup=" + regularcheckup + ", avgcheckuprate="
			+ avgcheckuprate + ", avgage=" + avgage + ", noOfinjuried=" + noOfinjuried + ", timetohospital="
			+ timetohospital + "]";
}

}