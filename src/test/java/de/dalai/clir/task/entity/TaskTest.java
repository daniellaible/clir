package de.dalai.clir.task.entity;

import static org.junit.jupiter.api.Assertions.*;

import de.dalai.clir.tool.TimeTool;
import de.dalai.clir.tool.UuidCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class TaskTest {

  private Task tut;

  @BeforeAll
  public void initTask(){
    this.tut = Task.builder()
        .creationDate(TimeTool.getLocalDateTime())
        .finished(false)
        .text("Test")
        .uuid(UuidCreator.createUuid())
        .build();
  }

  @Test
  public void testToJson() {
    String json = this.tut.toJson();
    assertTrue(json.startsWith("{\"uuid\":"));
  }
}