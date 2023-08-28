package cs3500.pa05.view;

import cs3500.pa05.controller.WeekViewController;
import cs3500.pa05.controller.WeekViewControllerImpl;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents an implemented view for the schedule
 */
public class BujoGuiViewImpl implements BujoGuiView {
  private FXMLLoader loader;

  /**
   * Constructs a view for the schedule.
   *
   * @param controller the controller for the view
   */
  public BujoGuiViewImpl(WeekViewController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("bujo.fxml"));
    this.loader.setController(controller);
  }

  /**
   * Loads the view.
   *
   * @return the scene of the view
   * @throws IllegalStateException
   *        if the scene could not be loaded
   */
  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException e) {
      throw new IllegalStateException("Could not load scene");
    }
  }
}
