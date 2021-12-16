package simulation;
import java.util.ArrayList;
import java.util.Random;
import java.security.SecureRandom;
import simulation.worker;

public class factory {
	
SecureRandom generator = new SecureRandom();
public int ID;
private int noworkers;
private int noFirstResponders;
//private int noInfirmaryRooms;
//private int noMedKits;
private boolean regularcheckup;
private double avgcheckup;
private double avgcheckuprate;
private double avg;
private double avgage;
private int timetohospital;
private int Dailyinjuried;
private int TotalInjuries;
private int DialynoOfPermanantInjuried;
private int TotalnoOfPermanantInjuried;

public ArrayList<worker> wlist = new ArrayList<worker>();
public ArrayList<worker> FirstResponders = new ArrayList<worker>();
public ArrayList<worker> Availablelist = new ArrayList<worker>();
public ArrayList<worker> Injuriedlist = new ArrayList<worker>();
public ArrayList<worker> retiredlist = new ArrayList<worker>();
public ArrayList<medicalintervention> MedicalIntervention = new ArrayList<medicalintervention>();
//public ArrayList<medicalintervention> FirstAidkits = new ArrayList<medicalintervention>();
//public ArrayList<medicalintervention> InfirmaryRooms = new ArrayList<medicalintervention>();

public factory(int iD) {

	ID = iD;
	avg = generator.nextInt(36)+25;
	avgcheckup= generator.nextInt(4)+1;
	regularcheckup = generator.nextBoolean();
	createworkers(generator.nextInt(451)+50);
	noworkers=wlist.size();
	createFirstAidKits((int)Math.round((double)noworkers/25.0));
	createRooms((int)Math.round((double)noworkers/100.0));
	timetohospital = Math.round((generator.nextInt(51)+10)/10)*10;
	avgage=Math.round(calculateAvgAge());
	avgcheckuprate=Math.round(calculateAvgcheckup());
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
			wlist.get(i).setRegularcheckuprate(above0);
		}
	}
		
}
public void createRooms(int noRooms) {
	
	for(int i=0; i<=noRooms; i++) {
		infirmaryroom room = new infirmaryroom();
		MedicalIntervention.add(room);
	}
		
}
public void createFirstAidKits(int noKits) {
	
	for(int i=0; i<=noKits; i++) {
		medicalintervention kit = new FirstAidKit();
		if(kit instanceof FirstAidKit) {
		MedicalIntervention.add(kit);}
	}
		
}
public double calculateAvgAge() {
	double sum=0;
	double avgage;
	for(int i=0; i<=noworkers-1; i++) {
		sum+=wlist.get(i).getAge();
	}
	avgage=sum/(double)noworkers;
	return avgage;
}
public double calculateAvgcheckup() {
	double sum=0;
	double avgcheckuprate;
	for(int i=0; i<=noworkers-1; i++) {
		sum+=wlist.get(i).getRegularcheckuprate();
	}
	avgcheckuprate=sum/(double)noworkers;
	return avgcheckuprate;
}
public worker chooseRandomworker(){
	worker randomSelect = wlist.get(generator.nextInt(wlist.size()-1));
	return randomSelect;
}
public void treatWorker(worker injuried, int simTime) {
	if (injuried.getCurrent().lvl==2) {
		for(int i=0; i<wlist.size();i++) {
			worker firstresponder=wlist.get(i);
			if(firstresponder instanceof medicallytrainedworker) {
				((medicallytrainedworker) firstresponder).treatmentadminstraition(injuried, simTime);
			}
			
			
		}
	}
	else {
		for(int i=0; i<MedicalIntervention.size();i++) {
			MedicalIntervention.get(i).treatmentadminstraition(injuried, simTime);
		}
	}
	injuried.ambulance();
	injuried.getHospitalcall().treatmentadminstraition(injuried, simTime);
}

public void workeroutofWork(worker injuried) {
	Injuriedlist.add(injuried);
	wlist.remove((injuried.id)-1);	
}
public void workerAfterTreatment(worker AT) {
	if(!AT.isInjuired()) {
	Injuriedlist.remove(Injuriedlist.indexOf(AT));
	wlist.add(AT.getId()-1, AT);
	}
}
public void showWorkerList() {
	System.out.println("ID		Age		Checkup");
	for(int i=0; i<=noworkers-1; i++) {
		System.out.println(wlist.get(i).id+"		"+wlist.get(i).getAge()+"		"+wlist.get(i).getRegularcheckuprate());
	}
}
//public int avalibleMidkits() {
//	int noOfavailble=0;
//	for(int i=0; i<FirstAidkits.size(); i++) {
//		if(FirstAidkits.get(i).isAvailble()==true) {
//			noOfavailble+=1;
//		}
//	}
//	return noOfavailble;	
//}
//public int avalibleRooms() {
//	int noOfavailble=0;
//	for(int i=0; i<InfirmaryRooms.size(); i++) {
//		if(InfirmaryRooms.get(i).isAvailble()==true) {
//			noOfavailble+=1;
//		}
//	}
//	return noOfavailble;	
//}

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


public boolean isRegularcheckup() {
	return regularcheckup;
}
public void setRegularcheckup(boolean regularcheckup) {
	this.regularcheckup = regularcheckup;
}
public ArrayList<worker> getWlist() {
	return wlist;
}
public void setWlist(ArrayList<worker> wlist) {
	this.wlist = wlist;
}
public ArrayList<worker> getInjuriedlist() {
	return Injuriedlist;
}
public void setInjuriedlist(ArrayList<worker> injuriedlist) {
	Injuriedlist = injuriedlist;
}
public ArrayList<worker> getRetiredlist() {
	return retiredlist;
}
public void setRetiredlist(ArrayList<worker> retiredlist) {
	this.retiredlist = retiredlist;
}
public ArrayList<medicalintervention> getMedicalIntervention() {
	return MedicalIntervention;
}
public void setMedicalIntervention(ArrayList<medicalintervention> medicalIntervention) {
	MedicalIntervention = medicalIntervention;
}
@Override
public String toString() {
	return "factory [ID=" + String.format("%02d",ID) + "| noworkers=" + String.format("%03d",noworkers)  + "| avgcheckuprate="
			+ avgcheckuprate + "| avgage=" + avgage + "| noOfinjuried=" + TotalInjuries + "| timetohospital="
			+ timetohospital + "| First Responders = " + String.format("%02d", ((int)Math.round(((double)noFirstResponders/(double)noworkers)*100)))+"%" + "| regularcheckup=" + regularcheckup +"]";
}
//public int getNoMedKits() {
//	return noMedKits;
//}
//public void setNoMedKits(int noMedKits) {
//	this.noMedKits = noMedKits;
//}
//public int getNoInfirmaryRooms() {
//	return noInfirmaryRooms;
//}
//public void setNoInfirmaryRooms(int noInfirmaryRooms) {
//	this.noInfirmaryRooms = noInfirmaryRooms;
//}

}
