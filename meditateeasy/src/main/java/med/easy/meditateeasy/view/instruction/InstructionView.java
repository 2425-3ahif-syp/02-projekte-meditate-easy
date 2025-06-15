package med.easy.meditateeasy.view.instruction;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import med.easy.meditateeasy.MeditateEasyApp;
import med.easy.meditateeasy.model.Instruction;
import med.easy.meditateeasy.util.NavBarView;

public class InstructionView {
    private final VBox root = new VBox();
    private final ListView<Instruction> listView = new ListView<>();
    private final TextField searchField = new TextField();
    private final ChoiceBox<String> difficultyFilter = new ChoiceBox<>();
    private final NavBarView navBar = new NavBarView();
    private final HBox controls = new HBox();

    public InstructionView() {
        init();
    }

    private void init() {
        root.setPrefWidth(MeditateEasyApp.getX());
        root.setPrefHeight(MeditateEasyApp.getY());


        searchField.setPromptText("Suche...");
        controls.getChildren().addAll(searchField, difficultyFilter);
        controls.setSpacing(20);
        controls.setPadding(new Insets(20, 20, 10, 20));

        HBox.setHgrow(searchField, Priority.ALWAYS);

        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Instruction> call(ListView<Instruction> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Instruction item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(String.format("%s\nSchwierigkeit: %s",
                                    item.getTitle(),
                                    item.getDifficulty()));
                        }
                    }
                };
            }
        });
        Region spacer = new Region();
        spacer.setMinHeight(20);
        root.getChildren().addAll(navBar, controls, listView, spacer);
        VBox.setVgrow(listView, Priority.ALWAYS);
    }

    public VBox getRoot() { return root; }
    public ListView<Instruction> getListView() { return listView; }
    public TextField getSearchField() { return searchField; }
    public ChoiceBox<String> getDifficultyFilter() { return difficultyFilter; }
    public Stage getStage() { return (Stage) root.getScene().getWindow(); }

    public NavBarView getNavBar() {
        return navBar;
    }
}