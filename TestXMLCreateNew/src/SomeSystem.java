
public class SomeSystem {

	private double driveStraightP;
	private double driveStraightI;
	private double driveStraightD;
	private double gyroTurnP;
	private double gyroTurnI;
	private double gyroTurnD;
	private double driveDistanceP;
	private double driveDistanceI;
	private double driveDistanceD;
		
	public SomeSystem() {
		
		driveStraightP = .75;
		driveStraightI = .0;
		driveStraightD = .8;
		
		gyroTurnP = .9;
		gyroTurnI = .99;
		gyroTurnD = .999;
		
		driveDistanceP = .4;
		driveDistanceI = .44;
		driveDistanceD = .444;
	}

	public void writeSystemINI() {
		TestXMLIni.robotMap.writeNewIniPID("driveStraight", driveStraightP, driveStraightI);
		TestXMLIni.robotMap.writeNewIniPID("gyroTurn", gyroTurnP, gyroTurnI);
		TestXMLIni.robotMap.writeNewIniPID("driveDistance", driveDistanceP, driveDistanceI);
	}
}
