package wordlistEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class InterfaceWordlist {
    public static void main(String[] args) {
        
        // FRAME PRINCIPAL
        JFrame window = new JFrame("Gerador de Wordlists");
        window.setSize(450, 600);
        window.setMinimumSize(new Dimension(450, 600));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setVisible(true);

        // PAINEL DOS GERADORES
        JPanel generatorPanel = new JPanel();
        generatorPanel.setLayout(new GridLayout(3, 1)); // 3 linhas 1 coluna

        // GERADOR DO SISTEMA
        JPanel frameSystemGenerator = new JPanel();
        frameSystemGenerator.setLayout(new GridLayout(3, 1));
        frameSystemGenerator.setBorder(BorderFactory.createTitledBorder("System Generator"));

        JPanel subframeCheckBoxGenerateSystem = new JPanel();
        JCheckBox checkBox1 = new JCheckBox("a-z");
        JCheckBox checkBox2 = new JCheckBox("A-Z");
        JCheckBox checkBox3 = new JCheckBox("0-9");
        JCheckBox checkBox4 = new JCheckBox("!@#$%&*?");
        subframeCheckBoxGenerateSystem.add(checkBox1);
        subframeCheckBoxGenerateSystem.add(checkBox2);
        subframeCheckBoxGenerateSystem.add(checkBox3);
        subframeCheckBoxGenerateSystem.add(checkBox4);

        JPanel subframeInputSizeLimitString = new JPanel();
        JLabel labelInputSizeLimitString = new JLabel("Digite o tamanho maximo da String: ");
        // colocar alerta para caso o numero seja maior que o permitido
        JTextField textInputSizeLimitString = new JTextField(2); // tamanho da string
        subframeInputSizeLimitString.add(labelInputSizeLimitString);
        subframeInputSizeLimitString.add(textInputSizeLimitString);
        
        JPanel subframeButtonGenerateSystem = new JPanel();
        JButton buttonGenerateSystem = new JButton("Generate Wordlist");
        subframeButtonGenerateSystem.add(buttonGenerateSystem);

        frameSystemGenerator.add(subframeCheckBoxGenerateSystem);
        frameSystemGenerator.add(subframeInputSizeLimitString);
        frameSystemGenerator.add(subframeButtonGenerateSystem);
        window.add(frameSystemGenerator);

        //SUBFRAME GERADOR PERSONALIZADO
        JPanel framePersonalizedGenerator = new JPanel();
        framePersonalizedGenerator.setLayout(new BorderLayout());
        framePersonalizedGenerator.setBorder(BorderFactory.createTitledBorder("Personalized Generator"));

        JLabel labelPersonalizedGenerator = new JLabel("Espaco reservado para geracao personalizada");
        framePersonalizedGenerator.add(labelPersonalizedGenerator, BorderLayout.CENTER);
        
        //SUBFRAME GERADOR SMARTED 
        JPanel frameSmartedGenerator = new JPanel();
        frameSmartedGenerator.setLayout(new BorderLayout());
        frameSmartedGenerator.setBorder(BorderFactory.createTitledBorder("Personalized Generator"));

        JLabel labelSmartedGenerator = new JLabel("Espaco reservado para geracao API");
        frameSmartedGenerator.add(labelSmartedGenerator, BorderLayout.CENTER);

        //ADICIONA AO PRINCIPAL
        generatorPanel.add(frameSystemGenerator);
        generatorPanel.add(framePersonalizedGenerator);
        generatorPanel.add(frameSmartedGenerator);

        //ADICIONA O PAINEL AO TOPO DA JANELA
        window.add(generatorPanel, BorderLayout.NORTH);

        // EXIBIR A WORDLIST GERADA
        JTextArea wordlistArea = new JTextArea();
        wordlistArea.setEditable(false);
        JScrollPane wordlistScrollPane = new JScrollPane(wordlistArea);
        wordlistScrollPane.setBorder(BorderFactory.createTitledBorder("Wordlist Gerada"));
        window.add(wordlistScrollPane, BorderLayout.CENTER);

        // options
        // wordsLowerCase
        // wordsUperCase
        // numbers
        // specialCharacters
        buttonGenerateSystem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    wordlistArea.setText(""); // Limpa a area

                    List<String> selectedOptions = new ArrayList<>();
                    if (checkBox1.isSelected()) selectedOptions.add("wordsLowerCase");
                    if (checkBox2.isSelected()) selectedOptions.add("wordsUperCase");
                    if (checkBox3.isSelected()) selectedOptions.add("numbers");
                    if (checkBox4.isSelected()) selectedOptions.add("specialCharacters");

                    String[] optionsArrayConvert = selectedOptions.toArray(new String[0]);

                    int maxSizeStringSystem = Integer.parseInt(textInputSizeLimitString.getText());
                    if (maxSizeStringSystem <= 0) {
                        JOptionPane.showMessageDialog(window, "O numero deve ser maior que 0!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // INICIO
                    SwingWorker<Void, String> worker = new SwingWorker<>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            EngineOne functions = new EngineOne();
                            List<String> wordlist = functions.wordlistSystem(maxSizeStringSystem, optionsArrayConvert);

                            for (String word : wordlist) {
                                publish(word);
                                Thread.sleep(50);
                            }
                            return null;
                        }

                        @Override
                        protected void process(List<String> chuncks) {
                            for (String word : chuncks) {
                                wordlistArea.append(word + "\n");
                            }
                        }

                        @Override
                        protected void done(){
                            JOptionPane.showMessageDialog(window, "Concluido", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        }
                    };
                    worker.execute();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(window, "Numero Invalido!", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(window, "Error ao gerar Wordlist: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}