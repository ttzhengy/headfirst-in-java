import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class QuizCardPlayer {
    private JFrame frame = new JFrame("Quiz Card Builder");
    private JPanel panel = new JPanel();
    ArrayList<QuizCard> cardList = new ArrayList<QuizCard>();
    JTextArea qText;
    JTextArea aText;
    boolean isShowedAnswer = false;
    int cardListSize = 0;
    int cardIndex = 0;

    public static void main(String[] args) {
        QuizCardBuilder q = new QuizCardBuilder();
        q.go();
    }

    public void go(){
        JMenuBar mBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.addActionListener(new LoadMenuListener());
        menu.add(loadItem);
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

    class NextCardListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(!isShowedAnswer){
                aText.setText(cardList.get(cardIndex).answer);
                isShowedAnswer = true;
            }else{
                if(cardIndex < cardListSize){
                    cardIndex++;
                    showNextCard();
                }else{
                    clearCard();
                    qText.setText("No more card!");
                }
            }
        }
    }

    class LoadMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JFileChooser fileLoad = new JFileChooser();
            fileLoad.showOpenDialog(frame);
            loadFile(fileLoad.getSelectedFile());
        }
    }

    private void loadFile(File file){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String[] qa;
            while(reader.readLine() != null){
                qa = reader.readLine().split("/");
                cardList.add(new QuizCard(qa[0], qa[1]));
                cardListSize++;
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Can't read the cardList in");
            e.printStackTrace();
        }
    }

    public void clearCard(){
        qText.setText("");
        aText.setText("");
        aText.requestFocus();
    }

    public void showNextCard(){
        clearCard();
        qText.setText(cardList.get(cardIndex).question);
    }
}

// class QuizCard{

//     String question;
//     String answer;

//     QuizCard(String q,String a){
//         question = q;
//         answer = a;
//     }

//     public String getQuestion(){
//         return question;
//     }

//     public String getAnswer(){
//         return answer;
//     }
// }

