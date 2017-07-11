package view.dialogs;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.Util;

public class AboutUsWindow extends JDialog {


	
	
	private JLabel titleLabel = new JLabel();
	
	private JTextField textName = new JTextField();
	private JTextArea textAbout = new JTextArea();
	private JTextArea textTerms = new JTextArea();

	private JButton okButton;




//	public Language language;

	JPanel panMain;

	// Getters and Setters

	public JTextField getTextName() {
		return textName;
	}

	public void setTextName(JTextField textName) {
		this.textName = textName;
	}

	public JTextArea getTextAbout() {
		return textAbout;
	}

	public void setTextAbout(JTextArea textAbout) {
		this.textAbout = textAbout;
	}

	public JTextArea getTextTerms() {
		return textTerms;
	}

	public void setTextTerms(JTextArea textTerms) {
		this.textTerms = textTerms;
	}





	private void initializePanel() {

		panMain = new JPanel(new BorderLayout());

		JPanel panCenter = new JPanel();
		BoxLayout boxCenter = new BoxLayout(panCenter, BoxLayout.Y_AXIS);
		panCenter.setLayout(boxCenter);
		panCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel image = new JLabel();
		//image.setIcon( );
		
		
		JLabel company_name = new JLabel("Awesome Software Inc.");
		Font font = company_name.getFont();
		// same font but bold
		Font boldFont = new Font(font.getFontName(), Font.BOLD, 15);
		company_name.setFont(boldFont);
		
		JLabel about_company = new JLabel(convertToMultiline("Naša misija je da stvorimo najbolje programe ikada.\n Mi smo odlučni da budu uspešni i težimo savršenstvu.\n Nadamo se da će biti u stanju da prepoznate priliku\n i dođite kod nas što je pre moguće."));
		
		JLabel founders_label = new JLabel("Osnivači");
		founders_label.setFont(boldFont);
		
		JLabel website = new JLabel("www.awesome-soft-inc.weebly.com");
		Font hyperlink_font = website.getFont();
		hyperlink_font = new Font(hyperlink_font.getName(),Font.PLAIN,2);
		website.setFont(boldFont);
		website.setForeground(Color.BLUE);
		website.setCursor(new Cursor(Cursor.HAND_CURSOR));
		website.addMouseListener(openWebsite);
		
		JPanel foundersPanel = new JPanel();
		foundersPanel.setLayout(new BoxLayout(foundersPanel, BoxLayout.X_AXIS));
		JLabel vojin = new JLabel("Vojin");
		vojin.setIcon(Util.loadImage(AboutUsWindow.this.getClass(), "vojinr.png"));
		vojin.setHorizontalTextPosition(JLabel.CENTER);
		vojin.setVerticalTextPosition(JLabel.TOP);
		vojin.setFont(boldFont);
		
		JLabel dragoljub = new JLabel("Dragoljub");
		dragoljub.setIcon(Util.loadImage(AboutUsWindow.this.getClass(), "dragoljubr.png"));
		dragoljub.setHorizontalTextPosition(JLabel.CENTER);
		dragoljub.setVerticalTextPosition(JLabel.TOP);
		dragoljub.setFont(boldFont);
		
		JLabel petja = new JLabel("Petja");
		petja.setIcon(Util.loadImage(AboutUsWindow.this.getClass(), "petjar.png"));
		petja.setHorizontalTextPosition(JLabel.CENTER);
		petja.setVerticalTextPosition(JLabel.TOP);
		petja.setFont(boldFont);
		
		foundersPanel.add(Box.createRigidArea(new Dimension(23,0)));
		foundersPanel.add(vojin);
		foundersPanel.add(Box.createRigidArea(new Dimension(23,0)));
		foundersPanel.add(dragoljub);
		foundersPanel.add(Box.createRigidArea(new Dimension(23,0)));
		foundersPanel.add(petja);
		
		
		
		panCenter.add(Box.createRigidArea(new Dimension(0,5)));
		panCenter.add(image);
		panCenter.add(Box.createRigidArea(new Dimension(0,5)));
		panCenter.add(company_name);
		panCenter.add(Box.createRigidArea(new Dimension(0,5)));
		panCenter.add(about_company);
		panCenter.add(Box.createRigidArea(new Dimension(0,5)));
		panCenter.add(website);
		panCenter.add(Box.createRigidArea(new Dimension(0,15)));
		panCenter.add(founders_label);
		
		
		JPanel panBottom = new JPanel();
		// dimenzije labela i tekstualnih komponenti
		Dimension dim = new Dimension(80, 20);
		okButton = new JButton("Ok");
		okButton.setPreferredSize(dim);
		okButton.setAlignmentX(CENTER_ALIGNMENT);
		
		panBottom.add(okButton);
		
		okButton.addActionListener(e->{dispose();});


		panMain.add(panCenter, BorderLayout.NORTH);
		panMain.add(foundersPanel,BorderLayout.CENTER);
		panMain.add(panBottom, BorderLayout.SOUTH);

		setTitle("O nama");

		Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        
        //Bugfix
        //setLocation(screenSize.width / 2 - 150, screenSize.height / 2 - 150);
        //setSize(500, 400);
		add(panMain);
		
		pack();
		setLocationRelativeTo(null);
		
		setIconImage(new ImageIcon("src/res/icons/about32.png").getImage());
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		setVisible(true);
		
		
	}

	public AboutUsWindow() {
		initializePanel();
//		new Controller(this);
	}


	

	public JPanel getPanel() {
		if (panMain == null)
			initializePanel();
		return panMain;
	}

	public static String convertToMultiline(String orig) {
		return "<html>" + orig.replaceAll("\n", "<br>");
	}

	public void setTitleMessage(String titleMessage) {
		titleLabel.setText(titleMessage);
	}

	public String getName() {
		return textName.getText().toString();
	}

	public String getAbout() {
		return textAbout.getText().toString();
	}

	public String getTermsAndConditions() {
		return textTerms.getText().toString();
	}


	
	MouseListener openWebsite = new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			try {
				Desktop.getDesktop().browse(new URI("http://awesome-soft-inc.weebly.com/"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private Image getScaledImage(String imagePath, int w, int h){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		int diameter = Math.min(img.getWidth(), img.getHeight());
	    BufferedImage mask = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = mask.createGraphics();
	    applyQualityRenderingHints(g2d);
	    g2d.fillOval(0, 0, diameter - 1, diameter - 1);
	    g2d.dispose();

	    BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	    g2d = masked.createGraphics();
	    applyQualityRenderingHints(g2d);
	    int x = (diameter - img.getWidth()) / 2;
	    int y = (diameter - img.getHeight()) / 2;
	    g2d.drawImage(img, x, y, null);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
	    g2d.drawImage(mask, 0, 0, null);
	    g2d.dispose();
		
		Image resizedImg =  masked.getScaledInstance(w, h,
		        Image.SCALE_SMOOTH);

	    return resizedImg;
	}
	private void applyQualityRenderingHints(Graphics2D g2d) {

	    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	}

}
