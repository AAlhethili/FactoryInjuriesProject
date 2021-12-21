package simulation;

//abstract class to combine differen treatment method
public abstract class medicalintervention implements medicaltreatmant {
int Rangeofcaplbility;


public int getRangeofcaplbility() {
	return Rangeofcaplbility;
}
public void setRangeofcaplbility(int rangeofcaplbility) {
	Rangeofcaplbility = rangeofcaplbility;
}

}

