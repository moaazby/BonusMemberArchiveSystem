package no.ntnu.idatt2001;

public class BasicMembership extends Membership{

    public BasicMembership() {
    }

    @Override
    public int registerPoints(int bonusPointBalance, int newPoints) {
        return bonusPointBalance+newPoints;
    }

    @Override
    public String getMembershipName() {
        return "Basic";
    }
}
