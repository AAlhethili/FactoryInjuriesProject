package simulation;

public class FirstAidKit extends medicalintervention {
	
 public FirstAidKit() {
	 Rangeofcaplbility=1;
	 availble=true;
	 
 }
	public void treatmentadminstraition(worker a) {
		timeTotreat=a.healingtime;
		availble=false;
	}

	public void finishedTratment(int simulationtime) {
		if(simulationtime==timeTotreat) {
			availble=true;
		}

	}
}
