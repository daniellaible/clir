package de.dalai.clir.task.controller;

import static org.junit.jupiter.api.Assertions.*;

import de.dalai.clir.task.entity.Task;
import de.dalai.clir.task.entity.TaskStorage;
import de.dalai.clir.tool.TimeTool;
import de.dalai.clir.tool.UuidCreator;
import java.util.List;
import org.junit.jupiter.api.Test;

class TaskManagerTest {

  @Test
  void testAddTask() {
    Task testTask = generateTask("testTask", false);
    TaskManager manager = new TaskManager();
    manager.addTask("test", testTask);
  }

  @Test
  void testGetOpenTasksWithCollection() {
    TaskStorage storgae = TaskStorage.getInstance();
    storgae.cleanTaskStorage();

    Task testTaskOne = generateTask("testTask", false);
    Task testTaskTwo = generateTask("testTask2", false);
    Task testTaskThree = generateTask("testTask3", true);
    TaskManager manager = new TaskManager();
    manager.addTask("test", testTaskOne);
    manager.addTask("test", testTaskTwo);
    manager.addTask("test", testTaskThree);

    final List<Task> testCollection = manager.getAllOpenTasks("test");
    assertTrue(testCollection.size() > 0);
    assertTrue(testCollection.size() == 2);
  }

  @Test
  void testGetAllOpenTasks(){
    TaskStorage storage = TaskStorage.getInstance();
    storage.cleanTaskStorage();

    Task testTaskOne = generateTask("testTask", false);
    Task testTaskTwo = generateTask("testTask2", false);
    Task testTaskThree = generateTask("testTask3", false);
    Task testTaskFour = generateTask("testTask4", true);
    TaskManager manager = new TaskManager();
    manager.addTask("test", testTaskOne);
    manager.addTask("test1", testTaskTwo);
    manager.addTask("test2", testTaskThree);
    manager.addTask("test", testTaskFour);

    final List<Task> allOpenTasks = manager.getAllOpenTasks();
    assertEquals(3, allOpenTasks.size());
  }

  @Test
  void testGetClosedTasksOfCollection() {
    TaskStorage storage = TaskStorage.getInstance();
    storage.cleanTaskStorage();

    Task testTaskOne = generateTask("testTask", false);
    Task testTaskTwo = generateTask("testTask2", true);

    TaskManager manager = new TaskManager();
    manager.addTask("test", testTaskOne);
    manager.addTask("test", testTaskTwo);

    final List<Task> testTasks = manager.getClosedTasks("test");
    assertTrue(testTasks.size() == 1);
  }

  @Test
  void testGetClosedTasks(){
    TaskStorage storage = TaskStorage.getInstance();
    storage.cleanTaskStorage();

    Task testTaskOne = generateTask("testTask", false);
    Task testTaskTwo = generateTask("testTask2", true);
    Task testTaskThree = generateTask("testTask3", false);
    Task testTaskFour = generateTask("testTask4", true);

    TaskManager manager = new TaskManager();
    manager.addTask("test", testTaskOne);
    manager.addTask("test", testTaskTwo);
    manager.addTask("test1", testTaskThree);
    manager.addTask("test1", testTaskFour);

    final List<Task> actual = manager.getClosedTasks();
    final int expected = 2;
    assertEquals(expected, actual.size());
  }

  @Test
  void testGetAllTasksOfCollection() {
    TaskStorage storage = TaskStorage.getInstance();
    storage.cleanTaskStorage();

    Task testTaskOne = generateTask("testTask", false);
    Task testTaskTwo = generateTask("testTask2", true);
    TaskManager manager = new TaskManager();
    manager.addTask("test", testTaskOne);
    manager.addTask("test", testTaskTwo);

    final List<Task> actual = manager.getAllTasks("test");
    assertEquals(2, actual.size());
  }

  @Test
  void testGetAllTasks(){
    TaskStorage storage = TaskStorage.getInstance();
    storage.cleanTaskStorage();

    Task testTaskOne = generateTask("testTask", false);
    Task testTaskTwo = generateTask("testTask2", true);
    Task testTaskThree = generateTask("testTask3", false);
    Task testTaskFour = generateTask("testTask4", true);

    TaskManager manager = new TaskManager();
    manager.addTask("test", testTaskOne);
    manager.addTask("test", testTaskTwo);
    manager.addTask("test1", testTaskThree);
    manager.addTask("test1", testTaskFour);

    final List<Task> actual = manager.getAllTasks();
    assertEquals(4, actual.size());
  }

  private Task generateTask(final String description, final boolean isFinished){
    return Task.builder()
        .creationDate(TimeTool.getLocalDateTime())
        .uuid(UuidCreator.createUuid())
        .description(description)
        .finished(isFinished)
        .build();
  }
}