package no.ntnu.idatt2001;

import org.apache.commons.lang3.math.NumberUtils;

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
        this.checkAndSetMembership();
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
    public void registerBonusPoints(int newPoints){
        if(newPoints<0)
            throw new IllegalArgumentException("New points input must be positive value.");
        if(newPoints==0)
            throw new IllegalArgumentException("New points can not be 0, it must be at least 1 point");

        this.checkAndSetMembership();
        this.bonusPointsBalance = this.membership.registerPoints(bonusPointsBalance,newPoints);

    }
    public String getMembershipLevel(){
        this.checkAndSetMembership();
        String lvl = "";
        if(membership instanceof GoldMembership){
            lvl = (this.bonusPointsBalance < 90000)? " Level 1": " Level 2";
        }
        return membership.getMembershipName()+lvl;
    }
    private void checkAndSetMembership(){
        if(bonusPointsBalance<SILVER_LIMIT){
            this.membership = new BasicMembership();
        }else if(bonusPointsBalance>=SILVER_LIMIT && bonusPointsBalance<GOLD_LIMIT){
            this.membership = new SilverMembership();

        }else if(bonusPointsBalance>=GOLD_LIMIT){
            this.membership = new GoldMembership();
        }

    }


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

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        this.checkAndSetMembership();
        return "Bonus Member" +
                "\nMember Number: " + memberNumber +
                "\nEnrolled Date: " + enrolledDate +
                "\nBonus Points Balance : " + bonusPointsBalance +
                "\nName: " + name +
                "\nEmail Address: " + eMailAddress +
                "\nMembership type: " + this.getMembershipLevel();
    }
}
