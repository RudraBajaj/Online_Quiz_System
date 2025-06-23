import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class Test {

    JFrame frame;
    JLabel questionLabel;
    JRadioButton option1, option2, option3, option4;
    JButton nextButton, previousButton;
    int currentQuestion = 0;
    int score = 0;

    String[] questions = new String[5];
    String[][] choices = new String[5][4];
    int[] answers = new int[5];
    int[] selectedAnswers = new int[5];

    ButtonGroup bg;

    @SuppressWarnings("Convert2Lambda")
    public Test() throws IOException {
        frame = new JFrame("Quiz App Created by Rudra");
        questionLabel = new JLabel();
        bg = new ButtonGroup();

        questionLabel.setBounds(50, 20, 400, 30);
        frame.add(questionLabel);

        option1 = new JRadioButton();
        option1.setBounds(50, 60, 200, 30);
        bg.add(option1);
        frame.add(option1);

        option2 = new JRadioButton();
        option2.setBounds(50, 90, 200, 30);
        bg.add(option2);
        frame.add(option2);

        option3 = new JRadioButton();
        option3.setBounds(50, 120, 200, 30);
        bg.add(option3);
        frame.add(option3);

        option4 = new JRadioButton();
        option4.setBounds(50, 150, 200, 30);
        bg.add(option4);
        frame.add(option4);

        previousButton = new JButton("Previous"); // 🟩 Shows "Previous" and goes back
        previousButton.setBounds(100, 200, 100, 30);
        frame.add(previousButton);

        nextButton = new JButton("Next"); // 🟩 Shows "Next" and goes forward
        nextButton.setBounds(210, 200, 100, 30);
        frame.add(nextButton);

        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1;
        }

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSelected();

                if (currentQuestion < questions.length - 1) {
                    currentQuestion++;
                    loadQuestion();
                } else {
                    // End of quiz
                    score = 0;
                    for (int i = 0; i < selectedAnswers.length; i++) {
                        if (selectedAnswers[i] == answers[i]) {
                            score++;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Quiz Over! Your score: " + score);
                    frame.dispose();
                }
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentQuestion > 0) {
                    saveSelected();
                    currentQuestion--;
                    loadQuestion();
                }
            }
        });

        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setVisible(true);

        loadQuestions();
        loadQuestion();
    }

    void saveSelected() {
        if (option1.isSelected()) {
            selectedAnswers[currentQuestion] = 0;
        } else if (option2.isSelected()) {
            selectedAnswers[currentQuestion] = 1;
        } else if (option3.isSelected()) {
            selectedAnswers[currentQuestion] = 2;
        } else if (option4.isSelected()) {
            selectedAnswers[currentQuestion] = 3;
        } else {
            selectedAnswers[currentQuestion] = -1;
        }
    }

    void loadQuestions() throws IOException {
        loadQuestionFromFile("C:/new Rudra/Workstation/java (Online quiz system)/questions/q1.txt", 0);
        loadQuestionFromFile("C:/new Rudra/Workstation/java (Online quiz system)/questions/q2.txt", 1);
        loadQuestionFromFile("C:/new Rudra/Workstation/java (Online quiz system)/questions/q3.txt", 2);
        loadQuestionFromFile("C:/new Rudra/Workstation/java (Online quiz system)/questions/q4.txt", 3);
        loadQuestionFromFile("C:/new Rudra/Workstation/java (Online quiz system)/questions/q5.txt", 4);
    }

    void loadQuestionFromFile(String filePath, int index) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        questions[index] = br.readLine();
        for (int i = 0; i < 4; i++) {
            choices[index][i] = br.readLine();
        }
        answers[index] = Integer.parseInt(br.readLine());
        br.close();
    }

    void loadQuestion() {
        questionLabel.setText("Q" + (currentQuestion + 1) + ": " + questions[currentQuestion]);
        option1.setText(choices[currentQuestion][0]);
        option2.setText(choices[currentQuestion][1]);
        option3.setText(choices[currentQuestion][2]);
        option4.setText(choices[currentQuestion][3]);

        bg.clearSelection();

        int selected = selectedAnswers[currentQuestion];
        if (selected == 0) option1.setSelected(true);
        else if (selected == 1) option2.setSelected(true);
        else if (selected == 2) option3.setSelected(true);
        else if (selected == 3) option4.setSelected(true);
    }

    public static void main(String[] args) throws IOException {
        new Test();
    }
}
