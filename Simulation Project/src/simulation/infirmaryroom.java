package simulation;

import java.security.SecureRandom;

public class infirmaryroom extends medicalintervention{
	SecureRandom generator = new SecureRandom();
	  public infirmaryroom() {
		 Rangeofcaplbility=/*7*/generator.nextInt(6)+1;
		 
	 }

	@Override
	public void treatmentadminstraition(worker injuried, int simTime, factory workerFactory) {
		if(!injuried.isCheckedInfermary()) {
			if(Rangeofcaplbility>injuried.getCurrent().level) {
			injuried.setProgressionRate(injuried.getProgressionRate()-10);
			injuried.setCheckedInfermary(true);
			injuried.setUsedIR(true);
			}
			if(Rangeofcaplbility==injuried.getCurrent().level) {
				injuried.setProgressionRate(injuried.getProgressionRate()-5);
				injuried.setCheckedInfermary(true);
				injuried.setUsedIR(true);
			}
			if(Rangeofcaplbility<injuried.getCurrent().level) {
				injuried.setProgressionRate(injuried.getProgressionRate()-1);
				injuried.setCheckedInfermary(true);
			}
			}
	
		}
		
	}


		