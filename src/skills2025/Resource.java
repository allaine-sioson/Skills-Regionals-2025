package skills2025;

public class Resource {
    private String name, category;
    private boolean availabilityStatus;
    private int timesCheckedOut;

    /**
     * Default constructor for Resource datatype
     * @param name name of the Resource
     * @param category category of the resource
     */
    public Resource(String name, String category) {
        this.name = name;
        this.category = category;
        this.availabilityStatus = true;
        this.timesCheckedOut = 0;
    }

    /**
     * Total constructor for Resource datatype
     * @param name name of the Resource
     * @param category category of the resource
     * @param availabilityStatus if it is available then true, else not
     * @param timesCheckedOut the amount of times the resource was checked out
     */
    public Resource(String name, String category, boolean availabilityStatus, int timesCheckedOut) {
        this.name = name;
        this.category = category;
        this.availabilityStatus = availabilityStatus;
        this.timesCheckedOut = timesCheckedOut;
    }

    @Override
    public String toString() {
        if (this.availabilityStatus) {
            return "\"" + this.name + "\" " + "(" + this.category + "): Available! (" + this.timesCheckedOut + " time(s) checked out)";
        } else {
            return "\"" + this.name + "\" " + "(" + this.category +"): NOT Available (" + this.timesCheckedOut + " time(s) checked out)";
        }
    }

    public int getTimesCheckedOut() {
        return timesCheckedOut;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public boolean isAvailabilityStatus() {
        return this.availabilityStatus;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;

        if (!this.availabilityStatus) {
            timesCheckedOut++;
        }
    }



    

    
}
