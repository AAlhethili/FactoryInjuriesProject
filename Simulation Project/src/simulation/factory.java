package simulation;
import java.util.ArrayList;
import java.security.SecureRandom;


public class factory {
	
SecureRandom generator = new SecureRandom();
ToolsofStrings tof = new ToolsofStrings();
private int ID;
private int noworkers;
private int noFirstResponders;
private int density;
private boolean regularcheckup;
private boolean HasSmartFirstAidkit;
private int avgcheckup;
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
public ArrayList<medicalintervention> MedicalIntervention = new ArrayList<medicalintervention>();
private infirmaryroom factoryinfermary;
private medicalintervention factoryKit;


public factory(int iD) {

	ID = iD;
	factorytoHosptial=new hospital();
	avg = generator.nextInt(36)+25;
	avgcheckup= generator.nextInt(4)+1;
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
public boolean checksmartkit() {
	for(int i=0; i<MedicalIntervention.size();i++) {
		if (MedicalIntervention.get(i) instanceof smartFirstAidkit) {
			return true;
		}
	}
	return false;
}
public boolean checkkit() {
	for(int i=0; i<MedicalIntervention.size();i++) {
		if (MedicalIntervention.get(i) instanceof FirstAidKit) {
			return true;
		}
	}
	return false;
}
public void createworkers(int noworkers) {
	
	for(int i=1; i<=noworkers; i++) {
		if(generator.nextInt(10)==0) {
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
			
			double above0 = generator.nextGaussian()*1;
			while(above0<0||above0>4)
			{
			above0 = generator.nextGaussian()*1;	
			}
			wlist.get(i).setRegularcheckuprate((int) Math.round(above0));
		}
	}
		
}
public void createRooms() {
	
		infirmaryroom room = new infirmaryroom();
		factoryinfermary=room;
		MedicalIntervention.add(room);
}
public void createFirstAidKits() {
		if(generator.nextInt(5)==0) {
			medicalintervention smartkit = new smartFirstAidkit();
			factoryKit=smartkit;
			MedicalIntervention.add(smartkit);
		}
		else {
			FirstAidKit kit = new FirstAidKit();
			factoryKit=kit;
			MedicalIntervention.add(kit);
		}
	
		
}
public void showFactoryInfo(int Days, int hours) {
	calculateNumofPermaInjuries();
	System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
	System.out.println("Factory number ["+getID()+"] with worker population of ["+noworkers+"] Had a total of ["+getTotalnoOfPermanantInjuried()+"] Permenant Injuries during the duration of ["+ Days +"] Days\n");
	System.out.println("The factory"+tof.doesor(checksmartkit())+"implemnt Smart Aid kits and had ["+noFirstResponders+"] Medically trained workers and ["+CalculatWhocanuseMK()+"] Who can use First Aid Kits\n" );
	System.out.println("The factory had a maximum injury of ["+MaxProgressedInjury+"%] and minumum injury of ["+MinProgressedInjury+"%]\n");
	System.out.println("The factory had a Infirmary capleability of ["+factoryinfermary.getRangeofcaplbility()+"] out of 6\n");
	System.out.println("Most workers were around age ["+String.format("%.2f", calculateAvgAge())+"] and the average time they do regular health checkup is ["+String.format("%.2f", calculateAvgcheckup())+"] times every month");
	System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
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
public int CalculatWhocanuseMK() {
	int total=0;
	for(int i=0; i<=noworkers-1; i++) {
		if(wlist.get(i).isKnowsHowtoUseMedikit())
			total++;
	}
	return total;
}
public worker chooseRandomworker(){
	worker randomSelect = wlist.get(generator.nextInt(wlist.size()));
		randomSelect = wlist.get(generator.nextInt(wlist.size()));	
		if(randomSelect.isPermenatlyinjuried()||randomSelect.isInjuired()) {
			randomSelect = wlist.get(generator.nextInt(wlist.size()));	
		}
	return randomSelect;
	
}
public void LoopTreating(int simTime,int simDay) {
	for(int i = 0; i<getInjuriedlist().size(); i++) {
	if(!getInjuriedlist().get(i).isInjuired()||getInjuriedlist().get(i).isPermenatlyinjuried()){
		getInjuriedlist().remove(getInjuriedlist().get(i));}
	if(!Injuriedlist.isEmpty()) {
	for(int j = 0; j<getInjuriedlist().size(); j++) {
		treatWorker(getInjuriedlist().get(j),simDay, simTime);
		}
	}
	}
}
public void treatWorker(worker injuried,int simDay, int simTime) {
	if (injuried.isMidkitwhenInjuried()) {
		for(int i=0; i<MedicalIntervention.size();i++) {
			if (!(MedicalIntervention.get(i) instanceof smartFirstAidkit)&&!(MedicalIntervention.get(i) instanceof infirmaryroom)) {
				MedicalIntervention.get(i).treatmentadminstraition(injuried,simDay, simTime, this);
			}
			if (MedicalIntervention.get(i) instanceof smartFirstAidkit) {
				MedicalIntervention.get(i).treatmentadminstraition(injuried,simDay, simTime, this);
			}
		}
	}
		MedicalWorker.treatmentadminstraition(injuried,simDay, simTime, this);
		
			for(int i=0; i<MedicalIntervention.size();i++) {
				if (MedicalIntervention.get(i) instanceof infirmaryroom) {
					MedicalIntervention.get(i).treatmentadminstraition(injuried,simDay, simTime, this);
				}
			}
			
		factorytoHosptial.treatmentadminstraition(injuried,simDay, simTime, this);		
//		printInjuried(simTime);
				

	

}

public void showWorkerList() {
	System.out.println("ID		Age		Checkup");
	for(int i=0; i<=noworkers-1; i++) {
		System.out.println(wlist.get(i).id+"		"+wlist.get(i).getAge()+"		"+wlist.get(i).getRegularcheckuprate());
	}
}
public void printInjuried(int simAlltime, int simDay) {
	for(int in=0; in<getInjuriedlist().size();in++) {
		if(simAlltime%simDay!=0) {
//		if(selectedFactory.getInjuriedlist().get(in).hat==0) {
//			
//
//		}
		if(!getInjuriedlist().get(in).isTreated()) {
			System.out.printf("%-10d|%-9d|%-3d|%-16s|%18s|%-14s|%-16d|%-11d|%-2d %-17s|\n", ID, getInjuriedlist().get(in).getId(),(int)getInjuriedlist().get(in).getAge(),getInjuriedlist().get(in).condsNames(), getInjuriedlist().get(in).getCurrent().getName(),getInjuriedlist().get(in).getTimeofinjury(),getInjuriedlist().get(in).getProgressionRate(),getInjuriedlist().get(in).getProgressionofInjury(),getInjuriedlist().get(in).getHat(),"minutes" );
		}
		}
		
	}
}

public void injuryandtreatment(worker injuried,int simDay, int simAlltime, int simMaxtime, String timeofinjury) {
	if(simAlltime!=simMaxtime) {
	if(generator.nextInt((10-getDensity())*10)==0) {
		if(generator.nextInt((10-getDensity())*3)==0) {
			if(getInjuriedlist().isEmpty()) {
				for(int k = 0; k<generator.nextInt((getDensity())+1); k++) {
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
			LoopTreating(simAlltime,simDay);
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

}
