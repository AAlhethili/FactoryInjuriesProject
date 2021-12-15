package simulation;


public abstract class medicalintervention implements medicaltreatmant {
boolean availble;
int timeTotreat;
int Rangeofcaplbility;
public boolean isAvailble() {
	return availble;
}

public void treatmentadminstraition(worker a) {
	timeTotreat=a.getHealingtime();
	availble=false;
}

public void finishedTratment(int simulationtime) {
	if(simulationtime==timeTotreat) {
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

