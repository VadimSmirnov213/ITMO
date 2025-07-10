package managers;

import models.HumanBeing;
import java.time.LocalDate;
import java.util.*;

public class CollectionManager {
    private long currentId = 1;
    private Map<Long, HumanBeing> persons = new HashMap<>();
    private TreeSet<HumanBeing> collection = new TreeSet<HumanBeing>();
    private LocalDate lastInitTime;
    private LocalDate lastSaveTime;
    private final DumpManager dumpManager;

    public CollectionManager(DumpManager dumpManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.dumpManager = dumpManager;
    }

    public TreeSet<HumanBeing> getCollection() {
        return collection;
    }

    public LocalDate getLastInitTime() {
        return lastInitTime;
    }

    public long generateId() {
        return getFreeId(); 
    }

    public LocalDate getLastSaveTime() {
        return lastSaveTime;
    }

    public void saveCollection() {
        dumpManager.writeCollection(collection);
        lastSaveTime = LocalDate.now();
    }

    public HumanBeing byId(Long id) { 
        return persons.get(id); 
    }

    public boolean isСontain(HumanBeing e) { 
        return e == null || byId(e.getId()) != null; 
    }

    public long getFreeId() {
        while (byId(currentId) != null)
            if (++currentId < 0)
                currentId = 1;
        return currentId;
    }

    public boolean add(HumanBeing d) {
        if (isСontain(d)) return false;
        persons.put(d.getId(), d);
        collection.add(d);
        return true;
    }

    public void clear() {
        persons.clear();
        collection.clear();
    }

    public boolean remove(long id) {
        var a = byId(id);
        if (a == null) return false;
        persons.remove(a.getId());
        collection.remove(a);
        return true;
    }

    public boolean loadCollection() {
        persons.clear();
        Collection<HumanBeing> loadedCollection = dumpManager.readCollection();
        lastInitTime = LocalDate.now();
        for (var e : loadedCollection) {
            if (byId(e.getId()) != null) {
                collection.clear();
                return false;
            } else {
                if (e.getId() > currentId) currentId = e.getId();
                persons.put(e.getId(), e);
            }
        }
        collection.clear();
        collection.addAll(loadedCollection);
        return true;
    }

    public void removeById(long id) { 
        collection.removeIf(h -> h.getId() == id); 
    }

    public HumanBeing getById(long id) { 
        return collection.stream().filter(h -> h.getId() == id).findFirst().orElse(null); 
    }
    
    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";

        StringBuilder info = new StringBuilder();
        for (HumanBeing dragon : collection) {
            info.append(dragon+"\n\n");
        }
        return info.toString().trim();
    }
}