package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GymManagementSystemUI extends Application {

    private final static MyGymManager newMyGymManager = new MyGymManager();

    //Create UI For Search and Sorting
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Gym Management System");
        Pane rootPaneForGymManagementSystem = new Pane();
        rootPaneForGymManagementSystem.getStylesheets().add("gymManagementSystem.css");
        // adding items to pane
        Button searchViewButton = new Button("SEARCH VIEW");
        Button sortListButton = new Button("SORT MEMBER LIST");

        //adding background pictures
        Image imgForGymManagementSystem = new Image("file:main01.png");
        ImageView iVForGymManagementSystem = new ImageView();
        iVForGymManagementSystem.setImage(imgForGymManagementSystem);
        //add pic for root pane
        rootPaneForGymManagementSystem.getChildren().add(iVForGymManagementSystem);
        //align search view element in pane
        rootPaneForGymManagementSystem.getChildren().add(searchViewButton);
        searchViewButton.setLayoutY(330);
        // set alignment
        searchViewButton.setLayoutX(100);
        searchViewButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage searchStage = new Stage();
                Pane searchRootPane = new Pane();
                searchRootPane.getStylesheets().add("gymManagementSystem.css");

                //adding background pictures
                Image imgForGymManagementSystem = new Image("file:search01.png");

                ImageView iVForGymManagementSystem = new ImageView();
                iVForGymManagementSystem.setImage(imgForGymManagementSystem);
                searchRootPane.getChildren().add(iVForGymManagementSystem);

                Label memberNumberLbl = new Label("Membership Number");
                TextField memberNumberOutputTxt = new TextField();

                Label searchNameMemberLbl = new Label("Member Name");
                Label memberNameForSearchLbl = new Label();

                Label startMembershipDateLbl = new Label("Start Membership Date");
                Label startMembershipDateOutputLbl = new Label();

                Label memberEmailLbl = new Label("Member Email");
                Label memberEmailOutputLbl = new Label();

                Label memberTypeLbl = new Label("Member Type");
                Label memberTypeOutputLbl = new Label();

                Button searchForMembers = new Button("Search");
                Label lblForValidation = new Label();

                //adding element to pane
                searchRootPane.getChildren().add(memberNumberLbl);
                memberNumberLbl.setLayoutY(200);
                // set alignment
                memberNumberLbl.setLayoutX(20);
                // add colors to member number label
                memberNumberLbl.setStyle("-fx-text-fill: white;");

                searchRootPane.getChildren().add(memberNumberOutputTxt);
                memberNumberOutputTxt.setLayoutY(200);
                // set alignment
                memberNumberOutputTxt.setLayoutX(140);
                //create search button
                searchRootPane.getChildren().add(searchForMembers);
                searchForMembers.setLayoutY(250);
                // set alignment
                searchForMembers.setLayoutX(110);
                searchForMembers.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String newSearchDetailsForMembers = memberNumberOutputTxt.getText();
                        newMyGymManager.memberListCheckByMemberShipNoForGUI(newSearchDetailsForMembers,memberNameForSearchLbl,startMembershipDateOutputLbl,memberEmailOutputLbl,memberTypeOutputLbl,lblForValidation);
                    }
                });
                searchRootPane.getChildren().add(lblForValidation);
                lblForValidation.setLayoutY(350);
                // set alignment
                lblForValidation.setLayoutX(70);
                lblForValidation.setStyle("-fx-text-fill: white;");
                // first row
                searchRootPane.getChildren().add(searchNameMemberLbl);
                searchNameMemberLbl.setLayoutY(200);
                // set alignment
                searchNameMemberLbl.setLayoutX(370);

                searchRootPane.getChildren().add(memberNameForSearchLbl);
                memberNameForSearchLbl.setLayoutY(200);
                // set alignment
                memberNameForSearchLbl.setLayoutX(470);

                // second row
                searchRootPane.getChildren().add(startMembershipDateLbl);
                startMembershipDateLbl.setLayoutY(250);
                // set alignment
                startMembershipDateLbl.setLayoutX(330);

                searchRootPane.getChildren().add(startMembershipDateOutputLbl);
                startMembershipDateOutputLbl.setLayoutY(250);
                // set alignment
                startMembershipDateOutputLbl.setLayoutX(470);

                // third row
                searchRootPane.getChildren().add(memberEmailLbl);
                memberEmailLbl.setLayoutY(300);
                // set alignment
                memberEmailLbl.setLayoutX(370);

                searchRootPane.getChildren().add(memberEmailOutputLbl);
                memberEmailOutputLbl.setLayoutY(300);
                // set alignment
                memberEmailOutputLbl.setLayoutX(470);

                // fourth row
                searchRootPane.getChildren().add(memberTypeLbl);
                memberTypeLbl.setLayoutY(350);
                // set alignment
                memberTypeLbl.setLayoutX(370);

                searchRootPane.getChildren().add(memberTypeOutputLbl);
                memberTypeOutputLbl.setLayoutY(350);
                // set alignment
                memberTypeOutputLbl.setLayoutX(470);

                Scene searchScene = new Scene(searchRootPane, 590,590);
                searchStage.setTitle("Search Members");
                searchStage.setScene(searchScene);
                searchStage.setResizable(false);
                searchStage.show();

            }
        });
        //align sort list element in pane
        rootPaneForGymManagementSystem.getChildren().add(sortListButton);
        sortListButton.setLayoutY(380);
        // set alignment
        sortListButton.setLayoutX(100);
        sortListButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage sortingStage = new Stage();

                // column for membership number
                TableColumn<DefaultMember, String> membershipColumn = new TableColumn<>("Membership Number");
                membershipColumn.setMinWidth(140);
                membershipColumn.setCellValueFactory(new PropertyValueFactory<>("memberShipNumber"));

                // column for member name
                TableColumn<DefaultMember, String> memberNameColumn = new TableColumn<>("Member Name");
                memberNameColumn.setMinWidth(140);
                memberNameColumn.setCellValueFactory(new PropertyValueFactory<>("memberName"));

                // column for start membership date
                TableColumn<DefaultMember, Date> startMembershipDateColumn = new TableColumn<>("Start Membership Date");
                startMembershipDateColumn.setMinWidth(100);
                startMembershipDateColumn.setCellValueFactory(new PropertyValueFactory<>("startMemberShipDate"));

                // column for member email
                TableColumn<DefaultMember, String> memberEmailColumn = new TableColumn<>("Member Email");
                memberEmailColumn.setMinWidth(200);
                memberEmailColumn.setCellValueFactory(new PropertyValueFactory<>("memberEmail"));

                TableView<DefaultMember> tableForMember = new TableView<>();
                tableForMember.setItems(setMembersForTableView());
                tableForMember.getColumns().addAll(membershipColumn, memberNameColumn, startMembershipDateColumn, memberEmailColumn);

                VBox vboxForSortingStage = new VBox();
                vboxForSortingStage.getChildren().addAll(tableForMember);
                Scene newSceneForSortingStage = new Scene(vboxForSortingStage);
                sortingStage.setScene(newSceneForSortingStage);
                sortingStage.setTitle("Sorting List");
                sortingStage.setResizable(false);
                sortingStage.show();

            }
        });
        Scene newSceneForGymManagementSystem = new Scene(rootPaneForGymManagementSystem, 590, 590);
        primaryStage.setScene(newSceneForGymManagementSystem);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //creating observable list and add members
    public ObservableList<DefaultMember> setMembersForTableView(){
        return FXCollections.observableArrayList(newMyGymManager.memberListForCalculations);
    }

    public static void main(String[] args) {
        // ---------
        int membersCountForSystem = 0;
        boolean isClickExit = true;
        //creating object for MyGymManager

        System.out.println("Welcome to Gym Management System");
        System.out.println("--------------------------------");
        //loading all user data from log files
        newMyGymManager.readLogFiles();
        while (isClickExit) {
            membersCountForSystem += 1;
            System.out.println();
            Scanner userInput = new Scanner(System.in);

            // main menu
            System.out.println("0: Help view");
            System.out.println("1: add new members");
            System.out.println("2: delete a member");
            System.out.println("3: get detail preview of members");
            System.out.println("4: sort members list");
            System.out.println("5: GUI - visualise the list of members & search for members");
            System.out.println("7: save member details to txt file");
            System.out.println("8: Exit");

            //defining user input variable
            int inputCheckForUserInput01;

            while (true) {
                try {
                    System.out.print("> ");
                    inputCheckForUserInput01 = userInput.nextInt();
                } catch (InputMismatchException | IllegalArgumentException e) {
                    System.out.println("Invalid! Please provide valid integers.");
                    userInput.next();
                    continue;
                }
                break;
            }

            // check first user input
            switch (inputCheckForUserInput01) {
                case 0:
                    showTheHelpView();
                    break;
                case 1: // adding new members
                    insertMembersToTheSystem();
                    break;
                case 2: // delete members using membership no
                    deleteMembersFromSystem();
                    break;
                case 3: // print list of members in CLI
                    printListMembersInSystem();
                    break;
                case 4: // sort the items
                    sortMembersFromSystem();
                    break;
                case 5: //searching for members - GUI Component
                    Application.launch();
                    isClickExit = false;
                    System.out.println("Thank you for using Gym Manager System.");
                    break;
                case 7: // user data report saving to txt file
                    saveMemberDetailsToTXTFile();
                    break;
                case 8: // exit from the program
                    isClickExit = false;
                    System.out.println("Thank you for using Gym Manager System.");
                    break;
                default:
                    System.out.println("Invalid. Please try again");
            }

        }
    }
        // export all data to txt file
    private static void saveMemberDetailsToTXTFile() {
        newMyGymManager.getMemberReport();
        System.out.println("Data export to txt file successfully.");
    }
        // show the help view then user can get idea about how system works
    private static void showTheHelpView() {
        System.out.println("HELP VIEW");
        System.out.println("---------");
        System.out.println("1. Please read the help view carefully.");
        System.out.println("2. When you add members please provide real information and be careful to enter the correct method.");
        System.out.println("3. If you wont to delete member please provide correct membership number.");
        System.out.println("4. After add, delete or any modifications you do, please make sure to save using \"6\" command.");
        System.out.println("5. In GUI you can visualise the list of members and search for members using membership number.");
    }

    private static void saveAlldataForLogFilesAndUserFiles() {
        newMyGymManager.saveAllMembersDataFile();
        System.out.println("Data saved successfully.");
    }

    private static void sortMembersFromSystem() {
       newMyGymManager.sortAllMembersData();
    }

    private static void printListMembersInSystem() {
        newMyGymManager.PrintListOfMembers();
    }

    private static void deleteMembersFromSystem() {
        Scanner userInputForDeleteUser = new Scanner(System.in);
        System.out.print("Please enter membership number: ");
        String memberShipNumberForDeleteDate = userInputForDeleteUser.next();
        newMyGymManager.deleteMember(memberShipNumberForDeleteDate);
    }

    private static void insertMembersToTheSystem() {
        boolean isLeapYear = false;
        Scanner userInputForAddUsers = new Scanner(System.in);

        //defining user input variable
        int startMemberShipYearForGettingData;
        int startMemberShipMonthForGettingData;
        int startMemberShipDateForGettingData;

        // Getting user data for default member
        System.out.print("Please enter member name: ");
        String memberNameForGettingData = userInputForAddUsers.nextLine();

        System.out.print("Please enter membership number: ");
        String memberShipNumberForGettingData = userInputForAddUsers.next();

        //setting up membership year
        while (true) {
            try {
                System.out.print("Please enter start membership year: ");
                startMemberShipYearForGettingData = userInputForAddUsers.nextInt();
                if (Integer.toString(startMemberShipYearForGettingData).length() == 4) {
                if (startMemberShipYearForGettingData % 4 == 0) {
                    if (startMemberShipYearForGettingData % 100 == 0) {
                        if (startMemberShipYearForGettingData % 400 == 0) {
                            isLeapYear = true; // year is leap year
                        } else {
                            isLeapYear = false; // not a leap year
                        }
                    } else {
                        isLeapYear = true;
                    }
                } else {
                    isLeapYear = false;
                }
            }else {
                    System.out.println("Invalid year");
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                userInputForAddUsers.next();
                continue;
            }
            break;
        }
        //setting up membership month

        while (true) {
            try {
                System.out.print("Please enter start membership month: ");
                startMemberShipMonthForGettingData = userInputForAddUsers.nextInt();
                    if (startMemberShipMonthForGettingData >= 1 && startMemberShipMonthForGettingData <= 12) {
                    break;
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                userInputForAddUsers.next();
                continue;
            }
            break;
        }
        //setting up membership date
        while (true) {
            try {
                System.out.print("Please enter start membership date: ");
                startMemberShipDateForGettingData = userInputForAddUsers.nextInt();
                if (startMemberShipDateForGettingData == 2){
                    if (isLeapYear) { //check for the leap year
                        if (startMemberShipDateForGettingData >= 1 && startMemberShipDateForGettingData <= 29) {
                            break;
                        }else {
                            System.out.println("This Year is a Leap Year, Feb can only have 29 days.");
                            userInputForAddUsers.next();
                        }
                    }else {
                        if (startMemberShipDateForGettingData >= 1 && startMemberShipDateForGettingData <= 28) {
                            break;
                        }else {
                            System.out.println("This month can only have 28 days.");
                            userInputForAddUsers.next();
                        }
                    }
                }else if (startMemberShipMonthForGettingData == 1 || startMemberShipMonthForGettingData == 3 || startMemberShipMonthForGettingData == 5 || startMemberShipMonthForGettingData == 7 || startMemberShipMonthForGettingData == 8 || startMemberShipMonthForGettingData == 10 || startMemberShipMonthForGettingData == 12){
                    if (startMemberShipDateForGettingData >= 1 && startMemberShipDateForGettingData <= 31){
                        break;
                    }else {
                        System.out.println("This month can only have 31 days.");
                        userInputForAddUsers.next();
                    }
                }else {
                    if (startMemberShipDateForGettingData >= 1 && startMemberShipDateForGettingData <= 30){
                        break;
                    }else {
                        System.out.println("This month can only have 30 days.");
                        userInputForAddUsers.next();
                    }
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                userInputForAddUsers.next();
                continue;
            }
            break;
        }
        //creating object and pass date
        final Date newDate = new Date(startMemberShipDateForGettingData, startMemberShipMonthForGettingData, startMemberShipYearForGettingData);
        String memberEmailForGettingData;
        int atCount = 0;
        while (true) {
            try {
                System.out.print("Please enter member email: ");
                memberEmailForGettingData = userInputForAddUsers.next();
                // validate the email
                for (int i = 0; i < memberEmailForGettingData.length(); i++) {
                    if (memberEmailForGettingData.charAt(i) == '@'){
                        atCount += 1;
                    }

                }
                if (atCount == 1){ // there need to be one '@' symbol in email
                    break;
                }else{
                    System.out.println("Please provide a valid email.");
                    userInputForAddUsers.next();
                }
            } catch (IllegalArgumentException e) {
                System.out.print(e.getMessage());
                userInputForAddUsers.next();
                continue;
            }
            break;
        }
        boolean isHealthInsurance = false;
        // get information about health insurance
        System.out.println("Availability of health insurance (Y/N): ");
        String AvailabilityOfHealthInsurance = userInputForAddUsers.next().toUpperCase();

        if (AvailabilityOfHealthInsurance.equals("Y")) {
            isHealthInsurance = true;
        }

        //checking type of the member
        System.out.println("PLEASE SELECT THE TYPE OF THE MEMBER");
        System.out.println("____________________________________");
        System.out.println("D: Default Member");
        System.out.println("S: Student Member");
        System.out.println("O: Over60 Member");

        DefaultMember newMember = null;
        StudentMember newStudentMember = null;
        Over60Member newOver60Member = null;

        System.out.print("> ");
        String inputCheckForUserInput03 = userInputForAddUsers.next().toUpperCase();
        // defining Default member

        switch (inputCheckForUserInput03) {
            //check for the member type
            case "D": // if its a default member
                newMember = new DefaultMember(memberShipNumberForGettingData, memberNameForGettingData, newDate, memberEmailForGettingData, isHealthInsurance);
                newMyGymManager.addNewMember(newMember);
                break;
            case "S": // if its a student member
                System.out.print("Please enter school name: ");
                String studentSchoolName = userInputForAddUsers.next();

                System.out.print("Please enter relative name: ");
                String studentRelativeName = userInputForAddUsers.next();

                newStudentMember = new StudentMember(memberShipNumberForGettingData, memberNameForGettingData, newDate, memberEmailForGettingData, isHealthInsurance, studentSchoolName, studentRelativeName);
                newMyGymManager.addNewMember(newStudentMember);
                break;

            case "O": // if its a over60 member
                int over60MemberAge;
                while (true) {
                    try {
                        System.out.println("Please enter member age: ");
                        over60MemberAge = userInputForAddUsers.nextInt();
                        if (over60MemberAge >= 60 && over60MemberAge <= 100) {
                            break;
                        }else {
                            System.out.println("Its only over 60 members.");
                            userInputForAddUsers.next();
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.print(e.getMessage());
                        userInputForAddUsers.next();
                        continue;
                    }
                    break;
                }
                System.out.println("Please enter relative name: ");
                String over60RelativeName = userInputForAddUsers.nextLine();

                newOver60Member = new Over60Member(memberShipNumberForGettingData, memberNameForGettingData, newDate, memberEmailForGettingData, isHealthInsurance, over60MemberAge, over60RelativeName);
                newMyGymManager.addNewMember(newOver60Member);
                break;
            default:
                System.out.println("Invalid input");
        }
    }


}
