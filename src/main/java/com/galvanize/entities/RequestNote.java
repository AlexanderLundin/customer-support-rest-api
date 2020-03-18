package com.galvanize.entities;

import javax.persistence.*;

@Entity
@Table(name = "request_notes", schema = "customer_api")
public class RequestNote{
    // database columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id", nullable = false)
    private long noteId;
    @Column(name = "date_time")
    private String dateTime;
    @Column(name = "notes")
    private String notes;


    public RequestNote(long noteId, String dateTime, String notes) {
        this.noteId = noteId;
        this.dateTime = dateTime;
        this.notes = notes;
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

}
