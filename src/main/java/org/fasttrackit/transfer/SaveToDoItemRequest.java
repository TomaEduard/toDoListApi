package org.fasttrackit.transfer;

import java.sql.Date;

// Facem aveasta clasa pentru ca variabila id din clasa ToDoItem nu este aici
// si nu dorim sa o adaugam noi, ea este incrementata din baza, acest obiect este
// destinat trensferului de informatii

public class SaveToDoItemRequest {

    private String description;
    private Date deadline;
    private boolean done;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "SaveToDoItemRequest{" +
                "description='" + description + '\'' +
                ", deadline=" + deadline +
                ", done=" + done +
                '}';
    }
}
