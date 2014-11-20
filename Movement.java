import rxtxrobot.*;

public class Movement {
	private RXTXRobot r;
	
	public Movement(RXTXRobot r) {
		this.r = r;	
	}
	
	public void align() {	
		int left = r.getPing(12);
		int right = r.getPing(13);
		int difference = left - right;
		while(!(difference > -2 && difference < 2)) {
			if(left < right) {
				r.runEncodedMotor(RXTXRobot.MOTOR1, 100, 5, RXTXRobot.MOTOR2, -100, 5);	//left forward
			}
			else {
				r.runEncodedMotor(RXTXRobot.MOTOR1, -100, 5, RXTXRobot.MOTOR2, 100, 5); //right forward
			}
			left = r.getPing(12);
			right = r.getPing(13);
			difference = left - right;
			System.out.println("Difference: " + difference + " cm");
		}
		System.out.println("Aligned!");
	}
	public void turn90right() {
		r.runEncodedMotor(RXTXRobot.MOTOR1, 150, 84, RXTXRobot.MOTOR2, -150, 84);
		align();
	}
	public void turn90left() {
		r.runEncodedMotor(RXTXRobot.MOTOR1, -100, 26, RXTXRobot.MOTOR2, -100, 26);
		r.runEncodedMotor(RXTXRobot.MOTOR1, -150, 84, RXTXRobot.MOTOR2, 150, 84);
		align();
	}
	public void turn180() {
		r.runEncodedMotor(RXTXRobot.MOTOR1, -200, 185, RXTXRobot.MOTOR2, 200, 185);
		align();
	}
}
