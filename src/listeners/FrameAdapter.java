package listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import app.ControllerManager;

public class FrameAdapter implements WindowListener {

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		ControllerManager.getInstance().getExitController().actionPerformed(null);

//		ObjectMapper mapper = new ObjectMapper();
//		mapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
//		mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
//		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//		mapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
//		try {
//			mapper.writeValue(new File(Context.CONTEXT_PATH),
//					Application.getInstance().getContext());
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//
//		System.out.println("Zatvara se");
//		Application.getInstance().getInfoFrame().dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
