package simulation;
import java.util.ArrayList;
import java.security.SecureRandom;


public class factory {
	
SecureRandom generator = new SecureRandom();
public int ID;
private int noworkers;
private int noFirstResponders;
private int density;
private boolean regularcheckup;
private boolean HasSmartFirstAidkit;
private double avgcheckup;
private double avgcheckuprate;
private double avg;
private double avgage;
private hospital factorytoHosptial;
private medicallytrainedworker MedicalWorker = new medicallytrainedworker();
private int timetohospital;
private int MaxProgressedInjury;
private int MinProgressedInjury;
private int TotalInjuries;
private int TotalnoOfPermanantInjuried;
public ArrayList<worker> wlist = new ArrayList<worker>();
public ArrayList<medicallytrainedworker> FirstResponders = new ArrayList<medicallytrainedworker>();
public ArrayList<worker> Injuriedlist = new ArrayList<worker>();
public ArrayList<worker> retiredlist = new ArrayList<worker>();
public ArrayList<medicalintervention> MedicalIntervention = new ArrayList<medicalintervention>();


public factory(int iD) {

	ID = iD;
	factorytoHosptial=new hospital();
	avg = generator.nextInt(36)+25;
	avgcheckup= 4;
	regularcheckup = false;
	MaxProgressedInjury=0;
	MinProgressedInjury=999999;
	createworkers(generator.nextInt(401)+100);
	noworkers=wlist.size();
	createFirstAidKits();
	createRooms();
	density=generator.nextInt(6)+1;
	timetohospital = (generator.nextInt(2)+1)*10;
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
		if(generator.nextInt(100)==0) {
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
public void createRooms() {
	
		infirmaryroom room = new infirmaryroom();
		MedicalIntervention.add(room);
}
public void createFirstAidKits() {
		HasSmartFirstAidkit=false;
		if(generator.nextInt(100)==0 /*true*/) {
			medicalintervention smartkit = new smartFirstAidkit();
			MedicalIntervention.add(smartkit);
			HasSmartFirstAidkit=true;
		}
		else {
			FirstAidKit kit = new FirstAidKit();
			MedicalIntervention.add(kit);
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
		if(randomSelect.isPermenatlyinjuried()||randomSelect.isInjuired()) {
			randomSelect = wlist.get(generator.nextInt(wlist.size()));	
		}
	return randomSelect;
	
}
public void LoopTreating(int simTime) {
	for(int i = 0; i<getInjuriedlist().size(); i++) {
	if(!getInjuriedlist().get(i).isInjuired()||getInjuriedlist().get(i).isPermenatlyinjuried()){
		getInjuriedlist().remove(getInjuriedlist().get(i));}
	if(!Injuriedlist.isEmpty()) {
	for(int j = 0; j<getInjuriedlist().size(); j++) {
		treatWorker(getInjuriedlist().get(j), simTime);
		}
	}
	}
}
public void treatWorker(worker injuried, int simTime) {
	if (injuried.isMidkitwhenInjuried()) {
		for(int i=0; i<MedicalIntervention.size();i++) {
			if (MedicalIntervention.get(i) instanceof FirstAidKit) {
				MedicalIntervention.get(i).treatmentadminstraition(injuried, simTime, this);
			}
			if (MedicalIntervention.get(i) instanceof smartFirstAidkit) {
				MedicalIntervention.get(i).treatmentadminstraition(injuried, simTime, this);
			}
		}
	}
			for(int i=0; i<MedicalIntervention.size();i++) {
				if (MedicalIntervention.get(i) instanceof infirmaryroom) {
					MedicalIntervention.get(i).treatmentadminstraition(injuried, simTime, this);
				}
			}
			
			MedicalWorker.treatmentadminstraition(injuried, simTime, this);
		
		factorytoHosptial.treatmentadminstraition(injuried, simTime, this);		
//		printInjuried(simTime);
				

	

}

public void showWorkerList() {
	System.out.println("ID		Age		Checkup");
	for(int i=0; i<=noworkers-1; i++) {
		System.out.println(wlist.get(i).id+"		"+wlist.get(i).getAge()+"		"+wlist.get(i).getRegularcheckuprate());
	}
}
public void printInjuried(int simAlltime) {
	for(int in=0; in<getInjuriedlist().size();in++) {
		if(simAlltime%480!=0) {
//		if(selectedFactory.getInjuriedlist().get(in).hat==0) {
//			
//
//		}
		if(!getInjuriedlist().get(in).isTreated()) {
			System.out.printf("%-10d|%-4d|%-2d|%-16s|%18s|%-14s|%-16d|%-19d|%-2d %-24s|\n", ID, getInjuriedlist().get(in).getId(),(int)getInjuriedlist().get(in).getAge(),getInjuriedlist().get(in).condsNames(), getInjuriedlist().get(in).getCurrent().getName(),getInjuriedlist().get(in).getTimeofinjury(),getInjuriedlist().get(in).getProgressionRate(),getInjuriedlist().get(in).getProgressionofInjury(),getInjuriedlist().get(in).getHat(),"minutes" );
		}
		}
		
	}
}

public void injuryandtreatment(worker injuried, int simAlltime, int simMaxtime, String timeofinjury) {
	if(simAlltime!=simMaxtime) {
	if(generator.nextInt((10-getDensity())*3)==0) {
		if(generator.nextInt((10-getDensity())*3)==0) {
			if(getInjuriedlist().isEmpty()) {
				for(int k = 0; k<generator.nextInt((getDensity())); k++) {
				injuried=chooseRandomworker();
					injuried.addinjury(this, timeofinjury);
					Injuriedlist.add(injuried);
					}
			}
			
		}
		else {
		if(getInjuriedlist().isEmpty()) {
				injuried=chooseRandomworker();
					injuried.addinjury(this, timeofinjury);
					Injuriedlist.add(injuried);
			}
		}
	}
}
		if(!Injuriedlist.isEmpty()) {
			LoopTreating(simAlltime);
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
public int getTotalInjuries() {
	return TotalInjuries;
}
public void setTotalInjuries(int totalInjuries) {
	TotalInjuries = totalInjuries;
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

public boolean isHasSmartFirstAidkit() {
	return HasSmartFirstAidkit;
}
public void setHasSmartFirstAidkit(boolean hasSmartFirstAidkit) {
	HasSmartFirstAidkit = hasSmartFirstAidkit;
}
public int getDensity() {
	return density;
}
public void setDensity(int density) {
	this.density = density;
}
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public int getMaxProgressedInjury() {
	return MaxProgressedInjury;
}
public void setMaxProgressedInjury(int maxProgressedInjury) {
	MaxProgressedInjury = maxProgressedInjury;
}
public int getMinProgressedInjury() {
	return MinProgressedInjury;
}
public void setMinProgressedInjury(int minProgressedInjury) {
	MinProgressedInjury = minProgressedInjury;
}
public static String padRight(String s, int n) {
    return String.format("%-" + n + "s", s);  
}

public static String padLeft(String s, int n) {
   return String.format("%" + n + "s", s);  
}
}
