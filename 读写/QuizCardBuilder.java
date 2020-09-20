import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class QuizCardBuilder {
    private JFrame frame = new JFrame("Quiz Card Builder");
    private JPanel panel = new JPanel();
    ArrayList<QuizCard> cardList = new ArrayList<QuizCard>();
    JTextArea qText;
    JTextArea aText;

    public static void main(String[] args) {
        QuizCardBuilder q = new QuizCardBuilder();
        q.go();
    }

    public void go(){
        JMenuBar mBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem saveItem = new JMenuItem("Save");
        newItem.addActionListener(new NewMenuListener());
        saveItem.addActionListener(new SaveMenuListener());
        menu.add(newItem);
        menu.add(saveItem);
        mBar.add(menu);

        JLabel question = new JLabel("Question:");
        qText = new JTextArea(6, 20);
        qText.setLineWrap(true);
        qText.setWrapStyleWord(true);
        JScrollPane qScroller = new JScrollPane(qText);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JLabel answer = new JLabel("Answer:");
        aText = new JTextArea(6, 20);
        aText.setLineWrap(true);
        aText.setWrapStyleWord(true);
        JScrollPane aScroller = new JScrollPane(aText);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(question);
        panel.add(qScroller);
        panel.add(answer);
        panel.add(aText);

        JButton nextCard = new JButton("Next Card");
        nextCard.addActionListener(new NextCardListener());
        panel.add(nextCard);

        frame.getContentPane().add("Center",panel);
        frame.setJMenuBar(mBar);
        frame.setSize(500, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public class SaveMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            QuizCard card = new QuizCard(qText.getText(), aText.getText());
            cardList.add(card);

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    class NewMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            cardList.clear();
            clearCard();
        }
    }

    class NextCardListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            QuizCard card = new QuizCard(qText.getText(), aText.getText());
            cardList.add(card);
            clearCard();
        }
    }

    public void clearCard(){
        qText.setText("");
        aText.setText("");
        qText.requestFocus();
    }

    public void saveFile(File file){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (QuizCard quizCard : cardList) {
                writer.write(quizCard.getQuestion() + "/");
                writer.write(quizCard.getAnswer() + "\n");
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Can't write the cardList Out");
            e.printStackTrace();
        }
    }
}

class QuizCard{

    String question;
    String answer;

    QuizCard(String q,String a){
        question = q;
        answer = a;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }
}
