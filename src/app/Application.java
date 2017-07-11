package app;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.fasterxml.jackson.databind.ObjectMapper;

import enums.UserType;
import interfaces.OnLogIn;
import interfaces.OnPathSetCallback;
import model.DBConnection;
import model.Entity;
import model.Repository;
import model.User;
import util.Util;
import view.InfoFrame;
import view.TutorialMessagePane;
import view.dialogs.InitialPathDialog;
import view.dialogs.LogInDialog;

public class Application implements OnPathSetCallback, OnLogIn {

	private static Application mInstance;
	private Context mContext;
	private InfoFrame mInfoFrame;
	private Repository mRepository;
	private DBConnection dbConnection;
	private User mCurrentUser;
	private String query;


	private Application() {

		setLookAndFeel();
		//System.out.println("Application - pre infoframe konstruktora");
		mInfoFrame = new InfoFrame();

		File contextFile = new File(Context.CONTEXT_PATH);

		if (contextFile.exists()) {

			ObjectMapper mapper = new ObjectMapper();
			
			try {
				mContext = mapper.readValue(contextFile, Context.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			mContext = new Context();
			JOptionPane.showMessageDialog(null,
					new TutorialMessagePane(),
					"Prvo pokretanje programa", JOptionPane.WARNING_MESSAGE);
		}

		/*
		 * Na osnovu podataka koje dobijemo u callbacku od login dijaloga,
		 * nastavljamo sa instanciranjem UI-a prilagodjenog za korisnika ili
		 * administratora
		 */
		
		//System.out.println("Application - pre invoke-a login dijaloga");
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				//System.out.println("Application - poziv LogIn konstruktora");
			//	dbConnection = new DBConnection();
				new LogInDialog(Application.this, mContext);
				
			}
		});
		
		
		query = null;
	}

	public static Application getInstance() {
		if (mInstance == null) {
			mInstance = new Application();
		}
		return mInstance;
	}

	public Context getContext() {
		return mContext;
	}

	public InfoFrame getInfoFrame() {
		return mInfoFrame;
	}

	public Repository getRepository() {
		return mRepository;
	}

	public void setRepository(Repository mRepository) {
		this.mRepository = mRepository;

		dbConnection = new DBConnection();
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onLogIn(User user) {
		mCurrentUser = user;

		if ((user.getUserType().equals(UserType.USER) && mContext.getMetaPath() == null)
				|| (user.getUserType().equals(UserType.USER) && !Util.validateJSON(Paths.get(mContext.getMetaPath())))) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new InitialPathDialog(Application.this);
				}
			});
		} else {
			initDataPresentation();
		}
	}

	@Override
	public void onPathSet(String string) {

		mContext.setMetaPath(string);
		initDataPresentation();
		
	}

	private void initDataPresentation() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mInfoFrame.initUI();
				mInfoFrame.revalidate();
				mInfoFrame.repaint();
			}
		});
	}

	public User getCurrentUser() {
		return mCurrentUser;
	}

	public void setCurrentUser(User mCurrentUser) {
		this.mCurrentUser = mCurrentUser;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public DBConnection geDbConnection(){
		return dbConnection;
	}
	
	
	
}
