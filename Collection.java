import rxtxrobot.*;



public class Collection {
	private int NTU;
	private double salinity;
	private int numofNTU;
	private int numofSal;
	private RXTXRobot r;
	private Movement move;
	

	
	public Collection(RXTXRobot r) {
		this.r = r;
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
	
	public int calcNTU() {
		
		return -1;
	}
	public double calcSalinity() {
		return -1.0;
	}
	
	public void collectTL() {
		
	}
	
	public void collectBL() {
		
	}
	
	public void collectBR() {
		
	}
	public void collectTR() {
		
	}
	public void backupDispense() {	//dispenses balls at location
			
	}
}
