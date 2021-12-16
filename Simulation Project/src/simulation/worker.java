package simulation;
import java.security.SecureRandom;
import simulation.medicalcondition;
import java.util.ArrayList;
import simulation.injuries;


public class worker {

SecureRandom generator = new SecureRandom();
public int id;
private double age;
private double regularcheckuprate;
private boolean injuired;
private boolean beingTreated;
private boolean waitingForAmbulance;
private medicalintervention hospitalcall = new hospital();
private int timetillpermenant;
private boolean permenatlyinjuried;
private int healingtime;
private injuries current;
private medicalcondition conds;
injuries[] injurytypes = injuries.values();



public worker(int id, double avg, double avgcheck) {
	
	this.id=id;
	injuired=false;
	permenatlyinjuried=false;
	age=generator.nextGaussian()*10+avg;
	while(age<20||age>70) {
		age=generator.nextGaussian()*10+avg;
	}
	age=Math.round(age);
	conds=medicalcondition.none;
	int random = generator.nextInt(3)+1;
	if(age>=50) {
		switch (random){
		case 1:
			conds=(medicalcondition.cancer);
			break;
		case 2:
			conds=(medicalcondition.respitoryissue);
			break;
		case 3:
			conds=(medicalcondition.deficincy);
			break;
		}
		if(age<50&&(generator.nextInt(10))==0) {
			switch (random){
			case 1:
				conds=medicalcondition.baddiet;
				break;
			case 2:
				conds=medicalcondition.obesity;
				break;
			case 3:
				conds=medicalcondition.deficincy;
				break;
			}
		}
	}
	
	regularcheckuprate=avgcheck;
	setCurrent(injuries.None);
}	

public void addinjury() {
	int random=generator.nextInt(injurytypes.length-1);
	while(random==9) {
		random=generator.nextInt(injurytypes.length-1);
	}
	current=injurytypes[random];
	setInjuired(true);
	int totalRecoverytime=current.getHealingtime()+conds.getComplication()+regularCheckupTime(regularcheckuprate);
	setHealingtime(totalRecoverytime);
	
}
public int regularCheckupTime(double freaquency) {
	int TC=0;
	switch((int)Math.round(freaquency)) {
	case 0:
		TC=40;
		break;
	case 1:
		TC=30;	
		break;
	case 2:
		TC=20;
		break;
	case 3:
		TC=10;
		break;
	case 4:
		TC=0;
		break;
	}
	return TC;
}
public void removeinjury() {
	current=injurytypes[9];
}
public void ambulance() {
	if(!isWaitingForAmbulance()) {
		hospitalcall= new hospital();
	}
}

public double getAge() {
	return age;
}


public void setAge(int age) {
	this.age = age;
}

public double getRegularcheckuprate() {
	return regularcheckuprate;
}

public void setRegularcheckuprate(double regularcheckuprate) {
	this.regularcheckuprate = regularcheckuprate;
}

public injuries getCurrent() {
	return current;
}

public void setCurrent(injuries current) {
	this.current = current;
}


public boolean isInjuired() {
	return injuired;
}


public void setInjuired(boolean injuired) {
	this.injuired = injuired;
}


public int getTimetillpermenant() {
	return timetillpermenant;
}


public void setTimetillpermenant(int timetillpermenant) {
	this.timetillpermenant = timetillpermenant;
}


public int getHealingtime() {
	return healingtime;
}


public void setHealingtime(int healingtime) {
	this.healingtime = healingtime;
}


public medicalcondition getConds() {
	return conds;
}


public void setConds(medicalcondition conds) {
	this.conds = conds;
}

public boolean isBeingTreated() {
	return beingTreated;
}

public void setBeingTreated(boolean beingTreated) {
	this.beingTreated = beingTreated;
}

public boolean isWaitingForAmbulance() {
	return waitingForAmbulance;
}

public void setWaitingForAmbulance(boolean waitingForAmbulance) {
	this.waitingForAmbulance = waitingForAmbulance;
}

public boolean isPermenatlyinjuried() {
	return permenatlyinjuried;
}

public void setPermenatlyinjuried(boolean permenatlyinjuried) {
	this.permenatlyinjuried = permenatlyinjuried;
}

public medicalintervention getHospitalcall() {
	return hospitalcall;
}

public void setHospitalcall(medicalintervention hospitalcall) {
	this.hospitalcall = hospitalcall;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}


}
