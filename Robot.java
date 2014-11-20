import rxtxrobot.*;

public class Robot {
	private RXTXRobot r;
	private Movement move;
	private Collection collect;
	private FinalPhase finalPhase;
	private Bridge bridge;
	
	
	public Robot(RXTXRobot r) {
		this.r = r;
		r.attachServo(RXTXRobot.SERVO2, 8);
		
		move = new Movement(r);
		collect = new Collection(r);
		finalPhase = new FinalPhase(r);
		bridge = new Bridge(r);
	}
	public void runRobot() {
//		backupBump();
//.		lowerSensorArm();
//		r.sleep(3000);
//		collect.testWater();
//		raiseSensorArm();
		collect.collectBL();
		move.turn90right();
		r.runEncodedMotor(RXTXRobot.MOTOR1, 100, 75, RXTXRobot.MOTOR2, 100, 75);
		r.sleep(500);
		collect.collectBR();
		move.turn90left();	
		r.sleep(500);
		collect.collectTR();
		move.turn90left();
	}
	
	public void backup() {	//back with a slow down
		
	}
	public void backupBump() {
		r.runMotor(RXTXRobot.MOTOR1, 100, RXTXRobot.MOTOR2, 100, 0);
		int sensor = 0;
		do {
			r.refreshAnalogPins();
			AnalogPin temp = r.getAnalogPin(4);
			sensor = temp.getValue();
			System.out.printf("Sensor: %d\n", sensor);
		} while(sensor < 700);
		r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0, 500);
	}

	public void lowerSensorArm() {
		for(int i = 90; i > 0; i-=10) {
			r.moveServo(RXTXRobot.SERVO2, i);
			r.sleep(300);
		}
	}
	public void raiseSensorArm() {
		r.moveServo(RXTXRobot.SERVO2, 40);
		r.sleep(8000);
		for(int i = 50; i < 70; i+=10) {
			r.moveServo(RXTXRobot.SERVO2, i);
			r.sleep(300);
		}
	}
}


