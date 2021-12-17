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
private hospital factorytoHosptial;
private int timetohospital;
private int Dailyinjuried;
private int TotalInjuries;
private int DialynoOfPermanantInjuried;
private int TotalnoOfPermanantInjuried;

public ArrayList<worker> wlist = new ArrayList<worker>();
public ArrayList<medicallytrainedworker> FirstResponders = new ArrayList<medicallytrainedworker>();
public ArrayList<worker> Availablelist = new ArrayList<worker>();
public ArrayList<worker> Injuriedlist = new ArrayList<worker>();
public ArrayList<worker> retiredlist = new ArrayList<worker>();
public ArrayList<medicalintervention> MedicalIntervention = new ArrayList<medicalintervention>();
//public ArrayList<medicalintervention> FirstAidkits = new ArrayList<medicalintervention>();
//public ArrayList<medicalintervention> InfirmaryRooms = new ArrayList<medicalintervention>();

public factory(int iD) {

	ID = iD;
	factorytoHosptial=new hospital();
	avg = generator.nextInt(36)+25;
	avgcheckup= 4;
	regularcheckup = false;
	createworkers(generator.nextInt(1501)+500);
	noworkers=wlist.size();
	createFirstAidKits((int)Math.round((double)noworkers/200));
	createRooms((int)Math.round((double)noworkers/300));
	timetohospital = Math.round((generator.nextInt(51)+10)/10)*10;
	avgage=Math.round(calculateAvgAge());
	avgcheckuprate=Math.round(calculateAvgcheckup());
	for(int i=0; i<=noworkers-1; i++) {
		if(wlist.get(i) instanceof medicallytrainedworker) {
			FirstResponders.add((medicallytrainedworker) wlist.get(i));
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
public void calculateNumofInjuries() {

	for(int i=0; i<=noworkers-1; i++) {
		TotalInjuries+=wlist.get(i).injurycount;
	}
}
public void calculateNumofPermaInjuries() {

	for(int i=0; i<=noworkers-1; i++) {
		if(wlist.get(i).isPermenatlyinjuried())
			TotalnoOfPermanantInjuried+=1;
		;
	}
}
public worker chooseRandomworker(){
	worker randomSelect = wlist.get(generator.nextInt(wlist.size()));
		randomSelect = wlist.get(generator.nextInt(wlist.size()));	
	return randomSelect;
	
}
public void treatWorker(worker injuried, int simTime) {
//	if (injuried.getCurrent().getLvl()==2) {
//		for(int i=0; i<FirstResponders.size();i++) {
//				if(!FirstResponders.get(i).isInjuired()) {
//				FirstResponders.get(i).treatmentadminstraition(injuried, simTime);}
//			}
//				
//		}
//	
//	else {
		for(int i=0; i<MedicalIntervention.size();i++) {
			MedicalIntervention.get(i).treatmentadminstraition(injuried, simTime);
			MedicalIntervention.get(i).finishedTreatment(injuried, simTime);
		}
//	}
		factorytoHosptial.treatmentadminstraition(injuried);
		factorytoHosptial.finishedTreatment(injuried, simTime);
}

//public void workeroutofWork(worker injuried) {
//	Injuriedlist.add(injuried);
//	wlist.remove(wlist.indexOf(injuried));
//}
public void workerAfterTreatment(worker AT) {
	if(!AT.isInjuired()) {
	Injuriedlist.remove(Injuriedlist.indexOf(AT));
	wlist.add(AT);
	}
}
public void showWorkerList() {
	System.out.println("ID		Age		Checkup");
	for(int i=0; i<=noworkers-1; i++) {
		System.out.println(wlist.get(i).id+"		"+wlist.get(i).getAge()+"		"+wlist.get(i).getRegularcheckuprate());
	}
}
public void showPermalisList() {
//	System.out.println("Factory: "+getID()+" Workers number: "+getNoworkers());
	calculateNumofInjuries();
	calculateNumofPermaInjuries();
	System.out.println(toString());
//	System.out.println("ID		Inuries		Treated		Permenant		inury		Treatment");
//	for(int i=0; i<=wlist.size()-1; i++) {
//		System.out.println(wlist.get(i).id+"		"+wlist.get(i).injurycount+"		"+wlist.get(i).treatmentcount+"		"+wlist.get(i).isPermenatlyinjuried()+"		"+wlist.get(i).isInjuired()+"		"+wlist.get(i).isBeingTreated());
//		}
	}
public void showList() {
//	System.out.println("Factory: "+getID()+" Workers number: "+getNoworkers());
	System.out.println(toString());
	System.out.println("ID		Inuries		Treated		Permenant		inury		Treatment");
	for(int i=0; i<=wlist.size()-1; i++) {
		System.out.println(wlist.get(i).id+"		"+wlist.get(i).injurycount+"		"+wlist.get(i).treatmentcount+"		"+wlist.get(i).isPermenatlyinjuried()+"		"+wlist.get(i).isInjuired()+"		"+wlist.get(i).isBeingTreated());
		}
	}
public boolean anyoneHealthy() {
	boolean isTrue=false;
	for(int i=0; i<wlist.size(); i++) {
		if(wlist.get(i).isInjuired()==false) {
			isTrue = true;
		}
	}
	return isTrue;
}
//	System.out.println("ID		Age		Permenant");
//	for(int i=0; i<=wlist.size()-1; i++) {
//		if(wlist.get(i).isPermenatlyinjuried()) {
//			retiredlist.add(wlist.get(i));
//		}
//	}
//	for(int i=0; i<=retiredlist.size()-1; i++) {
//		System.out.println(retiredlist.get(i).id+"		"+retiredlist.get(i).getAge()+"		"+retiredlist.get(i).isPermenatlyinjuried());
//	}
//}
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

public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
//@Override
//public String toString() {
//	return "factory [ID=" + String.format("%02d",ID) + "| noworkers=" + String.format("%03d",noworkers)  + "| avgcheckuprate="
//			+ avgcheckuprate + "| avgage=" + avgage + "| noOfinjuried=" + TotalInjuries + "| timetohospital="
//			+ timetohospital + "| First Responders = " + String.format("%02d", ((int)Math.round(((double)noFirstResponders/(double)noworkers)*100)))+"%" + "| regularcheckup=" + regularcheckup +"]";
//}
@Override
public String toString() {
	return "factory [ID=" + ID + ", noworkers=" + noworkers + ", noFirstResponders=" + noFirstResponders
			+ ", regularcheckup=" + regularcheckup + ", avgcheckuprate=" + avgcheckuprate + ", avgage=" + avgage
			+ ", TotalInjuries="+ TotalInjuries + ", TimeTohospital=" + factorytoHosptial.ArrivalTime  + ", TotalnoOfPermanantInjuried=" + TotalnoOfPermanantInjuried + "]";
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
