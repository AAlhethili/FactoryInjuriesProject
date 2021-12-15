package simulation;

public class infirmaryroom extends medicalintervention{
    int treating=0;
    boolean cantreat;
    public infirmaryroom(injuries current){
        CanTreat();
        if(cantreat) {
            if (current.lvl <= 3){
                CureInjury(current);
            }
        }
            else {
                Treatinjury(current);
            }
        }

    private void CanTreat(){
        if(InfirmaryReadiness != 0) {
            cantreat= true;
        }
        cantreat= false;
    }
    private void Treatinjury(injuries current){
        switch (current.lvl) {
            case 3:
                treating= 30;
            case 4:
                treating= 20;
            default:
                treating= 0;
        }
    }
    private void CureInjury(injuries current){
       switch (current.lvl) {
           case 1:
               //cured this injury
           case 2:
               //cured this injury
           default://nothing
       }
    }
}

