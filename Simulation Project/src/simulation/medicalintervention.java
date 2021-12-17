package simulation;


public abstract class medicalintervention implements medicaltreatmant {
boolean availble;
int timeTotreat;
int Rangeofcaplbility;
public boolean isAvailble() {
	return availble;
}
@Override
public void treatmentadminstraition(worker a, int simTime) {
	if(a.isInjuired()&&!a.isBeingTreated()&&isAvailble()&&a.getCurrent().getLvl()<=Rangeofcaplbility&&!a.isPermenatlyinjuried()) {
	timeTotreat=a.getHealingtime()+simTime;
	a.setWaitingForAmbulance(false);
	a.setBeingTreated(true);
	a.setTimetillpermenant(0);
	availble=false;
	}
}
@Override
public void finishedTreatment(worker a, int simTime) {
	if(simTime==timeTotreat&&a.isBeingTreated()&&a.isInjuired()&&!a.isPermenatlyinjuried()) {
		a.setBeingTreated(false);
		a.setCurrent(injuries.None);
		a.setHealingtime(0);
		a.treatmentcount+=1;
		a.setTimetillpermenant(0);
		a.setInjuired(false);
		a.setWaitingForAmbulance(false);
		availble=true;
		timeTotreat=0;
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

