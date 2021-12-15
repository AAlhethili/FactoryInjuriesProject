package simulation;
import java.util.ArrayList;
import java.security.SecureRandom;
import simulation.worker;

public class factory {
	
SecureRandom generator = new SecureRandom();
public int ID;
public int noworkers;
public int noFirstResponders;
public boolean regularcheckup;
public double avgcheckup;
public double avgcheckuprate;
public double avg;
public double avgage;
public int timetohospital;
public int Dailyinjuried;
public int TotalInjuries;
public int DialynoOfPermanantInjuried;
public int TotalnoOfPermanantInjuried;

public ArrayList<worker> wlist = new ArrayList<worker>();
public ArrayList<worker> FirstResponders = new ArrayList<worker>();
public ArrayList<worker> Availablelist = new ArrayList<worker>();
public ArrayList<worker> Outlist = new ArrayList<worker>();
public ArrayList<worker> retiredlist = new ArrayList<worker>();



public factory(int iD) {

	ID = iD;
	avg = generator.nextInt(36)+25;
	avgcheckup= generator.nextInt(4)+1;
	this.regularcheckup = generator.nextBoolean();
	createworkers(generator.nextInt(451)+50);
	this.noworkers=wlist.size();
	this.timetohospital = Math.round((generator.nextInt(51)+10)/10)*10;
	this.avgage=Math.round(calculateAvgAge());
	this.avgcheckuprate=Math.round(calculateAvgcheckup());
	for(int i=0; i<=noworkers-1; i++) {
		if(wlist.get(i) instanceof medicallytrainedworker) {
			FirstResponders.add(wlist.get(i));
		}
	}
	noFirstResponders=FirstResponders.size();
	
}
public void createworkers(int noworkers) {
	
	for(int i=1; i<=noworkers; i++) {
		if(generator.nextInt(40)==0) {
		worker a = new medicallytrainedworker(i,avg,avgcheckup);
		wlist.add(a);
		}
		else {
		worker a = new worker(i,avg,avgcheckup);
		wlist.add(a);
		}
	}
	if(regularcheckup==false) {
		for(int i=0; i<=noworkers-1; i++) {
			
			double above0 = generator.nextGaussian()*0.6;
			while(above0<0||above0>4)
			{
			above0 = generator.nextGaussian()*0.6;	
			}
			wlist.get(i).regularcheckuprate=above0;
		}
	}
		
}
public double calculateAvgAge() {
	double sum=0;
	double avgage;
	for(int i=0; i<=noworkers-1; i++) {
		sum+=wlist.get(i).age;
	}
	avgage=sum/(double)noworkers;
	return avgage;
}
public double calculateAvgcheckup() {
	double sum=0;
	double avgcheckuprate;
	for(int i=0; i<=noworkers-1; i++) {
		sum+=wlist.get(i).regularcheckuprate;
	}
	avgcheckuprate=sum/(double)noworkers;
	return avgcheckuprate;
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

public int getTimetohospital() {
	return timetohospital;
}
public void setTimetohospital(int timetohospital) {
	this.timetohospital = timetohospital;
}
public int getDailyinjuried() {
	return Dailyinjuried;
}
public void setDailyinjuried(int dailyinjuried) {
	Dailyinjuried = dailyinjuried;
}
public int getTotalInjuries() {
	return TotalInjuries;
}
public void setTotalInjuries(int totalInjuries) {
	TotalInjuries = totalInjuries;
}
public int getDialynoOfPermanantInjuried() {
	return DialynoOfPermanantInjuried;
}
public void setDialynoOfPermanantInjuried(int dialynoOfPermanantInjuried) {
	DialynoOfPermanantInjuried = dialynoOfPermanantInjuried;
}
public int getTotalnoOfPermanantInjuried() {
	return TotalnoOfPermanantInjuried;
}
public void setTotalnoOfPermanantInjuried(int totalnoOfPermanantInjuried) {
	TotalnoOfPermanantInjuried = totalnoOfPermanantInjuried;
}


@Override
public String toString() {
	return "factory [ID=" + String.format("%02d",ID) + "| noworkers=" + String.format("%03d",noworkers)  + "| avgcheckuprate="
			+ avgcheckuprate + "| avgage=" + avgage + "| noOfinjuried=" + TotalInjuries + "| timetohospital="
			+ timetohospital + "| First Responders = " + String.format("%02d", ((int)Math.round(((double)noFirstResponders/(double)noworkers)*100)))+"%" + "| regularcheckup=" + regularcheckup +"]";
}

}
