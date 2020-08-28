import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class run_BCVTB {

	public static void open_idf(String x, String y, String fileName) {
		
		// Create an instance of Robot class 
		// Guide your mouse to do the work automatically
        Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// 
		} 
		System.out.println(robot.toString());
		robot.mouseMove(Integer.valueOf(x), Integer.valueOf(y));
		try { 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		try { 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
		robot.mouseMove(Integer.valueOf(x), Integer.valueOf(y)+25);
		try { 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		try { 
            Thread.sleep(200); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
		robot.mouseMove(Integer.valueOf(x)+60, Integer.valueOf(y)+300);
		try { 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
		robot.mouseMove(Integer.valueOf(x), Integer.valueOf(y)+25);
		try { 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		try { 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		String pathTemp = jarDir.getAbsolutePath().replace("%20", " ");
		StringSelection stringSelection = new StringSelection(pathTemp + "\\system.xml");
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		try { 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		try { 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
		robot.keyRelease(KeyEvent.VK_CONTROL);
		try { 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
		robot.keyPress(KeyEvent.VK_ENTER);
		try { 
            Thread.sleep(2000); 
        } 
        catch (InterruptedException e) 
        { 
            //
        } 
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void run_idf(String x, String y) {
		// TODO Auto-generated method stub
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// 
		} 
		System.out.println(robot.toString());
		robot.mouseMove(Integer.valueOf(x), Integer.valueOf(y));
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	
}
