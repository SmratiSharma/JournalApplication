package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

//    Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName)
    {
        User user = userService.findByUserName(userName);
        List<JournalEntry> list = user.getJournalEntries();
        if(list!= null && !list.isEmpty())
        {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry ,@PathVariable String userName )
    {
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntityById(@PathVariable ObjectId myId)
    {
        Optional<JournalEntry> journalEntry = journalEntryService.journalEntryFindById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK); //response entity is used to customize the http status codes
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}/{userName}")
    public ResponseEntity<?> deleteJournalEntity(@PathVariable ObjectId myId, @PathVariable String userName)
    {
        journalEntryService.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalEntity(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName)
    {
        JournalEntry old  = journalEntryService.journalEntryFindById(myId).orElse(null);
        if(old!=null)
        {
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<> (old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
