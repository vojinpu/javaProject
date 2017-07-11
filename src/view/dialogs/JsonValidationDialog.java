package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.github.fge.jsonschema.core.report.ProcessingReport;

public class JsonValidationDialog extends JDialog { 
	
	public JsonValidationDialog(ProcessingReport report, EditDialog editDialog){
		
		initUI(report);
		setTitle("Do\u0161lo je do gre\u0161ke!");
		setModalityType(ModalityType.APPLICATION_MODAL);
		pack();
		setLocationRelativeTo(editDialog);
		setVisible(true);
	}
	
	private void initUI(ProcessingReport report) {
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BorderLayout());
		
		initMessagePanel(dialogPanel);
		initDebugPanel(dialogPanel, report);
		initButtonPanel(dialogPanel);
		
		add(dialogPanel);
	}
	
	private void initMessagePanel(JPanel dialogPanel) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("\u0160ema koju ste poku\u0161ali da sa\u010Duvate nije validna!");
		label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		panel.add(label);
		dialogPanel.add(panel, BorderLayout.NORTH);
	}
	
	private void initDebugPanel(JPanel dialogPanel, ProcessingReport report) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea(60, 120);
		textArea.setEditable(false);
		String string = reportToReadableString(report); 
		
		
		
		textArea.setText(string);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		panel.add(scrollPane);
		dialogPanel.add(panel, BorderLayout.CENTER);
		
		
	}
	
	private void initButtonPanel(JPanel dialogPanel) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton button = new JButton(new AbstractAction("OK") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		panel.add(button);
		dialogPanel.add(panel, BorderLayout.SOUTH);
	}
	
	private String reportToReadableString (ProcessingReport report) {
		
		String s = report.toString().replaceAll(".*failure", "");
		
		
		
		return s.replaceAll("(.{120})", "$1\n");
		
	}
	
}
