import javax.swing.*;

/**
* Esta classe representa a mesa do jogo e � respons�vel por avaliar a m�o do jogador,
* identificar combina��es e distribuir o pr�mio da rodada
* @author Victor e Paulo
*/
public class Table {
        /**
        * Avaliador da m�o do jogador.
        * @param hand String com a m�o do jogador.
        * @param bet Valor da aposta.
        * @return Resultado da aposta (valor da m�o) e string com a avalia��o da m�o.
        */
        public int hasHand(String[] hand, int bet, JPanel isGUI, JLabel gameResult) {
                
                int[][] card = new int[15][4];   
                
                boolean pair = false;
                boolean trinc = false;
                boolean flush = false;
                int straight = 0; // 0 = Not Initialized; 1 = Initialized; 2 = NOT A SEQUENCE

                // SEND CARDS TO MATRIX
                for (int i = 0; i < 5; i++) {

                        String[] _str = hand[i].split(" ");
                        
                        int cardNumber = Integer.parseInt(_str[0]);
                        int cardSuit = 0;
                        
                        if (_str[1].equals("clubs")) cardSuit = 0;
                        else if (_str[1].equals("diamonds")) cardSuit = 1;
                        else if (_str[1].equals("hearts")) cardSuit = 2;
                        else if (_str[1].equals("spades")) cardSuit = 3;

                        card[cardNumber][cardSuit]++;
                }
                
                int cardCount = 0;
                
                // ITERATE NUMBERS
                for (int i = 2; i < 15; i++){
                        
                        if (cardCount == 5) break;
                        int numAmount = 0;

                        // ITERATE SUITS
                        for (int j = 0; j < 4; j++) {

                                if (card[i][j] == 1) {

                                        numAmount++;
                                        cardCount++;
                                }
                        }

                        if (numAmount == 2) { 
                                if (pair) return result("DOIS PARES", bet, isGUI, gameResult);                    // TWO PAIR
                                if (trinc) return result("FULL HOUSE", bet * 20, isGUI, gameResult);          // FULL HOUSE
                                pair = true;
                        }
                        else if (numAmount == 3) {
                                if (pair) return result("FULL HOUSE", bet * 20, isGUI, gameResult);                // FULL HOUSE
                                trinc = true;
                        }
                        else if (numAmount == 4) return result("QUADRA", bet * 50, isGUI, gameResult);            // QUADRA

                        if (numAmount == 1 && straight != 2) straight = 1;
                        else if (numAmount > 1) straight = 2;

                        if (numAmount == 0 && cardCount < 5 && straight == 1) straight = 2;
                }

                if (pair) return result("DERROTA", -bet, isGUI, gameResult);
                if (trinc) return result("TRINCA", bet * 2, isGUI, gameResult);									// TRINCA
                cardCount = 0;
                
                // ITERATE SUITS
                for(int i = 0; i < 4; i++){

                        if (cardCount == 5) break;
                        int numAmount = 0;

                        // ITERATE NUMBERS
                        for (int j = 2; j < 15; j++){
                                
                                if (cardCount == 5) break;
                                if (card[j][i] == 1) {
                                        numAmount++;
                                        cardCount++;
                                }               
                        }
                        if(numAmount == 5){
                                flush = true;
                                break;
                        }
                }

                if (flush) {
                        if (straight == 1) {
                                
                                for (int i = 0; i < 5; i++) {
                                        String[] _str = hand[i].split(" ");
                                        if(Integer.parseInt(_str[0]) == 10) return result("ROYAL STRAIGHT FLUSH", bet * 200, isGUI, gameResult); // STRAIGHT FLUSH   
                                }
                                return result("STRAIGHT FLUSH", bet * 100, isGUI, gameResult);             
                        }
                        else return result("FLUSH", bet * 10, isGUI, gameResult);
                }
                else if (straight == 1) return result("STRAIGHT", bet * 5, isGUI, gameResult);
                
                return result("DERROTA", -bet, isGUI, gameResult);
        }
        /**
        * Imprime o resultado de forma did�tica.
        * @param str M�o do jogador avaliada
        * @param value Valor da aposta.
        * @return Resultado da m�o de forma did�tica.
        */
        public int result(String str, int value, JPanel isGUI, JLabel gameResult) {

        		
        		if (isGUI == null) System.out.print("\nVoc� conseguiu um(a): " + str + " [" + value + "]\n");
        		else {
        				gameResult.setVisible(true);
        				
        				if (str.equals("DERROTA")) gameResult.setIcon(new ImageIcon(GUI.class.getResource("/graphics/hand_perdeu.gif")));
        				else if (str.equals("DOIS PARES")) gameResult.setIcon(new ImageIcon(GUI.class.getResource("/graphics/hand_doisPares.gif")));
        				else if (str.equals("TRINCA")) gameResult.setIcon(new ImageIcon(GUI.class.getResource("/graphics/hand_trinca.gif")));
        				else if (str.equals("QUADRA")) gameResult.setIcon(new ImageIcon(GUI.class.getResource("/graphics/hand_quadra.gif")));
        				else if (str.equals("FULL HOUSE")) gameResult.setIcon(new ImageIcon(GUI.class.getResource("/graphics/hand_fullHouse.gif")));
        				else if (str.equals("FLUSH")) gameResult.setIcon(new ImageIcon(GUI.class.getResource("/graphics/hand_flush.gif")));
        				else if (str.equals("STRAIGHT")) gameResult.setIcon(new ImageIcon(GUI.class.getResource("/graphics/hand_straight.gif")));
        				else if (str.equals("STRAIGHT FLUSH")) gameResult.setIcon(new ImageIcon(GUI.class.getResource("/graphics/hand_straightFlush.gif")));
        				else if (str.equals("ROYAL STRAIGHT FLUSH")) gameResult.setIcon(new ImageIcon(GUI.class.getResource("/graphics/hand_royalStraightFlush.gif")));

        				JOptionPane.showMessageDialog(isGUI, "\nVoc� conseguiu um(a): " + str + " [" + value + "]\n");
        				gameResult.setVisible(false);
        		}

                return value;
        }
}