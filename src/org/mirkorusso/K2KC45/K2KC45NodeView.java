package org.mirkorusso.K2KC45;

import org.knime.core.node.NodeView;

/**
 * <code>NodeView</code> for the "Esempio" Node.
 * 
 *
 * @author Mirko Russo
 */
public class K2KC45NodeView extends NodeView<K2KC45NodeModel> {

    /**
     * Creates a new view.
     * 
     * @param nodeModel The model (class: {@link EsempioNodeModel})
     */
    protected K2KC45NodeView(final K2KC45NodeModel nodeModel) {
        super(nodeModel);

        // TODO instantiate the components of the view here.

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void modelChanged() {

        // TODO retrieve the new model from your nodemodel and 
        // update the view.
    	K2KC45NodeModel nodeModel = 
            (K2KC45NodeModel)getNodeModel();
        assert nodeModel != null;
        
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

        // TODO things to do when opening the view
    }

}

