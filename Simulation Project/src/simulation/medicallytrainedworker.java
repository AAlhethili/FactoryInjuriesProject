package simulation;

import java.security.SecureRandom;

public class medicallytrainedworker extends worker implements medicaltreatmant {

	int Rangeofcaplbility;
	int checkagain=9999999;
	SecureRandom generator = new SecureRandom();
	boolean someoneNearby=false;
	
	public medicallytrainedworker() {
		setRangeofcaplbility(2);
	}
	public medicallytrainedworker(int id, double avg, double avgcheck) {
		super(id,avg, avgcheck);
		setRangeofcaplbility(2);
		setKnowsHowtoUseMedikit(true);
	}

	@Override
	public void treatmentadminstraition(worker a, int simTime,factory workerfactory) {
		if(!a.isCheckedMedicalWorker()) {
			if(a.getCurrent().level<=Rangeofcaplbility) {
				checkagain=9999999;
				int worker=0;
				for(int i=1; i<= a.getPeopleAround();i++) {
					worker=generator.nextInt(workerfactory.getWlist().size());
					while
						(worker==checkagain) {
						worker = generator.nextInt(workerfactory.getWlist().size());
					}
					checkagain=worker;
					if(workerfactory.wlist.get(worker) instanceof medicallytrainedworker) {
						someoneNearby=true;
						break;
						}
				}

					
			if(someoneNearby) {
				a.setProgressionRate(a.getProgressionRate()-5);
				a.setCheckedMedicalWorker(someoneNearby);
				a.setHadMWnearby(true);
				someoneNearby=false;
				}
			}
			a.setWaitTime((generator.nextInt(3)+1)+simTime);
		}
		a.setCheckedMedicalWorker(true);
		
	}

	
	public int getRangeofcaplbility() {
		return Rangeofcaplbility;
	}
	public void setRangeofcaplbility(int rangeofcaplbility) {
		Rangeofcaplbility = rangeofcaplbility;
	}

	}

