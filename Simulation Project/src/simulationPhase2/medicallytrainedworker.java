package simulationPhase2;

import java.security.SecureRandom;

public class medicallytrainedworker extends worker implements medicaltreatmant {

	int checkagain=9999999;
	SecureRandom generator = new SecureRandom();
	boolean someoneNearby=false;
	
	public medicallytrainedworker() {
		
	}
	public medicallytrainedworker(int id, double avg, double avgCheckUpRate) {
		super(id,avg, avgCheckUpRate);
	
		setKnowsHowtoUseMedikit(true);
	}

	@Override
	public void treatmentadminstraition(worker a,int simDay, int simTime,factory workerfactory) {
		if(!a.isCheckedMedicalWorker()&&!a.isTreatdByInfermaryRoom()) {
				checkagain=9999999;
				int worker=0;
				for(int i=1; i<= a.getPeopleAround();i++) {
					worker=generator.nextInt(workerfactory.getWorkerList().size());
					while(worker==checkagain) {
						worker = generator.nextInt(workerfactory.getWorkerList().size());
					}
					checkagain=worker;
					if(workerfactory.workerList.get(worker) instanceof medicallytrainedworker) {
						someoneNearby=true;
						break;
						}
				}

					
			if(someoneNearby||a.isMedicalWorkerAvailable()) {
				a.setProgressionRate(a.getProgressionRate()-2);
				a.setCheckedMedicalWorker(someoneNearby);
				a.setTratedByMedicalWorker(true);
				workerfactory.counterTreatmentByMedicalWorker+=1;
				someoneNearby=false;
				}
			a.setWaitTime(6+simTime);
		}
		a.setCheckedMedicalWorker(true);
		
	}

	

	}

