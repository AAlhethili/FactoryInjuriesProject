package simulation;

import java.security.SecureRandom;

public class infirmaryroom extends medicalintervention{
	SecureRandom generator = new SecureRandom();
	  public infirmaryroom() {
		 Rangeofcaplbility=/*6;*/generator.nextInt(6)+1;
		 
	 }

	@Override
	public void treatmentadminstraition(worker injuried,int simDay, int simTime, factory workerFactory) {
		if(!injuried.isCheckedInfermary()) {
		if(simTime==injuried.getWaitTime()) {
			if(Rangeofcaplbility>injuried.getCurrent().level) {
			injuried.setProgressionRate(injuried.getProgressionRate()-15);
			injuried.setCheckedInfermary(true);
			injuried.setUsedIR(true);
			}
			if(Rangeofcaplbility==injuried.getCurrent().level) {
				injuried.setProgressionRate(injuried.getProgressionRate()-10);
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
		
}


		