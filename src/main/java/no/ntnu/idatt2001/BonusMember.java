package no.ntnu.idatt2001;

import java.time.LocalDate;

public class BonusMember {

    //some colors to make testing easier.
    public final String ANSI_RESET = "\u001B[0m";
    public final String ANSI_GREEN = "\u001B[32m";
    public final String ANSI_YELLOW = "\u001B[33m";
    public final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_CYAN = "\u001B[36m";

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
        if(memberNumber <= 0)
            throw new IllegalArgumentException("New member number must be positive number, 0 is unacceptable");
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException ("The name is required");}
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
        //zero and negative newPoints are not supported.
        if(newPoints<0)
            throw new IllegalArgumentException("New points input must be positive value.");
        if(newPoints==0)
            throw new IllegalArgumentException("New points can not be 0, it must be at least 1 point");

        this.bonusPointsBalance = this.membership.registerPoints(bonusPointsBalance,newPoints);
        this.checkAndSetMembership();// Update the membership after adding new points
                                     // 1-for next registration of new points, to use the right scaling factor.
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
                "\nMember Number: " +ANSI_GREEN+ memberNumber +ANSI_RESET+
                "\nEnrolled Date: " + enrolledDate +
                "\nBonus Points Balance : " +ANSI_GREEN+ bonusPointsBalance +ANSI_RESET+
                "\nName: " + name +
                "\nEmail Address: " + eMailAddress +
                "\nMembership type: " + this.getMembershipColor()+ this.getMembershipLevel()+ ANSI_RESET;
    }

    /**
     * if your IDE dose not support ANSI colors, please use this toString and comment the other one above.
     * */

    /**
    @Override
    public String toString() {
        return "Bonus Member" +
                "\nMember Number: " + memberNumber +
                "\nEnrolled Date: " + enrolledDate +
                "\nBonus Points Balance : " + bonusPointsBalance +
                "\nName: " + name +
                "\nEmail Address: " + eMailAddress +
                "\nMembership type: " + this.getMembershipLevel();
    }*/


    /** 'help method'
     * It helps to colorize the membership level.
     * It will be called in toString method.
     * @return ANSI value depending on membership level, Basic:purple, Silver:cyan, Gold:yellow.
     * if we created new membership f.eks Bronze, it will take the default value (ANSI_RESET)
     */
    public String getMembershipColor(){
        if(this.getMembershipLevel().equals("Basic")) return ANSI_PURPLE;
        if(this.getMembershipLevel().equals("Silver")) return ANSI_CYAN;
        if(this.getMembershipLevel().equals("Gold Level 1") || this.getMembershipLevel().equals("Gold Level 2")) return ANSI_YELLOW;
        return ANSI_RESET;
    }

}
