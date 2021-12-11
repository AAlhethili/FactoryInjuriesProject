package simulation;
import java.security.SecureRandom;
import java.util.ArrayList;
import simulation.injuries;
public class worker {
SecureRandom generator = new SecureRandom();
public int id;
public int age;
public int regularcheckuprate;
public ArrayList<medicalcondition> conds = new ArrayList<medicalcondition>();
public injuries current;


public worker(int id) {
	
	this.id=id;
	age=generator.nextInt(36)+25;
	regularcheckuprate=generator.nextInt(7)+1;
	
}

public int getAge() {
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
