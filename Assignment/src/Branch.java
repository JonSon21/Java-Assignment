import java.io.Serializable;
import java.util.Arrays;
import java.util.StringJoiner;

class Branch implements Serializable {
	
    //Variables
    private static int nextBranchId = 1; // Counting the branch starting from 1
    private String branchId;
    private String branchName;
    private Manager manager;

    //Constructor
    public Branch() {

    	this.branchId = new String("null");
    	this.branchName = new String ("n/a");
    	this.manager = new Manager(new PersonDetails("n/a","n/a",'n',"n/a","n/a", "n/a"),"n/a","n/a");

    }

    public Branch(String branchName, Manager manager) {
        this.branchId = String.format("B%02d",nextBranchId++);
        this.branchName = branchName;
        this.manager = manager;
    }

    //Getter
    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public Manager getManager() {
        return manager;
    }

    //Setter
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    // Override toString()
    @Override
    public String toString() {
        return "Branch{" +
                "branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", manager=" + manager +
                '}';
    }
}