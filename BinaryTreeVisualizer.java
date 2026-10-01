import javax.swing.*;
import java.awt.*;

public class BinaryTreeVisualizer extends JFrame {

    private Node root;
    private final TreePanel treePanel;
    private final JTextField inputField;
    private final JLabel traversalLabel;

    // Node class
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public BinaryTreeVisualizer() {

        setTitle("Binary Tree Visualizer");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Top panel
        JPanel topPanel = new JPanel();

        inputField = new JTextField(10);

        JButton insertButton = new JButton("Insert");
        JButton clearButton = new JButton("Clear");

        topPanel.add(new JLabel("Enter Number:"));
        topPanel.add(inputField);
        topPanel.add(insertButton);
        topPanel.add(clearButton);

        // Tree drawing area
        treePanel = new TreePanel();

        // Bottom panel
        JPanel bottomPanel = new JPanel();

        JButton inorderButton = new JButton("Inorder");
        JButton preorderButton = new JButton("Preorder");
        JButton postorderButton = new JButton("Postorder");

        traversalLabel = new JLabel("Traversal: ");

        bottomPanel.add(inorderButton);
        bottomPanel.add(preorderButton);
        bottomPanel.add(postorderButton);
        bottomPanel.add(traversalLabel);

        add(topPanel, BorderLayout.NORTH);
        add(treePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Insert button
        insertButton.addActionListener(e -> insertNumber());

        // Clear button
        clearButton.addActionListener(e -> {
            root = null;
            traversalLabel.setText("Traversal: ");
            treePanel.repaint();
        });

        // Traversal buttons
        inorderButton.addActionListener(e -> {
            StringBuilder result = new StringBuilder();
            inorder(root, result);

            traversalLabel.setText(
                    "Inorder: " + result
            );
        });

        preorderButton.addActionListener(e -> {
            StringBuilder result = new StringBuilder();
            preorder(root, result);

            traversalLabel.setText(
                    "Preorder: " + result
            );
        });

        postorderButton.addActionListener(e -> {
            StringBuilder result = new StringBuilder();
            postorder(root, result);

            traversalLabel.setText(
                    "Postorder: " + result
            );
        });

        setVisible(true);
    }

    // Insert number from text field
    private void insertNumber() {

        try {

            int value =
                    Integer.parseInt(inputField.getText());

            root = insert(root, value);

            inputField.setText("");

            treePanel.repaint();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid number."
            );
        }
    }

    // Insert into Binary Search Tree
    private Node insert(Node node, int value) {

        if (node == null) {
            return new Node(value);
        }

        if (value < node.data) {

            node.left =
                    insert(node.left, value);

        } else if (value > node.data) {

            node.right =
                    insert(node.right, value);

        }

        return node;
    }

    // Inorder Traversal
    private void inorder(
            Node node,
            StringBuilder result) {

        if (node != null) {

            inorder(node.left, result);

            result.append(node.data)
                    .append(" ");

            inorder(node.right, result);
        }
    }

    // Preorder Traversal
    private void preorder(
            Node node,
            StringBuilder result) {

        if (node != null) {

            result.append(node.data)
                    .append(" ");

            preorder(node.left, result);

            preorder(node.right, result);
        }
    }

    // Postorder Traversal
    private void postorder(
            Node node,
            StringBuilder result) {

        if (node != null) {

            postorder(node.left, result);

            postorder(node.right, result);

            result.append(node.data)
                    .append(" ");
        }
    }

    // Panel used to draw tree
    class TreePanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            if (root != null) {

                drawTree(
                        g,
                        root,
                        getWidth() / 2,
                        50,
                        getWidth() / 4
                );
            }
        }

        private void drawTree(
                Graphics g,
                Node node,
                int x,
                int y,
                int horizontalGap) {

            if (node == null) {
                return;
            }

            int nextY = y + 80;

            // Draw line to left child
            if (node.left != null) {

                int childX =
                        x - horizontalGap;

                g.drawLine(
                        x,
                        y,
                        childX,
                        nextY
                );

                drawTree(
                        g,
                        node.left,
                        childX,
                        nextY,
                        Math.max(
                                horizontalGap / 2,
                                30
                        )
                );
            }

            // Draw line to right child
            if (node.right != null) {

                int childX =
                        x + horizontalGap;

                g.drawLine(
                        x,
                        y,
                        childX,
                        nextY
                );

                drawTree(
                        g,
                        node.right,
                        childX,
                        nextY,
                        Math.max(
                                horizontalGap / 2,
                                30
                        )
                );
            }

            // Draw node
            g.setColor(Color.BLUE);

            g.fillOval(
                    x - 20,
                    y - 20,
                    40,
                    40
            );

            // Draw node value
            g.setColor(Color.WHITE);

            String value =
                    String.valueOf(node.data);

            FontMetrics fm =
                    g.getFontMetrics();

            int textX =
                    x - fm.stringWidth(value) / 2;

            int textY =
                    y + fm.getAscent() / 2 - 2;

            g.drawString(
                    value,
                    textX,
                    textY
            );

            g.setColor(Color.BLACK);
        }
    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                BinaryTreeVisualizer::new
        );
    }
}
