import java.text.*;
import java.util.*;
import java.io.*;

public class Logout {
    private String username;

    public Logout() {
        this.username = "";
    }

    // Logout time (Current Time)
    public String currentTime() {
    	
        Date now = new Date();
        SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");

        String loginTime = formatTime.format(now);
        return loginTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
