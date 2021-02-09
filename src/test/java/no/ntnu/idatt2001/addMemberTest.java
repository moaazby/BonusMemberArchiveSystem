package no.ntnu.idatt2001;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class addMemberTest {

    @Nested
    class MemberIsAdded{
        @Test
        @DisplayName("add new member to the list, with a number which not exist in the list (none of the members has this number)")
        void addNewMemberWithNotExistingMemberNumber(){
            MemberArchive memberArchive = new MemberArchive();
            BonusMember newMember = new BonusMember(10, LocalDate.now(), 20000, "I am test 1", "test@stud.ntnu.no");
            assertTrue(memberArchive.addMember(newMember));

        }

        @Test
        @DisplayName("add new Member (new element) to the list")
        void addNewMemberToTheHashMap(){
            MemberArchive memberArchive = new MemberArchive();
            memberArchive.addMember(new BonusMember(7, LocalDate.now(), 20000, "Yanes, Moaaz", "test@stud.ntnu.no"));
            int actualNumberOfElement = memberArchive.getNumberOfMembers();

            assertEquals(7, actualNumberOfElement,"this method check if the new element has been added by checking the list size before and after adding");

        }

    }

    @Nested
    class MemberIsNotAdded{

        @Test
        @DisplayName("add new member to the list, with a number which exist in the list (a member in the list already has this number)")
        void addNewMemberWithExistingMemberNumber(){
            MemberArchive memberArchive = new MemberArchive();
            BonusMember newMember = new BonusMember(1, LocalDate.now(), 20000, "Yanes, Moaaz", "test@stud.ntnu.no");
            assertFalse(memberArchive.addMember(newMember));

        }


    }

    @Nested
    class NotSupportedNewMemberNumber{

        @Test
        @DisplayName("New member number is negative")
        void NewMemberNumberIsNegative(){
            MemberArchive memberArchive = new MemberArchive();
            BonusMember newMember = new BonusMember(-1, LocalDate.now(), 20000, "Yanes, Moaaz", "test@stud.ntnu.no");
            assertThrows(IllegalArgumentException.class,()->memberArchive.addMember(newMember),"using a negative number as new member number should throw IllegalArgumentException");

        }

        @Test
        @DisplayName("New member number is zero ")
        void NewMemberNumberIsNegativeZero(){
            MemberArchive memberArchive = new MemberArchive();
            BonusMember newMember = new BonusMember(0, LocalDate.now(), 20000, "Yanes, Moaaz", "test@stud.ntnu.no");
            assertThrows(IllegalArgumentException.class,()->memberArchive.addMember(newMember),"using 0 as new member number should throw IllegalArgumentException");
        }

    }



}