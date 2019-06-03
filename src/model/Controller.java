package model;

public class Controller {

	private Generator generator;
	private Checker checker;
	private Signer signer;
	
	public Controller () {
		try {
			
			generator = new Generator();
			checker = new Checker();
			signer = new Signer();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createKeys(String alias, String password) throws Exception {	
		generator.createKeys(alias, 2048, password);	
	}
	
	public void signFile (String pathFile, String pathFileSigned, String password) throws Exception {
		generator
		String content = signer.loadFile(pathFile);
		signer.sign(content, privateKey)
	}
}
