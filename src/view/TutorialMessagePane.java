package view;

import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;

import app.ControllerManager;

public class TutorialMessagePane extends JEditorPane {
	
	public TutorialMessagePane() {
		super("text/html", "<html><body>Preporu\u010Dujemo da pro\u010Ditate uputstvo na <a href=\"http://google.com/\">wiki stranici projekta</a>.</body></html>");
		JLabel label = new JLabel();

		addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED))
					ControllerManager.getInstance().getVisitRepoTutController().actionPerformed(null);
			}
		});

		Font f = UIManager.getFont("Label.font");
		String bodyRule = "body { font-family: " + f.getFamily() + "; " + "font-size: " + f.getSize() + "pt; }";
		((HTMLDocument) getDocument()).getStyleSheet().addRule(bodyRule);

		setOpaque(false);
		setBorder(null);
		setFont(label.getFont());
		setEditable(false);
		setBackground(label.getBackground());
	}
}
