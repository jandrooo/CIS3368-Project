package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;


public class MainScreen {

    //gets all competitors
    public ObservableList<competitor> getCompetitors(){
        ObservableList<competitor> competitors = FXCollections.observableArrayList();
        competitors.add(new competitor("Alejandro", "UH", "Men", 146.5, 147.5, 0, 0, 0, "", "", ""));
        return competitors;
    }

    //For listener of choicebox and choicebox values
    public String weightTypeValue;
    public String bp = "Bench Press", s = "Squat", dl = "Deadlift";

    //Table and table columns
    @FXML
    TableView<competitor> table;
    @FXML
    TableColumn<competitor, String> competitorNameF;
    @FXML
    TableColumn<competitor, String> teamNameF;
    @FXML
    TableColumn<competitor, String> divisionF;
    @FXML
    TableColumn<competitor, Double> bodyWeightF;
    @FXML
    TableColumn<competitor, Double> weightClassF;
    @FXML
    TableColumn<competitor, Double> benchPressF;
    @FXML
    TableColumn<competitor, Double> squatF;
    @FXML
    TableColumn<competitor, Double> deadliftF;
    @FXML
    TableColumn<competitor, String> checksBPLift;
    @FXML
    TableColumn<competitor, String> checksSLift;
    @FXML
    TableColumn<competitor, String> checksDLlift;

    //for the competitor info display
    @FXML
    Label competitorNameDisplay;
    @FXML
    Label compTNDisplay;
    @FXML
    Label compDivDisplay;
    @FXML
    Label compBWDisplay;

    //add and delete buttons
    @FXML
    Button addButton;
    @FXML
    Button deleteButton;

    //lift buttons
    @FXML
    Button goodLift;
    @FXML
    Button badLift;

    //General data input
    @FXML
    TextField compIns;
    @FXML
    TextField teamIns;
    @FXML
    TextField divIns;
    @FXML
    TextField bdyWghtIns;
    @FXML
    TextField weightClssIns;

    //for choicebox options
    @FXML
    ChoiceBox<String> liftType;

    //weight input
    @FXML
    TextField weightInput;

    //where weights go
    @FXML
    HBox weightsGoHere;

    //actions for button
    public void addButtonClicked(){
        competitor competitor = new competitor();
        competitor.setCompetitorName(compIns.getText());
        competitor.setTeamName(teamIns.getText());
        competitor.setDivision(divIns.getText());
        competitor.setBodyWeight(Double.parseDouble(bdyWghtIns.getText()));
        competitor.setClassWeight(Double.parseDouble(weightClssIns.getText()));
        table.getItems().add(competitor);
        compIns.clear();
        teamIns.clear();
        divIns.clear();
        bdyWghtIns.clear();
        weightClssIns.clear();
    }

    public void deleteButtonClicked(){
        ObservableList<competitor> competitorSelected, allCompetitors;
        allCompetitors = table.getItems();
        competitorSelected = table.getSelectionModel().getSelectedItems();
        competitorSelected.forEach(allCompetitors::remove);
    }

    //puts values inside of table
    public void initialize() {
        competitorNameF.setCellValueFactory(new PropertyValueFactory<>("competitorName"));
        teamNameF.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        divisionF.setCellValueFactory(new PropertyValueFactory<>("division"));
        bodyWeightF.setCellValueFactory(new PropertyValueFactory<>("bodyWeight"));
        weightClassF.setCellValueFactory(new PropertyValueFactory<>("classWeight"));
        benchPressF.setCellValueFactory(new PropertyValueFactory<>("benchPress"));
        squatF.setCellValueFactory(new PropertyValueFactory<>("squat"));
        deadliftF.setCellValueFactory(new PropertyValueFactory<>("deadlift"));
        checksBPLift.setCellValueFactory(new PropertyValueFactory<>("bpChecker"));
        checksSLift.setCellValueFactory(new PropertyValueFactory<>("sChecker"));
        checksDLlift.setCellValueFactory(new PropertyValueFactory<>("dlChecker"));
        table.setItems(getCompetitors());

        //set items to be editable
        table.setEditable(true);
        competitorNameF.setCellFactory(TextFieldTableCell.forTableColumn());
        teamNameF.setCellFactory(TextFieldTableCell.forTableColumn());
        divisionF.setCellFactory(TextFieldTableCell.forTableColumn());
        bodyWeightF.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        weightClassF.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        benchPressF.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        squatF.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        deadliftF.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        checksBPLift.setCellFactory(TextFieldTableCell.forTableColumn());
        checksSLift.setCellFactory(TextFieldTableCell.forTableColumn());
        checksDLlift.setCellFactory(TextFieldTableCell.forTableColumn());

        //sets items for choicebox
        liftType.getItems().add(bp);
        liftType.getItems().add(s);
        liftType.getItems().add(dl);

        //listen for changes in weight lift selection(was trying to use for if else ended up finding another way)
        liftType.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> newValue = weightTypeValue);

        //listener for table selection to display info
        table.getSelectionModel().selectedItemProperty().addListener((sel, oldSelection, newSelection) -> {
            if (newSelection != null){
                competitor competitor = table.getSelectionModel().getSelectedItem();
                competitorNameDisplay.setText(competitor.getCompetitorName());
                compBWDisplay.setText(String.valueOf(competitor.getBodyWeight()));
                compDivDisplay.setText(competitor.getDivision());
                compTNDisplay.setText(competitor.getTeamName());
            }
        });

        //for hbox
        weightsGoHere.setSpacing(5);
    }

    //for edit changes
    public void onEditChangeC(TableColumn.CellEditEvent<competitor, String> competitorEdit){
        competitor competitor = table.getSelectionModel().getSelectedItem();
        competitor.setCompetitorName(competitorEdit.getNewValue());
    }
    public void onEditChangeT(TableColumn.CellEditEvent<competitor, String> competitorEdit) {
        competitor competitor = table.getSelectionModel().getSelectedItem();
        competitor.setTeamName(competitorEdit.getNewValue());
    }
    public void onEditChangeD(TableColumn.CellEditEvent<competitor, String> competitorEdit) {
        competitor competitor = table.getSelectionModel().getSelectedItem();
        competitor.setDivision(competitorEdit.getNewValue());
    }
    public void onEditChangeB(TableColumn.CellEditEvent<competitor, Double> competitorEdit){
        competitor competitor = table.getSelectionModel().getSelectedItem();
        competitor.setBodyWeight(competitorEdit.getNewValue());
    }
    public void onEditChangeW(TableColumn.CellEditEvent<competitor, Double> competitorEdit){
        competitor competitor = table.getSelectionModel().getSelectedItem();
        competitor.setClassWeight(competitorEdit.getNewValue());
    }
    public void onEditChangeBP(TableColumn.CellEditEvent<competitor, Double> competitorEdit){
        competitor competitor = table.getSelectionModel().getSelectedItem();
        competitor.setBenchPress(competitorEdit.getNewValue());
    }
    public void onEditChangeSq(TableColumn.CellEditEvent<competitor, Double> competitorEdit){
        competitor competitor = table.getSelectionModel().getSelectedItem();
        competitor.setSquat(competitorEdit.getNewValue());
    }
    public void onEditChangeDL(TableColumn.CellEditEvent<competitor, Double> competitorEdit){
        competitor competitor = table.getSelectionModel().getSelectedItem();
        competitor.setDeadlift(competitorEdit.getNewValue());
    }
    public void onEditChangeBPC(TableColumn.CellEditEvent<competitor, String> competitorEdit){
        competitor competitor = table.getSelectionModel().getSelectedItem();
        competitor.setBpChecker(competitorEdit.getNewValue());
    }
    public void onEditChangeSC(TableColumn.CellEditEvent<competitor, String> competitorEdit){
        competitor competitor = table.getSelectionModel().getSelectedItem();
        competitor.setSChecker(competitorEdit.getNewValue());
    }
    public void onEditChangeDLC(TableColumn.CellEditEvent<competitor, String> competitorEdit){
        competitor competitor = table.getSelectionModel().getSelectedItem();
        competitor.setDlChecker(competitorEdit.getNewValue());
    }

    //Pressing good lift button
    public void goodLiftClicked(){
        //to clear wieght box once clicked again
        weightsGoHere.getChildren().clear();
        //To calculate weights used
        double weight = Double.parseDouble(weightInput.getText());


        //counters for the weights that'll be used
        int fifties = 0, twentyFives = 0, twenties = 0, fifteens = 0, tens = 0, fives = 0, twoPFives = 0, onePTwoFives = 0, pFives = 0, pTwoFives = 0;

        if(liftType.getValue() == bp)
        {
            competitor competitor = table.getSelectionModel().getSelectedItem();
            competitor.setBenchPress(weight);
            competitor.setBpChecker("O");
            table.refresh();
        }
        else if(liftType.getValue() == s)
        {
            competitor competitor = table.getSelectionModel().getSelectedItem();
            competitor.setSquat(weight);
            competitor.setSChecker("O");
            table.refresh();
        }
        else if(liftType.getValue() == dl)
        {
            competitor competitor = table.getSelectionModel().getSelectedItem();
            competitor.setDeadlift(weight);
            competitor.setDlChecker("O");
            table.refresh();
        }

        //Subtracts the bar from total
        weight -= 20;

        //Weight calculation
        while (weight > 0)
        {
            if(weight >= 100)
            {
                weight -= 100;
                fifties++;
            }
            else if(weight >= 50)
            {
                weight -= 50;
                twentyFives++;
            }
            else if (weight >= 40)
            {
                weight -= 40;
                twenties++;
            }
            else if (weight >= 30)
            {
                weight -= 30;
                fifteens++;
            }
            else if (weight >= 20)
            {
                weight -= 20;
                tens++;
            }
            else if (weight >= 10)
            {
                weight -= 10;
                fives++;
            }
            else if (weight >= 5)
            {
                weight -= 5;
                twoPFives++;
            }
            else if (weight >= 2.5)
            {
                weight -= 2.5;
                onePTwoFives++;
            }
            else if (weight >= 1)
            {
                weight -= 1;
                pFives++;
            }
            else if (weight >= .5)
            {
                weight -= .5;
                pTwoFives++;
            }
        }

        //for weight display
        while(fifties != 0){
            Rectangle fifty = new Rectangle();
            fifty.setHeight(160);
            fifty.setWidth(20);
            Text fiftyText = new Text("50");
            fiftyText.setFill(Color.WHITE);
            StackPane fiftyStack = new StackPane();
            fiftyStack.getChildren().addAll(fifty, fiftyText);
            weightsGoHere.getChildren().addAll(fiftyStack);
            fifties--;
        }
        while(twentyFives != 0){
            Rectangle twentyFive = new Rectangle();
            twentyFive.setHeight(100);
            twentyFive.setWidth(20);
            Text twentyFiveText = new Text("25");
            twentyFiveText.setFill(Color.WHITE);
            StackPane twentyFiveStack = new StackPane();
            twentyFiveStack.getChildren().addAll(twentyFive, twentyFiveText);
            weightsGoHere.getChildren().add(twentyFiveStack);
            twentyFives--;
        }
        while (twenties != 0){
            Rectangle twenty = new Rectangle();
            twenty.setHeight(90);
            twenty.setWidth(20);
            Text twentyText = new Text("20");
            twentyText.setFill(Color.WHITE);
            StackPane twentyStack = new StackPane();
            twentyStack.getChildren().addAll(twenty, twentyText);
            weightsGoHere.getChildren().add(twentyStack);
            twenties--;
        }
        while (fifteens != 0){
            Rectangle fifteen = new Rectangle();
            fifteen.setHeight(80);
            fifteen.setWidth(15);
            Text fifteenText = new Text("15");
            fifteenText.setFill(Color.WHITE);
            StackPane fifteenStack = new StackPane();
            fifteenStack.getChildren().addAll(fifteen, fifteenText);
            weightsGoHere.getChildren().add(fifteenStack);
            fifteens--;
        }
        while (tens != 0){
            Rectangle ten = new Rectangle();
            ten.setHeight(70);
            ten.setWidth(15);
            Text tenText = new Text("10");
            tenText.setFill(Color.WHITE);
            StackPane tenStack = new StackPane();
            tenStack.getChildren().addAll(ten, tenText);
            weightsGoHere.getChildren().add(tenStack);
            tens--;
        }
        while (fives != 0){
            Rectangle five = new Rectangle();
            five.setHeight(60);
            five.setWidth(15);
            Text fiveText = new Text("5");
            fiveText.setFill(Color.WHITE);
            StackPane fiveStack = new StackPane();
            fiveStack.getChildren().addAll(five, fiveText);
            weightsGoHere.getChildren().add(fiveStack);
            fives--;
        }
        while (twoPFives != 0){
            Rectangle twoPfive = new Rectangle();
            twoPfive.setHeight(30);
            twoPfive.setWidth(10);
            Text twoPFiveText = new Text("2.5");
            twoPFiveText.setFill(Color.WHITE);
            StackPane twoPFiveStack = new StackPane();
            twoPFiveStack.getChildren().addAll(twoPfive, twoPFiveText);
            weightsGoHere.getChildren().add(twoPFiveStack);
            twoPFives--;
        }
        while (onePTwoFives != 0){
            Rectangle onePTwoFive = new Rectangle();
            onePTwoFive.setHeight(20);
            onePTwoFive.setWidth(10);
            Text onePTwoFiveText = new Text("1.25");
            onePTwoFiveText.setFill(Color.WHITE);
            StackPane onePtwoFiveStack = new StackPane();
            onePtwoFiveStack.getChildren().addAll(onePTwoFive, onePTwoFiveText);
            weightsGoHere.getChildren().add(onePtwoFiveStack);
            onePTwoFives--;
        }
        while (pFives != 0){
            Rectangle pFive = new Rectangle();
            pFive.setHeight(15);
            pFive.setWidth(10);
            Text pFiveText = new Text(".5");
            pFiveText.setFill(Color.WHITE);
            StackPane pFiveStack = new StackPane();
            pFiveStack.getChildren().addAll(pFive, pFiveText);
            weightsGoHere.getChildren().add(pFiveStack);
            pFives--;
        }
        while (pTwoFives != 0){
            Rectangle pTwoFive = new Rectangle();
            pTwoFive.setHeight(10);
            pTwoFive.setWidth(5);
            Text pTwoFiveText = new Text(".25");
            pTwoFiveText.setFill(Color.WHITE);
            StackPane pTwoFiveStack = new StackPane();
            pTwoFiveStack.getChildren().addAll(pTwoFive, pTwoFiveText);
            weightsGoHere.getChildren().add(pTwoFiveStack);
            pTwoFives--;
        }

    }



    //Pressing bad lift button
    public void badLiftClicked(){
        //to clear wieght box once clicked again
        weightsGoHere.getChildren().clear();
        //To calculate weights used
        double weight = Double.parseDouble(weightInput.getText());

        //counters for the weights that'll be used
        int fifties = 0, twentyFives = 0, twenties = 0, fifteens = 0, tens = 0, fives = 0, twoPFives = 0, onePTwoFives = 0, pFives = 0, pTwoFives = 0;

        if(liftType.getValue() == bp)
        {
            competitor competitor = table.getSelectionModel().getSelectedItem();
            competitor.setBenchPress(weight);
            competitor.setBpChecker("X");
            table.refresh();
        }
        else if(liftType.getValue() == s)
        {
            competitor competitor = table.getSelectionModel().getSelectedItem();
            competitor.setSquat(weight);
            competitor.setSChecker("X");
            table.refresh();
        }
        else if(liftType.getValue() == dl)
        {
            competitor competitor = table.getSelectionModel().getSelectedItem();
            competitor.setDeadlift(weight);
            competitor.setDlChecker("X");
            table.refresh();
        }

        //Subtracts the bar from total
        weight -= 20;

        //Weight calculation
        while (weight > 0)
        {
            if(weight >= 100)
            {
                weight -= 100;
                fifties++;
            }
            else if(weight >= 50)
            {
                weight -= 50;
                twentyFives++;
            }
            else if (weight >= 40)
            {
                weight -= 40;
                twenties++;
            }
            else if (weight >= 30)
            {
                weight -= 30;
                fifteens++;
            }
            else if (weight >= 20)
            {
                weight -= 20;
                tens++;
            }
            else if (weight >= 10)
            {
                weight -= 10;
                fives++;
            }
            else if (weight >= 5)
            {
                weight -= 5;
                twoPFives++;
            }
            else if (weight >= 2.5)
            {
                weight -= 2.5;
                onePTwoFives++;
            }
            else if (weight >= 1)
            {
                weight -= 1;
                pFives++;
            }
            else if (weight >= .5)
            {
                weight -= .5;
                pTwoFives++;
            }
        }

        //for weight display
        while(fifties != 0){
            Rectangle fifty = new Rectangle();
            fifty.setHeight(160);
            fifty.setWidth(20);
            Text fiftyText = new Text("50");
            fiftyText.setFill(Color.WHITE);
            StackPane fiftyStack = new StackPane();
            fiftyStack.getChildren().addAll(fifty, fiftyText);
            weightsGoHere.getChildren().addAll(fiftyStack);
            fifties--;
        }
        while(twentyFives != 0){
            Rectangle twentyFive = new Rectangle();
            twentyFive.setHeight(100);
            twentyFive.setWidth(20);
            Text twentyFiveText = new Text("25");
            twentyFiveText.setFill(Color.WHITE);
            StackPane twentyFiveStack = new StackPane();
            twentyFiveStack.getChildren().addAll(twentyFive, twentyFiveText);
            weightsGoHere.getChildren().add(twentyFiveStack);
            twentyFives--;
        }
        while (twenties != 0){
            Rectangle twenty = new Rectangle();
            twenty.setHeight(90);
            twenty.setWidth(20);
            Text twentyText = new Text("20");
            twentyText.setFill(Color.WHITE);
            StackPane twentyStack = new StackPane();
            twentyStack.getChildren().addAll(twenty, twentyText);
            weightsGoHere.getChildren().add(twentyStack);
            twenties--;
        }
        while (fifteens != 0){
            Rectangle fifteen = new Rectangle();
            fifteen.setHeight(80);
            fifteen.setWidth(15);
            Text fifteenText = new Text("15");
            fifteenText.setFill(Color.WHITE);
            StackPane fifteenStack = new StackPane();
            fifteenStack.getChildren().addAll(fifteen, fifteenText);
            weightsGoHere.getChildren().add(fifteenStack);
            fifteens--;
        }
        while (tens != 0){
            Rectangle ten = new Rectangle();
            ten.setHeight(70);
            ten.setWidth(15);
            Text tenText = new Text("10");
            tenText.setFill(Color.WHITE);
            StackPane tenStack = new StackPane();
            tenStack.getChildren().addAll(ten, tenText);
            weightsGoHere.getChildren().add(tenStack);
            tens--;
        }
        while (fives != 0){
            Rectangle five = new Rectangle();
            five.setHeight(60);
            five.setWidth(15);
            Text fiveText = new Text("5");
            fiveText.setFill(Color.WHITE);
            StackPane fiveStack = new StackPane();
            fiveStack.getChildren().addAll(five, fiveText);
            weightsGoHere.getChildren().add(fiveStack);
            fives--;
        }
        while (twoPFives != 0){
            Rectangle twoPfive = new Rectangle();
            twoPfive.setHeight(30);
            twoPfive.setWidth(10);
            Text twoPFiveText = new Text("2.5");
            twoPFiveText.setFill(Color.WHITE);
            StackPane twoPFiveStack = new StackPane();
            twoPFiveStack.getChildren().addAll(twoPfive, twoPFiveText);
            weightsGoHere.getChildren().add(twoPFiveStack);
            twoPFives--;
        }
        while (onePTwoFives != 0){
            Rectangle onePTwoFive = new Rectangle();
            onePTwoFive.setHeight(20);
            onePTwoFive.setWidth(10);
            Text onePTwoFiveText = new Text("1.25");
            onePTwoFiveText.setFill(Color.WHITE);
            StackPane onePtwoFiveStack = new StackPane();
            onePtwoFiveStack.getChildren().addAll(onePTwoFive, onePTwoFiveText);
            weightsGoHere.getChildren().add(onePtwoFiveStack);
            onePTwoFives--;
        }
        while (pFives != 0){
            Rectangle pFive = new Rectangle();
            pFive.setHeight(15);
            pFive.setWidth(10);
            Text pFiveText = new Text(".5");
            pFiveText.setFill(Color.WHITE);
            StackPane pFiveStack = new StackPane();
            pFiveStack.getChildren().addAll(pFive, pFiveText);
            weightsGoHere.getChildren().add(pFiveStack);
            pFives--;
        }
        while (pTwoFives != 0){
            Rectangle pTwoFive = new Rectangle();
            pTwoFive.setHeight(10);
            pTwoFive.setWidth(5);
            Text pTwoFiveText = new Text(".25");
            pTwoFiveText.setFill(Color.WHITE);
            StackPane pTwoFiveStack = new StackPane();
            pTwoFiveStack.getChildren().addAll(pTwoFive, pTwoFiveText);
            weightsGoHere.getChildren().add(pTwoFiveStack);
            pTwoFives--;
        }

    }

}
