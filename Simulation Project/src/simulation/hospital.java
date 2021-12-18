package simulation;
import java.security.SecureRandom;

public class hospital extends medicalintervention{
	private SecureRandom generator = new SecureRandom();
    public int ArrivalTime=0;
    
    public hospital(){
    }
    
	
	public int getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		ArrivalTime = arrivalTime;
	}


	@Override
	public void treatmentadminstraition(worker injuried, int simTime, factory workerFactory) {
	if(ArrivalTime==0) {
		if(generator.nextBoolean()) {
			ArrivalTime=workerFactory.getTimetohospital()-generator.nextInt(4);
			injuried.setHospitalarraivalTime(ArrivalTime);
			ArrivalTime=workerFactory.getTimetohospital()+simTime;
		}
		else {
			ArrivalTime=workerFactory.getTimetohospital()+generator.nextInt(7);
			injuried.setHospitalarraivalTime(ArrivalTime);
			ArrivalTime=workerFactory.getTimetohospital()+simTime;
		}
	}
	if(injuried.getProgressionRate()<0) {
		injuried.setProgressionRate(1);
	}
	if(ArrivalTime-simTime==0) {
		if(injuried.getProgressionofInjury()>=100){
			workerFactory.setMaxProgressedInjury(100);
			injuried.setPermenatlyinjuried(true);
			injuried.FinishedTreatment();
			ArrivalTime=0;
		}
		else {
			if(workerFactory.getMaxProgressedInjury()<injuried.getProgressionofInjury()) {
			workerFactory.setMaxProgressedInjury(injuried.getProgressionofInjury());}
			if(workerFactory.getMinProgressedInjury()>injuried.getProgressionofInjury()) {
				workerFactory.setMinProgressedInjury(injuried.getProgressionofInjury());}
			injuried.FinishedTreatment();
			ArrivalTime=0;
			}
		
		}
	injuried.progressInjury();
	if(simTime==1||simTime%480==0||simTime%480<=10) {
	injuried.showStatus(workerFactory);}
	}
		
	}
		
	

