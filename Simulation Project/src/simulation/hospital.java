package simulation;
import java.security.SecureRandom;
public class hospital extends medicalintervention{
	private SecureRandom generator = new SecureRandom();
    public int ArrivalTime;
    private int Rangeofcaplbility;
    private int timeTillperma=0;
    
    public hospital(){
    	ArrivalTime=(generator.nextInt(5)+3)*10;
    	if(generator.nextInt(20)==0) {
    		ArrivalTime=20;
    	}
    	if(generator.nextInt(40)==0) {
    		ArrivalTime=10;
    	}
//    	ArrivalTime=;

    	Rangeofcaplbility=3;
    }
    
	public void treatmentadminstraition(worker injuried) {
	if(!injuried.isBeingTreated()&&injuried.isInjuired()&&!injuried.isPermenatlyinjuried()) {
		injuried.setWaitingForAmbulance(true);}
	else {
		injuried.setWaitingForAmbulance(false);
	}
	}

	public void finishedTreatment(worker injuried, int  currenttime) {
		if(injuried.isWaitingForAmbulance()) {
			if(timeTillperma==0) {
			timeTillperma=injuried.getCurrent().getPermenanttime()+currenttime;}
			if((timeTillperma-currenttime)==0) {
				if((injuried.getCurrent().getPermenanttime()-ArrivalTime)<0) {
				injuried.setPermenatlyinjuried(true);
				injuried.setBeingTreated(false);
				injuried.setCurrent(injuries.None);
				injuried.setHealingtime(0);
				injuried.setInjuired(false);
				injuried.setWaitingForAmbulance(false);
				timeTillperma=0;
				}
				
				else {
					injuried.setBeingTreated(false);
					injuried.setCurrent(injuries.None);
					injuried.setHealingtime(0);
					injuried.setInjuired(false);
					injuried.treatmentcount+=1;
					injuried.setWaitingForAmbulance(false);
					timeTillperma=0;
				}
			}
		}
		
	}

	public int getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		ArrivalTime = arrivalTime;
	}
	
}
