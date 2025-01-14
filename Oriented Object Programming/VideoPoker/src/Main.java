/**
 * Classe main coordena a ação de outras classes
 * @author Victor e Paulo
 */

public class Main {

        public static void main(String[] args) throws Exception {

                Deck d = new Deck();
                Hand hand = new Hand();
                Table table = new Table();

                int credits = 200;
                while (credits > 0) {
                                                
                        System.out.print("Pressione [ENTER] para iniciar a rodada ");
                                                
                        if (EntradaTeclado.leString().length() > 0) continue;

                        System.out.print("Você tem " + credits + " créditos. Quanto deseja apostar? Digite 0 para sair. ");
                        int bet;
                        try{
                            bet = EntradaTeclado.leInt();
                            if (bet == 0) System.exit(0);
                        }
                        catch (NumberFormatException e){
                            continue;
                        }

                        // CHECA ENTRADA DA APOSTA
                        while (bet < 0 || bet > credits) {
                                System.out.print("Aposta inválida. Digite outro valor: ");
                                bet = EntradaTeclado.leInt();
                                continue;
                        }

                        // PEGA CARTAS DO BARALHO
                        for (int i = 0; i < 5; i++) {      
                                hand.pickCard(i);
                        }

                        DrawCards(hand);
                        
                        // Quantidade de vezes que se pode trocar
                        int m = 2;
                        while (m > 0) {
                                
                                System.out.print("Deseja trocar alguma carta? [S/N] ");

                                String str = EntradaTeclado.leString();

                                if (!str.equals("S") && !str.equals("s") && !str.equals("N") && !str.equals("n")) {
                                        System.out.print("Digite [S] para sim, ou [N] para não\n");
                                        continue;
                                }

                                if (str.equals("S") || str.equals("s")) {

                                        System.out.print("Quais cartas deseja trocar? ");

                                        str = EntradaTeclado.leString();
                                        String[] _str = str.split(" ");
                                        
                                        for (int i = 0; i < _str.length; i++) {
                                            try{
                                                if (Integer.parseInt(_str[i]) < 0 || Integer.parseInt(_str[i]) > 4) {
                                                        System.out.print("Posição fora do alcance, digite valores de 0 a 4. ");
                                                        str = EntradaTeclado.leString();
                                                        _str = str.split(" ");
                                                        i = -1;      
                                                }
                                            }
                                            catch (NumberFormatException e){
                                                System.out.print("Apenas valores numéricos são aceitos, digite valores de 0 a 4. ");
                                                str = EntradaTeclado.leString();
                                                _str = str.split(" ");
                                                i = -1;   
                                                continue;
                                            }
                                        }

                                        for (int i = 0; i < _str.length; i++) {
                                                
                                                hand.pickCard(Integer.parseInt(_str[i]));
                                        }
                                        
                                        DrawCards(hand);
                                }
                                else break;

                                m--;
                        }
                        
                        // CHECA COMBINAÃ‡Ã•ES POSSÃ�VEIS
                        credits += table.hasHand(hand.getCards(), bet, null, null);
                        System.out.print("[CREDITOS]: " + credits + "\n\n");
                        d.restartDeck();
                }

                System.out.print("Perdeu Playboy");
        }
        /**
         * Imprime as cartas para o usuário.
         * @param hand Mão do garotão que está jogando.
         */
        public static void DrawCards(Hand hand) {

                System.out.print("\n[CARTAS]:\n\n");

                // LINE'S INDEX
                for (int i = 0; i < 9; i++) {

                        // CARD'S INDEX
                        for (int j = 0; j < 5; j++)
                                System.out.print("     " + hand.toString(i, j));
                        
                        System.out.print("\n");
                }

                // SLOT'S INDEX
                for (int i = 0; i < 5; i++) 
                        System.out.print("          [" + i + "]" + "     ");
                System.out.print("\n\n");
        }
}