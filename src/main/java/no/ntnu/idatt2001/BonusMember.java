package no.ntnu.idatt2001;

import java.time.LocalDate;

/**
 * @author Moaaz Yanes
 * @version 1.0 2020-02-08
 */
public class BonusMember {

    //some colors to make testing of output easier.
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

    /**
     * constuctor which creat an object of type BonusMember
     * @param memberNumber Member's number
     * @param enrolledDate Enrolled date
     * @param bonusPointsBalance Member's bonus points balance
     * @param name Member's name
     * @param eMailAddress Member's Email
     */
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

    /**
     * This method will return true if the password(argument) matches member's password(this.password).
     * False if does not match
     * @param password Member's password
     * @return true or false
     */
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    /**
     * This method register new bonus points for the member based on membership.
     * So this method calls the method this.membership.registerPoints which add the points to
     * member's balance with the right scaling percent
     * In addition, it calls the method this.checkAndSetMembership() to update the membership after
     * new point registration
     * @param newPoints new bonus points
     */
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

    /**
     * This method gets membership name with the right level, based on this.membership
     * @return String : Basic, Silver, Gold Level 1 and Gold Level 2.
     */
    public String getMembershipLevel(){
        String level = "";
        if(membership instanceof GoldMembership){
            level = (this.bonusPointsBalance < 90000)? " Level 1": " Level 2";
        }
        return membership.getMembershipName()+level;
    }

    /**
     * This method sets the right level (type) for the member based on the bonus balance
     * balance ∈ <0, 25000> [Basic]
     * balance ∈ [25000, 75000> [Silver]
     * balance ∈  [75000, +∞> [Gold]
     */
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

    /**
     * toString method
     * @return all member's details,
     * it adds special colors for number, balance and membership type
     * to make testing easier
     */
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
     * IntelliJ supports ANSI colors
     * Eclipse -> add plugin 'ANSI Escape in Console' from Eclipse marketplace
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
