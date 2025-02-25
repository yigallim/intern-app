
import com.tarumt.entity.Location;

class Company {

    private String id;
    private String name;
    private String description;
    private Location location;

    public Company(String id, String name, String description, Location location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
