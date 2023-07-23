import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Task2 extends JFrame implements ActionListener {
    private static final HashSet<String> stopWords = new HashSet<>(Arrays.asList(
            "a", "an", "and", "the", "in", "on", "of", "to", "for", "is", "are", "was", "were", "has", "have", "had"
    ));

    private JTextArea inputTextArea;
    private JButton countButton;
    private JLabel wordCountLabel;
    private JLabel uniqueWordsLabel;
    private JTextArea frequencyTextArea;

    public Task2() {
        setTitle("Word Counter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        initUI();
    }

private void initUI() {
    Container container = getContentPane();
    container.setLayout(new BorderLayout());
    container.setBackground(Color.WHITE);

    inputTextArea = new JTextArea();
    inputTextArea.setLineWrap(true);
    JScrollPane inputScrollPane = new JScrollPane(inputTextArea);

    countButton = new JButton("Count Words");
    countButton.addActionListener(this);

    wordCountLabel = new JLabel("Word Count:");
    uniqueWordsLabel = new JLabel("Unique Words:");
    frequencyTextArea = new JTextArea();
    frequencyTextArea.setEditable(false);
    JScrollPane frequencyScrollPane = new JScrollPane(frequencyTextArea);

    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.add(inputScrollPane, BorderLayout.CENTER);
    topPanel.add(countButton, BorderLayout.SOUTH);

    // Set background color for the top panel
    topPanel.setBackground(Color.LIGHT_GRAY);

    JPanel bottomPanel = new JPanel(new GridLayout(2, 2));
    bottomPanel.add(wordCountLabel);
    bottomPanel.add(uniqueWordsLabel);
    bottomPanel.add(frequencyScrollPane);

    // Set background color for the bottom panel
    bottomPanel.setBackground(Color.LIGHT_GRAY);

    container.add(topPanel, BorderLayout.CENTER);
    container.add(bottomPanel, BorderLayout.SOUTH);
}
    private int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        String[] words = text.split("\\s+|\\p{Punct}+");
        return words.length;
    }

    private HashMap<String, Integer> getWordFrequency(String text) {
        HashMap<String, Integer> wordFrequencyMap = new HashMap<>();

        if (text == null || text.trim().isEmpty()) {
            return wordFrequencyMap;
        }

        String[] words = text.split("\\s+|\\p{Punct}+");

        for (String word : words) {
            word = word.toLowerCase();
            if (!word.isEmpty() && !stopWords.contains(word)) {
                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        return wordFrequencyMap;
    }

    private String readTextFromFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                content.append(fileScanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File not found: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return content.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputText = inputTextArea.getText();

        if (inputText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Empty input. Please enter the text or choose a file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int wordCount = countWords(inputText);
        wordCountLabel.setText("Word Count: " + wordCount);

        HashMap<String, Integer> wordFrequencyMap = getWordFrequency(inputText);
        uniqueWordsLabel.setText("Unique Words: " + wordFrequencyMap.size());

        StringBuilder frequencyText = new StringBuilder();
        for (String word : wordFrequencyMap.keySet()) {
            int frequency = wordFrequencyMap.get(word);
            frequencyText.append(word).append(": ").append(frequency).append("\n");
        }
        frequencyTextArea.setText(frequencyText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Task2 wordCounter = new Task2();
            wordCounter.setVisible(true);
        });
    }
}