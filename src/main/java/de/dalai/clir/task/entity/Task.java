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
  private String text;
  private boolean isfinished;

  public String toJson(){
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
