import rxtxrobot.*;
public class Main {
    public void main(String[] args) {
        RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
        r.setPort("COM8"); // Set the port to COM3 
        r.setVerbose(true); // Turn on debugging messages 
        r.connect(); 
        r.attachServo(RXTXRobot.SERVO1, 9); //Connect the servos to the Arduino 
        r.moveServo(RXTXRobot.SERVO1, 30); // Move Servo 1 to location 30 
        r.close(); 
    }
}
