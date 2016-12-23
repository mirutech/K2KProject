package org.mirkorusso.K2KChiRWC;

import org.knime.core.node.NodeView;

/**
 * <code>NodeView</code> for the "Esempio" Node.
 * 
 *
 * @author Mirko Russo
 */
public class K2KChiRWCNodeView extends NodeView<K2KChiRWCNodeModel> {

    /**
     * Creates a new view.
     * 
     * @param nodeModel The model (class: {@link EsempioNodeModel})
     */
    protected K2KChiRWCNodeView(final K2KChiRWCNodeModel nodeModel) {
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
    	K2KChiRWCNodeModel nodeModel = 
            (K2KChiRWCNodeModel)getNodeModel();
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

