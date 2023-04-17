import javax.management.MBeanAttributeInfo;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Trojuhelnik extends JFrame {
    private JButton lzeSestrojitButton;
    private JButton aButton;
    private JTextField bField;
    private JTextField aField;
    private JTextField cField;
    private JPanel panel;
    private JFileChooser jFileChooser = new JFileChooser(".");

    public static void main(String[] args) {
        new Trojuhelnik();
    }

    public Trojuhelnik() {
        aJeVetsiNez();
        lzeSestrojit();
        setTitle("Trojúhelník");
        JMenuBar jMenuBar = new JMenuBar();
        JMenu soubor = new JMenu("soubor");
        JMenu akce = new JMenu("akce");
        jMenuBar.add(soubor);
        jMenuBar.add(akce);
        setJMenuBar(jMenuBar);
        JMenuItem getA = new JMenuItem("A ->(B a C)");
        getA.addActionListener(e -> aJeVetsiNez());
        JMenuItem lzeSestrojit = new JMenuItem("Lze sestrojit trojůhelník?");
        lzeSestrojit.addActionListener(e -> lzeSestrojit());
        akce.add(lzeSestrojit);
        akce.addSeparator();
        akce.add(getA);
        JMenuItem uloz = new JMenuItem("Ulož");
        JMenuItem nacti = new JMenuItem("Načti");
        nacti.addActionListener(e -> vyberSoubor());
        uloz.addActionListener(e-> vyberUlozeni());
        soubor.add(nacti);
        soubor.addSeparator();
        soubor.add(uloz);
        setContentPane(panel);
        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void lzeSestrojit() {
        lzeSestrojitButton.addActionListener(e -> lzeSestrojitTrojuhelnik());
    }

    private void lzeSestrojitTrojuhelnik() {
        int a = Integer.parseInt(aField.getText());
        int b = Integer.parseInt(bField.getText());
        int c = Integer.parseInt(cField.getText());
        if ((a <= (b + c)) && (b <= (a + c)) && (c <= (a + b))) {
            JOptionPane.showMessageDialog(null, "Ze stran A: " + a + ", B: " + b + " a C: " + c + " lze sestrojit trojúhelník!");
        } else {
            JOptionPane.showMessageDialog(null, "Ze stran A: " + a + ", B: " + b + " a C: " + c + " nelze sestrojit trojúhelník!");
        }
    }

    private void aJeVetsiNez() {
        aButton.addActionListener(e -> {
            cField.setText(aField.getText());
            bField.setText(aField.getText());
        });
    }

    public void nactiSoubor(File selectedfile) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(selectedfile)))) {
            while (scanner.hasNextLine()) {
                String radek = scanner.nextLine();
                String[] polozky = radek.split(";");
                aField.setText(polozky[0]);
                bField.setText(polozky[1]);
                cField.setText(polozky[2]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("cannot open the file!");
        }
    }

    private void vyberSoubor() {
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            nactiSoubor(selectedFile);
        }
    }
    private void vyberUlozeni(){
        int result = jFileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            File file = jFileChooser.getSelectedFile();
            uloz(file);
        }
    }
    private void uloz(File file) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            writer.print("" + aField.getText() + ";" + bField.getText() + ";" + cField.getText() + "\n");
        } catch (IOException e) {
            System.out.println("soubor nelze načíst!");
        }
    }
}
