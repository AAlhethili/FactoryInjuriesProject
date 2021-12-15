package simulation;

public class medicallytrainedworker extends worker implements medicaltreatmant {

	public int timetoFinishTreatment;
	public boolean readiness;
	
	public medicallytrainedworker(int id, double avg, double avgcheck) {
		super(id,avg, avgcheck);
		readiness=true;
	}


	public void treatmentadminstraition(worker a) {
		timetoFinishTreatment=a.healingtime;
		readiness=false;
	}

	public void finishedTratment(int simulationtime) {
		if(simulationtime==timetoFinishTreatment) {
			readiness=true;
		}

	}

}
