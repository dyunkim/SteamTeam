import rxtxrobot.*;

import java.lang.Math;

public class Collection {
	private int NTU;
	private double salinity;
	private int numofNTU;
	private int numofSal;
	private RXTXRobot r;
	private Movement move;
	
	
	public Collection(RXTXRobot r) {
		this.r = r;
		move = new Movement(r);
	}
	public void testWater() {
		
		//testSalinity();
		testTurbidity();
	}
	
	public void prepareCollection() {
		r.runMotor(RXTXRobot.MOTOR1, -100, RXTXRobot.MOTOR2, -100, 0);
		int sensor = 1023;
		while(sensor > 950) {
			r.refreshAnalogPins();
			AnalogPin temp = r.getAnalogPin(3);
			sensor = temp.getValue();
			System.out.printf("Line Sensor: %d\n", sensor);
		}
		r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 1000);
		
	}
	public int testSalinity() {
		
		calcSalinity();
		return -1;
	}
	public void testTurbidity() {
		double sum = 0.0;
		double sensor = 0.0;
		for(int i = 0 ; i < 25; i++) {
			r.refreshAnalogPins();
			AnalogPin temp = r.getAnalogPin(7);
			sensor = calcVolt(temp.getValue());
			sum += sensor;
			System.out.printf("Sensor: %.2f\n", sensor);
		}
		double average = sum / 25.0;
		System.out.println("Average: " + average);
		calcNTU(average);
	}
	
	public void calcNTU(double voltage) {
		if(voltage > 5 || voltage < 3) {
			System.out.println("Value too high, too low, or none");
		}
		else {
			//double doubleNTU =  ((253.95 * Math.pow(voltage, 3)) - (3456.4 * Math.pow(voltage, 2)) + (15233 * voltage) - 21498);
//			System.out.println("doubleNTU: " + doubleNTU);
//			NTU = (int) doubleNTU;
//			System.out.println("Turbidity: " + NTU);
		}
	}
	public double calcSalinity() {
		return -1.0;
	}
	
	public void collectTL() {
		
	}
	
	public void collectBL() {
		prepareCollection();
		r.runEncodedMotor(RXTXRobot.MOTOR1, -100, 28, RXTXRobot.MOTOR2, -100, 28);
		move.turn90left();
		move.align();
		findDispenser();
		r.sleep(3000);
		backupDispenser();
		r.sleep(1000);
	}
	
	public void collectBR() {
		prepareCollection();
		r.runEncodedMotor(RXTXRobot.MOTOR1, -100, 22, RXTXRobot.MOTOR2, -100, 22);
		move.turn90right();
		move.align();
		findDispenser();
		r.sleep(3000);
		backupDispenser();
		r.sleep(1000);
	}
	public void collectTR() {
		prepareCollection();
		r.runEncodedMotor(RXTXRobot.MOTOR1, -100, 22, RXTXRobot.MOTOR2, -100, 22);
		move.turn90right();
		move.align();
		findDispenser();
		r.sleep(3000);
		backupDispenser();
		r.sleep(1000);
		
		
	}
	
	public void findDispenser() {	//go until 8cm apart, slow down at 15cm
		int sensor = 50;
		r.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
		r.resetEncodedMotorPosition(RXTXRobot.MOTOR2);
		r.runMotor(RXTXRobot.MOTOR1, -150, RXTXRobot.MOTOR2, -150, 0);
		int distance = r.getPing(13);
		boolean slower = true;
		boolean aligned = true;
		while(sensor > 12) {
			if(r.getPing(13) <= (distance * .75) && aligned) {
				r.sleep(300);
				move.align();
				r.runMotor(RXTXRobot.MOTOR1, -150, RXTXRobot.MOTOR2, -150, 0);
				
			}if(r.getPing(13) <= (distance / 2) && aligned) {
				r.sleep(300);
				move.align();
				aligned = false;
				r.runMotor(RXTXRobot.MOTOR1, -150, RXTXRobot.MOTOR2, -150, 0);
			}
			if(sensor < 50 && slower) {
				r.runMotor(RXTXRobot.MOTOR1, -75, RXTXRobot.MOTOR2, -75, 0);
				slower = false;
			}
			sensor = (r.getPing(13) + r.getPing(12)) / 2;
			System.out.println("distance from dispenser: " + sensor);
			
		}
		r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 0);
		
		
	}

	public double calcVolt(int data) {
		return (data / 764.0) * 5.0;
	}
	public void backupDispenser() {
		int motor1 = r.getEncodedMotorPosition(RXTXRobot.MOTOR1);
		int motor2 = r.getEncodedMotorPosition(RXTXRobot.MOTOR2);
		r.runEncodedMotor(RXTXRobot.MOTOR1, 150, -1 * motor1, RXTXRobot.MOTOR2, 150, -1 * motor2);
	}
}
