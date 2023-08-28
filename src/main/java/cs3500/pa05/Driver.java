package cs3500.pa05;

import cs3500.pa05.controller.WeekViewController;
import cs3500.pa05.controller.WeekViewControllerImpl;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.BujoGuiView;
import cs3500.pa05.view.BujoGuiViewImpl;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Represents the driver for the schedule.
 */
public class Driver extends Application {
  /**
   * Starts the application.
   *
   * @param stage the primary stage for this application, onto which
   *              the application scene can be set.
   *              Applications may create other stages, if needed, but they will not be
   *              primary stages.
   */
  @Override
  public void start(Stage stage) {
    Week week = new Week();
    WeekViewController controller = new WeekViewControllerImpl(stage, week);
    BujoGuiView view = new BujoGuiViewImpl(controller);

    try {
      stage.setScene(view.load());
      stage.show();
      controller.run();
    } catch (IllegalArgumentException | IOException exc) {
      System.out.println("Could not load scene");
    }
  }

  public static void main(String[] args) {
    launch();
  }
}
