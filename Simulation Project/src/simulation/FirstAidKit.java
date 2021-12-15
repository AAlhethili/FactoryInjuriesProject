package simulation;

public class FirstAidKit extends medicalintervention {
 public FirstAidKit() {
	 Rangeofcaplbility=1;
	 availble=true;
	 
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
	public int getRangeofcaplbility() {
		return Rangeofcaplbility;
	}
	public void setRangeofcaplbility(int rangeofcaplbility) {
		Rangeofcaplbility = rangeofcaplbility;
	}
	public boolean isAvailble() {
		return availble;
	}
	public void setAvailble(boolean availble) {
		this.availble = availble;
	}
	
}
