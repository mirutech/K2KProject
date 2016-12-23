package org.mirkorusso.K2KC45;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "Esempio" Node.
 * 
 *
 * @author Mirko Russo
 */
public class K2KC45NodeFactory 
        extends NodeFactory<K2KC45NodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public K2KC45NodeModel createNodeModel() {
        return new K2KC45NodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<K2KC45NodeModel> createNodeView(final int viewIndex,
            final K2KC45NodeModel nodeModel) {
        return new K2KC45NodeView(nodeModel);
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
        return new K2KC45NodeDialog();
    }
}