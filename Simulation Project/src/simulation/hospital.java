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
	if(ArrivalTime==0&&simTime%480!=0) {
		if(generator.nextBoolean()) {
			ArrivalTime=workerFactory.getTimetohospital()-generator.nextInt(4);
			injuried.hat=ArrivalTime;
			ArrivalTime=ArrivalTime+simTime;
			injuried.setHospitalarraivalTime(ArrivalTime);
		}
		else {
			ArrivalTime=workerFactory.getTimetohospital()+generator.nextInt(7);
			injuried.hat=ArrivalTime;
			ArrivalTime=ArrivalTime+simTime;
			injuried.setHospitalarraivalTime(ArrivalTime);
		}
	}
	if(simTime%480==0) {
		injuried.setProgressionofInjury(injuried.getProgressionofInjury()+(injuried.hat*injuried.getProgressionRate()));
		if(injuried.getProgressionofInjury()>100) {
			injuried.setProgressionofInjury(100);
		}
	}
	if(injuried.getProgressionRate()<=0) {
		if(workerFactory.getMaxProgressedInjury()<injuried.getProgressionofInjury()) {
			workerFactory.setMaxProgressedInjury(injuried.getProgressionofInjury());}
			if(workerFactory.getMinProgressedInjury()>injuried.getProgressionofInjury()) {
				workerFactory.setMinProgressedInjury(injuried.getProgressionofInjury());}
			if(simTime%480==0) {
			System.out.println("Factory: "+ workerFactory.getID()+" " + injuried.getId()+" " + injuried.hat+" " + injuried.getProgressionRate()+" " + injuried.getProgressionofInjury()+" Time of injury: "+ injuried.getTimeofinjury()+ " Worker Was inuried and is treated in Factroy");
			}
			injuried.FinishedTreatment();
			ArrivalTime=0;
	}
	if(ArrivalTime==simTime||simTime%480==0) {
		if(injuried.getProgressionofInjury()>=100){
			workerFactory.setMaxProgressedInjury(100);
			injuried.setPermenatlyinjuried(true);
////			if(simTime==1||simTime%480<=30&&simTime%480!=0) {
//			if(simTime%480==0) {
			System.out.println("Factory: "+ workerFactory.getID()+" " + injuried.getId()+" " + injuried.getProgressionofInjury()+" Time of injury: "+ injuried.getTimeofinjury()+" " + injuried.hat+" " + injuried.getProgressionRate()+ " Hospital arrived and unfortuntly worker was permenatly injuried");
//			}		
			injuried.FinishedTreatment();
			ArrivalTime=0;
		}
		else {
			if(workerFactory.getMaxProgressedInjury()<injuried.getProgressionofInjury()) {
			workerFactory.setMaxProgressedInjury(injuried.getProgressionofInjury());}
			if(workerFactory.getMinProgressedInjury()>injuried.getProgressionofInjury()) {
				workerFactory.setMinProgressedInjury(injuried.getProgressionofInjury());}
			if(simTime%480==0) {
			System.out.println("Factory: "+ workerFactory.getID()+" " + injuried.getId()+" " + injuried.getProgressionofInjury()+" Time of injury: "+ injuried.getTimeofinjury()+" " + injuried.hat+" " + injuried.getProgressionRate()+ " Hospital Arrived and Worker Was Treated");
			}
			injuried.FinishedTreatment();
			ArrivalTime=0;
			}
		}
	if(simTime%480!=0) {
	injuried.progressInjury();
	}
	if(injuried.hat>0) {
	injuried.hat-=1;
	}
//	injuried.showStatus(workerFactory);
	}
		
	}
		
	

