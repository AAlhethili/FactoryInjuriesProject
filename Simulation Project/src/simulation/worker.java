package simulation;
import java.security.SecureRandom;
import simulation.medicalcondition;
import java.util.ArrayList;
import simulation.injuries;
public class worker {

SecureRandom generator = new SecureRandom();
public int id;
public double age;
public double regularcheckuprate;
public boolean injuired;
public int timetillpermenant;
public int healingtime;
public injuries current;
public medicalcondition conds;



public worker(int id, double avg, double avgcheck) {
	
	this.id=id;
	injuired=false;
	age=generator.nextGaussian()*10+avg;
	while(age<20||age>70) {
		age=generator.nextGaussian()*10+avg;
	}
	age=Math.round(age);
	
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
		if(age<50&&(generator.nextInt(10)+1)==1) {
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

public void setRegularcheckuprate(int regularcheckuprate) {
	this.regularcheckuprate = regularcheckuprate;
}

public injuries getCurrent() {
	return current;
}

public void setCurrent(injuries current) {
	this.current = current;
}


}
