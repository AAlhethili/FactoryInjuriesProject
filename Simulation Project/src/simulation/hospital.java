package simulation;
import java.security.SecureRandom;

public class hospital extends medicalintervention{
	private SecureRandom generator = new SecureRandom();
	private ToolsofStrings tof = new ToolsofStrings();
    private int ArrivalTime=-1;
    public hospital(){
    }
    
	
	public int getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		ArrivalTime = arrivalTime;
	}


	@Override
	public void treatmentadminstraition(worker injuried,int simDay, int simTime, factory workerFactory) {
		//start arrival countdown and randomly gives time
		if(!injuried.isTreatdByInfermaryRoom()) {
		if ((((!injuried.isCalledHospital())&&simTime%simDay!=0))) {
		if(generator.nextBoolean()) {
			ArrivalTime=workerFactory.getAvgTimeToHospital()-generator.nextInt(4);
			injuried.setHospitalarraivalTimeCountdown(ArrivalTime+1);
			injuried.setArrivalTimenoCountdown(ArrivalTime);
			ArrivalTime=ArrivalTime+simTime;
			injuried.setCalledHospital(true);
		}
		else {
			ArrivalTime=workerFactory.getAvgTimeToHospital()+generator.nextInt(7);
			injuried.setHospitalarraivalTimeCountdown(ArrivalTime+1);
			injuried.setArrivalTimenoCountdown(ArrivalTime);
			ArrivalTime=ArrivalTime+simTime;
			injuried.setCalledHospital(true);
		}
	}
		//caps injury liver at 100 at the end of the day
	if(simTime%simDay==0) {
		injuried.setProgressionofInjury(injuried.getProgressionofInjury()+(injuried.getHospitalarraivalTimeCountdown()*injuried.getProgressionRate()));
		if(injuried.getProgressionofInjury()>100) {
			injuried.setProgressionofInjury(100);
		}
	}

	if((injuried.getHospitalarraivalTimeCountdown()==1||simTime%simDay==0)&&!injuried.isTreated()) {//checks if progression is over 100, add permanant injury and display inuried status
		if(injuried.getProgressionofInjury()>=100){
			workerFactory.setMaxProgressedInjury(100);
			injuried.setPermenatlyInjured(true);
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Worker "+injuried.getId()+" who's age is "+(int)injuried.getAge()+" Of Factory Number: "+ workerFactory.getID()+" "+tof.doesor(workerFactory.checksmartkit())+"have Smart Aid kits, was Injuried on: "+ injuried.getTimeOfInjury()+" had "+injuried.getPeopleAround()+" around when injuried");
			System.out.println("The worker"+tof.didOrDidNot(injuried.isUsedAidKit())+"First Aid Kit as there"+tof.wasno(injuried.isUsedAidKit())+"First Aid Kit close by when worker was injuried");
			System.out.println("The worker"+tof.didOrDidNot(injuried.isTratedByMedicalWorker())+"by Medically trained worker ");
			System.out.println("The worker"+tof.didOrDidNot(injuried.isTreatdByInfermaryRoom())+"The infirmary");
			System.out.println("Hospital arrived in "+ injuried.getArrivalTimenoCountdown()+" minutes and unfortuntly worker was permenatly injuried");	
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			injuried.FinishedTreatment();
		}
		else {// if ambulance arrived before 100% it heals and displays status
			if(workerFactory.getMaxProgressedInjury()<injuried.getProgressionofInjury()) {
			workerFactory.setMaxProgressedInjury(injuried.getProgressionofInjury());}
			if(injuried.getProgressionofInjury()<=workerFactory.getMinProgressedInjury()) {
				if(injuried.getProgressionofInjury()<0) {
					injuried.setProgressionofInjury(0);
				}
				workerFactory.setMinProgressedInjury(injuried.getProgressionofInjury());
			}
			workerFactory.counterTreatmentByHospital+=1;
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Worker "+injuried.getId()+" who's age is "+(int)injuried.getAge()+" Of Factory Number: "+ workerFactory.getID()+" "+tof.doesor(workerFactory.checksmartkit())+"have Smart Aid kits, was Injuried on: "+ injuried.getTimeOfInjury()+" had "+injuried.getPeopleAround()+" around when injuried");
			System.out.println("The worker"+tof.didOrDidNot(injuried.isUsedAidKit())+"First Aid Kit as there"+tof.wasno(injuried.isUsedAidKit())+"One knows how to use First Aid Kit");
			System.out.println("The worker"+tof.didOrDidNot(injuried.isTratedByMedicalWorker())+"Medically trained worker ");
			System.out.println("The worker"+tof.didOrDidNot(injuried.isTreatdByInfermaryRoom())+"The infirmary");
			System.out.println("Hospital Arrived in "+ injuried.getArrivalTimenoCountdown()+" minutes and Worker Was Treated");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			injuried.FinishedTreatment();
				}
			}

	if(simTime%simDay!=0) {//if it is not the end of the day it progresses injury
		if(injuried.isProgress()) {
	injuried.progressInjury();}
		else {
			injuried.setProgress(true);
		}
	}
		}
	if(injuried.getHospitalarraivalTimeCountdown()>0&&injuried.isCalledHospital()) {//countdown of ambulance arrival
	injuried.setHospitalarraivalTimeCountdown(injuried.getHospitalarraivalTimeCountdown() - 1);
	}
	}
		
	}
		
	

