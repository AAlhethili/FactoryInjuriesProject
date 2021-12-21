package simulationPhase2;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;



public class worker {

SecureRandom generator = new SecureRandom();
public int id;
private int hospitalarraivalTimeCountdown;
private String timeOfInjury;
private int waitTime;
private int ArrivalTimenoCountdown;
private double age;
private double regularCheckUprate;
private boolean injuired;
private boolean medicalWorkerAvailable;
private boolean calledHospital;
private boolean progress;
private boolean CheckedFirsAid;
private boolean usedAidKit;
private boolean usedSmartAidKit;
private boolean CheckedMedicalWorker;
private boolean tratedByMedicalWorker;
private boolean CheckedInfermary;
private boolean treatdByInfermaryRoom;
private boolean knowsHowtoUseMedikit;
private boolean Treated;
private boolean permenatlyInjured;
private boolean injuryFullyProgressed;
private int progressionRate;
private int peopleAround;
private int progressionOfInjury;
private injuries current;
private ArrayList<medicalcondition> conds = new ArrayList<medicalcondition>();
injuries[] injurytypes = injuries.values();

public worker() {
	
}

public worker(int id, double avg, double avgCheckUpRate) {
	
	this.id=id;
	progressionRate=0;
	progressionOfInjury=0;
	hospitalarraivalTimeCountdown=0;
	injuired=false;
	setProgress(false);
	permenatlyInjured=false;
	CheckedFirsAid=false;
	CheckedInfermary=false;
	CheckedMedicalWorker=false;
	calledHospital=false;
	injuryFullyProgressed=false;
	if(generator.nextInt(10)==0) {
		knowsHowtoUseMedikit=true;
	}
	else {
		knowsHowtoUseMedikit=false;
	}
	age=generator.nextGaussian()*10+avg;
	while(age<20||age>70) {
		age=generator.nextGaussian()*10+avg;
	}
	age=Math.round(age);
	conds.add(medicalcondition.none);
	int random = generator.nextInt(3)+1;
	int random2 = generator.nextInt(3)+1;
	if(age>=50) {
		conds.clear();
		switch (random){
		case 1:
			conds.add(medicalcondition.skinissues);
			break;
		case 2:
			conds.add(medicalcondition.heartissues);
			break;
		case 3:
			conds.add(medicalcondition.respitoryissue);
			break;
		}
	}
	if((generator.nextInt(10))==0) {
		if(age<50) {
			conds.clear();
		}
		switch (random2){
		case 1:
			conds.add(medicalcondition.baddiet);
			break;
		case 2:
			conds.add(medicalcondition.obesity);
			break;
		case 3:
			conds.add(medicalcondition.deficincy);
			break;
		}
	}
	
	regularCheckUprate=avgCheckUpRate;
	setCurrent(injuries.None);
}	

public void addinjury(factory myfactory, String timeofinjury) {
	this.timeOfInjury=timeofinjury;
	setProgress(false);
	setTreated(false);
	setUsedAidKit(false);
	setUsedSmartAidKit(false);
	setTreatdByInfermaryRoom(false);
	setTratedByMedicalWorker(false);
	setCalledHospital(false);
	setCheckedFirsAid(false);
	setCheckedInfermary(false);
	setCheckedMedicalWorker(false);
	setProgressionofInjury(0);
	setProgressionRate(0);
	setHospitalarraivalTimeCountdown(-1);
	current=injuries.None;
	int random=generator.nextInt(injurytypes.length);
	while(random==11) {
		random=generator.nextInt(injurytypes.length);
	}
	current=injurytypes[random];
	myfactory.counterInjuryLevel+=current.level;
	setInjuired(true);
	int CombinedProgressionRate=getCurrent().progressionfactor;
	for(int i =0; i<getConds().size();i++) {
		if(getConds().get(i).category==getCurrent().catogery) {
			CombinedProgressionRate+=(getConds().get(i).complication+3);
		}
		else {
			CombinedProgressionRate+=(getConds().get(i).complication);	
		}
	}
	CombinedProgressionRate-=regularCheckupTime(regularCheckUprate);	
	setProgressionRate(CombinedProgressionRate);
	myfactory.counterInitialProgressionOfInjury+=CombinedProgressionRate;
	if(generator.nextBoolean()) {
		
		peopleAround=myfactory.getDensity()+generator.nextInt(4);
	}
	else {
		
		peopleAround=myfactory.getDensity()-generator.nextInt(4);
		if(peopleAround<0) {peopleAround=0;}
		
	}
	myfactory.counterNoOfWorkerWhenInjuryHappened+=peopleAround;
	
	myfactory.counterInjuries+=1;
	
}
public int regularCheckupTime(double freaquency) {
	int TC=0;
	switch((int)Math.round(freaquency)) {
	case 0:
		TC=0;
		break;
	case 1:
		TC=1;	
		break;
	case 2:
		TC=2;
		break;
	case 3:
		TC=3;
		break;
	case 4:
		TC=4;
		break;
	}
	return TC;
}
public void FinishedTreatment(){
	setTreated(true);
	setInjuired(false);
}

public void progressInjury() {
	if(progressionOfInjury<0) {
		progressionOfInjury=0;
	}
	if(progressionOfInjury<100) {
		progressionOfInjury=progressionOfInjury+progressionRate;
		if(progressionOfInjury>=100) {
			progressionOfInjury=100;
		}
		}
}
public String condsNames(){
	String[] namesStrings= new String[conds.size()]; 
	for(int i =0; i<conds.size(); i++) {
			namesStrings[i]=conds.get(i).name();
	}
	String names = Arrays.stream(namesStrings).collect(Collectors.joining(","));
	return names;
}


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public double getAge() {
	return age;
}

public void setAge(double age) {
	this.age = age;
}

public double getRegularCheckUprate() {
	return regularCheckUprate;
}

public void setRegularcheckuprate(int regularcheckuprate) {
	this.regularCheckUprate = regularcheckuprate;
}

public boolean isInjuired() {
	return injuired;
}

public void setInjuired(boolean injuired) {
	this.injuired = injuired;
}

public boolean isCheckedFirsAid() {
	return CheckedFirsAid;
}

public void setCheckedFirsAid(boolean checkedFirsAid) {
	CheckedFirsAid = checkedFirsAid;
}

public boolean isCheckedMedicalWorker() {
	return CheckedMedicalWorker;
}

public void setCheckedMedicalWorker(boolean checkedMedicalWorker) {
	CheckedMedicalWorker = checkedMedicalWorker;
}

public boolean isCheckedInfermary() {
	return CheckedInfermary;
}

public void setCheckedInfermary(boolean checkedInfermary) {
	CheckedInfermary = checkedInfermary;
}

public boolean isKnowsHowtoUseMedikit() {
	return knowsHowtoUseMedikit;
}

public void setKnowsHowtoUseMedikit(boolean knowsHowtoUseMedikit) {
	this.knowsHowtoUseMedikit = knowsHowtoUseMedikit;
}

public boolean isPermenatlyInjured() {
	return permenatlyInjured;
}

public void setPermenatlyInjured(boolean permenatlyinjuried) {
	this.permenatlyInjured = permenatlyinjuried;
}

public int getProgressionRate() {
	return progressionRate;
}

public void setProgressionRate(int progressionRate) {
	this.progressionRate = progressionRate;
}

public injuries getCurrent() {
	return current;
}

public void setCurrent(injuries current) {
	this.current = current;
}

public ArrayList<medicalcondition> getConds() {
	return conds;
}

public void setConds(ArrayList<medicalcondition> conds) {
	this.conds = conds;
}

public int getProgressionofInjury() {
	return progressionOfInjury;
}

public void setProgressionofInjury(int progressionofInjury) {
	this.progressionOfInjury = progressionofInjury;
}

public boolean isInjuryFullyProgressed() {
	return injuryFullyProgressed;
}

public void setInjuryFullyProgressed(boolean fullyprogressed) {
	this.injuryFullyProgressed = fullyprogressed;
}

public int getPeopleAround() {
	return peopleAround;
}

public void setPeopleAround(int peopleAround) {
	this.peopleAround = peopleAround;
}

public String getTimeOfInjury() {
	return timeOfInjury;
}

public void setTimeOfInjury(String timeofinjury) {
	this.timeOfInjury = timeofinjury;
}

public boolean isTreated() {
	return Treated;
}

public void setTreated(boolean treated) {
	Treated = treated;
}

public int getHospitalarraivalTimeCountdown() {
	return hospitalarraivalTimeCountdown;
}

public void setHospitalarraivalTimeCountdown(int hat) {
	this.hospitalarraivalTimeCountdown = hat;
}

public boolean isUsedAidKit() {
	return usedAidKit;
}

public void setUsedAidKit(boolean usedAK) {
	this.usedAidKit = usedAK;
}

public boolean isUsedSmartAidKit() {
	return usedSmartAidKit;
}

public void setUsedSmartAidKit(boolean usedSAK) {
	this.usedSmartAidKit = usedSAK;
}

public boolean isTratedByMedicalWorker() {
	return tratedByMedicalWorker;
}

public void setTratedByMedicalWorker(boolean hadMWnearby) {
	tratedByMedicalWorker = hadMWnearby;
}

public boolean isTreatdByInfermaryRoom() {
	return treatdByInfermaryRoom;
}

public void setTreatdByInfermaryRoom(boolean usedIR) {
	this.treatdByInfermaryRoom = usedIR;
}

public int getArrivalTimenoCountdown() {
	return ArrivalTimenoCountdown;
}

public void setArrivalTimenoCountdown(int arrivalTimenoCountdown) {
	ArrivalTimenoCountdown = arrivalTimenoCountdown;
}

public int getWaitTime() {
	return waitTime;
}

public void setWaitTime(int waitTime) {
	this.waitTime = waitTime;
}

public boolean isCalledHospital() {
	return calledHospital;
}

public void setCalledHospital(boolean calledHospital) {
	this.calledHospital = calledHospital;
}

public boolean isProgress() {
	return progress;
}

public void setProgress(boolean progress) {
	this.progress = progress;
}

public boolean isMedicalWorkerAvailable() {
	return medicalWorkerAvailable;
}

public void setMedicalWorkerAvailable(boolean medicalWorkerAvailable) {
	this.medicalWorkerAvailable = medicalWorkerAvailable;
}



}
