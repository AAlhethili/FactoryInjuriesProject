package simulation;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;



public class worker {

SecureRandom generator = new SecureRandom();
public int id;
private int hat;
private String timeofinjury;
private int treatmentcount;
private int injurycount;
private int waitTime;
private int HospitalarraivalTime;
private int ArrivalTimenoCountdown;
private double age;
private double regularcheckuprate;
private boolean injuired;
private boolean calledHospital;
private boolean CheckedFirsAid;
private boolean usedAK;
private boolean usedSAK;
private boolean CheckedMedicalWorker;
private boolean HadMWnearby;
private boolean CheckedInfermary;
private boolean usedIR;
private boolean knowsHowtoUseMedikit;
private boolean Treated;
private boolean permenatlyinjuried;
private boolean fullyprogressed;
private int progressionRate;
private int peopleAround;
private boolean MidkitwhenInjuried;
private int progressionofInjury;
private injuries current;
private ArrayList<medicalcondition> conds = new ArrayList<medicalcondition>();
injuries[] injurytypes = injuries.values();

public worker() {
	
}

public worker(int id, double avg, double avgcheck) {
	
	this.id=id;
	treatmentcount=0;
	injurycount=0;
	progressionRate=0;
	progressionofInjury=0;
	HospitalarraivalTime=0;
	injuired=false;
	permenatlyinjuried=false;
	CheckedFirsAid=false;
	CheckedInfermary=false;
	CheckedMedicalWorker=false;
	calledHospital=false;
	fullyprogressed=false;
	if(generator.nextInt(50)==0) {
		knowsHowtoUseMedikit=true;
	}
	else {
		knowsHowtoUseMedikit=false;
	}
//	knowsHowtoUseMedikit=true;
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
	if((generator.nextInt(40))==0) {
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
	
	regularcheckuprate=avgcheck;
	setCurrent(injuries.None);
}	

public void addinjury(factory myfactory, String timeofinjury) {
	this.timeofinjury=timeofinjury;
	setTreated(false);
	setCalledHospital(false);
	setCheckedFirsAid(false);
	setCheckedInfermary(false);
	setCheckedMedicalWorker(false);
	setProgressionofInjury(0);
	setProgressionRate(0);
	current=injuries.None;
	int random=generator.nextInt(injurytypes.length);
	while(random==11) {
		random=generator.nextInt(injurytypes.length);
	}
	current=injurytypes[random];
	setInjuired(true);
	int CombinedProgressionRate=getCurrent().progressionfactor;
	for(int i =0; i<getConds().size();i++) {
		if(getConds().get(i).category==getCurrent().catogery) {
			CombinedProgressionRate+=(getConds().get(i).complication*2);
		}
		else {
			CombinedProgressionRate+=(getConds().get(i).complication);	
		}
	}
	CombinedProgressionRate-=regularCheckupTime(regularcheckuprate);	
	setProgressionRate(CombinedProgressionRate);
	if(generator.nextBoolean()) {
		peopleAround=myfactory.getDensity()+generator.nextInt(4);
	}
	else {
		peopleAround=myfactory.getDensity()-generator.nextInt(4);
		if(peopleAround<0) {peopleAround=0;}
	}
	MidkitwhenInjuried=generator.nextBoolean();
	injurycount+=1;

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
		TC=4;
		break;
	case 4:
		TC=6;
		break;
	}
	return TC;
}
public void FinishedTreatment(){

	setTreated(true);
	setInjuired(false);
}

public void progressInjury() {
	if(progressionofInjury<100) {
		progressionofInjury=progressionofInjury+progressionRate;
		if(progressionofInjury>=100) {
			progressionofInjury=100;
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
public void removeinjury() {
	current=injurytypes[9];
}
public void showStatus(factory myFactory) {
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getTreatmentcount() {
	return treatmentcount;
}

public void setTreatmentcount(int treatmentcount) {
	this.treatmentcount = treatmentcount;
}

public int getInjurycount() {
	return injurycount;
}

public void setInjurycount(int injurycount) {
	this.injurycount = injurycount;
}

public double getAge() {
	return age;
}

public void setAge(double age) {
	this.age = age;
}

public double getRegularcheckuprate() {
	return regularcheckuprate;
}

public void setRegularcheckuprate(double regularcheckuprate) {
	this.regularcheckuprate = regularcheckuprate;
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

public boolean isPermenatlyinjuried() {
	return permenatlyinjuried;
}

public void setPermenatlyinjuried(boolean permenatlyinjuried) {
	this.permenatlyinjuried = permenatlyinjuried;
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

public boolean isMidkitwhenInjuried() {
	return MidkitwhenInjuried;
}

public void setMidkitwhenInjuried(boolean midkitwhenInjuried) {
	MidkitwhenInjuried = midkitwhenInjuried;
}

public int getProgressionofInjury() {
	return progressionofInjury;
}

public void setProgressionofInjury(int progressionofInjury) {
	this.progressionofInjury = progressionofInjury;
}

public boolean isFullyprogressed() {
	return fullyprogressed;
}

public void setFullyprogressed(boolean fullyprogressed) {
	this.fullyprogressed = fullyprogressed;
}

public int getPeopleAround() {
	return peopleAround;
}

public void setPeopleAround(int peopleAround) {
	this.peopleAround = peopleAround;
}

public int getHospitalarraivalTime() {
	return HospitalarraivalTime;
}

public void setHospitalarraivalTime(int hospitalarraivalTime) {
	HospitalarraivalTime = hospitalarraivalTime;
}

public String getTimeofinjury() {
	return timeofinjury;
}

public void setTimeofinjury(String timeofinjury) {
	this.timeofinjury = timeofinjury;
}

public boolean isTreated() {
	return Treated;
}

public void setTreated(boolean treated) {
	Treated = treated;
}

public int getHat() {
	return hat;
}

public void setHat(int hat) {
	this.hat = hat;
}

public boolean isUsedAK() {
	return usedAK;
}

public void setUsedAK(boolean usedAK) {
	this.usedAK = usedAK;
}

public boolean isUsedSAK() {
	return usedSAK;
}

public void setUsedSAK(boolean usedSAK) {
	this.usedSAK = usedSAK;
}

public boolean isHadMWnearby() {
	return HadMWnearby;
}

public void setHadMWnearby(boolean hadMWnearby) {
	HadMWnearby = hadMWnearby;
}

public boolean isUsedIR() {
	return usedIR;
}

public void setUsedIR(boolean usedIR) {
	this.usedIR = usedIR;
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



}
