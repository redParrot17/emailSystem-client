package gui;

import javax.swing.*;
import java.awt.*;
import java.beans.JavaBean;

/**
 * <code>JImagePanel</code> is a generic lightweight container.
 * For examples and task-oriented documentation for JImagePanel, see
 * <a
 href="http://docs.oracle.com/javase/tutorial/uiswing/components/panel.html">How to Use Panels</a>,
 * a section in <em>The Java Tutorial</em>.
 * <p>
 * <strong>Warning:</strong> Swing is not thread safe. For more
 * information see <a
 * href="package-summary.html#threading">Swing's Threading
 * Policy</a>.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans&trade;
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @author Arnaud Weber
 * @author Steve Wilson
 * @since 1.2
 */
@JavaBean(defaultProperty = "UI", description = "A generic lightweight container.")
@SuppressWarnings("serial") // Same-version serialization only
public class JImagePanel extends JPanel {

    /**
     * @see #getUIClassID
     * @see #readObject
     */
    private static final String uiClassID = "ImagePanelUI";

    private Image bgImage = null;
    private boolean tile = false;

    /**
     * Creates a new JImagePanel with the specified layout manager and buffering
     * strategy.
     *
     * @param layout  the LayoutManager to use
     * @param isDoubleBuffered  a boolean, true for double-buffering, which
     *        uses additional memory space to achieve fast, flicker-free
     *        updates
     */
    public JImagePanel(LayoutManager layout, boolean isDoubleBuffered) { super(layout, isDoubleBuffered); }

    /**
     * Create a new buffered JImagePanel with the specified layout manager
     *
     * @param layout  the LayoutManager to use
     */
    public JImagePanel(LayoutManager layout) { super(layout); }

    /**
     * Creates a new <code>JImagePanel</code> with <code>FlowLayout</code>
     * and the specified buffering strategy.
     * If <code>isDoubleBuffered</code> is true, the <code>JImagePanel</code>
     * will use a double buffer.
     *
     * @param isDoubleBuffered  a boolean, true for double-buffering, which
     *        uses additional memory space to achieve fast, flicker-free
     *        updates
     */
    public JImagePanel(boolean isDoubleBuffered) { super(isDoubleBuffered); }

    /**
     * Creates a new <code>JImagePanel</code> with a double buffer
     * and a flow layout.
     */
    public JImagePanel() { super(); }

    public void setBackgroundImage(Image image, boolean tile) {
        bgImage = image;
        this.tile = tile;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(bgImage, 0,0,null);
        if (tile) {
            int iw = bgImage.getWidth(this);
            int ih = bgImage.getHeight(this);
            if (iw > 0 && ih > 0) {
                for (int x = 0; x < getWidth(); x += iw) {
                    for (int y = 0; y < getHeight(); y += ih) {
                        g.drawImage(bgImage, x, y, iw, ih, this);
                    }
                }
            }
        } else {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
