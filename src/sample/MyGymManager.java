package sample;

import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import javafx.scene.control.Label;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.mongodb.client.model.Filters.eq;

public class MyGymManager<readLogFiles> implements GymManager, Serializable{ // MyGymManager implement by GymManager
    List<DefaultMember> memberListForCalculations = new ArrayList<>();
    static ObjectOutputStream logFilesDataSaving = null;
    static ObjectInputStream readLogFiles = null;

    /*
     * all @Override methods where define in GymManage interface
     * addNewMember - method for add members
     * deleteNewMembers - method for delete members
     * PrintListOfMembers - print all members in the list (System)
     * sortItem - sort all members base on there memberShipNumber
     * saveItem - save all user data to mongo
     */

    public void readLogFiles(){
        // handling the mongo connection information
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

        final String uriString = "mongodb+srv://admin:1234@cluster0.xjafm.mongodb.net/test";

        MongoClient mongoClient = MongoClients.create(uriString);

        MongoDatabase mongoDB = mongoClient.getDatabase("GMS");
        MongoCollection<Document> collection = mongoDB.getCollection("Cluster0");

        // add all data in mongo to list
        FindIterable<Document> findIterable = collection.find(new Document());
        //memberListForCalculations.add(findIterable);

        mongoClient.close();
        System.out.println("Connection Establish Successfully.");
    }

    @Override
    public void addNewMember(DefaultMember DefaultNewMember) {
        boolean isMemberAlreadyIn = false;
        if (memberListForCalculations.size() < 101){ // if there are available free space --> add members to list
            for (DefaultMember membershipNumberForIdCheck : memberListForCalculations){ // check for the membership number
                if(membershipNumberForIdCheck.getMemberShipNumber().equals(DefaultNewMember.getMemberShipNumber())){
                    isMemberAlreadyIn = true;
                    break;
                }
            }
            //membership number not in the system then its only add
            if (!isMemberAlreadyIn) {
                memberListForCalculations.add(DefaultNewMember);
                saveAllMembersDataFile(); // save to mongo
                System.out.println("Member add Successfully.");
                System.out.println("Free membership available: " + (100 - memberListForCalculations.size()));
            }else {
                System.out.println("This membership number already in the system.");
            }
        }else{
            System.out.println("No free space available."); // if list have 100 members --> error massage prompt
        }
    }

    @Override
    public void deleteMember(String DefaultMemberShipNo) {

        // handling the mongo connection information
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

        final String uriString = "mongodb+srv://admin:1234@cluster0.xjafm.mongodb.net/test";

        MongoClient mongoClient = MongoClients.create(uriString);

        MongoDatabase mongoDB = mongoClient.getDatabase("GMS");
        MongoCollection<Document> collection = mongoDB.getCollection("Cluster0");

        Bson userMembershipNo = eq("_id", DefaultMemberShipNo);

        Document doc = collection.findOneAndDelete(userMembershipNo);
        System.out.println(DefaultMemberShipNo + " Member Delete Successfully.");
    }

    @Override
    public void PrintListOfMembers() {

        for (DefaultMember membersPrintFromList: memberListForCalculations){
            System.out.println("Members available in the system");
            System.out.println();
            System.out.println("Membership number: " + membersPrintFromList.getMemberShipNumber());
            System.out.println("Member name: " + membersPrintFromList.getMemberName());
            System.out.println("Start Date: " + membersPrintFromList.getStartMemberShipDate());
            System.out.println("Member email: " + membersPrintFromList.getMemberEmail());
            System.out.println("Health insurance availability: " + membersPrintFromList.isMemberHasHealthInsurance());
            // check member type and print specific information
            if (membersPrintFromList instanceof StudentMember){
                System.out.println("Member type: " + "Student member ");
                System.out.println("School name: " + ((StudentMember) membersPrintFromList).getSchoolName());
                System.out.println("Guardian name: " + ((StudentMember) membersPrintFromList).getGuardianName());
                System.out.println("Health insurance availability: " + membersPrintFromList.isMemberHasHealthInsurance());
            }else if (membersPrintFromList instanceof  Over60Member){
                System.out.println("Member type: " + "Over60 member ");
                System.out.println("Member age: " + ((Over60Member) membersPrintFromList).getMemberAge());
                System.out.println("Member relative name: " + ((Over60Member) membersPrintFromList).getMemberRelative());
            }else {
                System.out.println("Member type: " + "Default member ");
            }

        }

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("Free membership available: " + (100 - memberListForCalculations.size()));
        System.out.println("In system: " + (memberListForCalculations.size()));
        // if there are no any member in list print error massage
        if (memberListForCalculations.size() == 0){
            System.out.println("There are no members.");
        }
    }

    @Override
    public void sortAllMembersData() {
        Collections.sort(memberListForCalculations);
        System.out.println(memberListForCalculations);
    }

    @Override
    public void saveAllMembersDataFile() {

        // handling the mongo connection information
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

        final String uriString = "mongodb+srv://admin:1234@cluster0.xjafm.mongodb.net/test";

        MongoClient mongoClient = MongoClients.create(uriString);

        MongoDatabase mongoDB = mongoClient.getDatabase("GMS");
        MongoCollection<Document> collection = mongoDB.getCollection("Cluster0");
        Document userData = new Document();


        for (int i = 0; i < memberListForCalculations.size(); i++){
            userData.append("_id", memberListForCalculations.get(i).getMemberShipNumber()).append("UserName", memberListForCalculations.get(i).getMemberName()).append("UserEmail",memberListForCalculations.get(i).getMemberEmail()).append("UserData",memberListForCalculations.get(i).getStartMemberShipDate().toString());
        }

        collection.insertOne(userData);
        mongoClient.close();
    }

    @Override
    public void getMemberReport() {
        //saving data to file
        FileOutputStream memberDetailsSavingToFile = null;
        try {
            memberDetailsSavingToFile = new FileOutputStream("membersDetails.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert memberDetailsSavingToFile != null;
        BufferedWriter MemberDetailsSavingBuffer = new BufferedWriter(new OutputStreamWriter(memberDetailsSavingToFile));
        //convert list to array
        DefaultMember[] membersArray = new DefaultMember[memberListForCalculations.size()];
        memberListForCalculations.toArray(membersArray);
        for (DefaultMember membersDetails : membersArray) {
            try {
                MemberDetailsSavingBuffer.write("Membership Number: " + membersDetails.getMemberShipNumber() + " Member Name: " + membersDetails.getMemberName() + "  Membership Startup Date: " + membersDetails.getStartMemberShipDate() + "  Member Email: " + membersDetails.getMemberEmail());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                MemberDetailsSavingBuffer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            MemberDetailsSavingBuffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DefaultMember memberListCheckByMemberShipNoForGUI(String membershipNo, Label lblForName, Label lblForDate, Label lblForEmail, Label lblMemberType, Label lblForValidation) {
        for (DefaultMember membersSearchFromList: memberListForCalculations){
            if (membersSearchFromList.getMemberShipNumber().equals(membershipNo)) {
                lblForName.setText(membersSearchFromList.getMemberName());
                lblForDate.setText(membersSearchFromList.getStartMemberShipDate().toString());
                lblForEmail.setText(membersSearchFromList.getMemberEmail());
                if (membersSearchFromList instanceof StudentMember) { // check member type
                    lblMemberType.setText("Student Member");
                } else if (membersSearchFromList instanceof Over60Member) {
                    lblMemberType.setText("Over 60 member");
                } else {
                    lblMemberType.setText("Default member");
                }
                break;
            }else {
                lblForName.setText("");
                lblForDate.setText("");
                lblForEmail.setText("");
                lblMemberType.setText("");
            }
        }

        return null;
    }

}

