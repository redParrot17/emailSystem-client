package gui;

import javax.swing.*;
import java.awt.*;
import java.beans.JavaBean;

/**
 * <code>JRoundedPanel</code> is a generic lightweight container with rounded edges.
 * For examples and task-oriented documentation for JRoundedPanel, see
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
public class JRoundedPanel extends JPanel {

    /** Stroke size. it is recommended to set it to 1 for better view */
    private int strokeSize = 1;
    /** Color of shadow */
    private Color shadowColor = Color.BLACK;
    /** Sets if it drops shadow */
    private boolean shady = true;
    /** Sets if it has an High Quality view */
    private boolean highQuality = true;
    /** Double values for Horizontal and Vertical radius of corner arcs */
    private Dimension arcs = new Dimension(10, 10);
    /** Distance between shadow border and opaque panel border */
    private int shadowGap = 5;
    /** The offset of shadow.  */
    private int shadowOffset = 4;
    /** The transparency value of shadow. ( 0 - 255) */
    private int shadowAlpha = 100;

    /**
     * @see #getUIClassID
     * @see #readObject
     */
    private static final String uiClassID = "RoundedPanelUI";

    /**
     * Creates a new JRoundedPanel with the specified layout manager and buffering
     * strategy.
     *
     * @param layout  the LayoutManager to use
     * @param isDoubleBuffered  a boolean, true for double-buffering, which
     *        uses additional memory space to achieve fast, flicker-free
     *        updates
     */
    public JRoundedPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        setOpaque(false);
    }

    /**
     * Create a new buffered JRoundedPanel with the specified layout manager
     *
     * @param layout  the LayoutManager to use
     */
    public JRoundedPanel(LayoutManager layout) {
        super(layout);
        setOpaque(false);
    }

    /**
     * Creates a new <code>JRoundedPanel</code> with <code>FlowLayout</code>
     * and the specified buffering strategy.
     * If <code>isDoubleBuffered</code> is true, the <code>JRoundedPanel</code>
     * will use a double buffer.
     *
     * @param isDoubleBuffered  a boolean, true for double-buffering, which
     *        uses additional memory space to achieve fast, flicker-free
     *        updates
     */
    public JRoundedPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        setOpaque(false);
    }

    /**
     * Creates a new <code>JRoundedPanel</code> with a double buffer
     * and a flow layout.
     */
    public JRoundedPanel() {
        super();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(),
                shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        //Sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //Draws shadow borders if any.
        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(
                    shadowOffset,// X position
                    shadowOffset,// Y position
                    width - strokeSize - shadowOffset, // width
                    height - strokeSize - shadowOffset, // height
                    arcs.width, arcs.height);// arc Dimension
        } else {
            shadowGap = 1;
        }

        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);

        //Sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
    }
}