package simulation;

public class firstresponder extends medicaltreatment{
    int treating=0;
    boolean cantreat;
    public firstresponder(injuries current){
        Treatinjury(current);
    }
    private void Treatinjury(injuries current) {
        switch (current.lvl) {
            case 1:
                treating= 3
            case 2:
                treating= 2;
            default:
                treating= 0;
        }
    }
    private void FirstResponderTreatment(){
        cantreat= treating != 0;
    }
}
