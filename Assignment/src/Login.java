import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

class Login {
	
	// Variables
    private String username;
    private String password;
    private int index;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // Login function for employee
    public boolean employeeLogin(ArrayList<Employee> eArray) {

        for (index = 0; index < eArray.size(); index++) {

            // Compare username and password with the database
            if (eArray.get(index).getEmployeeID().equals(username)
                    && eArray.get(index).getPassword().equals(password)) {
                return true;
                
                // Only output wrong username or password if
                // the username and password is not present in the file
            }
        }
        return false;
    }

    // Login function for manager
    public boolean managerLogin(Manager manager) {

        if (manager.getManagerID().equals(username) && manager.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    // Login time
    public String currentTime() {
    	
        Date now = new Date();
        SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");
        String loginTime = formatTime.format(now);

        return loginTime;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getIndex() {
        return index;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
