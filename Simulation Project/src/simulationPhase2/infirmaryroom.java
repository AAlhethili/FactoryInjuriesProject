package simulationPhase2;

import java.security.SecureRandom;

public class infirmaryroom extends medicalintervention{
	SecureRandom generator = new SecureRandom();
	ToolsofStrings tos = new ToolsofStrings();
	  public infirmaryroom() {
		 Rangeofcaplbility=/*6;*/generator.nextInt(3)+4;
		 
	 }

	@Override
	public void treatmentadminstraition(worker injuried,int simDay, int simTime, factory workerFactory) {
		if(!injuried.isCheckedInfermary()) {
		if(simTime==injuried.getWaitTime()) {
			if(Rangeofcaplbility>=injuried.getCurrent().level) {
				if(workerFactory.getMaxProgressedInjury()<injuried.getProgressionofInjury()) {
					workerFactory.setMaxProgressedInjury(injuried.getProgressionofInjury());}
				if(injuried.getProgressionofInjury()<=workerFactory.getMinProgressedInjury()) {
					if(injuried.getProgressionofInjury()<0) {
						injuried.setProgressionofInjury(0);
					}
					workerFactory.setMinProgressedInjury(injuried.getProgressionofInjury());
				}
				injuried.setTreatdByInfermaryRoom(true);
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Worker "+injuried.getId()+" who's age is "+(int)injuried.getAge()+" Of Factory Number: "+ workerFactory.getID()+" "+tos.doesor(workerFactory.checksmartkit())+"have Smart Aid kits, was Injuried on: "+ injuried.getTimeOfInjury()+" had "+injuried.getPeopleAround()+" around when injuried");
				System.out.println("The worker"+tos.didOrDidNot(injuried.isUsedAidKit())+"First Aid Kit as there"+tos.wasno(injuried.isUsedAidKit())+"First Aid Kit close by when worker was injuried");
				System.out.println("The worker"+tos.didOrDidNot(injuried.isTratedByMedicalWorker())+"Medically trained worker");
				System.out.println("The worker"+tos.didOrDidNot(injuried.isTreatdByInfermaryRoom())+"The infirmary");
				System.out.println("Worker Was injuried and the factroy is cabable of treating the worker");
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			injuried.FinishedTreatment();
			injuried.setCheckedInfermary(true);
			workerFactory.counterTreatmentByCapableInfermaryRoom+=1;
			}
			if(Rangeofcaplbility<injuried.getCurrent().level) {
				injuried.setProgressionRate(injuried.getProgressionRate());
				injuried.setCheckedInfermary(true);
				}
			}
		}
	}
		
}


		