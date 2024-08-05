You have been tasked to create a radio show database system called WappuRadio. This database stores information about radio show hosts and their respective shows. Assume that a host has typical fields such as `name`, `show name`, `airtime`, and `genre`.

WappuRadio's hosts and administrative staff can make queries in the show host database to search for specific show host records. Additionally, the database supports administrative staff updates to the data. Why is this? For example, there may have been initial errors in the data, or it might be necessary to update a show's airtime, change a host's show, mark a host as inactive, etc.

(In reality, the database would likely be stored on disk and use some type of database engine, but let's assume here that the database is always entirely in the memory of a Java process as simple objects, and the state of the objects is saved back to a file at the end of the day and reloaded into memory at the beginning of the next day. All data processing would thus simply take place with objects in the memory of the Java process.)

## Task

What principle of data protection would you choose in the case of the ShowHost type when you want to serve the two different roles mentioned:

    A host makes a search query and you want to offer the host a view that only supports reading the show host records.

    Administrative staff want to permanently edit the information of a specific host.

Describe the mechanisms you choose and their consequences for class features, class invariant, and usage. It will suffice to focus mainly on the ShowHost type. For simplicity, we can assume that the database is an array (ShowHost[]) or a list (List[ShowHost]) of show hosts (you can choose either - lists should be familiar to you from previous courses). You may also implement the program if you wish, but just creating the specifications is enough.

### Answer:
The principle is: **Encapsulation and Integrity**.


## Describe the mechanisms you choose and their consequences for class features, class invariant, and usage.

### Answer:
Based on the role, host and administrative staff can view data, but only staff can update the host, so:
- Class features:
  + Get show host data.
  + Update show host data.
- Class invariant: these data of ShowHost should not be null:
  + Id.
  + Name.
  + Show name.
  + Airtime.
  + Genre.
- We use `Class-Level Visibility Protection Modifiers` to limit the update on these methods of `ShowHost`:
  + **public**: `getId()`, `getName()`, `getShowName()`, `getAirTime()`, `getGenre()`.
  + **package**: `updateData()`.
  + **private**: `setName()`, `setShowName()`, `setAirTime()`, `setGenre()`.
- Then we use a `ShowHostService` in the same package with `ShowHost` to determine the current role of user, check permission and execute the action (for example: call `ShowHost::updateData()` when a staff sends the request to update a host). 