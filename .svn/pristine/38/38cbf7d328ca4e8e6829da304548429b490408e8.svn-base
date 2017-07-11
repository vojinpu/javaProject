package app;

import app.controllers.AboutDialogController;
import app.controllers.TreeFilling;
import app.controllers.app.ExitController;
import app.controllers.app.LogOffController;
import app.controllers.app.ParseController;
import app.controllers.app.VisitRepoTutController;
import app.controllers.data.AddController;
import app.controllers.data.BlockFactorController;
import app.controllers.data.DeleteController;
import app.controllers.data.FetchController;
import app.controllers.data.MakeIndFileController;
import app.controllers.data.MergeController;
import app.controllers.data.ModifyController;
import app.controllers.data.SearchController;
import app.controllers.editdialog.LoadSchemeToEditorController;
import app.controllers.editdialog.MetaToolbarController;
import app.controllers.editdialog.SaveMetaSchemeController;
import app.controllers.editdialog.StartEditorController;
import app.controllers.pathdialog.SavePathController;
import app.controllers.pathdialog.SetPathController;
import app.controllers.pathdialog.StartAdminPathDialogController;
import app.controllers.pathdialog.StartInitialPathDialogController;
import app.controllers.pathdialog.StartUserPathDialogController;
import model.Entity;
import view.InfoTablePanel;

public class ControllerManager {

	private static ControllerManager mInstance;

	private ParseController mParseController;
	/* EditDialog actions */
	private StartEditorController mStartEditorController;
	private SaveMetaSchemeController mSaveMetaSchemeController;
	private MetaToolbarController mMetaToolbarController;
	private LoadSchemeToEditorController mLoadSchemeToEditorController;
	private AboutDialogController mAboutDialogController;
	/* PathDialogs */
	private StartUserPathDialogController mStartUserPathDialogController;
	private StartAdminPathDialogController mStartAdminPathDialogController;
	private StartInitialPathDialogController mStartInitialPathDialogController;
	private SavePathController mSavePathController;
	private SetPathController mSetPathController;
	/* App actions */
	private ExitController mExitController;
	private LogOffController mLogOffController;
	private VisitRepoTutController mVisitRepoTutController;
	/* Tree */
	private TreeFilling mFillTreeController;
	/* Data manipulation */
	private AddController mAddController;
	private DeleteController mDeleteController;
	private SearchController mSearchController;
	private ModifyController mModifyController;
	private FetchController mFetchController;
	private AddController mChildAddController;
	private DeleteController mChildDeleteController;
	private SearchController mChildSearchController;
	private ModifyController mChildModifyController;
	private BlockFactorController mBlockFactorController;
	private MakeIndFileController mMakeIndFileController;
	private MergeController mMergeController;
	private MergeController mChildMergeController;

	private ControllerManager() {
		mParseController = new ParseController();
		mStartEditorController = new StartEditorController();
		mSaveMetaSchemeController = new SaveMetaSchemeController();
		mMetaToolbarController = new MetaToolbarController();
		mAboutDialogController = new AboutDialogController();
		mStartUserPathDialogController = new StartUserPathDialogController();
		mStartAdminPathDialogController = new StartAdminPathDialogController();
		mStartInitialPathDialogController = new StartInitialPathDialogController();
		mSavePathController = new SavePathController();
		mSetPathController = new SetPathController();
		mLoadSchemeToEditorController = new LoadSchemeToEditorController();
		mExitController = new ExitController();
		mLogOffController = new LogOffController();
		mFillTreeController = new TreeFilling();
		mVisitRepoTutController = new VisitRepoTutController();
		mAddController = new AddController();
		mDeleteController = new DeleteController();
		mSearchController = new SearchController();
		mModifyController = new ModifyController();
		mFetchController = new FetchController();
		mChildAddController = new AddController();
		mChildDeleteController = new DeleteController();
		mChildSearchController = new SearchController();
		mChildModifyController = new ModifyController();
		mBlockFactorController = new BlockFactorController();
		mMakeIndFileController = new MakeIndFileController();
		mMergeController = new MergeController();
		mChildMergeController = new MergeController();
	}

	public static ControllerManager getInstance() {
		if (mInstance == null) {
			mInstance = new ControllerManager();
		}
		return mInstance;
	}
	
	public TreeFilling getFillTreeController() {
		return mFillTreeController;
	}

	public ParseController getParseController() {
		return mParseController;
	}

	public StartEditorController getStartEditorController() {
		return mStartEditorController;
	}

	public SaveMetaSchemeController getSaveMetaSchemeController() {
		return mSaveMetaSchemeController;
	}

	public MetaToolbarController getMetaToolbarController() {
		return mMetaToolbarController;
	}

	public StartUserPathDialogController getStartUserPathDialogController() {
		return mStartUserPathDialogController;
	}

	public SavePathController getSavePathController() {
		return mSavePathController;
	}

	public SetPathController getSetPathController() {
		return mSetPathController;
	}

	public LoadSchemeToEditorController getLoadSchemeToEditorController() {
		return mLoadSchemeToEditorController;
	}

	public StartInitialPathDialogController getStartInitialPathDialogController() {
		return mStartInitialPathDialogController;
	}
	
	public ExitController getExitController() {
		return mExitController;
	}

	public LogOffController getLogOffController() {
		return mLogOffController;
	}

	public StartAdminPathDialogController getStartAdminPathDialogController() {
		return mStartAdminPathDialogController;
	}

	public AboutDialogController getAboutDialogController() {
		return mAboutDialogController;
	}

	public VisitRepoTutController getVisitRepoTutController() {
		return mVisitRepoTutController;
	}

	public AddController getAddController() {
		return mAddController;
	}

	public DeleteController getDeleteController() {
		return mDeleteController;
	}

	public SearchController getSearchController() {
		return mSearchController;
	}

	public ModifyController getModifyController() {
		return mModifyController;
	}
	
	public FetchController getFetchController() {
		return mFetchController;
	}

	public BlockFactorController getBlockFactorController() {
		return mBlockFactorController;
	}
	
	public MakeIndFileController getMakeIndFileController(){
		return mMakeIndFileController;
	}

	public MergeController getmMergeController() {
		return mMergeController;
	}

	public void setmMergeController(MergeController mMergeController) {
		this.mMergeController = mMergeController;
	}
	

	public AddController getmChildAddController() {
		return mChildAddController;
	}

	public void setmChildAddController(AddController mChildAddController) {
		this.mChildAddController = mChildAddController;
	}

	public DeleteController getmChildDeleteController() {
		return mChildDeleteController;
	}

	public void setmChildDeleteController(DeleteController mChildDeleteController) {
		this.mChildDeleteController = mChildDeleteController;
	}

	public SearchController getmChildSearchController() {
		return mChildSearchController;
	}

	public void setmChildSearchController(SearchController mChildSearchController) {
		this.mChildSearchController = mChildSearchController;
	}

	public ModifyController getmChildModifyController() {
		return mChildModifyController;
	}

	public void setmChildModifyController(ModifyController mChildModifyController) {
		this.mChildModifyController = mChildModifyController;
	}

	public MergeController getmChildMergeController() {
		return mChildMergeController;
	}

	public void setmChildMergeController(MergeController mChildMergeController) {
		this.mChildMergeController = mChildMergeController;
	}

	/*Pozovite ga kad se promeni selektovani entitet i aktivni view*/
	public void updateDataControllers(Entity entity, InfoTablePanel table) {
		mAddController.setEntity(entity);
		mDeleteController.setEntity(entity);
		mSearchController.setEntity(entity);
		mModifyController.setEntity(entity);
		mAddController.setTable(table);
		mDeleteController.setTable(table);
		mSearchController.setTable(table);
		mModifyController.setTable(table);
		mFetchController.setModel(entity);
		mFetchController.setView(table);
		mBlockFactorController.setModel(entity);
		mBlockFactorController.setView(table);
		mMergeController.setEntity(entity);
		mMergeController.setTable(table);
	}
	public void updateChildDataControllers(Entity entity, InfoTablePanel table) {
		mChildAddController.setEntity(entity);
		mChildAddController.setTable(table);
		mChildDeleteController.setEntity(entity);
		mChildDeleteController.setTable(table);
		mChildSearchController.setEntity(entity);
		mChildSearchController.setTable(table);
		mChildModifyController.setEntity(entity);
		mChildModifyController.setTable(table);
		mChildMergeController.setEntity(entity);
		mChildMergeController.setTable(table);
	}
}
