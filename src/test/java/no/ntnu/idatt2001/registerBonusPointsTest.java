package no.ntnu.idatt2001;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class registerBonusPointsTest {

    @Nested
    class AddNewPointsWithRightAdditionalPoints{

        @Test
        @DisplayName("Add new points to Basic member (no extra) [no change in the membership] ")
        void addNewPointToBasicNoChangeInMembership() {
            BonusMember basicMember = new BonusMember(5, LocalDate.now(), 10000, "FLo, Finn", "finn.flo@gmail.com");

            //Add the point(registerBonusPoints) then call the member getBonusPointsBalance.
            //registerBonusPoints is void method,
            //that's why we need to execute it and then bring the balance to check with expected value.
            basicMember.registerBonusPoints(5000);
            int actualBalance = basicMember.getBonusPointsBalance();

            assertEquals(15000, actualBalance ,"This method should add new points to the balance, basically");
        }

        @Test
        @DisplayName("Add new points to Silver member (20% extra) [no change in the membership] ")
        void addNewPointToSilverNoChangeInMembership() {
            BonusMember silverMember = new BonusMember(5, LocalDate.now(), 30000, "FLo, Finn", "finn.flo@gmail.com");

            //Add the point(registerBonusPoints) then call the member getBonusPointsBalance.
            //registerBonusPoints is void method,
            //that's why we need to execute it and then bring the balance to check with expected value.
            silverMember.registerBonusPoints(5000);
            int actualBalance = silverMember.getBonusPointsBalance();

            assertEquals(36000, actualBalance ,"This method should add new points to the Silver balance, with 20% of the new points");
        }

        @Test
        @DisplayName("Add new points to Gold member level 1 (30% extra) [no change in the membership] ")
        void addNewPointToGoldLvlOneNoChangeInMembership() {
            BonusMember goldMember = new BonusMember(5, LocalDate.now(), 75000, "FLo, Finn", "finn.flo@gmail.com");

            //Add the point(registerBonusPoints) then call the member getBonusPointsBalance.
            //registerBonusPoints is void method,
            //that's why we need to execute it and then bring the balance to check with expected value.
            goldMember.registerBonusPoints(5000);
            int actualBalance = goldMember.getBonusPointsBalance();

            assertEquals(81500, actualBalance ,"This method should add new points to the Gold level 1 balance, with 30% of the new points");
        }

        @Test
        @DisplayName("Add new points to Gold member level 2 (50% extra) [no change in the membership] ")
        void addNewPointToGoldLvlTwoNoChangeInMembership() {
            BonusMember goldMember = new BonusMember(5, LocalDate.now(), 90000, "FLo, Finn", "finn.flo@gmail.com");

            //Add the point(registerBonusPoints) then call the member getBonusPointsBalance.
            //registerBonusPoints is void method,
            //that's why need to execute it and then bring the balance to check with expected value.
            goldMember.registerBonusPoints(5000);
            int actualBalance = goldMember.getBonusPointsBalance();

            assertEquals(97500, actualBalance ,"This method should add new points to the Gold level 2 balance, with 50% of the new points");
        }

    }

    @Nested
    class AddNewPointsAndChangeTheMembership{

        @Test
        @DisplayName("[Basic -> Silver] Add new points to Basic member, and change the membership to Silver ")
        void addNewPointToBasicAndChangeMembershipToSilver() {
            BonusMember basicMember = new BonusMember(5, LocalDate.now(), 20000, "FLo, Finn", "finn.flo@gmail.com");
            basicMember.registerBonusPoints(10000);

            assertEquals("Silver", basicMember.getMembershipLevel() ,"This method should add new points to the balance, and change the membership to Silver");
        }

        @Test
        @DisplayName("[Silver -> Gold level 1] Add new points to Silver member, and change the membership to Gold level 1 ")
        void addNewPointToSilverAndChangeMembershipToGoldLevelOne() {
            BonusMember silverMember = new BonusMember(5, LocalDate.now(), 70000, "FLo, Finn", "finn.flo@gmail.com");
            silverMember.registerBonusPoints(10000);

            assertEquals("Gold Level 1", silverMember.getMembershipLevel() ,"This method should add new points to the balance, and change the membership to Gold level 1");
        }

        @Test
        @DisplayName("[Gold 1 -> Gold 2] Add new points to Gold level 1 member, and change the membership to Gold level 2 ")
        void addNewPointToSilverAndChangeMembershipToGoldLevelTwo() {
            BonusMember goldMember = new BonusMember(5, LocalDate.now(), 88000, "FLo, Finn", "finn.flo@gmail.com");
            goldMember.registerBonusPoints(10000);

            assertEquals("Gold Level 2", goldMember.getMembershipLevel() ,"This method should add new points to the balance, and change the membership to Gold level 2");
        }


    }

    @Nested

    class AddNewPointsManyTimes{

        @Test
        @DisplayName("Add new points respectively to the same member and check the balance")
        void addNewPointsRespectivelyAndCheckBalance(){

            BonusMember member = new BonusMember(5, LocalDate.now(), 20000, "FLo, Finn", "finn.flo@gmail.com");
            member.registerBonusPoints(30000);//now the membership is Silver and should add 20% next time.

            member.registerBonusPoints(30000);//now the membership is Gold 1 and should add 30% next time.

            member.registerBonusPoints(30000);//now the membership is Gold 2 and should add 50% next time.

            member.registerBonusPoints(30000);//should add 50%

            int expectedBalance = (int) (20000+30000+(30000*1.2)+(30000*1.3)+(30000*1.5));
            int actualBalance = member.getBonusPointsBalance();

            assertEquals(expectedBalance,actualBalance,"this method should add new points respectively with the right membership scaling factor ");

        }

        @Test
        @DisplayName("Add new points respectively to the same member and check the membership level")
        void addNewPointsRespectivelyAndCheckMembershipLevel(){

            BonusMember member = new BonusMember(5, LocalDate.now(), 20000, "FLo, Finn", "finn.flo@gmail.com");
            member.registerBonusPoints(30000);//now the membership is Silver.

            member.registerBonusPoints(30000);//now the membership is Gold 1.

            member.registerBonusPoints(30000);//now the membership is Gold 2.

            assertEquals("Gold Level 2",member.getMembershipLevel(),"this method should set the right membership level after adding new points respectively ");

        }

    }

    @Nested
    class NotSupportedNewPoints{

        @Test
        @DisplayName("New points is a negative number ")
        void NewPointsIsNegative(){
            BonusMember member = new BonusMember(5, LocalDate.now(), 20000, "FLo, Finn", "finn.flo@gmail.com");
            assertThrows(IllegalArgumentException.class,()->member.registerBonusPoints(-1),"adding negative number as new points should throw IllegalArgumentException");

        }

        @Test
        @DisplayName("New points is zero ")
        void NewPointsIsZero(){
            BonusMember member = new BonusMember(5, LocalDate.now(), 20000, "FLo, Finn", "finn.flo@gmail.com");
            assertThrows(IllegalArgumentException.class,()->member.registerBonusPoints(0),"adding 0 as new points should throw IllegalArgumentException");
        }


    }


}