package no.ntnu.idatt2001;

public class GoldMembership extends Membership{

    private final float POINTS_SCALING_FACTOR_LEVEL_1 = 1.3f;
    private final float POINTS_SCALING_FACTOR_LEVEL_2 = 1.5f;

    public GoldMembership() {
    }

    @Override
    public int registerPoints(int bonusPointBalance, int newPoints) {
        return (bonusPointBalance<90000)?
        bonusPointBalance+Math.round(newPoints*POINTS_SCALING_FACTOR_LEVEL_1):
        bonusPointBalance+Math.round(newPoints*POINTS_SCALING_FACTOR_LEVEL_2);


    }

    @Override
    public String getMembershipName() {
        return "Gold";
    }

}
