package de.dalai.clir.task.entity;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {

  private String uuid;
  private String creationDate;
  private String completionDate;
  private String description;
  private boolean finished;

  public String toJson(){
    Gson gson = new Gson();
    return gson.toJson(this);
  }

  public Task fromJson(String json){
    Gson gson = new Gson();
    return gson.fromJson(json, Task.class);
  }
}
