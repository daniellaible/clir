package de.dalai.clir.task.entity;

import static org.junit.jupiter.api.Assertions.*;

import de.dalai.clir.tool.TimeTool;
import de.dalai.clir.tool.UuidCreator;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class TaskStorageTest {

  @Test
  public void testGetInstance() {
    TaskStorage storage = TaskStorage.getInstance();
    assertTrue(null != storage);
  }

  @Test
  public void testAddTaskTest() {
    Task task = buildTask("Test", false);
    TaskStorage storage = TaskStorage.getInstance();
    storage.addTask("test", task);
  }

  @Test
  void testGetCollections() {
    TaskStorage storage = TaskStorage.getInstance();
    storage.cleanTaskStorage();
    List<String> collections = storage.getCollections();
    assertTrue(null != collections);
    var tempCollectionSize = collections.size();
    assertTrue(tempCollectionSize >= 0);

    Task task = buildTask("Test", false);
    storage.addTask("test", task);
    collections = storage.getCollections();
    assertTrue(tempCollectionSize + 1 == collections.size());
    assertEquals("test", collections.get(0));
  }

  @Test
  void testGetTasksFromCollection() {
    TaskStorage storage = TaskStorage.getInstance();
    storage.cleanTaskStorage();
    final List<Task> test = storage.getTasksFromCollection("test");
    assertTrue(null != test);
  }

  @Test
  void testSerialized() throws IOException {
    assertTrue(true);
    TaskStorage storage = TaskStorage.getInstance();
    storage.cleanTaskStorage();
    Task firstTask = buildTask("first", false);
    storage.addTask("test", firstTask);
    Task secondTask = buildTask("second", true);
    storage.addTask("test", secondTask);
    Task thirdTask = buildTask("third", false);
    storage.addTask("test", thirdTask);

    storage.serializeCollection("test");
  }

  Task buildTask(String desc, boolean finished){
    return Task.builder()
        .description(desc)
        .uuid(UuidCreator.createUuid())
        .creationDate(TimeTool.getLocalDateTime())
        .finished(finished)
        .build();
  }
}