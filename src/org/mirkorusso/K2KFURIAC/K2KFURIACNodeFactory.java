package org.mirkorusso.K2KFURIAC;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "Esempio" Node.
 * 
 *
 * @author Mirko Russo
 */
public class K2KFURIACNodeFactory 
        extends NodeFactory<K2KFURIACNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public K2KFURIACNodeModel createNodeModel() {
        return new K2KFURIACNodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<K2KFURIACNodeModel> createNodeView(final int viewIndex,
            final K2KFURIACNodeModel nodeModel) {
        return new K2KFURIACNodeView(nodeModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
        return new K2KFURIACNodeDialog();
    }
}