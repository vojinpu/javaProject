package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import app.controllers.TableTabCloseController;

public class InfoTabbedCloseButton extends JButton {

	public InfoTabbedCloseButton(InfoTabbedPane tabbedPane,InfoTablePanel infotable) {
		int size = 10;
		setPreferredSize(new Dimension(size, size));

		setUI(new BasicButtonUI());

		setContentAreaFilled(false);

		setFocusable(false);
		setBorder(BorderFactory.createEtchedBorder());
		setBorderPainted(false);

		setRolloverEnabled(true);
		addActionListener(new TableTabCloseController(tabbedPane, infotable));
	}


	public void updateUI() {
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();

		if (getModel().isPressed()) {
			g2.translate(1, 1);
		}
		// g2.drawRect(2, 3, 12, 12);
		// g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		if (getModel().isRollover()) {
			g2.setColor(Color.RED);
		}
		int delta = 2;

		g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
		g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
		g2.dispose();
	}
}
