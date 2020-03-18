package com.galvanize.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "request_notes", schema = "customer_api")
public class RequestNote implements Serializable {
    // database columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id", nullable = false)
    private long noteId;
    @Column(name = "date_time")
    private String dateTime;
    @Column(name = "notes")
    private String notes;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //foreign key owner
    @JoinColumn(name = "request_number", nullable = false)
    private Request request;

    public RequestNote(long noteId, String dateTime, String notes, Request request) {
        this.noteId = noteId;
        this.dateTime = dateTime;
        this.notes = notes;
        this.request = request;
    }
    public RequestNote() {
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
