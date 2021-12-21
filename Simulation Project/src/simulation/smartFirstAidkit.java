package simulation;

public class smartFirstAidkit extends FirstAidKit {
//smart kit same as normal but doesn't require prior knowldge
	@Override
	public void treatmentadminstraition(worker a,int simDay, int simTime, factory workerfactory) {
		if(!a.isCheckedFirsAid()&&!a.isTreatdByInfermaryRoom()) {
			if(a.getPeopleAround()>0) {//only checks if there is somone around to trat injuried
			a.setProgressionRate(a.getProgressionRate()-2);
			a.setUsedAidKit(true);
			a.setUsedSmartAidKit(true);
			workerfactory.counterTreatmentByFirstAidKit+=1;
		}
		}
		a.setCheckedFirsAid(true);
	}

	@Override
	public int getRangeofcaplbility() {
		// TODO Auto-generated method stub
		return super.getRangeofcaplbility();
	}

	@Override
	public void setRangeofcaplbility(int rangeofcaplbility) {
		// TODO Auto-generated method stub
		super.setRangeofcaplbility(rangeofcaplbility);
	}
	

}
