package net.engineeringdigest.journalApp.Entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "user") //ye spring se bolega ki ye pura class ek mongodb mai map hoga
@Data

public class User {     //pojo - plain old java object
    @Id                 //document k ek collection mai ye hai unique key
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull            //from lambok
    private String userName;
    @NonNull
    private String password;
    @DBRef              //iska use karte hain reference create karne k liye between two or more tables
    private List<JournalEntry> journalEntries = new ArrayList<>();

}
