package net.engineeringdigest.journalApp.Entity;

import com.mongodb.annotations.NotThreadSafe;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries") //ye spring se bolega ki ye pura class ek mongodb mai map hoga

//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
@Data

public class JournalEntry {  //pojo - plain old java object
    @Id
    private ObjectId id; //document k ek collection mai ye hai unique key
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;

//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public ObjectId getId() {
//        return id;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
}
