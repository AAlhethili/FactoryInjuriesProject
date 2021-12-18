package simulation;

public class medicallytrainedworker extends worker implements medicaltreatmant {

	int Rangeofcaplbility;
	boolean someoneNearby=false;
	
	public medicallytrainedworker(int id, double avg, double avgcheck) {
		super(id,avg, avgcheck);
		setRangeofcaplbility(2);
	}

	@Override
	public void treatmentadminstraition(worker a, int simTime,factory workerfactory) {
		if(!a.isCheckedMedicalWorker()) {
			if(workerfactory.getWlist().size()-1-workerfactory.getWlist().indexOf(a)<=(workerfactory.getDensity())) {
				for(int i=workerfactory.getWlist().indexOf(a); i<= workerfactory.getWlist().indexOf(a)-workerfactory.getDensity();i++) {
	
					if(workerfactory.wlist.get(i).isKnowsHowtoUseMedikit()) {
						someoneNearby=true;
						break;
					}
				}
			}
			if(someoneNearby) {
				a.setProgressionRate(a.getProgressionRate()-5);
				someoneNearby=false;
			}
		}
		
	}

	
	public int getRangeofcaplbility() {
		return Rangeofcaplbility;
	}
	public void setRangeofcaplbility(int rangeofcaplbility) {
		Rangeofcaplbility = rangeofcaplbility;
	}

	}

