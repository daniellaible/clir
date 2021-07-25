package de.dalai.clir.task.entity;

import com.google.gson.Gson;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {

  private int id;
  private String creationDate;
  private String completionDate;
  private String description;
  private boolean finished;
  private List<Task> subtasks;

  public String toJson(){
    Gson gson = new Gson();
    return gson.toJson(this);
  }

  public Task fromJson(String json){
    Gson gson = new Gson();
    return gson.fromJson(json, Task.class);
  }

  public String blockOutput(){
    StringBuilder builder = new StringBuilder();
    builder.append("    id: " + id + System.lineSeparator());
    builder.append("    text: " + description + System.lineSeparator());
    builder.append("    open: " + creationDate + System.lineSeparator());
    if(finished) {
      builder.append("    status: closed" + System.lineSeparator());
      builder.append("    closed: " + completionDate + System.lineSeparator());
    }else {
      builder.append("    status: open" + System.lineSeparator());
    }
    return builder.toString();
  }

  public String listOutputOpen(){
    return "  id: " + id + " text: " + description + " open" + creationDate;
  }
}
