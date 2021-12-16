package simulation;
import java.security.SecureRandom;
public class hospital extends medicalintervention{
	private SecureRandom generator = new SecureRandom();
    private int ArrivalTime;
    private int Rangeofcaplbility;
    private int timeTillperma=0;
    
    public hospital(){
    	ArrivalTime=(generator.nextInt(6)+1)*10;
    	Rangeofcaplbility=3;
    }
    
	public void treatmentadminstraition(worker injuried, int  currenttime ) {
	if(!injuried.isBeingTreated()) {
		injuried.setWaitingForAmbulance(true);}
	else {
		injuried.setWaitingForAmbulance(false);
	}
	}

	public void finishedTreatment(worker injuried, int  currenttime ) {
		if(injuried.isWaitingForAmbulance()) {
			if(timeTillperma==0) {
			timeTillperma=injuried.getHealingtime()+currenttime;}
			if((timeTillperma-currenttime)==0) {
				if((injuried.getCurrent().getPermenanttime()-ArrivalTime)==0)
				injuried.setPermenatlyinjuried(true);
				else {
					injuried.setBeingTreated(false);
					injuried.setCurrent(injuries.None);
					injuried.setHealingtime(0);
					injuried.setInjuired(false);
				}
			}
		}
		
	}
}
