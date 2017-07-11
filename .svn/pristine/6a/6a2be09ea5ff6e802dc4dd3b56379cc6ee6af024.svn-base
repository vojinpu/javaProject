package app.controllers.app;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.Application;
import app.Context;
import util.Util;

public class ExitController extends AbstractAction {

	public ExitController() {
		putValue(NAME, "Izlaz");
		putValue(SMALL_ICON, Util.loadIcon(getClass(), "error24.png"));
		putValue(LARGE_ICON_KEY, Util.loadIcon(getClass(), "error32.png"));
		putValue(SHORT_DESCRIPTION, "Izlaz iz aplikacije");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_I);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
		mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		mapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
		try {
			mapper.writeValue(new File(Context.CONTEXT_PATH),
					Application.getInstance().getContext());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Application.getInstance().getInfoFrame().dispose();
		
	}

}
