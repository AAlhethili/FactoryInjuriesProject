package simulation;

import java.security.SecureRandom;

public class infirmaryroom extends medicalintervention{
	SecureRandom generator = new SecureRandom();
	  public infirmaryroom() {
		 Rangeofcaplbility=generator.nextInt(6)+3;
		 
	 }

	@Override
	public void treatmentadminstraition(worker injuried, int simTime, factory workerFactory) {
		if(!injuried.isCheckedInfermary()) {
			if(Rangeofcaplbility>injuried.getCurrent().level) {
			injuried.setProgressionRate(injuried.getProgressionRate()-15);
			injuried.setCheckedInfermary(true);
			injuried.usedIR=true;
			}
			if(Rangeofcaplbility==injuried.getCurrent().level) {
				injuried.setProgressionRate(injuried.getProgressionRate()-10);
				injuried.setCheckedInfermary(true);
				injuried.usedIR=true;
			}
			if(Rangeofcaplbility<injuried.getCurrent().level) {
				injuried.setProgressionRate(injuried.getProgressionRate()-1);
				injuried.setCheckedInfermary(true);
			}
			}
	
		}
		
	}


		