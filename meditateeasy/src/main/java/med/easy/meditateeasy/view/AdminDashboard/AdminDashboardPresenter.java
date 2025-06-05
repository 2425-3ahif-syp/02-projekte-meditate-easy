package med.easy.meditateeasy.view.AdminDashboard;

import javafx.scene.Scene;
import javafx.stage.Stage;


public class AdminDashboardPresenter {
    private final AdminDashboardView view;

    public AdminDashboardPresenter(AdminDashboardView view) {
        this.view = view;
    }

    public static void show(Stage stage) {
        AdminDashboardView view = new AdminDashboardView();
        new AdminDashboardPresenter(view);

        Scene scene = new Scene(view.getRoot());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public AdminDashboardView getView() {
        return view;
    }
}
