import controller.Controller;
import controller.Simulation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CellularAutomatonSimulation;
import model.automata.BicolorVarGoLAutomaton;
import model.automata.BicolorVarGoLState;
import model.automata.GameOfLifeAutomaton;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * Entry point for <i>The Game of Life</i> application.
 *
 */
public class SimulatorApplication extends Application {

  public static final int NUMBER_OF_ROWS = 40;
  public static final int NUMBER_OF_COLUMNS = 70;

  public static final Random GENERATOR = new Random();

  private static final String APP_NAME = "2D Cellular automata";
  private static final String VIEW_RESOURCE_PATH = "/view/view.fxml";

  private final Simulation simulation;
  private Stage primaryStage;
  private Parent view;

  /**
   * Creates a new {@code GameOfLifeApplication} instance.
   */
  public SimulatorApplication() {
    this.simulation =
      new CellularAutomatonSimulation<>(
              new BicolorVarGoLAutomaton(NUMBER_OF_COLUMNS,NUMBER_OF_ROWS),
              GENERATOR
      );
  }


  @Override
  public void start(Stage primaryStage) throws IOException {
    initializePrimaryStage(primaryStage);
    initializeView();
    showScene();
  }

  private void initializePrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle(APP_NAME);
    this.primaryStage.setOnCloseRequest(event -> Platform.exit());
    this.primaryStage.setResizable(false);
    this.primaryStage.sizeToScene();
  }

  private void initializeView() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    URL location = SimulatorApplication.class.getResource(VIEW_RESOURCE_PATH);
    loader.setLocation(location);
    view = loader.load();
    Controller controller = loader.getController();
    controller.setSimulation(simulation);
  }


  private void showScene() {
    Scene scene = new Scene(view);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
