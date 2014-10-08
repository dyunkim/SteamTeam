import java.util.Scanner;
import rxtxrobot.*;

public class main {
	final private static int PING_PIN = 13;
	public static void main(String[] args) {
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
		Scanner s = new Scanner(System.in);
		boolean test = false;
		r.setPort("COM8"); 
		r.setVerbose(true); // Turn on debugging messages 
		r.connect();
		//ServoTest(r, 135);
		//DCTest(r);
		//EncoderTest(r);
		//testPing(r);
		//testBump(r);
		
		/*do {
			System.out.println("\nCHOOSE: \n1. ServoTest");
			System.out.println("2. DC Motor Test");
			System.out.println("3. Encoded Motor Test");
			System.out.println("4. Ping test");
			System.out.println("5. Bump Test");
			System.out.println("0. EXIT");
			
			int choice = s.nextInt();
			if(choice == 1) {
				ServoTest(r, 135);
			}
			else if(choice == 2) {
				DCTest(r);
			}
			else if(choice == 3) {
				EncoderTest(r);
			}
			else if(choice == 4) {
				testPing(r);
			}
			else if(choice == 5) {
				testBump(r);
			}
			else if(choice == 0) {
				test = true;
			}
			else {
				System.out.println("Not an option");
			}
		} while(!test);*/
		r.close();
	}


	public static void ServoTest(RXTXRobot r, int angle) {
		r.attachServo(RXTXRobot.SERVO1, 10); //Connect the servos to the Arduino 
		r.moveServo(RXTXRobot.SERVO1, 0); // Move Servo 1 to location 30
		r.sleep(1000);
		r.moveServo(RXTXRobot.SERVO1, angle);
	}
	
	public static void DCTest(RXTXRobot r) {
		r.runMotor(RXTXRobot.MOTOR1, 300, 2000); // Run motor 1 forward (speed of 125) for 5 seconds 
	}
	public static void EncoderTest(RXTXRobot r) {
		r.runEncodedMotor(RXTXRobot.MOTOR1, 100, 200);
	}
	public static void testPing(RXTXRobot r) {
		for (int x=0; x < 10; ++x) 
		{ 
			System.out.println("Response: " + r.getPing(PING_PIN) + " cm");
			r.sleep(300); 
		} 
	}
	public static void testBump(RXTXRobot r) {
		int check = 0;
		r.runMotor(RXTXRobot.MOTOR1, 200, 0);
		do {
			r.refreshAnalogPins();
			AnalogPin temp = r.getAnalogPin(0);
			check = temp.getValue();
			System.out.println("Sensor gave back: " + check);
			
		} while(check < 500);
		r.runMotor(RXTXRobot.MOTOR1, 0, 1000);
		
	}
}
