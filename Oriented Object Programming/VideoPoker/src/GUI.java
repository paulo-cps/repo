import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Font;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Video Poker");
		frame.setResizable(false);
		
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// MAIN MENU
		JPanel menu = new JPanel();
		menu.setForeground(Color.BLACK);
		menu.setBounds(0, 0, 794, 371);
		frame.getContentPane().add(menu);
		menu.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(GUI.class.getResource("/graphics/logo.gif")));
		logo.setBounds(262, 57, 317, 124);
		menu.add(logo);
		
		JButton btnPLAY = new JButton("PLAY");
		btnPLAY.setBounds(363, 223, 80, 35);
		menu.add(btnPLAY);
		
		JButton btnEXIT = new JButton("EXIT");
		btnEXIT.setBounds(363, 269, 80, 35);
		menu.add(btnEXIT);
		
		JLabel bg_menu = new JLabel("");
		bg_menu.setIcon(new ImageIcon(GUI.class.getResource("/graphics/bg.png")));
		bg_menu.setBounds(0, 0, 794, 371);
		menu.add(bg_menu);
		
		menu.setVisible(true);
		//////////////////////////////////

		// GAME
		JPanel game = new JPanel();
		game.setForeground(Color.BLACK);
		game.setBounds(0, 0, 794, 371);
		frame.getContentPane().add(game);
		game.setLayout(null);
		
		JButton btnEXCHANGE = new JButton("EXCHANGE");
		btnEXCHANGE.setFont(btnEXCHANGE.getFont().deriveFont(11f));
		btnEXCHANGE.setVisible(false);
		btnEXCHANGE.setBounds(350, 284, 121, 23);
		game.add(btnEXCHANGE);
		
		JButton btnEND = new JButton("END ROUND");
		btnEND.setFont(btnEND.getFont().deriveFont(11f));
		btnEND.setVisible(false);
		btnEND.setBounds(350, 318, 121, 23);
		game.add(btnEND);
						
		JLabel[] cards = new JLabel[5];
		JCheckBox[] cb_Card = new JCheckBox[5];

		for (int i = 0; i < 5; i++) {
			cards[i] = new JLabel();
			cb_Card[i] = new JCheckBox();

			cb_Card[i].setVisible(false);

			cards[i].setIcon(new ImageIcon(GUI.class.getResource("/graphics/Gray_back.png")));
			cards[i].setBounds(106 + 130 * i, 128, 82, 119);
			cb_Card[i].setBounds(138 + 130 * i, 254, 20, 20);

			game.add(cards[i]);
			game.add(cb_Card[i]);

		}
		
		JLabel tokensValue = new JLabel("200");
		tokensValue.setFont(tokensValue.getFont().deriveFont(tokensValue.getFont().getStyle() | Font.BOLD, 11f));
		tokensValue.setForeground(Color.WHITE);
		tokensValue.setHorizontalAlignment(SwingConstants.CENTER);
		tokensValue.setBounds(25, 80, 46, 14);
		game.add(tokensValue);
		
		JLabel tokensIcon = new JLabel("");
		tokensIcon.setHorizontalAlignment(SwingConstants.CENTER);
		tokensIcon.setIcon(new ImageIcon(GUI.class.getResource("/graphics/tokens.png")));
		tokensIcon.setBounds(25, 25, 50, 50);
		game.add(tokensIcon);
		
		JLabel gameResult = new JLabel("");
		gameResult.setHorizontalAlignment(SwingConstants.CENTER);
		gameResult.setIcon(new ImageIcon(GUI.class.getResource("/graphics/hand_perdeu.gif")));
		gameResult.setBounds(0, 20, 790, 76);
		game.add(gameResult);
		
		JLabel bg_game = new JLabel("");
		bg_game.setBounds(0, 0, 794, 371);
		game.add(bg_game);
		bg_game.setIcon(new ImageIcon(GUI.class.getResource("/graphics/backgroundBLUR.png")));
		
		gameResult.setVisible(false);
		game.setVisible(false);
		//////////////////////////////////

        final int[] m = {2};
        final int[] credit = {200};
        final int[] bet = {0};
        
		Deck d = new Deck();
        Hand hand = new Hand();
        Table table = new Table();
        
        // BUTTON EXIT
		btnEXIT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		// BUTTON PLAY
		btnPLAY.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
			        credit[0] = 200;
					tokensValue.setText(Integer.toString(credit[0]));

	                inGame(menu, game, btnEXCHANGE, btnEND, credit, bet, hand, cb_Card, cards, m);			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
		
    	// BUTTON EXCHANGE
		btnEXCHANGE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < 5; i++)
					if (cb_Card[i].isSelected()) {
						hand.pickCard(i);
		                cards[i].setIcon(new ImageIcon(GUI.class.getResource("/graphics/" + hand.getCardSuit(i) + "_" + hand.getCardNum(i) + ".png")));
					}
				
        		for (int i = 0; i < 5; i++)
        			cb_Card[i].setSelected(false);
        		
				m[0]--;
				
				if (m[0] == 0) {
					
	        		for (int i = 0; i < 5; i++)
	        			cb_Card[i].setVisible(false);
	        		
					btnEXCHANGE.setVisible(false);
				}
			}
		});
		
        // BUTTON END
		btnEND.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				credit[0] += table.hasHand(hand.getCards(), bet[0], game, gameResult);
				tokensValue.setText(Integer.toString(credit[0]));

				d.restartDeck();
                
        		for (int i = 0; i < 5; i++) {
        			cb_Card[i].setSelected(false);
        			cb_Card[i].setVisible(false);

        			cards[i].setIcon(new ImageIcon(GUI.class.getResource("/graphics/Gray_back.png")));
        		}
        		
				btnEXCHANGE.setVisible(false);
        		btnEND.setVisible(false);
        		
                int keepGame = JOptionPane.showConfirmDialog(game, "Start new match?", "Keep", 0);
                
                if (keepGame == 1 || credit[0] <= 0) {
        			menu.setVisible(true);
        			game.setVisible(false);
    				gameResult.setVisible(false);
        			return;
                }
                
                inGame(menu, game, btnEXCHANGE, btnEND, credit, bet, hand, cb_Card, cards, m);			
			}
		});
	} 
	
	public void inGame(JPanel menu, JPanel game, JButton btnEXCHANGE, JButton btnEND, final int[] credit, final int[] bet, Hand hand, JCheckBox[] cb_Card, JLabel[] cards, final int[] m) {
		
		menu.setVisible(false);
		game.setVisible(true);
		
		bet[0] = -1;
		while (bet[0] > credit[0] || bet[0] < 0) {
			
			try {
				bet[0] = Integer.parseInt(JOptionPane.showInputDialog(game, "Place your bet"));
			}
			catch(Exception e) {
	            JOptionPane.showMessageDialog(game, "Valor incorreto");
	            bet[0] = Integer.parseInt(JOptionPane.showInputDialog(game, "Place your bet"));
			}
			
			if (bet[0] > credit[0] || bet[0] < 0) {
				JOptionPane.showMessageDialog(game, "Valor incorreto");
	            bet[0] = Integer.parseInt(JOptionPane.showInputDialog(game, "Place your bet"));
			}
        }
        
        btnEXCHANGE.setVisible(true);
        btnEND.setVisible(true);
        
        // PICK CARDS OF DECK
        for (int i = 0; i < 5; i++) {      
                hand.pickCard(i);
                cb_Card[i].setVisible(true);
                cards[i].setIcon(new ImageIcon(GUI.class.getResource("/graphics/" + hand.getCardSuit(i) + "_" + hand.getCardNum(i) + ".png")));
        }

		m[0] = 2;
	}
}
