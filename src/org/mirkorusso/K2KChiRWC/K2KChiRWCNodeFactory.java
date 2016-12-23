package org.mirkorusso.K2KChiRWC;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "Esempio" Node.
 * 
 *
 * @author Mirko Russo
 */
public class K2KChiRWCNodeFactory 
        extends NodeFactory<K2KChiRWCNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public K2KChiRWCNodeModel createNodeModel() {
        return new K2KChiRWCNodeModel();
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
    public NodeView<K2KChiRWCNodeModel> createNodeView(final int viewIndex,
            final K2KChiRWCNodeModel nodeModel) {
        return new K2KChiRWCNodeView(nodeModel);
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
        return new K2KChiRWCNodeDialog();
    }
}