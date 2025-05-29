package view;

import javax.swing.*;
import java.awt.*;

public class OrderPanel extends JPanel {
    private final JTextArea orderSummaryArea;
    private final JButton payButton;
    private final JButton clearButton;

    public OrderPanel() {
        setLayout(new BorderLayout());

        orderSummaryArea = new JTextArea();
        orderSummaryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderSummaryArea);

        JPanel buttonPanel = new JPanel();
        payButton = new JButton("결제");
        clearButton = new JButton("초기화");

        buttonPanel.add(clearButton);
        buttonPanel.add(payButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateOrderSummary(String summary) {
        orderSummaryArea.setText(summary);
    }

    public void setPayAction(Runnable action) {
        payButton.addActionListener(e -> action.run());
    }

    public void setClearAction(Runnable action) {
        clearButton.addActionListener(e -> action.run());
    }
}
