package sample;

import javafx.scene.control.Label;

import java.io.IOException;


public interface GymManager {
    // adding new members
    void addNewMember(DefaultMember DefaultNewMember) throws IOException;
    // delete a member
    void deleteMember(String DefaultMemberShipNo);
    // getting list of members
    void PrintListOfMembers();
    // sorting items
    void sortAllMembersData();
    // saving data file
    void saveAllMembersDataFile();
    // export member details to txt file
    void getMemberReport();
    // search by membership number
    DefaultMember memberListCheckByMemberShipNoForGUI(String membershipNo, Label lblForName, Label lblForDate, Label lblForEmail, Label lblMemberType, Label lblForValidation);


}
