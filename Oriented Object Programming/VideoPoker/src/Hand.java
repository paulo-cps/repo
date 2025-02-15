/**
 * Esta classe contém a mão do jogador e é responsável por imprimir as cartas da mão
 * atualmente.
 * @author Victor e Paulo
 */

public class Hand {

        private Deck d = new Deck();
        private String[] cards = {"", "", "", "", ""};
        /**
         * Pega uma carta randômica do deck do baralho
         * @param i Posição da carta na mão.
         * @return Retorna se a mão teve ou não sucesso
         */
        public boolean pickCard(int i) {
                
                if (i < 0 || i >= cards.length)
                        return false;
                // if (cards[i] == null) dados[i] = new String();

                cards[i] = d.pick();
                return true;
        }
        /**
         * Retorna a string com a mão do jogador.
         * @return String com a mão do jogador.
         */
        public String[] getCards() {
                return cards;
        }
        /**
         * Converte a carta para uma forma esteticamente agradável.
         * @param i Posição para impressão.
         * @param j Posição para impressão.
         * @return Retorna a parte da carta na Posição dada.
         */
        public String toString (int i, int j) {

                if (i == 0) return "/-----------\\";
                else if (i == 1) return "|" + convNum(cards[j]) + "         |";
                else if (i == 2) return "|           |";
                else if (i == 3) return "|           |";
                else if (i == 4) return "|     " + convSuit(cards[j]) + "     |";
                else if (i == 5) return "|           |";
                else if (i == 6) return "|           |";
                else if (i == 7) return "|        " + convNum(cards[j]) + " |";
                else if (i == 8) return "\\-----------/";

                return "";
        }
        /**
         * Decodifica números em cartas especiais.
         * @param j Carta a ser decodificada.
         * @return Resultado convertido da carta pedida.
         */
        
        public String getCardNum(int i) {
        		String[] str = cards[i].split(" ");
        		return str[0];
        }
        
        public String getCardSuit(int i) {
    		String[] str = cards[i].split(" ");
    		return str[1];
        }
        
        public String convNum (String card) {

            	String[] _str = card.split(" ");
            	int n = Integer.parseInt(_str[0]);
            	
                if (n == 10) return "10";
                else if (n == 11) return " J";
                else if (n == 12) return " Q";
                else if (n == 13) return " K";
                else if (n == 14) return " A";
                
                return " " + n + "";
        }
        /**
         * Decodifica números em naipes da carta.
         * @param i Carta a ser decodificada.
         * @return Resultado convertido da carta pedida.
         */
        public String convSuit (String card) {

        		String[] _str = card.split(" ");
        		String str = _str[1];
        		
                if (str.equals("clubs")) return "\u2660";
                else if (str.equals("diamonds")) return "\u2663";
                else if (str.equals("hearts")) return "\u2665";
                else if (str.equals("spades")) return "\u2666";

                return "";
        }
}