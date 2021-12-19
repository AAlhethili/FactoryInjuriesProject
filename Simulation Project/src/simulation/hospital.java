package simulation;
import java.security.SecureRandom;

public class hospital extends medicalintervention{
	private SecureRandom generator = new SecureRandom();
    private int ArrivalTime=0;
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
			injuried.setHat(ArrivalTime+1);
			injuried.setArrivalTimenoCountdown(ArrivalTime);
			ArrivalTime=ArrivalTime+simTime;
			injuried.setHospitalarraivalTime(ArrivalTime);
		}
		else {
			ArrivalTime=workerFactory.getTimetohospital()+generator.nextInt(7);
			injuried.setHat(ArrivalTime+1);
			injuried.setArrivalTimenoCountdown(ArrivalTime);
			ArrivalTime=ArrivalTime+simTime;
			injuried.setHospitalarraivalTime(ArrivalTime);
		}
	}
	if(simTime%480==0) {
		injuried.setProgressionofInjury(injuried.getProgressionofInjury()+(injuried.getHat()*injuried.getProgressionRate()));
		if(injuried.getProgressionofInjury()>100) {
			injuried.setProgressionofInjury(100);
		}
	}
	if(injuried.getProgressionRate()<=0&&!injuried.isTreated()) {
		injuried.setProgressionRate(0);
		if(workerFactory.getMaxProgressedInjury()<injuried.getProgressionofInjury()) {
			workerFactory.setMaxProgressedInjury(injuried.getProgressionofInjury());}
			if(workerFactory.getMinProgressedInjury()>injuried.getProgressionofInjury()) {
				workerFactory.setMinProgressedInjury(injuried.getProgressionofInjury());}
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Worker "+injuried.getId()+" who's age is "+injuried.getAge()+" Of Factory Number: "+ workerFactory.getID()+"was Injuried on: "+ injuried.getTimeofinjury());
			System.out.println("The worker"+didOrDidNot(injuried.isCheckedFirsAid())+"by First Aid Kit");
			System.out.println("The worker"+didOrDidNot(injuried.isCheckedMedicalWorker())+"by Medically trained worker");
			System.out.println("The worker"+didOrDidNot(injuried.isCheckedInfermary())+"by The infirmary");
			System.out.println("Worker Was injuried and the factroy is cabable of treating the worker");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
			injuried.FinishedTreatment();
			ArrivalTime=0;
	}
	if((injuried.getHat()==1||simTime%480==0)&&!injuried.isTreated()) {
		if(injuried.getProgressionofInjury()>=100){
			workerFactory.setMaxProgressedInjury(100);
			injuried.setPermenatlyinjuried(true);
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Worker "+injuried.getId()+" who's age is "+injuried.getAge()+" Of Factory Number: "+ workerFactory.getID()+"was Injuried on: "+ injuried.getTimeofinjury());
			System.out.println("The worker"+didOrDidNot(injuried.isCheckedFirsAid())+"by First Aid Kit");
			System.out.println("The worker"+didOrDidNot(injuried.isCheckedMedicalWorker())+"by Medically trained worker");
			System.out.println("The worker"+didOrDidNot(injuried.isCheckedInfermary())+"by The infirmary");
			System.out.println("Hospital arrived in "+ injuried.getArrivalTimenoCountdown()+" minutes and unfortuntly worker was permenatly injuried");	
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
			injuried.FinishedTreatment();
			ArrivalTime=0;
		}
		else {
			if(workerFactory.getMaxProgressedInjury()<injuried.getProgressionofInjury()) {
			workerFactory.setMaxProgressedInjury(injuried.getProgressionofInjury());}
			if(workerFactory.getMinProgressedInjury()>injuried.getProgressionofInjury()) {
				workerFactory.setMinProgressedInjury(injuried.getProgressionofInjury());}
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Worker "+injuried.getId()+" who's age is "+injuried.getAge()+" Of Factory Number: "+ workerFactory.getID()+"was Injuried on: "+ injuried.getTimeofinjury());
			System.out.println("The worker"+didOrDidNot(injuried.isCheckedFirsAid())+"by First Aid Kit");
			System.out.println("The worker"+didOrDidNot(injuried.isCheckedMedicalWorker())+"by Medically trained worker");
			System.out.println("The worker"+didOrDidNot(injuried.isCheckedInfermary())+"by The infirmary");
			System.out.println("Hospital Arrived in "+ injuried.getArrivalTimenoCountdown()+" minutes and Worker Was Treated");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
			injuried.FinishedTreatment();
			ArrivalTime=0;
			}
		}
	if(simTime%480!=0) {
	injuried.progressInjury();
	}
	if(injuried.getHat()>0) {
	injuried.setHat(injuried.getHat() - 1);
	}
	}
		
	public String didOrDidNot(boolean dd) {
		String dod;
		if(dd) {
			dod=" was treated by ";
		}
		else {
			dod= " was not treated by ";
		}
		return dod;
	}
	}
		
	

