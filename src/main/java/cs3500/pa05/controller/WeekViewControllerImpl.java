package cs3500.pa05.controller;

import cs3500.pa05.model.DayName;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents a controller for a week view.
 */
public class WeekViewControllerImpl implements WeekViewController {
  //TODO: implement method to go through a week's days and get the tasks for taskque
  private Week week;
  private Popup addTaskPopup;
  private Popup taskWarnPopup;
  private Popup eventWarnPopup;
  private Popup addEventPopup;
  @FXML
  private Scene mainScene;
  @FXML
  private Button addEvent;
  @FXML
  private Button addTask;
  private Stage stage;
  // Add Task Controls
  @FXML
  private TextField taskName;
  @FXML
  private TextField taskDescription;
  @FXML
  private TextField taskDay;
  @FXML
  private Button taskDoneButton;
  // Add Event Controls
  @FXML
  private TextField eventName;
  @FXML
  private TextField eventDescription;
  @FXML
  private TextField eventDay;
  @FXML
  private TextField eventStart;
  @FXML
  private TextField eventDuration;
  @FXML
  private Button eventDoneButton;
  @FXML
  private TextField weekName;
  @FXML
  private Button saveName;
  @FXML
  private Label weekNameLabel;
  @FXML
  private Button closeTaskWarning;
  @FXML
  private Button closeEventWarning;
  @FXML
  private Label eventsOverview;
  @FXML
  private Label tasksOverview;
  @FXML
  private Label percentComplete;
  @FXML
  private TextField maxTasksGui;
  @FXML
  private TextField maxEventsGui;
  @FXML
  private Button setMaxButton;
  @FXML
  private Button setMaxMainButton;
  private Popup setMaxPopup;
  @FXML
  private VBox sundayVbox;
  @FXML
  private VBox mondayVbox;
  @FXML
  private VBox tuesdayVbox;
  @FXML
  private VBox wednesdayVbox;
  @FXML
  private VBox thursdayVbox;
  @FXML
  private VBox fridayVbox;
  @FXML
  private VBox saturdayVbox;
  private Popup taskMiniViewerPopup;
  private Popup eventMiniViewerPopup;
  @FXML
  private Label miniEventName;
  @FXML
  private Label miniEventDay;
  @FXML
  private Label miniEventDescription;
  @FXML
  private Label miniEventStartTime;
  @FXML
  private Label miniEventDuration;
  @FXML
  private Label miniTaskName;
  @FXML
  private Label miniTaskDay;
  @FXML
  private Label miniTaskDescription;
  @FXML
  private Label miniTaskComplete;
  @FXML
  private Button miniTaskClose;
  @FXML
  private Button save;
  @FXML
  private Button open;
  @FXML
  private Button miniEventClose;
  @FXML
  private VBox taskQueue;
  @FXML
  private Button loadButton;
  @FXML
  private Button saveButton;
  @FXML
  private TextField filepathText;
  private Popup editEventPopup;
  private Popup editTaskPopup;
  private Popup initLoadPopup;
  @FXML
  private TextField editEventName;
  @FXML
  private TextField editEventDescription;
  @FXML
  private TextField editEventDay;
  @FXML
  private TextField editEventStart;
  @FXML
  private TextField editEventDuration;
  @FXML
  private Button editEventDoneButton;
  @FXML
  private Button editTaskButtonDone;
  @FXML
  private TextField editTaskName;
  @FXML
  private TextField editTaskDescription;
  @FXML
  private TextField editTaskDay;

  private String filePath;

  /**
   * Constructs a WeekViewControllerImpl.
   *
   * @param stage the stage to display the view on
   * @param week  the week to display
   */
  public WeekViewControllerImpl(Stage stage, Week week) {
    this.stage = stage;
    this.week = week;
  }

  /**
   * Runs the calendar program
   *
   * @throws IllegalStateException
   *        if the view cannot be run
   */
  @FXML
  public void run() throws IllegalStateException, IOException {
    initLoadPopup();
    try {
      initAddEvent();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      initAddTask();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    initWeekName();
    try {
      initSetMax();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    initSaveWeek();
    initLoadWeek();
  }

  @FXML
  private void updateTaskQueue() {
    taskQueue.getChildren().clear();
    for (Task task : week.getTasks()) {
      Label taskLabel = new Label("Task Name: " + task.getName());
      boolean taskComplete = task.isComplete();
      Label completeLabel;
      if (taskComplete) {
        completeLabel = new Label("Status: Complete");
      } else {
        completeLabel = new Label("Status: Incomplete");
      }
      VBox queueVbox = new VBox(taskLabel, completeLabel);
      taskQueue.getChildren().add(queueVbox);
    }
  }

  @FXML
  private void initSaveWeek() {
    save.setOnAction(e -> {
      week.save(filePath);
    });
    final KeyCombination cmdT = new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);
    this.stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
      if (cmdT.match(e)) {
        week.save(filePath);
        e.consume();
      }
    });
  }

  @FXML
  private void initLoadWeek() {
    open.setOnAction(e -> {
      this.week.load(filePath);
      loadWeekIntoGUI();
      updateTaskQueue();
    });
    final KeyCombination cmdT = new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN);
    this.stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
      if (cmdT.match(e)) {
        week.save(filePath);
        e.consume();
      }
    });
  }

  private void loadWeekIntoGUI() {
    // Clear the existing GUI elements
    sundayVbox.getChildren().clear();
    mondayVbox.getChildren().clear();
    tuesdayVbox.getChildren().clear();
    wednesdayVbox.getChildren().clear();
    thursdayVbox.getChildren().clear();
    fridayVbox.getChildren().clear();
    saturdayVbox.getChildren().clear();
    taskQueue.getChildren().clear();

    // Load the tasks and events into the GUI
    for (Task task : week.getTasks()) {
      showTask(task);
    }
    for (Event event : week.getEvents()) {
      showEvent(event);
    }

    // Update the week name label
    weekNameLabel.setText(week.getName());

    // Update the week overview
    updateWeekOverview();
  }

  @FXML
  private void initLoadPopup() throws IOException {
    initLoadPopup = new Popup();
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("bujoPrompt.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    initLoadPopup.getContent().add((Node) s.getRoot());
    makePopup(initLoadPopup);

    loadButton.setOnAction(e -> {
      initLoadPopup.hide();
      String filePath = filepathText.getText();
      this.week.load(filePath);
      loadWeekIntoGUI();
    });

    saveButton.setOnAction(e -> {
      initLoadPopup.hide();
      filePath = filepathText.getText();
    });
  }

  @FXML
  private void initSetMax() throws IOException {
    setMaxPopup = new Popup();
    setMaxMainButton.setOnAction(e -> makePopup(setMaxPopup));
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("setMaxes.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    setMaxPopup.getContent().add((Node) s.getRoot());

    setMaxButton.setOnAction(e -> {
      setMaxPopup.hide();
      int maxTasks = Integer.parseInt(maxTasksGui.getText());
      int maxEvents = Integer.parseInt(maxEventsGui.getText());
      this.week.setMaxTasks(maxTasks);
      this.week.setMaxEvents(maxEvents);
    });
  }

  @FXML
  private void makePopup(Popup inPopup) {
    inPopup.show(this.stage);
  }

  @FXML
  private void initAddTask() throws IOException {
    addTaskPopup = new Popup();
    addTask.setOnAction(e -> makePopup(addTaskPopup));
    final KeyCombination cmdT = new KeyCodeCombination(KeyCode.T, KeyCombination.SHORTCUT_DOWN);
    this.stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
      if (cmdT.match(e)) {
        makePopup(addTaskPopup);
        e.consume();
      }
    });
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("taskPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    addTaskPopup.getContent().add((Node) s.getRoot());

    taskDoneButton.setOnAction(e -> taskButtonSetter());
  }

  @FXML
  private void initAddEvent() throws IOException {
    this.addEventPopup = new Popup();
    final KeyCombination cmdE = new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN);
    addEvent.setOnAction(e -> makePopup(addEventPopup));
    this.stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
      if (cmdE.match(e)) {
        makePopup(addEventPopup);
        e.consume();
      }
    });
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("eventPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    addEventPopup.getContent().add((Node) s.getRoot());
    eventDoneButton.setOnAction(e -> eventButtonSetter());
  }

  private void taskButtonSetter() {
    addTaskPopup.hide();
    if (!(taskDay.getText().equals("") || taskName.getText().equals(""))) {
      DayName day = DayName.valueOf(taskDay.getText().toUpperCase());
      String taskNameString = this.taskName.getText();
      String description = this.taskDescription.getText();
      updateWeekTasks(day, taskNameString, description);
      taskName.clear();
      taskDescription.clear();
      taskDay.clear();
    }
  }

  private void eventButtonSetter() {
    addEventPopup.hide();
    if (!(eventDay.getText().equals("") || eventStart.getText().equals("")
        || eventDuration.getText().equals(""))) {
      DayName day = DayName.valueOf(eventDay.getText().toUpperCase());
      String name = this.eventName.getText();
      String description = this.eventDescription.getText();
      String start = this.eventStart.getText();
      int duration = Integer.parseInt(this.eventDuration.getText());
      updateWeekEvents(day, name, description, start, duration);
      eventDay.clear();
      eventStart.clear();
      eventDescription.clear();
      eventDuration.clear();
      eventName.clear();


    }
  }

  private void updateWeekTasks(DayName day, String taskName, String description) {
    if (!(this.week.isTasksFull(day))) {
      Task tempTask = new Task(taskName, description, day);
      this.week.addDayTask(day, tempTask);
      updateTaskQueue();
      showTask(tempTask);
      updateWeekOverview();
    } else {
      try {
        addTaskPopup.hide();
        taskWarning();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
  }

  private void updateWeekEvents(DayName day, String name, String description, String start,
                                int duration) {
    if (!(this.week.isEventsFull(day))) {
      Event tempEvent = new Event(name, description, day, start, duration);
      this.week.addDayEvent(day, tempEvent);
      showEvent(tempEvent);
      updateWeekOverview();
    } else {
      try {
        addEventPopup.hide();
        eventWarning();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
  }

  @FXML
  private void showEvent(Event event) {
    DayName day = event.getDayOfWeek();
    Label eventLabel = new Label(event.getName());
    String description = event.getDescription();
    String startTime = event.getStartTime();
    String duration = Integer.toString(event.getDuration());
    Button deleteButton = new Button("X");
    Button editButton = new Button("Edit");
    HBox dayHbox = new HBox(eventLabel, deleteButton, editButton);
    editButton.setOnAction(e -> {
      try {
        showEditEvent(event);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    deleteButton.setOnAction(e -> {
      this.week.removeDayEvent(day, event);
      VBox parent = (VBox) dayHbox.getParent();
      parent.getChildren().remove(dayHbox);
      updateWeekOverview();
    });
    eventLabel.setOnMouseClicked(mouseEvent -> {
      try {
        showEventMiniViewer(event.getName(), description,
            startTime, duration, day);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    eventLabel.setWrapText(true);
    eventLabel.setMaxWidth(100);
    eventLabel.setMinWidth(100);
    eventLabel.setMinHeight(50);
    eventLabel.setMaxHeight(50);
    eventLabel.setAlignment(Pos.CENTER);
    eventLabel.setTextAlignment(TextAlignment.CENTER);
    eventLabel.setStyle("-fx-background-color: #00ff00");
    deleteButton.setMinWidth(50);
    deleteButton.setMaxWidth(50);
    deleteButton.setMinHeight(50);
    deleteButton.setMaxHeight(50);
    deleteButton.setAlignment(Pos.CENTER);
    deleteButton.setTextAlignment(TextAlignment.CENTER);
    deleteButton.setStyle("-fx-background-color: #ff4d4d");
    editButton.setMinWidth(50);
    editButton.setMaxWidth(50);
    editButton.setMinHeight(50);
    editButton.setMaxHeight(50);
    editButton.setAlignment(Pos.CENTER);
    editButton.setTextAlignment(TextAlignment.CENTER);
    editButton.setStyle("-fx-background-color: #3bb0ff");
    switch (day) {
      case SUNDAY:
        sundayVbox.getChildren().add(dayHbox);
        break;
      case MONDAY:
        mondayVbox.getChildren().add(dayHbox);
        break;
      case TUESDAY:
        tuesdayVbox.getChildren().add(dayHbox);
        break;
      case WEDNESDAY:
        wednesdayVbox.getChildren().add(dayHbox);
        break;
      case THURSDAY:
        thursdayVbox.getChildren().add(dayHbox);
        break;
      case FRIDAY:
        fridayVbox.getChildren().add(dayHbox);
        break;
      case SATURDAY:
        saturdayVbox.getChildren().add(dayHbox);
      default:
        throw new IllegalArgumentException("Invalid day");
    }
  }

  @FXML
  private void showTask(Task task) {

    for (Task weekTask : week.getTasks()) {
      Label taskLabel = new Label(task.getName());
      String description = task.getDescription();
      DayName dayName = task.getDayOfWeek();
      Boolean complete = task.isComplete();
      Button taskButton = new Button("X");
      Button taskCompleteButton = new Button("Done");
      Button editTaskButton = new Button("Edit");
      HBox dayHbox = new HBox(taskLabel, taskCompleteButton, taskButton, editTaskButton);
      editTaskButton.setOnAction(e -> {
        try {
          showEditTask(task);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      });
      taskButton.setOnAction(e -> {
        this.week.removeDayTask(task.getDayOfWeek(), task);
        updateWeekOverview();
        VBox parent = (VBox) dayHbox.getParent();
        parent.getChildren().remove(dayHbox);
        updateTaskQueue();
      });
      taskCompleteButton.setOnAction(e -> {
        this.week.completeDayTask(task.getDayOfWeek(), task);
        updateWeekOverview();
        updateTaskQueue();
      });
      taskLabel.setOnMouseClicked(mouseEvent -> {
        try {
          showTaskMiniViewer(task.getName(), task.getDescription(),
              task.isComplete(), task.getDayOfWeek());
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      });
      taskLabel.setWrapText(true);
      taskLabel.setMaxWidth(100);
      taskLabel.setMinWidth(100);
      taskLabel.setMinHeight(50);
      taskLabel.setMaxHeight(50);
      taskLabel.setAlignment(Pos.CENTER);
      taskLabel.setTextAlignment(TextAlignment.CENTER);
      taskLabel.setStyle("-fx-background-color: #82ff8c");
      taskButton.setMinWidth(50);
      taskButton.setMaxWidth(50);
      taskButton.setMinHeight(50);
      taskButton.setMaxHeight(50);
      taskButton.setAlignment(Pos.CENTER);
      taskButton.setTextAlignment(TextAlignment.CENTER);
      taskButton.setStyle("-fx-background-color: #ff4d4d");
      taskCompleteButton.setMinWidth(50);
      taskCompleteButton.setMaxWidth(50);
      taskCompleteButton.setMinHeight(50);
      taskCompleteButton.setMaxHeight(50);
      taskCompleteButton.setAlignment(Pos.CENTER);
      taskCompleteButton.setTextAlignment(TextAlignment.CENTER);
      taskCompleteButton.setStyle("-fx-background-color: #57a8ff");
      editTaskButton.setMinWidth(50);
      editTaskButton.setMaxWidth(50);
      editTaskButton.setMinHeight(50);
      editTaskButton.setMaxHeight(50);
      editTaskButton.setAlignment(Pos.CENTER);
      editTaskButton.setTextAlignment(TextAlignment.CENTER);
      editTaskButton.setStyle("-fx-background-color: #3bb0ff");
      switch (task.getDayOfWeek()) {
        case SUNDAY:
          sundayVbox.getChildren().add(dayHbox);
          break;
        case MONDAY:
          mondayVbox.getChildren().add(dayHbox);
          break;
        case TUESDAY:
          tuesdayVbox.getChildren().add(dayHbox);
          break;
        case WEDNESDAY:
          wednesdayVbox.getChildren().add(dayHbox);
          break;
        case THURSDAY:
          thursdayVbox.getChildren().add(dayHbox);
          break;
        case FRIDAY:
          fridayVbox.getChildren().add(dayHbox);
          break;
        case SATURDAY:
          saturdayVbox.getChildren().add(dayHbox);
          break;
        default:
          throw new IllegalArgumentException("Invalid day");
      }
    }
  }

  /**
   * Shows an event after it has been edited
   *
   * @param event
   *        the event to be displayed
   * @throws IOException
   *        if the fxml file cannot be found
   */
  @FXML
  public void showEditEvent(Event event) throws IOException {
    this.editEventPopup = new Popup();
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("editEventPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    editEventPopup.getContent().add((Node) s.getRoot());
    editEventDoneButton.setOnAction(e -> editEventSetter(event));
    makePopup(editEventPopup);
  }

  @FXML
  private void showEditTask(Task task) throws IOException {
    this.editTaskPopup = new Popup();
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("editTaskPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    editTaskPopup.getContent().add((Node) s.getRoot());
    editTaskButtonDone.setOnAction(e -> editTaskSetter(task));
    makePopup(editTaskPopup);
  }

  @FXML
  private void editEventSetter(Event event) {
    editEventPopup.hide();
    week.getEvents().remove(event);
    event.setDayOfWeek(DayName.valueOf(editEventDay.getText().toUpperCase()));
    event.setDescription(this.editEventDescription.getText());
    event.setName(this.editEventName.getText());
    event.setStartTime(this.editEventStart.getText());
    event.setDuration(Integer.parseInt(this.editEventDuration.getText()));
    updateEventView(event);
  }

  private TextFlow createTextFlowWithLinks(String description) {
    TextFlow textFlow = new TextFlow();

    String[] words = description.split("\\s+");

    for (String word : words) {
      if (word.startsWith("http://") || word.startsWith("https://")) {
        // If the word is a URL, create a Hyperlink
        Hyperlink link = new Hyperlink(word);
        link.setOnAction(e -> {
          try {
            // Open the link in the default web browse
            Desktop.getDesktop().browse(new URI(link.getText()));
          } catch (Exception ex) {
            throw new RuntimeException(ex);
          }
        });
        textFlow.getChildren().add(link);
      } else {
        // If the word is not a URL, create a Text
        Text text = new Text(word + " ");  // Add a space after the word
        textFlow.getChildren().add(text);
      }
    }

    return textFlow;
  }

  @FXML
  private void showEventMiniViewer(String name, String description, String startTime,
                                  String duration,
                                  DayName day) throws IOException {
    this.eventMiniViewerPopup = new Popup();
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("miniEventPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    eventMiniViewerPopup.getContent().add((Node) s.getRoot());
    miniEventName.setText(name);
    miniEventDescription.setText(description);
    miniEventDay.setText(day.toString());
    miniEventDuration.setText(duration);
    miniEventStartTime.setText(startTime);
    miniEventClose.setOnAction(e -> eventMiniViewerPopup.hide());
    makePopup(eventMiniViewerPopup);
  }

  @FXML
  private void editTaskSetter(Task task) {
    editTaskPopup.hide();
    week.getTasks().remove(task);
    task.setDayOfWeek(DayName.valueOf(editTaskDay.getText().toUpperCase()));
    task.setDescription(this.editTaskDescription.getText());
    task.setName(this.editTaskName.getText());
    week.addDayTask(task.getDayOfWeek(), task);
  }

  public void updateEventView(Event event) {
    VBox dayVbox;
    switch (event.getDayOfWeek()) {
      case SUNDAY:
        dayVbox = sundayVbox;
        break;
      case MONDAY:
        dayVbox = mondayVbox;
        break;
      case TUESDAY:
        dayVbox = tuesdayVbox;
        break;
      case WEDNESDAY:
        dayVbox = wednesdayVbox;
        break;
      case THURSDAY:
        dayVbox = thursdayVbox;
        break;
      case FRIDAY:
        dayVbox = fridayVbox;
        break;
      case SATURDAY:
        dayVbox = saturdayVbox;
        break;
      default:
        throw new IllegalArgumentException("Invalid day");
    }

    for (Node node : dayVbox.getChildren()) {
      if (node instanceof HBox) {
        HBox hbox = (HBox) node;

        Label eventLabel = (Label) hbox.getChildren().get(0);
        eventLabel.setText(event.getName());

        HBox eventNameHbox = (HBox) eventMiniViewerPopup.getContent().get(0);
        TextField eventNameTextField = (TextField) eventNameHbox.getChildren().get(1);
        String eventNameString = eventNameTextField.getText();
        miniEventName = new Label(eventNameString);

        HBox eventStartTimeHbox = (HBox) eventMiniViewerPopup.getContent().get(3);
        TextField eventStartTimeTextField = (TextField) eventStartTimeHbox.getChildren().get(1);
        String eventStartTimeString = eventStartTimeTextField.getText();
        miniEventStartTime = new Label(eventStartTimeString);

        HBox eventDurationHbox = (HBox) eventMiniViewerPopup.getContent().get(4);
        TextField eventDurationTextField = (TextField) eventDurationHbox.getChildren().get(1);
        String eventDurationString = eventDurationTextField.getText();
        miniEventDuration = new Label(eventDurationString);

      }
    }
  }

  public void updateTaskView(Task task) {

    VBox dayVbox;
    switch (task.getDayOfWeek()) {
      case SUNDAY:
        dayVbox = sundayVbox;
        break;
      case MONDAY:
        dayVbox = mondayVbox;
        break;
      case TUESDAY:
        dayVbox = tuesdayVbox;
        break;
      case WEDNESDAY:
        dayVbox = wednesdayVbox;
        break;
      case THURSDAY:
        dayVbox = thursdayVbox;
        break;
      case FRIDAY:
        dayVbox = fridayVbox;
        break;
      case SATURDAY:
        dayVbox = saturdayVbox;
        break;
      default:
        throw new IllegalArgumentException("Invalid day");
    }

    for (Node node : dayVbox.getChildren()) {
      if (node instanceof HBox) {
        HBox hbox = (HBox) node;

        Label taskLabel = (Label) hbox.getChildren().get(0);
        taskLabel.setText(task.getName());

        HBox taskDescriptionHBox = (HBox) taskMiniViewerPopup.getContent().get(2);
        TextField taskDescriptionTextField = (TextField) taskDescriptionHBox.getChildren().get(1);
        String taskDescriptionString = taskDescriptionTextField.getText();
        miniTaskDescription = new Label(taskDescriptionString);

        HBox taskNameHbox = (HBox) taskMiniViewerPopup.getContent().get(0);
        TextField taskNameTextField = (TextField) taskNameHbox.getChildren().get(1);
        String taskNameString = taskNameTextField.getText();
        miniTaskName = new Label(taskNameString);

        HBox taskCompleteHbox = (HBox) taskMiniViewerPopup.getContent().get(3);
        TextField taskCompleteTextField = (TextField) taskCompleteHbox.getChildren().get(1);
        String taskCompleteString = taskCompleteTextField.getText();
        miniTaskComplete = new Label(taskCompleteString);

      }
    }
  }

  @FXML
  private void showTaskMiniViewer(String name, String description, boolean complete, DayName day)
      throws IOException {
    this.taskMiniViewerPopup = new Popup();
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("miniTaskPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    taskMiniViewerPopup.getContent().add((Node) s.getRoot());
    miniTaskName.setText(name);
    miniTaskDescription.setText(description);
    if (complete) {
      miniTaskComplete.setText("Yes");
    } else {
      miniTaskComplete.setText("No");
    }
    miniTaskDay.setText(day.toString());
    miniTaskClose.setOnAction(e -> taskMiniViewerPopup.hide());
    makePopup(taskMiniViewerPopup);
  }

  @FXML
  private void eventWarning() throws IOException {
    this.eventWarnPopup = new Popup();
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("eventWarningPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    eventWarnPopup.getContent().add((Node) s.getRoot());
    closeEventWarning.setOnAction(e -> eventWarnPopup.hide());
    makePopup(eventWarnPopup);
  }

  @FXML
  private void taskWarning() throws IOException {
    taskWarnPopup = new Popup();
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("taskWarningPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    taskWarnPopup.getContent().add((Node) s.getRoot());
    closeTaskWarning.setOnAction(e -> taskWarnPopup.hide());
    makePopup(taskWarnPopup);
  }


  @FXML
  private void initWeekName() {
    saveName.setOnAction(e -> {
      this.week.setName(weekName.getText());
      weekNameLabel.setText(weekName.getText());
    });
  }

  @FXML
  private void updateWeekOverview() {
    eventsOverview.setText(Integer.toString(this.week.getNumEvents()));
    tasksOverview.setText(Integer.toString(this.week.getNumTasks()));
    Double temppc = ((double) Math.round((this.week.getPercentTasksComplete() * 100) * 100)) / 100;
    String formattedPercentComplete = temppc + "%";
    percentComplete.setText(formattedPercentComplete);
  }

  private boolean isValidUrl(String url) {
    String regex = "^(http|https)://.*$";
    return url.matches(regex);
  }
}
