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
        window.setSize(450, 400); //largura e altura
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
        JCheckBox checkBoxSystem1 = new JCheckBox("a-z");
        JCheckBox checkBoxSystem2 = new JCheckBox("A-Z");
        JCheckBox checkBoxSystem3 = new JCheckBox("0-9");
        JCheckBox checkBoxSystem4 = new JCheckBox("!@#$%&*?");
        subframeCheckBoxGenerateSystem.add(checkBoxSystem1);
        subframeCheckBoxGenerateSystem.add(checkBoxSystem2);
        subframeCheckBoxGenerateSystem.add(checkBoxSystem3);
        subframeCheckBoxGenerateSystem.add(checkBoxSystem4);

        JPanel subframeInputSizeLimitString = new JPanel();
        JLabel labelInputSizeLimitString = new JLabel("Digite o tamanho maximo da String: ");
        // colocar alerta para caso o numero seja maior que o permitido [REALIZADO]
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
        //framePersonalizedGenerator.setLayout(new BorderLayout());
        framePersonalizedGenerator.setLayout(new GridLayout(4, 1));
        framePersonalizedGenerator.setBorder(BorderFactory.createTitledBorder("Personalized Generator"));

        JPanel subframeInputKeysWords = new JPanel(); // Frame para inserir as palavras chave da wordlist
        JLabel labelInputKeysWords = new JLabel("Digite as palavras chave (dividas por espaco): ");
        JTextField textFieldInputKeysWords = new JTextField(50); // Gambiarra!!! sujeito a buffer Overflow
        subframeInputKeysWords.add(labelInputKeysWords);
        subframeInputKeysWords.add(textFieldInputKeysWords);

        JPanel subframeInputSizeKeysWords = new JPanel(); // Frame para inserir as palavras chave da wordlist
        JLabel labelInputSizeKeysWords = new JLabel("Digite o tamanho maximo de cada String (key=1): ");
        JTextField textFieldInputSizeKeysWords = new JTextField(2); // Gambiarra!!! sujeito a buffer Overflow
        subframeInputSizeKeysWords.add(labelInputSizeKeysWords);
        subframeInputSizeKeysWords.add(textFieldInputSizeKeysWords);

        JPanel subframeButtonGeneratePersonalized = new JPanel();
        JButton buttonGeneratePersonalized = new JButton("Generate Wordlist");
        subframeButtonGeneratePersonalized.add(buttonGeneratePersonalized);
        
        framePersonalizedGenerator.add(subframeInputKeysWords);
        framePersonalizedGenerator.add(subframeInputSizeKeysWords);
        framePersonalizedGenerator.add(subframeButtonGeneratePersonalized);
        window.add(framePersonalizedGenerator);
        

        //SUBFRAME GERADOR SMARTED 
        JPanel frameSmartedGenerator = new JPanel();
        frameSmartedGenerator.setLayout(new BorderLayout());
        frameSmartedGenerator.setBorder(BorderFactory.createTitledBorder("Smarted Generator"));
        frameSmartedGenerator.setLayout(new GridLayout(2, 1));

        JPanel subframeCheckBoxGenerateSmarted = new JPanel();
        JCheckBox checkBoxSmarted1 = new JCheckBox("GitHub DataBase");
        JCheckBox checkBoxSmarted2 = new JCheckBox("IHaveBeenPwaned");
        JCheckBox checkBoxSmarted3 = new JCheckBox("Listas Online");
        JCheckBox checkBoxSmarted4 = new JCheckBox("Outros");
        subframeCheckBoxGenerateSmarted.add(checkBoxSmarted1);
        subframeCheckBoxGenerateSmarted.add(checkBoxSmarted2);
        subframeCheckBoxGenerateSmarted.add(checkBoxSmarted3);
        subframeCheckBoxGenerateSmarted.add(checkBoxSmarted4);

        JPanel subframeButtonGenerateSmarted = new JPanel();
        JButton buttonGenerateSmarted = new JButton("Generate Wordlist");
        subframeButtonGenerateSmarted.add(buttonGenerateSmarted);

        frameSmartedGenerator.add(subframeCheckBoxGenerateSmarted);
        frameSmartedGenerator.add(subframeButtonGenerateSmarted);
        window.add(frameSmartedGenerator);


        //ADICIONA AO PRINCIPAL
        generatorPanel.add(frameSystemGenerator);
        generatorPanel.add(framePersonalizedGenerator);
        generatorPanel.add(frameSmartedGenerator);

        //ADICIONA O PAINEL AO TOPO DA JANELA
        window.add(generatorPanel, BorderLayout.NORTH);
        
        // EXIBIR A WORDLIST GERADA
        // Fixar um tamanho para a area de texto
        JTextArea wordlistArea = new JTextArea();
        wordlistArea.setEditable(false);

        Dimension fixedSize = new Dimension(450, 400);
        wordlistArea.setMinimumSize(fixedSize);

        JScrollPane wordlistScrollPane = new JScrollPane(wordlistArea);
        wordlistScrollPane.setBorder(BorderFactory.createTitledBorder("Wordlist Gerada"));
        window.add(wordlistScrollPane, BorderLayout.CENTER);

        // OPTIONS
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
                    if (checkBoxSystem1.isSelected()) selectedOptions.add("wordsLowerCase");
                    if (checkBoxSystem2.isSelected()) selectedOptions.add("wordsUperCase");
                    if (checkBoxSystem3.isSelected()) selectedOptions.add("numbers");
                    if (checkBoxSystem4.isSelected()) selectedOptions.add("specialCharacters");

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

        buttonGeneratePersonalized.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    wordlistArea.setText(""); // Limpa a area

                    String textFieldInputKeysWordsConvert = textFieldInputKeysWords.getText();
                    if (textFieldInputKeysWordsConvert.length() <= 0) {
                        JOptionPane.showMessageDialog(window, "O campo de texto nao pode ficar em branco!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                                        
                    int maxSizeStringPersonalized = Integer.parseInt(textFieldInputSizeKeysWords.getText());
                    String verifyTextPersonalizedIsNull = textFieldInputSizeKeysWords.getText(); // Gambiarra!!! Deve ter outra forma de verificar se e null
                    if (verifyTextPersonalizedIsNull.length() <= 0) {
                        JOptionPane.showMessageDialog(window, "O campo de texto nao pode ficar em branco!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        if (maxSizeStringPersonalized <= 0) {
                            JOptionPane.showMessageDialog(window, "O numero deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    SwingWorker<Void, String> worker = new SwingWorker<>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            EngineTwo functions = new EngineTwo();
                            List<String> wordlist = functions.wordlistPersonalized(maxSizeStringPersonalized, textFieldInputKeysWordsConvert);

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

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(window, "Error ao gerar Wordlist: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });



    }
}