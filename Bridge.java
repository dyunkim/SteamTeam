import rxtxrobot.*;

public class Bridge {
	private RXTXRobot r;
	private Movement move;
	
	public Bridge(RXTXRobot r) {
		this.r = r;
		
	}
	

	public boolean findBridge() {
		r.runMotor(RXTXRobot.MOTOR1, 100, RXTXRobot.MOTOR2, 100, 0); 
		int sensorDiff = 0; //sensor difference
		int previousValue = 0;
		int sensor = 0;
		while(sensorDiff < 50)		// run ping continuously
		{
			sensor = r.getPing(14); 	//whatever the last ping pin is in
			sensorDiff = previousValue - sensor;
			previousValue = sensor;
		}
		return true;
	}
	public void crossBridge() {
		move.turn90left();
		//TODO - Figure out when to stop after crossing based on ping sensors
		
	}
	
}
