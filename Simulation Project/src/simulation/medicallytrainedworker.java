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


	public void treatmentadminstraition(worker a, int simTime) {
		if(!a.isBeingTreated()&&isReadiness()&&a.getCurrent().lvl<=Rangeofcaplbility) {
		timetoFinishTreatment=a.getHealingtime()+simTime;
		a.setWaitingForAmbulance(false);
		a.setTimetillpermenant(0);
		readiness=false;
		}
	}

	public void finishedTreatment(worker a, int simTime) {
		if(a.getHealingtime()==timetoFinishTreatment) {
			a.setBeingTreated(false);
			a.setCurrent(injuries.None);
			a.setHealingtime(0);
			a.setInjuired(false);
			readiness=true;
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
