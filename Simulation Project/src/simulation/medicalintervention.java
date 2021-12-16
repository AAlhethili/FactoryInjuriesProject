package simulation;


public abstract class medicalintervention implements medicaltreatmant {
boolean availble;
int timeTotreat;
int Rangeofcaplbility;
public boolean isAvailble() {
	return availble;
}

public void treatmentadminstraition(worker a, int simTime) {
	if(!a.isBeingTreated()&&isAvailble()&&a.getCurrent().lvl<=Rangeofcaplbility) {
	timeTotreat=a.getHealingtime()+simTime;
	a.setWaitingForAmbulance(false);
	availble=false;
	}
}

public void finishedTreatment(worker a, int simTime) {
	if(a.getHealingtime()==timeTotreat) {
		a.setBeingTreated(false);
		a.setCurrent(injuries.None);
		a.setHealingtime(0);
		a.setTimetillpermenant(0);
		a.setInjuired(false);
		availble=true;
	}
}
public void setAvailble(boolean availble) {
	this.availble = availble;
}
public int getTimeTotreat() {
	return timeTotreat;
}
public void setTimeTotreat(int timeTotreat) {
	this.timeTotreat = timeTotreat;
}
public int getRangeofcaplbility() {
	return Rangeofcaplbility;
}
public void setRangeofcaplbility(int rangeofcaplbility) {
	Rangeofcaplbility = rangeofcaplbility;
}

}

