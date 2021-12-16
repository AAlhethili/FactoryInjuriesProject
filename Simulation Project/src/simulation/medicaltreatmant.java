package simulation;

public interface medicaltreatmant {
	
	abstract void treatmentadminstraition(worker injuried, int simTime);
	abstract void finishedTreatment(worker injuried, int simTime);

}
