package simulation;
import java.security.SecureRandom;
import java.util.ArrayList;
import simulation.injuries;
public class worker {

SecureRandom generator = new SecureRandom();
public int id;
public double age;
public boolean injuired;
public int regularcheckuprate;
public ArrayList<medicalcondition> conds = new ArrayList<medicalcondition>();
public int timetillpermenant;
public int healingtime;
public injuries current;


public worker(int id, double avg) {
	
	this.id=id;
	injuired=false;
	age=generator.nextGaussian()*5+avg;
	regularcheckuprate=generator.nextInt(7)+1;
	
}

public double getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public int getRegularcheckuprate() {
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
