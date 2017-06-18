package org.mirkorusso.K2KFURIAC;

import java.util.Hashtable;

import javax.swing.JTextArea;

import org.knime.core.node.NodeView;

/**
 * <code>NodeView</code> for the "Esempio" Node.
 * 
 *
 * @author Mirko Russo
 */
public class K2KFURIACNodeView extends NodeView<K2KFURIACNodeModel> {

    Hashtable<Integer, String> model = null;
    /**
     * Creates a new view.
     * 
     * @param nodeModel The model (class: {@link EsempioNodeModel})
     */
    protected K2KFURIACNodeView(K2KFURIACNodeModel nodeModel) {
        super(nodeModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void modelChanged() {

        // TODO retrieve the new model from your nodemodel and 
        // update the view.
    	K2KFURIACNodeModel nodeModel = 
            (K2KFURIACNodeModel)getNodeModel();
        assert nodeModel != null;
        
        model = nodeModel.model;
        
        // be aware of a possibly not executed nodeModel! The data you retrieve
        // from your nodemodel could be null, emtpy, or invalid in any kind.
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onClose() {
    
        // TODO things to do when closing the view
    }

    /**
     * {@inheritDoc}
     */
	@Override
    protected void onOpen() {
    	modelChanged();
    	JTextArea modelView = new JTextArea();
    	
    	if (model == null)
    		return;
    	else
		    for(int i = 0; model.get(i) != null; i++)
		    	modelView.append(model.get(i) + "\n");

		modelView.setEditable(false);
		setComponent(modelView);    
    }

}

