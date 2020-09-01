/**
 * @(#)Branch.java
 *
 *
 * @author 
 * @version 1.00 2020/9/1
 */


import java.io.Serializable;
import java.util.Arrays;
import java.util.StringJoiner;

class Branch implements Serializable {
	
    private static int nextBranchId = 1; // To count the amount of branches starting from 1
    
    private String branchId;
    private String branchName;
    private Manager branchManager;

    //Constructor
    public Branch() {
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

    @Override
    public String toString() {
        return "Branch{" +
                "branchId='" + branchId + '\'' +
                ", branchName='" + branchName + '\'' +
                ", manager=" + manager +
                '}';
    }
}