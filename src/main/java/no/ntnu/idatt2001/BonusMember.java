package no.ntnu.idatt2001;

import java.time.LocalDate;

public class BonusMember {

    private int  memberNumber;
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

        this.bonusPointsBalance = this.membership.registerPoints(bonusPointsBalance,newPoints);
        this.checkAndSetMembership();// Update the membership after adding new points
                                     // 1-for next registration of new points,to use the right scaling factor.
                                     // 2-to call the right level in method getMembershipLevel()
    }

    public String getMembershipLevel(){
        String level = "";
        if(membership instanceof GoldMembership){
            level = (this.bonusPointsBalance < 90000)? " Level 1": " Level 2";
        }
        return membership.getMembershipName()+level;
    }

    private void checkAndSetMembership(){
        if(this.bonusPointsBalance<SILVER_LIMIT){
            this.membership = new BasicMembership();

        }else if(this.bonusPointsBalance>=SILVER_LIMIT && this.bonusPointsBalance<GOLD_LIMIT){
            this.membership = new SilverMembership();

        }else {
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
        return "Bonus Member" +
                "\nMember Number: " + memberNumber +
                "\nEnrolled Date: " + enrolledDate +
                "\nBonus Points Balance : " + bonusPointsBalance +
                "\nName: " + name +
                "\nEmail Address: " + eMailAddress +
                "\nMembership type: " + this.getMembershipLevel();
    }
}
