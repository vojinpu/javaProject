package app.controllers.editdialog;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;
import org.fife.ui.rtextarea.SearchResult;

import view.dialogs.EditDialog;

public class MetaToolbarController extends AbstractAction {

	private EditDialog mEditDialog;

	@Override
	public void actionPerformed(ActionEvent e) {

		SearchContext context = new SearchContext();
		context.setMatchCase(false);
		context.setWholeWord(mEditDialog.isWholeWordChecked());
		context.setSearchFor(mEditDialog.getSearchTerm());
		SearchResult result = new SearchResult();
		
		System.out.println(e.getActionCommand());
		
		switch (e.getActionCommand()) {
		case EditDialog.PREVIOUS:
			context.setSearchForward(false);
			result = SearchEngine.find(mEditDialog.getEditArea(), context);			
			break;
		case EditDialog.NEXT:
			context.setSearchForward(true);
			System.out.println(mEditDialog.getSearchTerm() + "   " + context.getSearchFor());
			result = SearchEngine.find(mEditDialog.getEditArea(), context);		
			break;
		case EditDialog.REPLACE:
			context.setSearchForward(true);
			context.setReplaceWith(mEditDialog.getReplaceTerm());
			result = SearchEngine.replace(mEditDialog.getEditArea(), context);			
			break;
		case EditDialog.REPLACE_ALL:
			context.setSearchForward(true);
			context.setReplaceWith(mEditDialog.getReplaceTerm());
			result = SearchEngine.replaceAll(mEditDialog.getEditArea(), context);	
			break;		
		default:
			break;
		}	
		System.out.println(result.wasFound());
	}

	public void setEditDialog(EditDialog editDialog) {
		mEditDialog = editDialog;
	}

}
