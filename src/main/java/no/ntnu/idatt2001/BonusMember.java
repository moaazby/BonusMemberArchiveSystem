package no.ntnu.idatt2001;

import java.time.LocalDate;

public class BonusMember {

    private int memberNumber;
    private LocalDate enrolledDate;
    private int bonusPointsBalance = 0;
    private String name;
    private String eMailAddress;
    private String password;
    private Membership membership;

    private static final int SILVER_LIMIT = 25000;
    private static final int GOLD_LIMIT = 75000;

    public BonusMember(int memberNumber, LocalDate enrolledDate, int bonusPointsBalance, String name, String eMailAddress) {
        this.memberNumber = memberNumber;
        this.enrolledDate = enrolledDate;
        this.bonusPointsBalance = bonusPointsBalance;
        this.name = name;
        this.eMailAddress = eMailAddress;
    }

    public boolean checkPassword(String password){
        return this.password.equalsIgnoreCase(password);
    }
    public void registerBonusPoints(int newPoints){}
    public String getMembershipLevel(){
        return null;
    }
    private void checkAndSetMembership(){}



    public int getMemberNumber() {
        return memberNumber;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }

    public int getBonusPointsBalance() {
        return bonusPointsBalance;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return eMailAddress;
    }

    @Override
    public String toString() {
        return "BonusMember{" +
                "memberNumber=" + memberNumber +
                ", enrolledDate=" + enrolledDate +
                ", bonusPointsBalance=" + bonusPointsBalance +
                ", name='" + name + '\'' +
                ", eMailAddress='" + eMailAddress + '\'' +
                ", membership=" + membership +
                '}';
    }
}
