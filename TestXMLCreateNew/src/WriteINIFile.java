
public class WriteINIFile {

	public WriteINIFile() {
		
	}
	
	public void writeNewINI() {
		
		TestXMLIni.robotMap.writeNewIniInit();
		TestXMLIni.aSystem.writeSystemINI();
		TestXMLIni.robotMap.writeNewIniFinish();
		
	}
}
