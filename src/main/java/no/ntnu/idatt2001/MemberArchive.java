package no.ntnu.idatt2001;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * The member archive holds all the bonus members. The archive provides
 * functionality for adding members to the register, looking up bonuspoints
 * of given members, registering new bonuspoints and listing all the members.
 *
 * @author arne
 */
public class MemberArchive {

    // Use a HashMap, since the members have a unique member number.
    private HashMap<Integer, BonusMember> members;

    /**
     * Creates a new instance of MemberArchive.
     */
    public MemberArchive() {
        this.members = new HashMap<>();
        this.fillRegisterWithTestdata();
    }

    /**
     * Adds a new member to the register. The new member must have a memebr number
     * different from exsisting members. If not, the new member will not be added.
     *
     * @return {@code true} if the new member was added successfully,
     *         {@code false} if the new member could not be added, due to a
     *          membernumber that allready exsists.
     */
    public boolean addMember(BonusMember bonusMember) {
        boolean success = false;
        //TODO: Fill in your solution:ok
        if (!(this.members.containsKey(bonusMember.getMemberNumber())))
        {
            this.members.put(bonusMember.getMemberNumber(), bonusMember);
            success = true;
        }
        return success;
    }

    /**
     * Registers new bonuspoints to the member with the member number given
     * by the parameter {@code memberNumber}. If no member in the register
     * matches the provided member number, {@code false} is returned.
     *
     * @param memberNumber the member number to add the bonus points to
     * @param bonusPoints the bonus points to be added
     * @return {@code true} if bonuspoints were added successfully,
     *         {@code flase} if not.
     */
    public boolean registerPoints(int memberNumber, int bonusPoints) {
        boolean success = false;
        //TODO: Fill in your solution:ok
        if(this.members.containsKey(memberNumber)){
            this.members.get(memberNumber).registerBonusPoints(bonusPoints);
            success = true;
        }

        return success;
    }

    /**
     * Lists all members to the console.
     */
    public void listAllMembers() {
        //TODO: Fill in your solution:ok
        members.values().forEach(s->System.out.println(s.toString()+"\n---------------------------------"));
    }


    /**
     * Fills the register with some arbitrary members, for testing purposes.
     */
    private void fillRegisterWithTestdata() {
        BonusMember member = new BonusMember(1, LocalDate.now(), 10000, "Olsen, Ole", "ole@olsen.biz");
        this.members.put(member.getMemberNumber(), member);

        //the membership for this person should become Silver after 10000 addition
        member = new BonusMember(2, LocalDate.now(), 15000, "Jensen, Jens", "jens@jensen.biz");
        this.members.put(member.getMemberNumber(), member);
        member = new BonusMember(3, LocalDate.now(), 5000, "Lie, Linda", "linda@lie.no");
        this.members.put(member.getMemberNumber(), member);

        //the membership for this person should become Gold level 1 after 10000 addition
        member = new BonusMember(4, LocalDate.now(), 70000, "Paulsen, Paul", "paul@paulsen.org");
        this.members.put(member.getMemberNumber(), member);
        member = new BonusMember(5, LocalDate.now(), 75000, "FLo, Finn", "finn.flo@gmail.com");
        this.members.put(member.getMemberNumber(), member);

        //the membership for this person should become Gold level 2 after 10000 addition
        member = new BonusMember(6, LocalDate.now(), 85000, "Yanes, Moaaz", "moaazby@stud.ntnu.no");
        this.members.put(member.getMemberNumber(), member);


    }

    /*findPoints() - skal ta medlemsnummer og passord som argument og returnere antall
    poeng denne kunden har spart opp. Returner en negativ verdi hvis medlem med dette
    nummeret ikke fins, eller passord er ugyldig*/
    public int findPoints(int memberNumber, String password){
        //TODO: Fill in your solution:ok
        if(this.members.containsKey(memberNumber) && this.members.get(memberNumber).checkPassword(password)){
            return this.members.get(memberNumber).getBonusPointsBalance();
        }
        return -1;

    }

    //will be used in testing, addMember method test, JUnit class
    protected int getNumberOfMembers() {
        return members.size();
    }

}
