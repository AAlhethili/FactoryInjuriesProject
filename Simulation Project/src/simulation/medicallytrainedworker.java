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

	@Override
	public void treatmentadminstraition(worker a, int simTime) {
		if(a.isInjuired()&&!a.isBeingTreated()&&isReadiness()&&a.getCurrent().getLvl()<=Rangeofcaplbility&&!a.isPermenatlyinjuried()) {
			timetoFinishTreatment=a.getHealingtime()+simTime;
			a.setWaitingForAmbulance(false);
			a.setBeingTreated(true);
			a.setTimetillpermenant(0);
			readiness=false;
		}
	}
	@Override
	public void finishedTreatment(worker a, int simTime) {
		if(simTime==timetoFinishTreatment&&a.isBeingTreated()&&a.isInjuired()&&!a.isPermenatlyinjuried()) {
			a.setBeingTreated(false);
			a.setCurrent(injuries.None);
			a.setHealingtime(0);
			a.treatmentcount+=1;
			a.setTimetillpermenant(0);
			a.setInjuired(false);
			a.setWaitingForAmbulance(false);
			readiness=true;
			timetoFinishTreatment=0;
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
