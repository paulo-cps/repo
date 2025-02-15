/**
* Esta classe representa o baralho do jogo, responsável por gerar as cartas aleatóriamente,
* tirando-as do baralho até o fim da rodada, depois colocando-as de volta
* @author Paulo e Victor
*/
public class Deck {

        private Random rand = new Random();

        private int[][] inDeck = new int[52][4];
        private String[] number = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14" };
        private String[] suits = { "clubs", "diamonds", "hearts", "spades" };

        /**
        * Gera uma carta
        * @return String que representa a carta no formato "Numero Naipe"
        */
        public String pick() {
                
                int _number = rand.getIntRand(12);
                int _suit = rand.getIntRand(3);
                String newCard = number[_number] + " " + suits[_suit];

                while (inDeck[_number][_suit] != 0) {
                        _number = rand.getIntRand(12);
                        _suit = rand.getIntRand(3);
                        newCard = number[_number] + " " + suits[_suit];
                }

                inDeck[_number][_suit] = 1;
                return newCard;
        }

        /**
        * Reinicia o baralho
        */
        public void restartDeck() {

                inDeck = new int[52][4];
        }
}