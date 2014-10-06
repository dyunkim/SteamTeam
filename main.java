import java.util.Scanner;
import rxtxrobot.*;

public class main {
	final private static int PING_PIN = 13;
	public static void main(String[] args) {
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
		r.setPort("COM8"); // Set the port to COM3 
		r.setVerbose(true); // Turn on debugging messages 
		r.connect();
		
		//ServoTest(r, 180);
		//DCTest(r);
		//EncoderTest(r);
		testPing(r);
		r.close(); 
	}


	public static void ServoTest(RXTXRobot r, int angle) {
		r.attachServo(RXTXRobot.SERVO1, 10); //Connect the servos to the Arduino 
		r.moveServo(RXTXRobot.SERVO1, 0); // Move Servo 1 to location 30
		r.sleep(1000);
		r.moveServo(RXTXRobot.SERVO1, angle);
	}
	
	public static void DCTest(RXTXRobot r) {
		r.runMotor(RXTXRobot.MOTOR1, 400, 3000); // Run motor 1 forward (speed of 125) for 5 seconds 
	}
	public static void EncoderTest(RXTXRobot r) {
		r.runEncodedMotor(RXTXRobot.MOTOR1, 400, 500);
	}
	public static void testPing(RXTXRobot r) {
		for (int x=0; x < 1; ++x) 
		{ 
			//Read the ping sensor value, which is connected to pin 12 
			System.out.println("Response: " + r.getPing(PING_PIN) + " cm");
			System.out.println("BLAHSDHFASD: " + PING_PIN);
			r.sleep(300); 
		} 
	}
}
