package simulation;

public class smartFirstAidkit extends FirstAidKit {

	@Override
	public void treatmentadminstraition(worker a,int simDay, int simTime, factory workerfactory) {
		if(!a.isCheckedFirsAid()) {
			a.setProgressionRate(a.getProgressionRate()-5);
			a.setUsedAK(true);
			a.setUsedSAK(true);
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
