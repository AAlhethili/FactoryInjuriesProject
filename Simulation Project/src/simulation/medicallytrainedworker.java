package simulation;

public class medicallytrainedworker extends worker implements medicaltreatmant {

	private int timetoFinishTreatment;
	private int Rangeofcaplbility;
	private boolean readiness;
	
	public medicallytrainedworker(int id, double avg, double avgcheck) {
		super(id,avg, avgcheck);
		setRangeofcaplbility(2);
		setReadiness(true);
	}


	public void treatmentadminstraition(worker a) {
		timetoFinishTreatment=a.getHealingtime();
		setReadiness(false);
	}

	public void finishedTratment(int simulationtime,worker injuired) {
		if(simulationtime==timetoFinishTreatment) {
			injuired.setInjuired(false);
			injuired.setCurrent(injuries.None);
			setReadiness(true);
			
		}

	}


	public int getRangeofcaplbility() {
		return Rangeofcaplbility;
	}


	public void setRangeofcaplbility(int rangeofcaplbility) {
		Rangeofcaplbility = rangeofcaplbility;
	}


	public boolean isReadiness() {
		return readiness;
	}


	public void setReadiness(boolean readiness) {
		this.readiness = readiness;
	}

}
