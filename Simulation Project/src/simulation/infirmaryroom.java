package simulation;

public class infirmaryroom extends medicalintervention{
	  public infirmaryroom() {
		 Rangeofcaplbility=3;
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
}