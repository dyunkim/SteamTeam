import java.util.Scanner;

import rxtxrobot.*;


//Motor1 is left
//Motor2 is right
public class main {
	public static void main(String[] args) {
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
		r.setPort("COM8"); 
		//r.setVerbose(true); // Turn on debugging messages 
		r.connect();
		//r.runEncodedMotor(RXTXRobot.MOTOR1, 200, 200, RXTXRobot.MOTOR2, 200, 200);
		//ping(r);
		//sensor(r);
		//servoTest(r, 9);
		Robot robot = new Robot(r);
		robot.runRobot();
		r.close();
	}
	
	public static void sensor(RXTXRobot r) {
		//X < 950 = WHITE			x > 950 = black
		for(int i = 0 ; i < 25; i++) {
			r.refreshAnalogPins();
			AnalogPin temp = r.getAnalogPin(7);
			System.out.printf("Sensor: %.2f\n", calcVolt(temp.getValue()));
		}
		
	}
	public static double calcVolt(int data) {
		return (data / 764.0) * 5.0;
	}
	

	public static void ping(RXTXRobot r) {
		for(int i = 0; i < 10; i++) {
			System.out.printf("%d cm\n", r.getPing(12));
			System.out.printf("%d cm\n", r.getPing(13));
		}
	}
	public static void servoTest(RXTXRobot r, int pin) {
		r.attachMotor(RXTXRobot.SERVO3, pin);
		r.moveServo(RXTXRobot.SERVO3, 150);
	}

}
